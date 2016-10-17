package com.linuxtek.kona.app.sales.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisher.Purchases.Subscriptions;
import com.google.api.services.androidpublisher.AndroidPublisher.Purchases.Subscriptions.Cancel;
import com.google.api.services.androidpublisher.AndroidPublisher.Purchases.Subscriptions.Get;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.linuxtek.kona.app.sales.entity.KCart;
import com.linuxtek.kona.app.sales.entity.KCartItem;
import com.linuxtek.kona.app.sales.entity.KInvoice;
import com.linuxtek.kona.app.sales.entity.KPayment;
import com.linuxtek.kona.app.sales.entity.KPaymentType;
import com.linuxtek.kona.app.sales.entity.KProduct;
import com.linuxtek.kona.app.sales.entity.KPurchase;
import com.linuxtek.kona.encryption.KEncryptUtil;

public abstract class KAbstractGooglePlayService<
										   PURCHASE extends KPurchase,
										   CART extends KCart,
										   CART_ITEM extends KCartItem,
										   PAYMENT extends KPayment,
										   INVOICE extends KInvoice,
										   PRODUCT extends KProduct>
		implements KGooglePlayService<PURCHASE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractGooglePlayService.class);
    
	// ----------------------------------------------------------------------------
    
    protected static Map<Long,AndroidPublisher> androidPublisherCache = new HashMap<Long,AndroidPublisher>();

	// ----------------------------------------------------------------------------
    
	protected abstract PURCHASE getNewPurchaseObject();
    
	protected abstract String getGooglePlayPackageName(Long appId);
    
	protected abstract String getGooglePlayServiceAccountEmail(Long appId);
    
	protected abstract String getGooglePlayServiceAccountPrivateKey(Long appId);
    
	protected abstract String getGooglePlayAppName(Long appId);
    
	protected abstract <S extends KInvoiceService<INVOICE,CART,CART_ITEM>> S getInvoiceService();
    
	protected abstract <S extends KProductService<PRODUCT>> S getProductService();
    
	protected abstract <S extends KPurchaseService<PURCHASE>> S getPurchaseService();
    
	protected abstract <S extends KPaymentService<PAYMENT,INVOICE>> S getPaymentService();
    
	// ----------------------------------------------------------------------------
    
    protected AndroidPublisher getAndroidPublisher(Long appId) {
    	AndroidPublisher androidPublisher = androidPublisherCache.get(appId);
        if (androidPublisher != null) {
        	return androidPublisher;
        }
        
		try {
            //String serviceAccountEmail = getConfig(appId).getString("google.api.oauth.serviceAccountEmail");
            //String serviceAccountPrivateKey = getConfig(appId).getString("google.api.oauth.serviceAccountPrivateKey");
            //String appName = getConfig(appId).getString("google.api.client.applicationName");
            
            String serviceAccountEmail = getGooglePlayServiceAccountEmail(appId);
            String serviceAccountPrivateKey = getGooglePlayServiceAccountPrivateKey(appId);
            String appName = getGooglePlayAppName(appId);
            
            
            logger.debug("Initializing GoogleAndroid Service:"
            		+ "\nappName: [" + appName + "]"
            		+ "\nserviceAccountEmail: [" + serviceAccountEmail + "]"
            		+ "\nserviceAccountPrivateKey: [" + serviceAccountPrivateKey + "]"
            );
            
            //File keyFile = KFileUtil.writeTempFile(serviceAccountPrivateKey);
            
            // make sure key doesn't include any headers
            serviceAccountPrivateKey = serviceAccountPrivateKey.replaceAll(
            		"(-+BEGIN PRIVATE KEY-+\\r?\\n|-+END PRIVATE KEY-+\\r?\\n?)"
            , "");
            
            PrivateKey privateKey = KEncryptUtil.loadPrivateKey(serviceAccountPrivateKey);
            
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

			// service account credential (uncomment setServiceAccountUser for domain-wide delegation)
			GoogleCredential credential;
			credential = new GoogleCredential.Builder().setTransport(httpTransport)
					.setJsonFactory(jsonFactory)
					.setServiceAccountId(serviceAccountEmail)
					.setServiceAccountScopes(AndroidPublisherScopes.all())
					.setServiceAccountPrivateKey(privateKey)
					//.setServiceAccountPrivateKeyFromP12File(keyFile)
					// .setServiceAccountUser("user@example.com")
					.build();

			// set up global AndroidPublisher instance
			androidPublisher = new AndroidPublisher.Builder(httpTransport, jsonFactory, credential)
				.setApplicationName(appName).build();
            
			androidPublisherCache.put(appId, androidPublisher);
            
            return androidPublisher;
            
		} catch (GeneralSecurityException | IOException e) {
			throw new RuntimeException(e);
		}
	}
    
	// ----------------------------------------------------------------------------

	private String getSubscriptionToken(Long accountId, Long productId) {
		PURCHASE purchase = getPurchaseService()
						.fetchByAccountIdAndProductId(accountId, productId);
		
		if (purchase == null) {
			logger.warn("AccountSubscription is null for accountId: " + accountId + "  productId: " + productId);
			return null;
		}

		if (purchase.getInvoiceNo() == null) {
			logger.warn("No account invoiceNo for subscriptionPurchaseId: " + purchase.getId());
			return null;
		}

		INVOICE invoice = getInvoiceService().fetchByInvoiceNo(purchase.getInvoiceNo());
		if (invoice == null) {
			logger.warn("No invoice found for invoiceNo: " + purchase.getInvoiceNo());
			return null;
		}

		if (invoice.getPaymentRef() == null) {
			logger.warn("No invoice paymentRef found for invoiceNo: " + purchase.getInvoiceNo());
			return null;
		}

		PAYMENT payment = getPaymentService().fetchByProcessRef(invoice.getPaymentRef());
		if (payment == null) {
			logger.warn("No payment found for paymentRef: " + invoice.getPaymentRef());
			return null;
		}
		
		KPaymentType paymentType = KPaymentType.getInstance(payment.getTypeId());
		if (paymentType != KPaymentType.GOOGLE_PLAY) {
			logger.warn("PaymentType for payment is not GOOGLE_PLAY.  type: " + paymentType);
			return null;
		}

		String token = payment.getProcessorReceipt();
		if (token == null) {
			logger.warn("No processor receipt for paymentId: " + payment.getId());
			return null;
		}
		return token;
	}
    
	// ----------------------------------------------------------------------------


	@Override
	public PURCHASE getSubscription(Long accountId, Long productId) throws IOException {
        String token = getSubscriptionToken(accountId, productId);
        if (token == null) {
        	logger.warn("No subscription token found for accountId: " + accountId);
            return null;
        }
        
		return getSubscription(productId, token);
	}
    
	// ----------------------------------------------------------------------------
    
	@Override
	public void cancelSubscription(Long accountId, Long productId) throws IOException {
        String token = getSubscriptionToken(accountId, productId);
        if (token == null) {
        	logger.warn("No subscription token found for accountId: " + accountId);
            return;
        }
		cancelSubscription(productId, token);
	}
    
	// ----------------------------------------------------------------------------
	
    @Override
	public PURCHASE getSubscription(Long productId, String token) throws IOException {
        PRODUCT product = getProductService().fetchById(productId);
    	//String packageName = getConfig(product.getAppId()).getString("google.play.packageName");
        String packageName = getGooglePlayPackageName(product.getAppId());
        String googleProductId = product.getName();
        return getSubscription(product.getAppId(), packageName, googleProductId, token);
    }
    
	// ----------------------------------------------------------------------------

    @Override
	public PURCHASE getSubscription(Long appId, String packageName, String productId, String token) throws IOException {
        Subscriptions subs = getAndroidPublisher(appId).purchases().subscriptions();
        Get get = subs.get(packageName, productId, token);
        com.google.api.services.androidpublisher.model.SubscriptionPurchase result = get.execute();
        PURCHASE purchase = getNewPurchaseObject();
        purchase.setEnabled(true);

        if (result.getAutoRenewing() != null) {
        	purchase.setAutoRenew(result.getAutoRenewing());
        }
        
        if (result.getExpiryTimeMillis() != null) {
        	Date expirationDate = new Date(result.getExpiryTimeMillis());
        	purchase.setExpirationDate(expirationDate);
        }
        
        if (result.getStartTimeMillis() != null) {
        	Date createdDate = new Date(result.getStartTimeMillis());
        	purchase.setCreatedDate(createdDate);
        }
        
        purchase.setKind(result.getKind());
        return purchase;
	}
    
	// ----------------------------------------------------------------------------
    
    @Override
  	public void cancelSubscription(Long appId, String packageName, String productId, String token) throws IOException {
          Subscriptions subs = getAndroidPublisher(appId).purchases().subscriptions();
          Cancel cancel = subs.cancel(packageName, productId, token);
          cancel.execute();
  	}
    
	// ----------------------------------------------------------------------------
    
    @Override
	public void cancelSubscription(Long productId, String token) throws IOException {
        PRODUCT product = getProductService().fetchById(productId);
    	//String packageName = getConfig(product.getAppId()).getString("google.play.packageName");
    	String packageName = getGooglePlayPackageName(product.getAppId());
        String googleProductId = product.getName();
        cancelSubscription(product.getAppId(), packageName, googleProductId, token);
    }
    
}

