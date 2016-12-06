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

-- ------------------------------------------------
-- Comm tables
-- ------------------------------------------------

CREATE TABLE `comm__email` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
  `group_id` bigint(20) unsigned DEFAULT NULL,
  `to_address_id` bigint(20) unsigned DEFAULT NULL,
  `ses_id` varchar(255) DEFAULT NULL,
  `from_address` varchar(255) NOT NULL,
  `to_address` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `failed` tinyint(1) NOT NULL DEFAULT '0',
  `delivered` tinyint(1) NOT NULL DEFAULT '0',
  `bounced` tinyint(1) NOT NULL DEFAULT '0',
  `complained` tinyint(1) NOT NULL DEFAULT '0',
  `opted_out` tinyint(1) NOT NULL DEFAULT '0',
  `open_count` int(11) NOT NULL DEFAULT '0',
  `click_count` int(11) NOT NULL DEFAULT '0',
  `print_count` int(11) NOT NULL DEFAULT '0',
  `forward_count` int(11) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `sent_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_comm__email_uid` (`uid`),

  UNIQUE KEY `ix_comm__email_ses_id` (`ses_id`),

  UNIQUE KEY `ix_comm__email_campaign_channel_to` (`campaign_id`,`campaign_channel_id`,`to_address_id`),

  KEY `ix_comm__email_campaign` (`campaign_id`),

  KEY `ix_comm__email_group` (`group_id`),

  KEY `ix_comm__email_to_address` (`to_address_id`),

  KEY `ix_comm__email_campaign_channel` (`campaign_channel_id`),

  CONSTRAINT `fk_email_campaign` FOREIGN KEY (`campaign_id`) 
        REFERENCES `sales_campaign` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_email_campaign_channel` FOREIGN KEY (`campaign_channel_id`) 
        REFERENCES `sales_campaign_channel` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_email_group` FOREIGN KEY (`group_id`) 
        REFERENCES `comm__email_group` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_email_to_address` FOREIGN KEY (`to_address_id`) 
        REFERENCES `comm__email_address` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `comm__email_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `mobile_number` varchar(255) default NULL,
  `gender` varchar(255) default NULL,
  `birth_year` varchar(255) default NULL,
  `company` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `extra` varchar(255) default NULL,
  `street1` varchar(255) default NULL,
  `street2` varchar(255) default NULL,
  `city` varchar(255) default NULL,
  `state` varchar(255) default NULL,
  `postal_code` varchar(255) default NULL,
  `country` varchar(255) default NULL,
  `source` varchar(255) DEFAULT NULL,
  `scrubbed` tinyint(1) NOT NULL DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `confirmed` tinyint(1) NOT NULL DEFAULT '0',
  `opted_in_date` datetime(6) DEFAULT NULL,
  `opted_out_date` datetime(6) DEFAULT NULL,
  `bounced_date` datetime(6) DEFAULT NULL,
  `complained_date` datetime(6) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_comm__email_address_uid` (`uid`),

  UNIQUE KEY `ux_comm__email_address_email` (`email`),

  KEY `ix_comm__email_address_user` (`user_id`),

  CONSTRAINT `fk_email_address_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `comm__email_event` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) unsigned NOT NULL,
  `email_id` bigint(20) unsigned NOT NULL,
  `target` varchar(2000) DEFAULT NULL,
  `error` varchar(2000) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `browser` varchar(512) DEFAULT NULL,
  `event_date` datetime(6) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  KEY `ix_comm__email_event_type` (`type_id`),

  KEY `ix_comm__email_event_email` (`email_id`),

  CONSTRAINT `fk_comm__email_event_email` FOREIGN KEY (`email_id`) 
        REFERENCES `comm__email` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_comm__email_event_type` FOREIGN KEY (`type_id`) 
        REFERENCES `comm__email_event_type` (`id`) ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `comm__email_event_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `display_name` varchar(128) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `comm__email_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_comm__email_group_uid` (`uid`),

  UNIQUE KEY `ux_comm__email_group_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `comm__email_group_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) unsigned NOT NULL,
  `address_id` bigint(20) unsigned NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ix_comm__email_group_address_group_address` (`group_id`,`address_id`),

  KEY `fk_comm__email_group_address_address` (`address_id`),

  CONSTRAINT `fk_comm__email_group_address_address` FOREIGN KEY (`address_id`) 
        REFERENCES `comm__email_address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_comm__email_group_address_group` FOREIGN KEY (`group_id`) 
        REFERENCES `comm__email_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------


CREATE TABLE `comm__push_notification_device` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `push_notification_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `device_uuid` varchar(255) NOT NULL,
  `platform_name` varchar(255) NOT NULL, -- should match value in push_notification
  `push_token` varchar(1024) DEFAULT NULL,
  `push_endpoint` varchar(1024) DEFAULT NULL,
  `sandbox` tinyint(1) NOT NULL DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  -- different users using same app on same device
  UNIQUE KEY `ux_comm__push_notification` (`app_id`,`user_id`,`device_uuid`,`sandbox`), 

  UNIQUE KEY `ux_comm__push_notification_device_push_token` (`push_token`(255)),

  KEY `ix_comm__push_notification_device_app` (`app_id`),

  KEY `ix_comm__push_notification_device_user` (`user_id`),

  KEY `ix_comm__push_notification_device_device` (`device_uuid`),

  CONSTRAINT `fk_comm__push_notification_device_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_core__push_notification_device_push_notification` FOREIGN KEY (`push_notification_id`) 
        REFERENCES `comm__push_notification` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_comm__push_notification_device_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `comm__push_notification` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `platform_name` varchar(255) NOT NULL,
  `push_server_key` varchar(2048) DEFAULT NULL,
  `push_server_secret` varchar(2048) DEFAULT NULL,
  `push_endpoint` varchar(1024) DEFAULT NULL,
  `sandbox` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_comm__push_notification` (`app_id`,`platform_name`,`sandbox`),

  CONSTRAINT `fk_comm__push_notification_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `comm__push_notification_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `sandbox` tinyint(1) NOT NULL DEFAULT '0',
  `title` varchar(255) DEFAULT NULL,
  `message` varchar(2000) NOT NULL,
  `image_url` varchar(512) DEFAULT NULL,
  `action_url` varchar(2000) DEFAULT NULL,
  `filter` varchar(2000) DEFAULT NULL,
  `devices` blob DEFAULT NULL,
  `device_count` int(11) unsigned NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_comm__push_notification_message_uid` (`uid`),

  KEY `ix_comm__push_notification_message_app` (`app_id`),

  CONSTRAINT `fk_comm__push_notification_message_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

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

