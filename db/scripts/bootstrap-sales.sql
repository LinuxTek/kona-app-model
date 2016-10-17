/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- --------------------------------------------------------------------------

CREATE TABLE `campaign` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` datetime(6) NOT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_campaign_name` (`name`),
  UNIQUE KEY `ux_campaign_uid` (`uid`),
  KEY `ix_campaign_app` (`app_id`),
  KEY `ix_campaign_promo` (`promo_id`),
  KEY `ix_campaign_partner` (`partner_id`),
  CONSTRAINT `fk_campaign_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`),
  CONSTRAINT `fk_campaign_partner` FOREIGN KEY (`partner_id`) REFERENCES `partner` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_campaign_promo` FOREIGN KEY (`promo_id`) REFERENCES `promo` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `campaign_channel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `type_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `sms_number` varchar(255) DEFAULT NULL,
  `url_path` varchar(255) DEFAULT NULL,
  `facebook_tracking_id` varchar(255) DEFAULT NULL,
  `google_tracking_id` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `title` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `main_content` varchar(2000) DEFAULT NULL,
  `conversion_content` varchar(2000) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_campaign_channel_uid` (`uid`),
  UNIQUE KEY `ux_campaign_channel_name` (`campaign_id`,`name`),
  UNIQUE KEY `ux_campaign_channel_campaign_type_name` (`campaign_id`,`type_id`,`name`),
  UNIQUE KEY `ux_campaign_channel_sms_number` (`sms_number`),
  UNIQUE KEY `ux_campaign_channel_name_url_path` (`name`,`url_path`),
  KEY `ix_campaign_channel_campaign` (`campaign_id`),
  KEY `ix_campaign_channel_type` (`type_id`),
  CONSTRAINT `fk_campaign_channel_campaign` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_campaign_channel_type` FOREIGN KEY (`type_id`) REFERENCES `campaign_type` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `campaign_event` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `channel_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `event_category` varchar(255) DEFAULT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `event_label` varchar(255) DEFAULT NULL,
  `event_value` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `browser` varchar(512) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_campaign_event_uid` (`uid`),
  KEY `ix_campaign_event_campaign` (`campaign_id`),
  KEY `ix_campaign_event_channel` (`channel_id`),
  KEY `ix_campaign_event_user` (`user_id`),
  CONSTRAINT `fk_campaign_event_campaign` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_campaign_event_channel` FOREIGN KEY (`channel_id`) REFERENCES `campaign_channel` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_campaign_event_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `campaign_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_campaign_type_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE `cart` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `account_id` bigint(20) unsigned DEFAULT NULL,
  `currency_id` bigint(20) unsigned DEFAULT NULL,
  `invoice_id` bigint(20) unsigned DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `browser` varchar(512) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `default_card_last4` varchar(4) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `shipping` decimal(10,2) DEFAULT NULL,
  `tax` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `checked_out` tinyint(1) NOT NULL DEFAULT '0',
  `invoiced` tinyint(1) NOT NULL DEFAULT '0',
  `expired` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `expired_date` datetime(6) DEFAULT NULL,
  `checked_out_date` datetime(6) DEFAULT NULL,
  `invoiced_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `ix_cart_app` (`app_id`),
  KEY `ix_cart_user` (`user_id`),
  KEY `ix_cart_account` (`account_id`),
  KEY `ix_cart_invoice` (`invoice_id`),
  KEY `ix_cart_currency` (`currency_id`),
  CONSTRAINT `fk_cart_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_cart_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`),
  CONSTRAINT `fk_cart_currency` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_cart_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `cart_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `cart_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `description` varchar(1024) DEFAULT NULL,
  `discount_description` varchar(1024) DEFAULT NULL,
  `unit_price` decimal(10,2) DEFAULT NULL,
  `setup_fee` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `subscription_start_date` datetime(6) DEFAULT NULL,
  `subscription_end_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `ix_cart_item_cart` (`cart_id`),
  KEY `ix_cart_item_product` (`product_id`),
  KEY `ix_cart_item_promo` (`promo_id`),
  CONSTRAINT `fk_cart_item_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_cart_item_promo` FOREIGN KEY (`promo_id`) REFERENCES `promo` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_cart_item_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `currency` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `symbol` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `iso_code` varchar(4) NOT NULL,
  `country` varchar(64) DEFAULT NULL,
  `subdivison` varchar(64) DEFAULT NULL,
  `regime` varchar(64) DEFAULT NULL,
  `locale` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ix_currency_iso_code` (`iso_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `invoice` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `currency_id` bigint(20) unsigned DEFAULT NULL,
  `invoice_no` varchar(255) DEFAULT NULL,
  `start_balance` decimal(10,2) DEFAULT NULL,
  `end_balance` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `tax` decimal(10,2) DEFAULT NULL,
  `shipping` decimal(10,2) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) NOT NULL,
  `amount_due` decimal(10,2) NOT NULL,
  `amount_paid` decimal(10,2) DEFAULT NULL,
  `paid` tinyint(1) NOT NULL DEFAULT '0',
  `closed` tinyint(1) NOT NULL DEFAULT '0',
--  `subscription_start_date` datetime(6) DEFAULT NULL,
--  `subscription_end_date` datetime(6) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `invoice_date` datetime(6) NOT NULL,
  `due_date` datetime(6) DEFAULT NULL,
  `paid_date` datetime(6) DEFAULT NULL,
  `closed_date` datetime(6) DEFAULT NULL,
  `payment_attempted` tinyint(1) NOT NULL DEFAULT '0',
  `payment_attempt_count` int(11) DEFAULT NULL,
  `last_payment_attempt_date` datetime(6) DEFAULT NULL,
  `next_payment_attempt_date` datetime(6) DEFAULT NULL,
  `payment_card_last4` varchar(4) DEFAULT NULL,
  `payment_ref` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_invoice_invoice_no` (`invoice_no`),
  KEY `ix_invoice_app` (`app_id`),
  KEY `ix_invoice_user` (`user_id`),
  KEY `ix_invoice_account` (`account_id`),
  KEY `ix_invoice_currency` (`currency_id`),
  CONSTRAINT `fk_invoice_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_invoice_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`),
  CONSTRAINT `fk_invoice_currency` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_invoice_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `invoice_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `invoice_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `discount_description` varchar(1024) DEFAULT NULL,
  `unit_price` decimal(10,2) DEFAULT NULL,
  `setup_fee` decimal(10,2) DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `subtotal` decimal(10,2) NOT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) NOT NULL,
  `subscription_start_date` datetime(6) DEFAULT NULL,
  `subscription_end_date` datetime(6) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `ix_invoice_item_invoice` (`invoice_id`),
  KEY `ix_invoice_product` (`product_id`),
  KEY `fk_invoice_item_promo` (`promo_id`),
  CONSTRAINT `fk_invoice_item_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_invoice_item_promo` FOREIGN KEY (`promo_id`) REFERENCES `promo` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_invoice_item_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;




CREATE TABLE `partner` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `facebook_url` varchar(255) DEFAULT NULL,
  `twitter_handle` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `street1` varchar(255) DEFAULT NULL,
  `street2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `population` int(11) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `retired_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_partner_uid` (`uid`),
  UNIQUE KEY `ux_partner_name` (`name`),
  KEY `ix_partner_parent` (`parent_id`),
  CONSTRAINT `fk_partner_parent` FOREIGN KEY (`parent_id`) REFERENCES `partner` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `payment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `type_id` bigint(20) unsigned DEFAULT NULL,
  `status_id` bigint(20) unsigned DEFAULT NULL,
  `currency_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `account_id` bigint(20) unsigned DEFAULT NULL,
  `invoice_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `browser` varchar(512) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `card_token` varchar(255) DEFAULT NULL,
  `card_last4` varchar(4) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `amount_refunded` decimal(10,2) DEFAULT NULL,
  `processor_ref` varchar(255) DEFAULT NULL,
  `processor_receipt` text,
  `processor_error` varchar(255) DEFAULT NULL,
  `processor_fee` decimal(10,2) DEFAULT NULL,
  `paid` tinyint(1) NOT NULL DEFAULT '0',
  `refunded` tinyint(1) NOT NULL DEFAULT '0',
  `disputed` tinyint(1) NOT NULL DEFAULT '0',
  `failed` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `paid_date` datetime(6) DEFAULT NULL,
  `disputed_date` datetime(6) DEFAULT NULL,
  `refunded_date` datetime(6) DEFAULT NULL,
  `failed_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_payment_processor_ref` (`processor_ref`),
  KEY `ix_payment_app` (`app_id`),
  KEY `ix_payment_type` (`type_id`),
  KEY `ix_payment_status` (`status_id`),
  KEY `ix_payment_currency` (`currency_id`),
  KEY `ix_payment_user` (`user_id`),
  KEY `ix_payment_account` (`account_id`),
  KEY `ix_payment_invoice` (`invoice_id`),
  KEY `ix_payment_promo` (`promo_id`),
  CONSTRAINT `fk_payment_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_payment_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`),
  CONSTRAINT `fk_payment_currency` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_payment_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_payment_promo` FOREIGN KEY (`promo_id`) REFERENCES `promo` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_payment_status` FOREIGN KEY (`status_id`) REFERENCES `payment_status` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_payment_type` FOREIGN KEY (`type_id`) REFERENCES `payment_type` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_payment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `payment_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `display_name` varchar(128) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_payment_status_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `payment_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `display_name` varchar(128) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_payment_type_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `pre_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned DEFAULT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `product_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `payment_id` bigint(20) unsigned DEFAULT NULL,
  `ref_app_id` bigint(20) unsigned DEFAULT NULL,
  `referred_by_user_id` bigint(20) unsigned DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `reconciled` tinyint(1) NOT NULL DEFAULT '0',
  `proxy_payment` tinyint(1) NOT NULL DEFAULT '0',
  `processor` enum('stripe') DEFAULT NULL,
  `payment_description` varchar(255) DEFAULT NULL,
  `payment_token` varchar(255) DEFAULT NULL,
  `payment_card_last4` varchar(255) DEFAULT NULL,
  `payment_ref` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `shipping_address1` varchar(255) DEFAULT NULL,
  `shipping_address2` varchar(255) DEFAULT NULL,
  `shipping_city` varchar(255) DEFAULT NULL,
  `shipping_state` varchar(255) DEFAULT NULL,
  `shipping_postal_code` varchar(255) DEFAULT NULL,
  `shipping_country` varchar(255) DEFAULT NULL,
  `notes` varchar(2000) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `browser` varchar(512) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `shipped_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_pre_order_uid` (`uid`),
  KEY `ix_pre_order_referred_by` (`referred_by_user_id`),
  KEY `ix_pre_order_app` (`app_id`),
  KEY `ix_pre_order_partner` (`partner_id`),
  KEY `ix_pre_order_ref_app` (`ref_app_id`),
  KEY `ix_pre_order_payment` (`payment_id`),
  KEY `ix_pre_order_campaign` (`campaign_id`),
  CONSTRAINT `fk_pre_order_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_pre_order_campaign` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_pre_order_partner` FOREIGN KEY (`partner_id`) REFERENCES `partner` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_pre_order_payment` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_pre_order_ref_app` FOREIGN KEY (`ref_app_id`) REFERENCES `app` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_pre_order_referred_by` FOREIGN KEY (`referred_by_user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `promo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `visible` tinyint(1) NOT NULL DEFAULT '0',
  `signup_default` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `use_count` int(11) DEFAULT NULL,
  `use_per_account` int(11) DEFAULT '1',
  `max_use_count` int(11) DEFAULT NULL,
  `discount_pct` int(11) DEFAULT NULL,
  `discount_amount` decimal(10,2) DEFAULT NULL,
  `setup_fee` decimal(10,2) DEFAULT NULL,
  `trial_days` int(11) DEFAULT NULL,
  `subscription_days` int(11) DEFAULT NULL,
  `validation_rule` varchar(255) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ix_promo_name` (`name`),
  KEY `ix_promo_app` (`app_id`),
  KEY `ix_promo_product` (`product_id`),
  CONSTRAINT `fk_promo_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT `fk_promo_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `promo_page` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `banner_url` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `intro_message` varchar(2000) DEFAULT NULL,
  `registered_message` varchar(2000) DEFAULT NULL,
  `facebook_tracking_id` varchar(255) DEFAULT NULL,
  `google_tracking_id` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `root` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `expired_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_promo_page_phone_number` (`phone_number`),
  UNIQUE KEY `ux_promo_page_path_name` (`path`,`name`),
  KEY `ix_promo_page_app` (`app_id`),
  KEY `ix_promo_page_promo` (`promo_id`),
  KEY `ix_promo_page_partner` (`partner_id`),
  KEY `ix_promo_page_campaign` (`campaign_id`),
  CONSTRAINT `fk_promo_page_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_promo_page_campaign` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_promo_page_partner` FOREIGN KEY (`partner_id`) REFERENCES `partner` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_promo_page_promo` FOREIGN KEY (`promo_id`) REFERENCES `promo` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `sales_lead` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned DEFAULT NULL,
  `ref_app_id` bigint(20) unsigned DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `twitterUsername` varchar(255) DEFAULT NULL,
  `facebookUsername` varchar(255) DEFAULT NULL,
  `referred_by_user_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `channel` enum('website','referral') DEFAULT NULL,
  `interests` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `browser` varchar(512) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_sales_lead_uid` (`uid`),
  KEY `ix_sales_lead_referred_by` (`referred_by_user_id`),
  KEY `ix_sales_lead_app` (`app_id`),
  KEY `ix_sales_lead_ref_app` (`ref_app_id`),
  KEY `ix_sales_lead_campaign` (`campaign_id`),
  CONSTRAINT `fk_sales_lead_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_sales_lead_campaign` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_sales_lead_ref_app` FOREIGN KEY (`ref_app_id`) REFERENCES `app` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_sales_lead_referred_by` FOREIGN KEY (`referred_by_user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



-- --------------------------------------------------------------------------

CREATE TABLE `product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `display_order` int(11) unsigned DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `setup_fee` decimal(10,2) DEFAULT NULL,
  `trial_days` int(11) DEFAULT NULL,
  `subscription` tinyint(1) NOT NULL DEFAULT '0',
  `subscription_days` int(11) DEFAULT NULL,
  `support_type` enum('Email','Priority') DEFAULT NULL,
  -- `default_plan` tinyint(1) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_product_app_name` (`app_id`,`name`),
  KEY `ix_product_name` (`name`),
  CONSTRAINT `fk_product_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `product_purchase` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `payment_type_id` bigint(20) unsigned DEFAULT NULL,
  `kind` varchar(255) DEFAULT NULL, -- field used by google play
  `invoice_no` varchar(255) DEFAULT NULL,
  `auto_renew` tinyint(1) NOT NULL DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `expiration_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_product_purchase` (`account_id`,`product_id`),
  KEY `ix_product_purchase_parent` (`parent_id`),
  KEY `ix_product_purchase_user` (`user_id`),
  KEY `ix_product_purchase_product` (`product_id`),
  KEY `ix_product_purchase_app` (`app_id`),
  KEY `ix_product_purchase_promo` (`promo_id`),
  KEY `ix_product_purchase_partner` (`partner_id`),
  KEY `ix_product_purchase_campaign` (`campaign_id`),
  KEY `ix_product_purchase_payment_type` (`payment_type_id`),
  KEY `ix_product_purchase_invoice` (`invoice_no`),
  CONSTRAINT `fk_product_purchase_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_product_purchase_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_product_purchase_campaign` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`),
  CONSTRAINT `fk_product_purchase_invoice` FOREIGN KEY (`invoice_no`) REFERENCES `invoice` (`invoice_no`),
  CONSTRAINT `fk_product_purchase_parent` FOREIGN KEY (`parent_id`) REFERENCES `product_purchase` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_product_purchase_partner` FOREIGN KEY (`partner_id`) REFERENCES `partner` (`id`),
  CONSTRAINT `fk_product_purchase_payment_type` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_product_purchase_promo` FOREIGN KEY (`promo_id`) REFERENCES `promo` (`id`),
  CONSTRAINT `fk_product_purchase_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_product_purchase_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
