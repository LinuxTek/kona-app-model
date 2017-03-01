package com.linuxtek.kona.app.core.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.comm.entity.KEmail;
import com.linuxtek.kona.app.comm.entity.KEmailEvent;
import com.linuxtek.kona.app.comm.entity.KSms;
import com.linuxtek.kona.app.comm.model.KEmailMedia;
import com.linuxtek.kona.app.comm.service.KEmailException;
import com.linuxtek.kona.app.comm.service.KEmailService;
import com.linuxtek.kona.app.comm.service.KSmsException;
import com.linuxtek.kona.app.comm.service.KSmsService;
import com.linuxtek.kona.app.core.entity.KApp;
import com.linuxtek.kona.app.core.entity.KAppConfig;
import com.linuxtek.kona.app.core.entity.KFile;
import com.linuxtek.kona.app.core.entity.KFileAccess;
import com.linuxtek.kona.app.core.entity.KFileType;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.util.KCallback;
import com.linuxtek.kona.app.util.KUtil;
import com.linuxtek.kona.templates.KTemplate;
import com.linuxtek.kona.util.KClassUtil;
import com.linuxtek.kona.util.KFileUtil;


public abstract class KAbstractSystemService<
            APP extends KApp, 
            APP_CONFIG extends KAppConfig, 
            USER extends KUser, 
            SMS extends KSms,
            EMAIL extends KEmail,
            EMAIL_EVENT extends KEmailEvent,
            FILE extends KFile> 
        implements KSystemService<APP,USER,FILE> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractSystemService.class);

    // ----------------------------------------------------------------------------

    private static Map<Long,Configuration> appConfigCache = new HashMap<Long,Configuration>();

    private APP systemApp;

    private USER systemUser;

    // ----------------------------------------------------------------------------
    protected abstract FILE getNewFileObject();

    protected abstract String getTestLoginCode();
    protected abstract String getSystemUsername();
    protected abstract String getSystemAppName();
    protected abstract String getSystemMailAlertTo();
    protected abstract String getSystemMailFrom();
    protected abstract String getFilesBaseUrl();
    protected abstract String getAssetsBaseUrl();
    protected abstract String getAppBaseUrl();
    protected abstract String getTemplatePath(String templateName);

    protected abstract <S extends KAppConfigService<APP_CONFIG>> S getAppConfigService();
    protected abstract <S extends KAppService<APP>> S getAppService();
    protected abstract <S extends KUserService<USER>> S getUserService();
    protected abstract <S extends KSmsService<SMS>> S getSmsService();
    protected abstract <S extends KEmailService<EMAIL,EMAIL_EVENT>> S getEmailService();

    // ----------------------------------------------------------------------------

    @Override
    public Configuration getConfig(Long appId) {
        Configuration result = appConfigCache.get(appId);

        if (result == null) {
            String env = System.getProperty("env", "dev");

            Map<String,Object> config = getAppConfigService().getConfig(appId, env);

            if (config == null) {
                throw new IllegalStateException("Cofiguration not found for appId: " + appId);
            }

            result = new MapConfiguration(config);

            appConfigCache.put(appId, result);
        }

        return result;
    }

    // ----------------------------------------------------------------------------

    @Override
    public void sendEmail(String body, String subject, String from, String replyTo,
            String to, String cc, String bcc, boolean html, List<KEmailMedia> mediaList) throws KEmailException {

        getEmailService().send(body, subject, from, replyTo, to, cc, bcc, html, mediaList);
    }

    // ----------------------------------------------------------------------------

    @Override
    public void sendEmail(String templateName, Map<String,Object> params, String subject, String to) throws KEmailException {
        sendEmail(templateName, params, subject, null, null, to, null, null, null);
    }

    // ----------------------------------------------------------------------------

    @Override
    public void sendEmail(String templateName, Map<String,Object> params, String subject, String from,
            String replyTo, String to, String cc, String bcc, List<KEmailMedia> mediaList) throws KEmailException {

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    _sendEmail(templateName, params, subject, from, replyTo, to, cc, bcc, mediaList);
                } catch (Exception e) {
                    alert("Error sending email for template: " + templateName, e);
                }
            }
        };

        t.start();

    }

    // ----------------------------------------------------------------------------

    private void _sendEmail(String templateName, Map<String,Object> params, String subject, String from,
            String replyTo, String to, String cc, String bcc, List<KEmailMedia> mediaList) throws KEmailException {


        String templatePath = getTemplatePath(templateName);
        String appBaseUrl = getAppBaseUrl();
        String filesBaseUrl = getFilesBaseUrl();
        String assetsBaseUrl = getAssetsBaseUrl();



        if (from == null) {
            from = getSystemMailFrom();
        }

        if (replyTo == null) {
            replyTo = from;
        }

        if (params == null) {
            params = new HashMap<String,Object>();
        }

        params.put("ASSETS_BASE_URL", assetsBaseUrl);
        params.put("Util", KUtil.getInstance());
        params.put("app", getSystemApp());

        String body = null;
        try {
            KTemplate t = new KTemplate(templatePath, params, appBaseUrl, filesBaseUrl);
            t.setInlineCss(true);
            body = t.toHtml();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new KEmailException(e.getMessage(), e);
        }

        boolean html = true;

        String email = "\n"
                + "\ntemplateName: " + templateName
                + "\nparams: " + KClassUtil.toString(params)
                + "\nsubject: " + subject
                + "\nfrom: " + from
                + "\nreplyTo: " + replyTo
                + "\nto: " + to
                + "\ncc: " + cc
                + "\nbcc: " + bcc
                + "\nhtml: " + html
                + "\nbody: " + body.substring(1, 100);

        logger.debug("sendEmail: message properties:" + email);

        sendEmail(body, subject, from, replyTo, to, cc, bcc, html, mediaList);
    }

    // ----------------------------------------------------------------------------

    @Override
    public void sendSms(String to, String body, KCallback callback) throws KSmsException {
        sendSms(to, body, (String) null, callback);
    }

    // ----------------------------------------------------------------------------

    @Override
    public void sendSms(String to, String body, String mediaUrl, KCallback callback) throws KSmsException {
        List<String> urls = null;

        if (mediaUrl != null) {
            urls = new ArrayList<String>();
            urls.add(mediaUrl);
        }

        sendSms(to, body, urls, callback);
    }

    @Override
    public void sendSms(String to, String body, List<String> mediaUrls, KCallback callback) throws KSmsException {
        sendSms(to, body, mediaUrls, null, callback);
    }

    // ----------------------------------------------------------------------------

    @Override
    public void sendSms(String to, String body, List<String> mediaUrls, Long delay, KCallback callback) throws KSmsException {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    if (delay != null) {
                        Thread.sleep(delay);
                    }

                    SMS sms = _sendSms(to, body, mediaUrls);

                    if (callback != null) {
                        callback.callback(sms);
                    }

                } catch (Exception e) {
                    alert("Error sending sms to number: " + to, e);
                }
            }
        };

        t.start();
    }

    // ----------------------------------------------------------------------------

    private SMS _sendSms(String to, String body, List<String> mediaUrls) throws KSmsException {
        if (isTestPhoneNumber(to)) {
            return null;
        }

        return getSmsService().sendMessage(to, body, mediaUrls);
    }


    // ----------------------------------------------------------------------------

    @Override
    public void alert(String subject, Throwable t) {
        alert(subject, null, t);
    }

    // ----------------------------------------------------------------------------

    @Override
    public void alert(String subject, String body) {
        alert(subject, body, false);
    }

    // ----------------------------------------------------------------------------

    @Override
    public void alert(String subject, String body, Throwable t) {
        StringWriter s = new StringWriter();
        if (t != null) {
            t.printStackTrace(new PrintWriter(s));
        }
        if (body == null) body = "";
        body += "\n\n" + s.toString();
        alert(subject, body, false);
    }

    // ----------------------------------------------------------------------------

    @Override
    public void alert(String subject, String body, Boolean html) {

        String from = getSystemMailFrom();
        String to = getSystemMailAlertTo();
        String cc = null;
        String bcc = null;
        String replyTo = null;

        try {
            sendEmail(body, subject, from, replyTo, to, cc, bcc, html, null);
        } catch (KEmailException e) {
            logger.error(e.getMessage(), e);
        }
    }

    // ----------------------------------------------------------------------------

    @Override
    public APP getSystemApp() {
        if (systemApp == null) {
            String appName = getSystemAppName();
            systemApp = getAppService().fetchByName(appName);
        }

        return systemApp;
    }


    // ----------------------------------------------------------------------------

    @Override
    public USER getSystemUser() {
        if (systemUser == null) {
            String username = getSystemUsername();
            systemUser = getUserService().fetchByUsername(username);
        }

        return systemUser;
    }

    // ----------------------------------------------------------------------------

    @Override
    public FILE toFile(USER user, byte[] data, String contentType, String filename) {
        String srcFilename = filename;
        String srcHostname = "127.0.0.1";
        boolean tempFile = false;
        return toFile(user, data, contentType, filename, srcFilename, srcHostname, tempFile);
    }

    // ----------------------------------------------------------------------------

    @Override
    public FILE toFile(USER user, byte[] data, String contentType, String filename, String srcFilename, String srcHostname, boolean tempFile) {
        KFileType type = KFileType.getInstance(contentType, true);

        FILE file = getNewFileObject();

        file.setTypeId(type.getId());
        file.setAccessId(KFileAccess.PUBLIC.getId());
        file.setUserId(user.getId());
        file.setAccountId(user.getAccountId());
        file.setTokenId(null);
        file.setSrcFilename(srcFilename);
        file.setSrcHostname(srcHostname);
        file.setName(filename);
        file.setContentType(contentType);
        file.setSize(new Long(data.length));
        file.setActive(true);
        file.setEnabled(true);
        file.setHidden(false);
        file.setTempFile(tempFile);
        file.setCreatedDate(new Date());
        file.setData(data);

        return file;
    }

    // ----------------------------------------------------------------------------

    @Override
    public FILE urlToFile(String url) throws IOException {
        return urlToFile(null, url);
    }

    // ----------------------------------------------------------------------------

    @Override
    public FILE urlToFile(USER user, String url) throws IOException {
        if (user == null) {
            user = getSystemUser();
        }

        URL u = new URL(url);
        URLConnection uc = u.openConnection();
        byte[] data = KFileUtil.toByteArray(uc.getInputStream());
        return toFile(user, data, uc.getContentType(), u.getPath());
    }

    // ----------------------------------------------------------------------

    @Override
    public boolean isTestPhoneNumber(String phoneNumber) {
        return getSmsService().isTestPhoneNumber(phoneNumber);
    }

    // ----------------------------------------------------------------------

    @Override
    public boolean isTestLoginCode(String code) {
        String testCode = getTestLoginCode();

        if (code != null && testCode != null && testCode.equalsIgnoreCase(code)) {
            return true;
        }

        return false;
    }

    // ----------------------------------------------------------------------

    // Assumes US number
    @Override
    public String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("+1")) {
            phoneNumber = phoneNumber.substring(2);
        }

        String testNumber = phoneNumber.replaceAll("[^\\d]", "");

        testNumber = "+1" + testNumber;

        return testNumber;
    }

}
