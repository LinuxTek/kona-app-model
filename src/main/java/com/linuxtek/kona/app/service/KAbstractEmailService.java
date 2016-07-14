/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailResult;
import com.amazonaws.services.sqs.model.Message;
import com.linuxtek.kona.app.entity.KEmail;
import com.linuxtek.kona.app.entity.KEmailAddress;
import com.linuxtek.kona.app.entity.KEmailEvent;
import com.linuxtek.kona.app.entity.KEmailEventType;
import com.linuxtek.kona.app.entity.KEmailFooter;
import com.linuxtek.kona.app.entity.KEmailGroup;
import com.linuxtek.kona.app.entity.KEmailGroupAddress;
import com.linuxtek.kona.app.entity.KEmailStats;
import com.linuxtek.kona.app.util.KUtil;
import com.linuxtek.kona.data.dao.KMyBatisDao;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.locale.KValidator;
import com.linuxtek.kona.mailer.KMailer;
import com.linuxtek.kona.mailer.KMailerException;
import com.linuxtek.kona.templates.KTemplate;
import com.linuxtek.kona.templates.KTemplateException;
import com.linuxtek.kona.util.KDateUtil;
import com.linuxtek.kona.util.KStringUtil;

public abstract class KAbstractEmailService<EMAIL extends KEmail,
                                   EMAIL_EXAMPLE,
                                   EMAIL_EVENT extends KEmailEvent,
                                   EMAIL_EVENT_EXAMPLE,
                                   EMAIL_GROUP extends KEmailGroup,
                                   EMAIL_ADDRESS extends KEmailAddress,
                                   EMAIL_GROUP_ADDRESS extends KEmailGroupAddress>
        extends KAbstractService<EMAIL, EMAIL_EXAMPLE>
        implements KEmailService<EMAIL,EMAIL_EVENT> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractEmailService.class);

	private Queue<MimeMessage> localMailQueue = new LinkedList<MimeMessage>();
	
	// ----------------------------------------------------------------------------
	
	protected abstract <D extends KMyBatisDao<EMAIL_EVENT,EMAIL_EVENT_EXAMPLE>> D getEmailEventDao();
	
	protected abstract AmazonSimpleEmailServiceClient getAmazonSESClient();
	
	protected abstract String getAmazonSESBounceQueueName();
	
	protected abstract String getAmazonSESComplaintQueueName();
	
	protected abstract String getAmazonSESDeliveryQueueName();
	
	protected abstract EMAIL getNewEmailObject();
	
	protected abstract EMAIL_EVENT getNewEmailEventObject();
	
	protected abstract EMAIL_ADDRESS getNewEmailAddressObject();
	
	protected abstract String getSystemMailhost();
	
	protected abstract String getSystemSenderEmailAddress();
	
	protected abstract String getSystemFromEmailAddress();
	
	protected abstract String getSystemBaseUrl();
	
	protected abstract String getEmailEventUrl();
	
	protected abstract String getEmailFooterHtmlSelector();
	
	protected abstract String getEmailTestDomain();
	
	protected abstract String getEmailTextFooterTemplatePath();
	
	protected abstract String getEmailHtmlFooterTemplatePath();
	
	protected abstract <S extends KEmailAddressService<EMAIL_ADDRESS>> S getEmailAddressService();
	
	protected abstract <S extends KEmailGroupService<EMAIL_GROUP,EMAIL_ADDRESS,EMAIL_GROUP_ADDRESS>> S getEmailGroupService();
	
	protected abstract <S extends KEmailGroupAddressService<EMAIL_GROUP_ADDRESS>> S getEmailGroupAddressService();
	
	protected abstract <S extends KQueueService> S getQueueService();
	
	// ----------------------------------------------------------------------------

	@Override
	public void validate(EMAIL email) {
		if (email.getCreatedDate() == null) {
			email.setCreatedDate(new Date());
		}
		
		email.setLastUpdated(new Date());

		if (email.getUid() == null) {
			email.setUid(uuid());
		}
		
		if (email.getOpenCount() == null) {
			email.setOpenCount(0);
		}

		if (email.getPrintCount() == null) {
			email.setPrintCount(0);
		}

		if (email.getForwardCount() == null) {
			email.setForwardCount(0);
		}

		if (email.getClickCount() == null) {
			email.setClickCount(0);
		}

	}
	
	// ----------------------------------------------------------------------

	@Override
	public void processLocalQueue() {
		Iterator<MimeMessage> it = localMailQueue.iterator();
		while (it.hasNext()) {
			MimeMessage message = it.next();
			try {
				KMailer.send(message);
				it.remove();
			} catch (KMailerException e) {
				logger.warn("processQueue: unable to send message: " + message);
			}
		}
	}
	
	// ----------------------------------------------------------------------

	@Override
	public void send(String body, String subject, String from, String replyTo, 
			String to, String cc, String bcc, boolean html) throws KEmailException {

		String mailhost = getSystemMailhost();
		String sender = getSystemSenderEmailAddress();

		KMailer mailer = new KMailer(mailhost);
		mailer.setSender(sender); // sender's domain must match mailer's authenticated domain
		mailer.setFrom(from); // could be different from sender but will likely be shown as "on behalf of" sender
		mailer.setReplyTo(replyTo);
		mailer.setTo(to);
		mailer.setCc(cc);
		mailer.setBcc(bcc);
		mailer.setSubject(subject);
		
		if (html) {
			mailer.setHtmlBody(body);
		} else {
			mailer.setTextBody(body);
		}
		
		mailer.setHTML(html);

		String email = "\n"
				+ "\nmailhost: " + mailhost
				+ "\nsender: " + sender
				+ "\nsubject: " + subject
				+ "\nfrom: " + from
				+ "\nreplyTo: " + replyTo
				+ "\nto: " + to
				+ "\ncc: " + cc
				+ "\nbcc: " + bcc
				+ "\nhtml: " + html
				+ "\nbody: " + body.substring(1, 100);

		logger.debug("sendEmail: message properties:" + email);


		logger.debug("--- EMAIL MESSAGE START ---\n\n" 
				+ mailer.toString() + "\n\n" 
				+ "--- EMAIL MESSAGE END ---\n\n");

		
		String testDomain = getEmailTestDomain().toLowerCase();
		
		if (to.toLowerCase().endsWith(testDomain)) {
			logger.warn("Email address [{}] ends in test domain [{}]: skipping ...", to, testDomain);
			return;
		}
		
		try {
			mailer.send();
		} catch (KMailerException e) {
			MimeMessage message = e.getMimeMessage();

			// if message is null, we had a problem composing the message so give up
			if (message == null) {
				throw new KEmailException(e);
			}

			// otherwise, we had some issue where the message couldn't be delivered, so queue
			// it and try again.
			
			// FIXME: setup a thread to resend messages in queue. until then throw exception
			//localMailQueue.add(message);
			
			throw new KEmailException(e);
		}
	}

	// ----------------------------------------------------------------------

	@Override
	public String sendAWS(String from, String to, String subject, String textBody, String htmlBody) 
			throws KEmailException {
		return sendAWS(from, null, null, new String[]{to}, null, null, subject, textBody, htmlBody);
	}
	
	// ----------------------------------------------------------------------

	@Override
	public String sendAWS(String from, String[] replyTo, String returnPath, 
			String[] to, String[] cc, String[] bcc, String subject, String textBody, String htmlBody) 
					throws KEmailException {

		KMailer mailer = new KMailer();
		mailer.setFrom(from);
		mailer.setReplyTo(replyTo);
		mailer.setReturnPath(returnPath);
		mailer.setTo(to);
		mailer.setCc(cc);
		mailer.setBcc(bcc);
		mailer.setSubject(subject);
		mailer.setTextBody(textBody);
		mailer.setHtmlBody(htmlBody);

		try {
			Session session = Session.getInstance(new Properties(), null);
			MimeMessage mimeMessage = mailer.getMimeMessage(session);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			mimeMessage.writeTo(outputStream);

			String stringMessage = outputStream.toString();
			logger.debug("rawMessage:\n" + stringMessage);

			RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

			SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);

			// make sure from/to are encoded
			from = KMailer.toEncodedAddress(from);
			List<String> toList = new ArrayList<String>();
			for (String s : to) {
				s = KMailer.toEncodedAddress(s);
				toList.add(s);
			}

			//rawEmailRequest.setDestinations(Arrays.asList(to));
			rawEmailRequest.setDestinations(toList);
			rawEmailRequest.setSource(from);
			SendRawEmailResult result = getAmazonSESClient().sendRawEmail(rawEmailRequest);
			return result.getMessageId();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// ----------------------------------------------------------------------
	
	@Override
	public EMAIL_EVENT addEvent(EMAIL_EVENT event) {
		getEmailEventDao().insert(event);
		return event;
	}

	// ----------------------------------------------------------------------

	@Override
	public EMAIL fetchBySesId(String sesId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("sesId", sesId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------

	@Override
	public EMAIL fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------
	
	@Override
	public List<EMAIL> fetchByCampaignIdAndChannelId(Long campaignId, Long campaignChannelId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
		filter.put("campaignChannelId", campaignChannelId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------

	@Override
	public EMAIL fetchByCampaignIdAndChannelIdAndToId(Long campaignId, Long campaignChannelId, Long toAddressId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
		filter.put("campaignChannelId", campaignChannelId);
		filter.put("toAddressId", toAddressId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------
	
	@Override
	public List<EMAIL> fetchByGroupId(Long groupId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------
	
	@Override
	public List<EMAIL> fetchByCampaignId(Long campaignId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------

	@Override
	public List<EMAIL> fetchByGroupName(String groupName) {
		EMAIL_GROUP group = getEmailGroupService().fetchByName(groupName);
		return fetchByGroupId(group.getId());
	}

	// ----------------------------------------------------------------------

	@Override
	public KEmailStats calcStatsByGroupName(String groupName) {
		List<EMAIL> emailList = fetchByGroupName(groupName);
		return calcStats(emailList);
	}
	
	// ----------------------------------------------------------------------

	@Override
	public KEmailStats calcStatsByCampaignId(Long campaignId) {
		List<EMAIL> emailList = fetchByCampaignId(campaignId);
		return calcStats(emailList);
	}
	
	// ----------------------------------------------------------------------

	@Override
	public KEmailStats calcStatsByCampaignIdAndChannelId(Long campaignId, Long channelId) {
		List<EMAIL> emailList = fetchByCampaignIdAndChannelId(campaignId, channelId);
		return calcStats(emailList);
	}

	// ----------------------------------------------------------------------
	
	@Override
	public KEmailStats calcStats(List<EMAIL> emailList) {

		double failed = 0.0;		double failedRate = 0.0;
		double delivered = 0.0;		double deliveredRate = 0.0;
		double bounced = 0.0;		double bouncedRate = 0.0;
		double complained = 0.0;	double complainedRate = 0.0;
		double optedOut = 0.0;		double optedOutRate = 0.0;
		double opened = 0.0;		double openedAllRate = 0.0;
		double clicked = 0.0;		double clickedAllRate = 0.0;
		double printed = 0.0;		double printedAllRate = 0.0;
		double forwarded = 0.0;		double forwardedAllRate = 0.0;

		double openedDeliveredRate = 0.0;
		double clickedDeliveredRate = 0.0;
		double printedDeliveredRate = 0.0;
		double forwardedDeliveredRate = 0.0;

		double clickedOpenedRate = 0.0;
		double printedOpenedRate = 0.0;
		double forwardedOpenedRate = 0.0;


		double emailCount = (double) emailList.size();

		for (EMAIL email : emailList) {
			if (email.isFailed()) 				failed += 1.0;
			if (email.isDelivered()) 			delivered += 1.0;
			if (email.isBounced()) 				bounced += 1.0;
			if (email.isComplained()) 			complained += 1.0;
			if (email.isOptedOut()) 			optedOut += 1.0;
			if (email.getOpenCount() > 0) 		opened += 1.0;
			if (email.getClickCount() > 0) 		clicked += 1.0;
			if (email.getPrintCount() > 0) 		printed += 1.0;
			if (email.getForwardCount() > 0) 	forwarded += 1.0;
		}

		failedRate = failed / emailCount;
		deliveredRate = delivered / emailCount;
		bouncedRate = bounced / emailCount;
		complainedRate = complained / emailCount;
		optedOutRate = optedOut / emailCount;

		openedAllRate = opened / emailCount;
		openedDeliveredRate = opened / delivered;

		clickedAllRate = clicked / emailCount;
		clickedDeliveredRate = clicked / delivered;
		clickedOpenedRate = clicked / opened;

		printedAllRate = printed / emailCount;
		printedDeliveredRate = printed / delivered;
		printedOpenedRate = printed / opened;

		forwardedAllRate = forwarded / emailCount;
		forwardedDeliveredRate = forwarded / delivered;
		forwardedOpenedRate = forwarded / opened;


		KEmailStats stats = new KEmailStats();
        stats.setEmailCount(emailCount);
        stats.setFailed(failed);
        stats.setDelivered(delivered);
        stats.setBounced(bounced);
        stats.setComplained(complained);
        stats.setOptedOut(optedOut);
        stats.setOpened(opened);
        stats.setClicked(clicked);
        stats.setPrinted(printed);
        stats.setForwarded(forwarded);
        
        stats.setFailedRate(failedRate);
        stats.setDeliveredRate(deliveredRate);
        stats.setBouncedRate(bouncedRate);
        stats.setComplainedRate(complainedRate);
        stats.setOptedOutRate(optedOutRate);
        
        stats.setOpenedAllRate(openedAllRate);
        stats.setOpenedDeliveredRate(openedDeliveredRate);
        
        stats.setClickedAllRate(clickedAllRate);
        stats.setClickedDeliveredRate(clickedDeliveredRate);
        stats.setClickedOpenedRate(clickedOpenedRate);
        
        stats.setPrintedAllRate(printedAllRate);
        stats.setPrintedDeliveredRate(printedDeliveredRate);
        stats.setPrintedOpenedRate(printedOpenedRate);
        
        stats.setForwardedAllRate(forwardedAllRate);
        stats.setForwardedDeliveredRate(forwardedDeliveredRate);
        stats.setForwardedOpenedRate(forwardedOpenedRate);
        
		return stats;
	}

	// ----------------------------------------------------------------------
	
	@Override
	public void deliver(Long campaignId, Long campaignChannelId, Long groupId, String fromAddress, 
			String subject, String text, String html, KEmailFooter footer, Long throttleTime) {
		
		List<EMAIL_GROUP_ADDRESS> addressList = getEmailGroupAddressService().fetchByGroupId(groupId);

		for (EMAIL_GROUP_ADDRESS ega : addressList) {
			EMAIL_ADDRESS address = getEmailAddressService().fetchById(ega.getAddressId());

			if (!getEmailAddressService().isValid(address)) {
				logger.debug("deliver: emailAddress is invalid: " + address.getEmail());
				continue;
			}

			deliver(campaignId, campaignChannelId, groupId, address, fromAddress, subject, text, html, footer);

			throttle(throttleTime);
		}
	}
	
	// ----------------------------------------------------------------------

	@Override
	public void deliver(String fromAddress, String toAddress, 
			String subject, String text, String html, KEmailFooter footer) {
		
		if (toAddress == null || !KValidator.isEmail(toAddress)) {
			throw new KEmailException("Email address is null or invalid: " + toAddress);
		}

		EMAIL_ADDRESS address = getEmailAddressService().fetchByEmail(toAddress);
		
		if (address == null) {
			address = getNewEmailAddressObject();
			address.setEmail(toAddress);
			address.setEnabled(true);
			address.setCreatedDate(new Date());
			address = getEmailAddressService().add(address);
		}

		if (!getEmailAddressService().isValid(address)) {
			throw new KEmailException("deliver: emailAddress is invalid: " + address.getEmail());
		}

		deliver(null, null, null, address, fromAddress, subject, text, html, footer);
	}
	
	// ----------------------------------------------------------------------

	private void deliver(Long campaignId, Long campaignChannelId, Long groupId, EMAIL_ADDRESS address, String fromAddress, 
			String subject, String text, String html, KEmailFooter footer) {

		String toAddress = formatAddress(address);

		// make sure we haven't already sent an email to this user 
		EMAIL email = null;
		
		if (campaignId != null && campaignChannelId != null) {
			email = fetchByCampaignIdAndChannelIdAndToId(campaignId, campaignChannelId, address.getId());

			if (email != null) {
				logger.warn("EmailService.deliver: Skipping:  Email already delivered for campaignId: " 
						+ campaignId + "  and channelId: " + campaignChannelId);
				return;
			}
		}
		
		String uid = uuid();
		
		String text1 = processContent(text, uid, address, footer, false);
		
		String html1 = processContent(html, uid, address, footer, true);

		Date now = new Date();

		email = getNewEmailObject();
		email.setUid(uid);
		email.setCampaignId(campaignId);
		email.setCampaignChannelId(campaignChannelId);
		email.setGroupId(groupId);
		email.setFromAddress(fromAddress);
		email.setToAddress(toAddress);
		email.setToAddressId(address.getId());
		email.setSubject(subject);
		email.setCreatedDate(now);
		email.setSentDate(now);
		email.setOpenCount(0);
		email.setPrintCount(0);
		email.setForwardCount(0);
		email.setClickCount(0);
		
		EMAIL_EVENT event = getNewEmailEventObject();
		event.setEventDate(now);
		event.setCreatedDate(now);
		
		String testDomain = getEmailTestDomain().toLowerCase();
		
		if (toAddress.toLowerCase().endsWith(testDomain)) {
			logger.warn("Email address [{}] ends in test domain [{}]: skipping ...", toAddress, testDomain);
			return;
		}
		
		try {
			String sesId = sendAWS(fromAddress, toAddress, subject, text1, html1);
			email.setSesId(sesId);
			email = add(email);
			
			event.setTypeId(KEmailEventType.ATTEMPTED.getId());
			event.setEmailId(email.getId());
			addEvent(event);
		} catch (Exception e) {
			email.setFailed(true);
			email = add(email);
			
			event.setTypeId(KEmailEventType.FAILED.getId());
			event.setEmailId(email.getId());
			event.setError(e.getMessage());
			addEvent(event);
		}
	}
	
	// ----------------------------------------------------------------------

	private void throttle(Long throttleTime) {
		if (throttleTime != null) {
			try {
				Thread.sleep(throttleTime);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	// ----------------------------------------------------------------------

	private String formatAddress(EMAIL_ADDRESS address) {
		String email = "";
		if (address.getFirstName() != null) {
			String firstName = address.getFirstName().replaceAll("[^a-zA-Z -]", "");
			email = firstName;
		}

		if (address.getLastName() != null) {
			String lastName = address.getLastName().replaceAll("[^a-zA-Z -]", "");
			if (email.length() == 0) {
				email = lastName;
			} else {
				email = email + " " + lastName;
			}
		}

		if (email.length() == 0) {
			email = address.getEmail();
		} else {
			email = email + " <" + address.getEmail() + ">";
		}
		return email;
	}
	
	// ----------------------------------------------------------------------

	protected String transformUrls(String text, String clickUrl) {
		String urlValidationRegex = "(https?|ftp)://(www\\d?|[a-zA-Z0-9]+)?.[a-zA-Z0-9-]+(\\:|.)([a-zA-Z0-9.]+|(\\d+)?)([/?:].*)?";
		Pattern p = Pattern.compile(urlValidationRegex);
		Matcher m = p.matcher(text);
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			String targetUrl = m.group(0); 
			try {
				targetUrl = URLEncoder.encode(targetUrl, "UTF-8");
			} catch (UnsupportedEncodingException e1) { }
			targetUrl = clickUrl + targetUrl;
			m.appendReplacement(sb, targetUrl); 
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	// ----------------------------------------------------------------------

	private String processContent(String content, String messageId, EMAIL_ADDRESS address, KEmailFooter footer, boolean html) {
		if (content == null) return null;
		String systemBaseUrl = getSystemBaseUrl();
		String emailEventUrl = getEmailEventUrl();
		String clickUrl = emailEventUrl +"click/" + messageId + "?u=";
		String openUrl = emailEventUrl +"open/" + messageId;
		String unsubscribeUrl = emailEventUrl +"unsubscribe/" + messageId;

		if (footer != null) {
			footer.setUnsubscribeUrl(unsubscribeUrl);
		}


		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("emailAddress", address);
			map.put("systemBaseUrl", systemBaseUrl);
			map.put("unsubscribeUrl", unsubscribeUrl);
			map.put("Util", KUtil.getInstance());

			KTemplate t = new KTemplate();
			t.setStringTemplate(content);
			t.addContextMap(map);
			content = t.toString();
			content = content.trim();
		} catch (KTemplateException e) {
			logger.error(e.getMessage(), e);
		}

		if (!html) {
			content = transformUrls(content, clickUrl);
			content = content + generateEmailFooter(footer, false);
			content = content.trim();
			return content;
		}

		//Document doc = Jsoup.parseBodyFragment(content);
		Document doc = Jsoup.parse(content);
		Element body = doc.body();

		Elements elements = body.select("a");
		for (Element e : elements) {
			String targetUrl = e.attr("href");
            
            if (targetUrl != null && targetUrl.trim().equals(unsubscribeUrl)) {
            	continue;
            }
            
			try {
				targetUrl = URLEncoder.encode(targetUrl, "UTF-8");
			} catch (UnsupportedEncodingException e1) { }
            
			targetUrl = clickUrl + targetUrl;
			e.attr("href", targetUrl);
		}

		String selector = getEmailFooterHtmlSelector();
		
		Element element = doc.select(selector).first();
		if (element != null) {
			element.append(generateEmailFooter(footer, html));
		}

		element = new Element(Tag.valueOf("img"), "");
		element.attr("src", openUrl);
		body.appendChild(element);


		content = doc.html();

		logger.debug("processed email html:\n" + content);
		return content;
	}

	// ----------------------------------------------------------------------

	private String generateEmailFooter(KEmailFooter footer, boolean html) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("footer", footer);

		String footerTmpl = getEmailTextFooterTemplatePath();  
		
		if (html) {
			footerTmpl = getEmailHtmlFooterTemplatePath(); 
		}

		try {
			KTemplate t = new KTemplate(footerTmpl);
			t.addContextMap(map);
			return t.toString();
		} catch (KTemplateException e) {
			logger.error(e.getMessage(), e);
			return "";
		}
	}
	
	// ----------------------------------------------------------------------

	private Map<String,Object> parseMessage(Message message) {
		String json = message.getBody();
		logger.debug("Processing SQS Message:\n" + json);
		//json = StringEscapeUtils.unescapeJava(json);

		Map<String,Object> body = KStringUtil.toMap(json);
		String m = (String) body.get("Message");

		logger.debug("Processing SQS Message Body:\n" + m);
		Map<String,Object> map = KStringUtil.toMap(m);
		return map;
	}
	
	// ----------------------------------------------------------------------

	@Override
	public void processSESNotifications() {
		processBounceQueue();
		processComplaintQueue();
		processDeliveryQueue();
	}
	
	// ----------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	private void processBounceQueue() {
		String bounceQueue = getAmazonSESBounceQueueName();

		List<Message> messageList = getQueueService().fetchMessages(bounceQueue, -1);
		for (Message message : messageList) {
			Map<String,Object> notification = parseMessage(message);
			String notificationType = (String) notification.get("notificationType");

			if (notificationType.equalsIgnoreCase("AmazonSnsSubscriptionSucceeded")) {
				getQueueService().deleteMessage(bounceQueue, message);
				continue;
			}

			if (!notificationType.equalsIgnoreCase("bounce")) {
				logger.warn("bounce queue: found notification type: " + notificationType);
				getQueueService().deleteMessage(bounceQueue, message);
				continue;
			}

			Map<String,Object> mail = (Map<String, Object>) notification.get("mail");
			Map<String,Object> bounce = (Map<String, Object>) notification.get("bounce");
			String sesId = (String) mail.get("messageId");
			String timestamp = (String) bounce.get("timestamp");
			Date bounceDate = KDateUtil.parseISO8601(timestamp);

			EMAIL email = fetchBySesId(sesId);
			if (email != null && email.isBounced() == false) {
				email.setBounced(true);
				update(email);

				EMAIL_EVENT event = getNewEmailEventObject();
				event.setEmailId(email.getId());
				event.setTypeId(KEmailEventType.BOUNCED.getId());
				event.setEventDate(bounceDate);
				event.setCreatedDate(new Date());
				addEvent(event);
				
				disableEmailAddress(email, KEmailEventType.BOUNCED);
			} else {
				logger.warn("Email not found for sesId: " + sesId);
			}
			
			getQueueService().deleteMessage(bounceQueue, message);
		}
	}
	
	// ----------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	private void processComplaintQueue() {
		String complaintQueue = getAmazonSESComplaintQueueName();
		List<Message> messageList = getQueueService().fetchMessages(complaintQueue, -1);
		for (Message message : messageList) {
			Map<String,Object> notification = parseMessage(message);

			String notificationType = (String) notification.get("notificationType");

			if (notificationType.equalsIgnoreCase("AmazonSnsSubscriptionSucceeded")) {
				getQueueService().deleteMessage(complaintQueue, message);
				continue;
			}

			if (!notificationType.equalsIgnoreCase("Complaint")) {
				logger.warn("Complaint queue: found notification type: " + notificationType);
				getQueueService().deleteMessage(complaintQueue, message);
				continue;
			}


			Map<String,Object> mail = (Map<String, Object>) notification.get("mail");
			Map<String,Object> complaint = (Map<String, Object>) notification.get("complaint");
			String sesId = (String) mail.get("messageId");
			String timestamp = (String) complaint.get("timestamp");
			Date complainedDate = KDateUtil.parseISO8601(timestamp);

			EMAIL email = fetchBySesId(sesId);
			if (email != null && email.isComplained() == false) {
				email.setComplained(true);
				update(email);

				EMAIL_EVENT event = getNewEmailEventObject();
				event.setEmailId(email.getId());
				event.setTypeId(KEmailEventType.COMPLAINED.getId());
				event.setEventDate(complainedDate);
				event.setCreatedDate(new Date());
				addEvent(event);
				
				disableEmailAddress(email, KEmailEventType.COMPLAINED);
			} else {
				logger.warn("Email not found for sesId: " + sesId);
			}
			
			getQueueService().deleteMessage(complaintQueue, message);
		}
	}
	
	// ----------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	private void processDeliveryQueue() {
		String deliveryQueue = getAmazonSESDeliveryQueueName();
		
		List<Message> messageList = getQueueService().fetchMessages(deliveryQueue, -1);

		for (Message message : messageList) {
			Map<String,Object> notification = parseMessage(message);

			String notificationType = (String) notification.get("notificationType");

			if (notificationType.equalsIgnoreCase("AmazonSnsSubscriptionSucceeded")) {
				getQueueService().deleteMessage(deliveryQueue, message);
				continue;
			}

			if (!notificationType.equalsIgnoreCase("Delivery")) {
				logger.warn("Delivery queue: found notification type: " + notificationType);
				getQueueService().deleteMessage(deliveryQueue, message);
				continue;
			}

			Map<String,Object> mail = (Map<String, Object>) notification.get("mail");
			Map<String,Object> delivery = (Map<String, Object>) notification.get("delivery");
			String sesId = (String) mail.get("messageId");
			String timestamp = (String) delivery.get("timestamp");
			Date deliveredDate = KDateUtil.parseISO8601(timestamp);

			EMAIL email = fetchBySesId(sesId);
			if (email != null && email.isDelivered() == false) {
				email.setDelivered(true);
				update(email);

				EMAIL_EVENT event = getNewEmailEventObject();
				event.setEmailId(email.getId());
				event.setTypeId(KEmailEventType.DELIVERED.getId());
				event.setEventDate(deliveredDate);
				event.setCreatedDate(new Date());
				addEvent(event);
			} else {
				logger.warn("Email not found for sesId: " + sesId);
			}
			
			getQueueService().deleteMessage(deliveryQueue, message);
		}
	}
	
	// ----------------------------------------------------------------------

	private void disableEmailAddress(EMAIL email, KEmailEventType type) {
		EMAIL_ADDRESS address = null;
		
		if (email.getToAddressId() != null) {
			address = getEmailAddressService().fetchById(email.getToAddressId());
		} else if (email.getToAddress() != null) {
			address = getEmailAddressService().fetchByEmail(email.getToAddress());
		}
		
		if (address != null) {
			if (type == KEmailEventType.BOUNCED) {
				address.setBouncedDate(new Date());
			} else if  (type == KEmailEventType.COMPLAINED) {
				address.setComplainedDate(new Date());
			}
			
			address.setEnabled(false);
			
			getEmailAddressService().update(address);
		}
	}


}
