package com.linuxtek.kona.app.comm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public abstract class KAbstractQueueService implements KQueueService {

    private static Logger logger = LoggerFactory.getLogger(KAbstractQueueService.class);

    private static final Map<String,String> queueMap = new HashMap<String,String>();
    
	// ----------------------------------------------------------------------------
    
	protected abstract AmazonSQSClient getClient();
    
	// ----------------------------------------------------------------------------
    
    private String getQueueUrl(String queueName) {
        String url = queueMap.get(queueName);
        
        if (url == null) {
        	GetQueueUrlResult result = getClient().getQueueUrl(queueName);
        	url = result.getQueueUrl();
            queueMap.put(queueName, url);
        }
        
        return url;
    }
    
	// ----------------------------------------------------------------------------

	@Override
	public String createQueue(String queueName) throws KQueueException {
		CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
		
        String queueUrl = getClient().createQueue(createQueueRequest).getQueueUrl();
        
        logger.debug("queuerUrl for queueName: " + queueName + "  url: " + queueUrl);
        
        return queueUrl;
	}

	// ----------------------------------------------------------------------------

	@Override
	public void deleteQueue(String queueName) throws KQueueException {
		getClient().deleteQueue(new DeleteQueueRequest(getQueueUrl(queueName)));
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<String> listQueues() throws KQueueException {
		return getClient().listQueues().getQueueUrls();
	}

	// ----------------------------------------------------------------------------

	@Override
	public void sendMessage(String queueName, String message) throws KQueueException {
		getClient().sendMessage(new SendMessageRequest(getQueueUrl(queueName), message));
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<Message> receiveMessages(String queueName) throws KQueueException {
		String queueUrl = getQueueUrl(queueName);

		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        
        // max is 10 messages per request
        receiveMessageRequest.setMaxNumberOfMessages(10); 
        
        // long polling: wait up to 20secs for a response. helps eliminate false negatives
        // by polling all sqs servers and not just a random sample that may not have any
        // messages stored on them.
        // http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-long-polling.html
        receiveMessageRequest.setWaitTimeSeconds(20); 
        
        List<Message> messages = getClient().receiveMessage(receiveMessageRequest).getMessages();

        return messages;
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public List<Message> fetchMessages(String queueName, Integer maxCount) throws KQueueException {
        Integer size = getQueueSize(queueName);

        if (maxCount == 0) return null;

        if (maxCount < 0) {
        	maxCount = size;
        }
        
        logger.debug("fetchAllMessage: queue size for queue: " + queueName + "  size: " + size);

        List<Message> result = receiveMessages(queueName);

        while (result.size() < maxCount) {
            try { Thread.sleep(2500); } catch (InterruptedException e) {}
        	result.addAll(receiveMessages(queueName));
        }

        logger.debug("fetchAllMessage: fetched results for queue: " + queueName + "  size: " + result.size());

        return result;
	}

	// ----------------------------------------------------------------------------

	@Override
	public void deleteMessage(String queueName, Message message) throws KQueueException {
        String queueUrl = getQueueUrl(queueName);

        String handle = message.getReceiptHandle();

        logger.debug("deleting message for queue: " + queueUrl + "  handle: " + handle);

		getClient().deleteMessage(new DeleteMessageRequest(queueUrl, handle));
	}

	// ----------------------------------------------------------------------------

	@Override
	public Integer getQueueSize(String queueName) throws KQueueException {
		String queueUrl = getQueueUrl(queueName);

        List<String> attributes = new ArrayList<String>();

        attributes.add("All");

		GetQueueAttributesResult result = getClient().getQueueAttributes(queueUrl, attributes);

        Map<String,String> map = result.getAttributes();

        Integer size = Integer.parseInt(map.get("ApproximateNumberOfMessages"));

        return size;
	}
}
