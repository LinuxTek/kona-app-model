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

CREATE TABLE `_changelog` (
  `ID` decimal(20,0) NOT NULL,
  `APPLIED_AT` varchar(25) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `stripe_uid` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `verified` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `retired_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_account_name` (`name`),
  UNIQUE KEY `ux_account_uid` (`uid`),
  UNIQUE KEY `ux_account_owner` (`owner_id`),

  CONSTRAINT `fk_account_owner` FOREIGN KEY (`owner_id`) 
        REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `api_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `version_id` bigint(20) unsigned NOT NULL,
  `client_id` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `end_point` varchar(255) NOT NULL,
  `class_name` varchar(255) NOT NULL,
  `method_name` varchar(255) NOT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `browser` varchar(512) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_api_log_uid` (`uid`),
  KEY `ix_api_log_owner` (`owner_id`),
  KEY `ix_api_log_app` (`app_id`),
  KEY `ix_api_log_version` (`version_id`),
  KEY `ix_api_log_client` (`client_id`),
  KEY `ix_api_log_user` (`user_id`),

  CONSTRAINT `fk_api_log_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_api_log_client` FOREIGN KEY (`client_id`) 
        REFERENCES `app_creds` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_api_log_owner` FOREIGN KEY (`owner_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_api_log_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_api_log_version` FOREIGN KEY (`version_id`) 
        REFERENCES `api_version` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `api_version` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `published_date` datetime(6) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_api_version_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `app` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `logo_id` bigint(20) unsigned DEFAULT NULL,
  `logo_url_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `app_url` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_url` varchar(255) DEFAULT NULL,
  `privacy_url` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` datetime(6) NOT NULL,
  `retired_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_app_name` (`name`),
  UNIQUE KEY `ux_app_uid` (`uid`),
  KEY `ix_app_type` (`type_id`),
  KEY `ix_app_user` (`user_id`),
  KEY `ix_app_logo` (`logo_id`),

  CONSTRAINT `fk_app_logo` FOREIGN KEY (`logo_id`) 
        REFERENCES `file` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_app_type` FOREIGN KEY (`type_id`) 
        REFERENCES `app_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,

  CONSTRAINT `fk_app_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `app_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `env` enum('dev','qa','beta','sandbox','prd') DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(4000) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `app_config_env_name` (`app_id`,`env`,`name`),
  KEY `ix_app_config_app` (`app_id`),

  CONSTRAINT `fk_app_config_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `app_creds` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `api_version_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `redirect_uri` varchar(255) DEFAULT NULL,
  `scope` varchar(255) NOT NULL DEFAULT 'read,write',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `access_token_timeout` int(11) DEFAULT NULL,
  `refresh_token_timeout` int(11) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `retired_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_app_creds_name` (`app_id`,`name`),
  UNIQUE KEY `ux_app_creds_client_id` (`client_id`),
  UNIQUE KEY `ux_app_creds_client_secret` (`client_secret`),
  KEY `ix_app_creds_api_version` (`api_version_id`),

  CONSTRAINT `fk_app_creds_api_version` FOREIGN KEY (`api_version_id`) 
        REFERENCES `api_version` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_app_creds_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `push_notification_device` (
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
  UNIQUE KEY `ux_push_notification` (`app_id`,`user_id`,`device_uuid`,`sandbox`), 

  UNIQUE KEY `ux_push_notification_device_push_token` (`push_token`(255)),

  KEY `ix_push_notification_device_app` (`app_id`),
  KEY `ix_push_notification_device_user` (`user_id`),
  KEY `ix_push_notification_device_device` (`device_uuid`),

  CONSTRAINT `fk_push_notification_device_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_push_notification_device_push_notification` FOREIGN KEY (`push_notification_id`) 
        REFERENCES `push_notification` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_push_notification_device_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `push_notification` (
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
  UNIQUE KEY `ux_push_notification` (`app_id`,`platform_name`,`sandbox`),

  CONSTRAINT `fk_push_notification_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `push_notification_message` (
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
  UNIQUE KEY `ux_push_notification_message_uid` (`uid`),
  KEY `ix_push_notification_message_app` (`app_id`),

  CONSTRAINT `fk_push_notification_message_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `app_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_app_type_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `app_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `token_id` bigint(20) unsigned DEFAULT NULL,
  `app_user_id` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `revoked_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_app_user_app_user` (`app_id`,`user_id`),
  KEY `ix_user_app_user` (`user_id`),
  KEY `ix_user_app_token` (`token_id`),

  CONSTRAINT `fk_app_user_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_app_user_token` FOREIGN KEY (`token_id`) 
        REFERENCES `token` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_app_user_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `app_webhook` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `events` varchar(2000) DEFAULT NULL,
  `url` varchar(2000) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_app_webhook_uid` (`uid`),
  UNIQUE KEY `ux_app_webhook_app_name` (`app_id`,`name`),
  KEY `ix_app_webhook_app` (`app_id`),
  CONSTRAINT `fk_app_webhook_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `auth_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) unsigned NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `code` varchar(255) NOT NULL,
  `valid` tinyint(1) NOT NULL DEFAULT '0',
  `use_count` int(11) unsigned NOT NULL DEFAULT '0',
  `max_use_count` int(11) unsigned NOT NULL DEFAULT '0',
  `expiration_date` datetime(6) DEFAULT NULL,
  `last_accessed_date` datetime(6) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_auth_code_code` (`code`),
  KEY `ix_auth_code_type` (`type_id`),
  KEY `ix_auth_code_app` (`app_id`),
  KEY `ix_auth_code_user` (`user_id`),
  CONSTRAINT `fk_auth_code_type` FOREIGN KEY (`type_id`) 
        REFERENCES `auth_code_type` (`id`),
  CONSTRAINT `fk_auth_code_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`),
  CONSTRAINT `fk_auth_code_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------


--
-- Table structure for table `file`
--

CREATE TABLE `file` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type_id` bigint(20) unsigned DEFAULT NULL,
  `access_id` bigint(20) unsigned DEFAULT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `token_id` bigint(20) unsigned DEFAULT NULL,
  `thumb_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `content_type` varchar(255) NOT NULL,
  `size` bigint(20) unsigned NOT NULL,
  `folder` tinyint(1) NOT NULL DEFAULT '0',
  `shared` tinyint(1) NOT NULL DEFAULT '0',
  `archive` tinyint(1) NOT NULL DEFAULT '0',
  `compressed` tinyint(1) NOT NULL DEFAULT '0',
  `hidden` tinyint(1) NOT NULL DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `bits_per_pixel` int(11) DEFAULT NULL,
  `frames_per_second` int(11) DEFAULT NULL,
  `duration` bigint(20) unsigned DEFAULT NULL,
  `src_hostname` varchar(255) DEFAULT NULL,
  `src_filename` varchar(255) DEFAULT NULL,
  `local_path` varchar(255) DEFAULT NULL,
  `url_path` varchar(255) DEFAULT NULL,
  `thumb_url_path` varchar(255) DEFAULT NULL,
  `upload_time` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `retired_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_file_uid` (`uid`),
  KEY `ix_file_type` (`type_id`),
  KEY `ix_file_parent` (`parent_id`),
  KEY `ix_file_user` (`user_id`),
  KEY `ix_file_token` (`token_id`),
  KEY `ix_file_thumb` (`thumb_id`),
  KEY `ix_file_access` (`access_id`),
  KEY `ix_file_url_path` (`url_path`),
  CONSTRAINT `fk_file_access` FOREIGN KEY (`access_id`) 
        REFERENCES `file_access` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_file_parent` FOREIGN KEY (`parent_id`) 
        REFERENCES `file` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_file_thumb` FOREIGN KEY (`thumb_id`) 
        REFERENCES `file` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_file_token` FOREIGN KEY (`token_id`) 
        REFERENCES `token` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_file_type` FOREIGN KEY (`type_id`) 
        REFERENCES `file_type` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_file_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `file_access`
--

CREATE TABLE `file_access` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_file_access_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `file_type`
--

CREATE TABLE `file_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_file_type_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



--
-- Table structure for table `redirect`
--

CREATE TABLE `redirect` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `short_url_id` bigint(20) unsigned NOT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `request_url` varchar(2000) NOT NULL,
  `redirect_url` varchar(2000) NOT NULL,
  `hostname` varchar(255) NOT NULL,
  `browser` varchar(512) NOT NULL,
  `referer` varchar(2000) DEFAULT NULL,
  `locale` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `latitiude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `request_date` datetime(6) NOT NULL,
  `redirect_date` datetime(6) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  KEY `ix_redirect_short_url` (`short_url_id`),
  KEY `ix_redirect_promo` (`promo_id`),
  CONSTRAINT `fk_redirect_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `promo` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_redirect_short_url` FOREIGN KEY (`short_url_id`) 
        REFERENCES `short_url` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `referred_by_id` bigint(20) unsigned DEFAULT NULL,
  `client_id` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `hostname` varchar(255) default NULL,
  `browser` varchar(512) DEFAULT NULL,
  `device_name` varchar(255) DEFAULT NULL,
  `device_uuid` varchar(255) DEFAULT NULL,
  `platform_name` varchar(255) DEFAULT NULL,
  `platform_version` varchar(255) DEFAULT NULL,
  `signup_time` int(10) unsigned DEFAULT NULL,
  `email_verified` tinyint(1) NOT NULL DEFAULT '0',
  `email_pending` tinyint(1) NOT NULL DEFAULT '0',
  `mobile_verified` tinyint(1) NOT NULL DEFAULT '0',
  `mobile_pending` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `reminded_date` datetime(6) DEFAULT NULL,
  `registered_date` datetime(6) DEFAULT NULL,
  `deactivated_date` datetime(6) DEFAULT NULL,
  `deleted_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `ix_registration_app` (`app_id`),
  KEY `ix_registration_user` (`user_id`),
  KEY `ix_registration_campaign` (`campaign_id`),
  KEY `ix_registration_partner` (`partner_id`),
  KEY `ix_registration_promo` (`promo_id`),
  KEY `ix_registration_referred_by` (`referred_by_id`),
  CONSTRAINT `fk_registration_referred_by` FOREIGN KEY (`referred_by_id`) 
        REFERENCES `user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_registration_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`),
  CONSTRAINT `fk_registration_campaign` FOREIGN KEY (`campaign_id`) 
        REFERENCES `campaign` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_registration_partner` FOREIGN KEY (`partner_id`) 
        REFERENCES `partner` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_registration_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `promo` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_registration_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `remote_service`
--

CREATE TABLE `remote_service` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_remote_service_uid` (`uid`),
  UNIQUE KEY `ux_remote_service_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `remote_service_app_creds`
--

CREATE TABLE `remote_service_app_creds` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `remote_service_id` bigint(20) unsigned NOT NULL,
  `authorize_uri` varchar(1024) DEFAULT NULL,
  `token_uri` varchar(1024) DEFAULT NULL,
  `scope` varchar(1024) DEFAULT NULL,
  `client_key` varchar(1024) DEFAULT NULL,
  `client_secret` varchar(1024) DEFAULT NULL,
  `redirect_uri` varchar(1024) DEFAULT NULL,
  `namespace` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_remote_service_app_creds_app_remote_service` (`app_id`,`remote_service_id`),
  KEY `ix_remote_service_app_creds_remote_service` (`remote_service_id`),

  CONSTRAINT `fk_remote_service_app_creds_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_remote_service_app_creds_remote_service` FOREIGN KEY (`remote_service_id`) 
        REFERENCES `remote_service` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `remote_service_user_creds`
--

CREATE TABLE `remote_service_user_creds` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `remote_service_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `remote_service_user_id` varchar(255) DEFAULT NULL,
  `remote_service_screen_name` varchar(255) DEFAULT NULL,
  `auth_type` enum('oauth','oauth2','credentials') DEFAULT NULL,
  `access_token` varchar(1024) DEFAULT NULL,
  `refresh_token` varchar(1024) DEFAULT NULL,
  `token_secret` varchar(1024) DEFAULT NULL,
  `expire_date` datetime(6) DEFAULT NULL,
  `connected_date` datetime(6) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_remote_service_user_creds_app_account_name` (`app_id`,`account_id`,`name`),
  KEY `ix_remote_service_user_creds_account` (`account_id`),
  KEY `ix_remote_service_user_creds_user` (`user_id`),
  KEY `ix_remote_service_user_creds_remote_service` (`remote_service_id`),
  KEY `ix_remote_service_user_creds_remote_service_user` (`remote_service_user_id`),
  KEY `ix_remote_service_user_creds_remote_service_screen_name` (`remote_service_screen_name`),
  CONSTRAINT `fk_remote_service_user_creds_account` FOREIGN KEY (`account_id`) 
        REFERENCES `account` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_remote_service_user_creds_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_remote_service_user_creds_remote_service` FOREIGN KEY (`remote_service_id`) 
        REFERENCES `remote_service` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_remote_service_user_creds_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `setting`
--

CREATE TABLE `setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(4000) NOT NULL,
  `overwrite_global` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_setting_user_name` (`user_id`,`name`),
  KEY `ix_setting_user` (`user_id`),
  KEY `ix_setting_name` (`name`),
  CONSTRAINT `fk_setting_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `short_url`
--

CREATE TABLE `short_url` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `domain` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `short_url` varchar(255) NOT NULL,
  `long_url` varchar(2000) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `script` tinyint(1) NOT NULL DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `expired_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_short_url_short_url` (`short_url`),
  UNIQUE KEY `ux_short_url_path` (`path`),
  KEY `ix_short_url_app` (`app_id`),
  KEY `ix_short_url_user` (`user_id`),
  KEY `ix_short_url_promo` (`promo_id`),
  KEY `ix_short_url_long_url` (`long_url`(255)),
  KEY `ix_short_url_partner` (`partner_id`),
  CONSTRAINT `fk_short_url_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`),
  CONSTRAINT `fk_short_url_partner` FOREIGN KEY (`partner_id`) 
        REFERENCES `partner` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_short_url_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `promo` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_short_url_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `client_id` varchar(255) NOT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authentication` blob,
  `username` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `browser` varchar(512) DEFAULT NULL,
  `app_version` varchar(255) DEFAULT NULL,
  `app_build` varchar(255) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `approved` tinyint(1) NOT NULL DEFAULT '0',
  `access_count` bigint(20) unsigned DEFAULT NULL,
  `login_date` datetime(6) NOT NULL,
  `last_login_date` datetime(6) DEFAULT NULL,
  `logout_date` datetime(6) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `retired_date` datetime(6) DEFAULT NULL,
  `access_expiration_date` datetime(6) DEFAULT NULL,
  `refresh_expiration_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_token_access_token` (`access_token`),
  UNIQUE KEY `ux_token_refresh_token` (`refresh_token`),
  KEY `ix_token_client` (`client_id`),
  KEY `ix_token_app` (`app_id`),
  KEY `ix_token_user` (`user_id`),
  KEY `ix_token_type` (`type_id`),
  KEY `ix_token_hostname` (`hostname`),
  CONSTRAINT `fk_token_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`),
  CONSTRAINT `fk_token_client` FOREIGN KEY (`client_id`) 
        REFERENCES `app_creds` (`client_id`),
  CONSTRAINT `fk_token_type` FOREIGN KEY (`type_id`) 
        REFERENCES `token_type` (`id`),
  CONSTRAINT `fk_token_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


--
-- Table structure for table `token_type`
--

CREATE TABLE `token_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_token_type_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `type_id` bigint(20) unsigned DEFAULT NULL,
  `roles` set('SYSTEM','ADMIN','USER', 'GUEST') NOT NULL DEFAULT 'GUEST',
  `account_id` bigint(20) unsigned NOT NULL,
  `status_id` bigint(20) unsigned DEFAULT NULL,
  `presence_id` bigint(20) unsigned DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `locale` varchar(255) DEFAULT NULL,
  `time_zone` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `floor` int(11) unsigned DEFAULT NULL,
  `coords` geometry DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `retired_date` datetime(6) DEFAULT NULL,
  `login_date` datetime(6) DEFAULT NULL,
  `last_login_date` datetime(6) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_username` (`username`),
  UNIQUE KEY `ux_user_uid` (`uid`),
  UNIQUE KEY `ux_user_email` (`email`),
  UNIQUE KEY `ux_user_mobile_number` (`mobile_number`),
  KEY `ix_user_parent` (`parent_id`),
  KEY `ix_user_type` (`type_id`),
  KEY `ix_user_status` (`status_id`),
  KEY `ix_user_account` (`account_id`),
  KEY `ix_user_coords` (`coords`(255)),
  KEY `ix_user_presence` (`presence_id`),
  CONSTRAINT `fk_user_account` FOREIGN KEY (`account_id`) 
        REFERENCES `account` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_parent` FOREIGN KEY (`parent_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_status` FOREIGN KEY (`status_id`) 
        REFERENCES `user_status` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_user_presence` FOREIGN KEY (`presence_id`) 
        REFERENCES `user_presence` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_user_type` FOREIGN KEY (`type_id`) 
        REFERENCES `user_type` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


--
-- Table structure for table `user_auth`
--

CREATE TABLE `user_auth` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `password` varchar(255) NOT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `question1` varchar(255) DEFAULT NULL,
  `answer1` varchar(255) DEFAULT NULL,
  `question2` varchar(255) DEFAULT NULL,
  `answer2` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_user_auth_user` (`user_id`),
  CONSTRAINT `fk_user_auth_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `user_media`
--

CREATE TABLE `user_media` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `file_id` bigint(20) unsigned DEFAULT NULL,
  `file_type_id` bigint(20) unsigned DEFAULT NULL,
  `url_path` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `floor` int(11) unsigned DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `primary_photo` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_media_uid` (`uid`),
  UNIQUE KEY `ux_user_media_file` (`file_id`),
  KEY `ix_user_media_user` (`user_id`),
  CONSTRAINT `fk_user_media_file` FOREIGN KEY (`file_id`) 
        REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_media_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `user_presence`
--

CREATE TABLE `user_presence` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_presence_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_role_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `user_status`
--

CREATE TABLE `user_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_status_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `user_type`
--

CREATE TABLE `user_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_type_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


--
-- Table structure for table `auth_code_type`
--
CREATE TABLE `auth_code_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL, 
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_auth_code_type_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


--
-- Table structure for table `entity_name_rule`
--

CREATE TABLE `entity_name_rule` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pattern` varchar(255) DEFAULT NULL,
  `black_listed` tinyint(1) NOT NULL DEFAULT '0',
  `reserved` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_entity_name_rule` (`pattern`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE `position` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `sample_no` bigint(20) unsigned NOT NULL,
  `network` varchar(255) DEFAULT NULL,
  `battery` int(11) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `floor` int(11) unsigned DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `accuracy` double DEFAULT NULL,
  `altitude_accuracy` double DEFAULT NULL,
  `heading` double DEFAULT NULL,
  `speed` double DEFAULT NULL,
  `timestamp` bigint(20) unsigned DEFAULT NULL,
  `error_code` varchar(255) DEFAULT NULL,
  `error_msg` varchar(255) DEFAULT NULL,
  `error_date` datetime DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `ix_position_app` (`app_id`),
  KEY `ix_position_user` (`user_id`),
  CONSTRAINT `fk_position_app` FOREIGN KEY (`app_id`) 
        REFERENCES `app` (`id`),
  CONSTRAINT `fk_position_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `email` (
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
  UNIQUE KEY `ux_email_uid` (`uid`),
  UNIQUE KEY `ix_email_ses_id` (`ses_id`),
  UNIQUE KEY `ix_email_campaign_channel_to` (`campaign_id`,`campaign_channel_id`,`to_address_id`),
  KEY `ix_email_campaign` (`campaign_id`),
  KEY `ix_email_group` (`group_id`),
  KEY `ix_email_to_address` (`to_address_id`),
  KEY `ix_email_campaign_channel` (`campaign_channel_id`),
  CONSTRAINT `fk_email_campaign` FOREIGN KEY (`campaign_id`) 
        REFERENCES `campaign` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_email_campaign_channel` FOREIGN KEY (`campaign_channel_id`) 
        REFERENCES `campaign_channel` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_email_group` FOREIGN KEY (`group_id`) 
        REFERENCES `email_group` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_email_to_address` FOREIGN KEY (`to_address_id`) 
        REFERENCES `email_address` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `email_address` (
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
  UNIQUE KEY `ux_email_address_uid` (`uid`),
  UNIQUE KEY `ux_email_address_email` (`email`),
  KEY `ix_email_address_user` (`user_id`),
  CONSTRAINT `fk_email_address_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `email_event` (
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
  KEY `ix_email_event_type` (`type_id`),
  KEY `ix_email_event_email` (`email_id`),
  CONSTRAINT `fk_email_event_email` FOREIGN KEY (`email_id`) 
        REFERENCES `email` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_email_event_type` FOREIGN KEY (`type_id`) 
        REFERENCES `email_event_type` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `email_event_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `display_name` varchar(128) DEFAULT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE `email_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_email_group_uid` (`uid`),
  UNIQUE KEY `ux_email_group_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE `email_group_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) unsigned NOT NULL,
  `address_id` bigint(20) unsigned NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_email_group_address_group_address` (`group_id`,`address_id`),
  KEY `fk_email_group_address_address` (`address_id`),

  CONSTRAINT `fk_email_group_address_address` FOREIGN KEY (`address_id`) 
        REFERENCES `email_address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_email_group_address_group` FOREIGN KEY (`group_id`) 
        REFERENCES `email_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;




CREATE TABLE `friendship` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `friend_id` bigint(20) unsigned DEFAULT NULL,
  `circle_id` bigint(20) unsigned DEFAULT NULL,
  `status_id` bigint(20) unsigned NOT NULL,
  `friendship_requested` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_friendship_uid` (`uid`),
  UNIQUE KEY `ux_friendship_user_friend` (`user_id`,`friend_id`),
  KEY `ix_friendship_user` (`user_id`),
  KEY `ix_friendship_friend` (`friend_id`),
  KEY `ix_friendship_circle` (`circle_id`),
  KEY `ix_friendship_status` (`status_id`),

  CONSTRAINT `fk_friendship_friend` FOREIGN KEY (`friend_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_friendship_status` FOREIGN KEY (`status_id`) 
        REFERENCES `friendship_status` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_friendship_circle` FOREIGN KEY (`circle_id`) 
        REFERENCES `friendship_circle` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_friendship_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;




CREATE TABLE `friendship_event` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) unsigned NOT NULL,
  `friendship_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `friend_id` bigint(20) unsigned DEFAULT NULL,
  `event` varchar(2000) DEFAULT NULL,
  `event_date` datetime(6) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `ix_friendship_event_type` (`type_id`),
  KEY `ix_friendship_event_friendship` (`friendship_id`),
  KEY `ix_friendship_event_user` (`user_id`),
  KEY `ix_friendship_event_friend` (`friend_id`),
  CONSTRAINT `fk_friendship_event_friend` FOREIGN KEY (`friend_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_friendship_event_friendship` FOREIGN KEY (`friendship_id`) 
        REFERENCES `friendship` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_friendship_event_type` FOREIGN KEY (`type_id`) 
        REFERENCES `friendship_event_type` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_friendship_event_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `friendship_event_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_friendship_event_type_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE `friendship_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_friendship_status_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE `friendship_circle` (
      `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
      `uid` varchar(255) NOT NULL,
      `user_id` bigint(20) unsigned NOT NULL,
      `name` varchar(255) NOT NULL,
      `display_name` varchar(255) NOT NULL,
      `default_circle` tinyint(1) NOT NULL DEFAULT '0',
      `created_date` datetime(6) NOT NULL,
      `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
      PRIMARY KEY (`id`),
      UNIQUE KEY `id` (`id`),
      UNIQUE KEY `ux_circle_uid` (`uid`),
      UNIQUE KEY `ux_circle_name` (`user_id`,`name`),
      CONSTRAINT `fk_circle_user` FOREIGN KEY (`user_id`) 
            REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `address_book` (
      `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
      `user_id` bigint(20) unsigned NOT NULL,
      `ref_user_id` bigint(20) unsigned DEFAULT NULL,
      `photo_id` bigint(20) unsigned DEFAULT NULL,
      `photo_url_path` varchar(255) DEFAULT NULL,
      `first_name` varchar(255) DEFAULT NULL,
      `last_name` varchar(255) DEFAULT NULL,
      `display_name` varchar(255) DEFAULT NULL,
      `address` varchar(255) DEFAULT NULL,
      `city` varchar(255) DEFAULT NULL,
      `state` varchar(255) DEFAULT NULL,
      `postal_code` varchar(255) DEFAULT NULL,
      `country` varchar(255) DEFAULT NULL,
      `email` varchar(255) DEFAULT NULL,
      `mobile_number` varchar(255) DEFAULT NULL,
      `twitter_id` varchar(255) DEFAULT NULL,
      `twitter_handle` varchar(255) DEFAULT NULL,
      `facebook_id` varchar(255) DEFAULT NULL,
      `facebook_username` varchar(255) DEFAULT NULL,
      `email_verified` tinyint(1) NOT NULL DEFAULT '0',
      `mobile_verified` tinyint(1) NOT NULL DEFAULT '0',
      `invited_date` datetime(6) DEFAULT NULL,
      `registered_date` datetime(6) DEFAULT NULL,
      `created_date` datetime(6) NOT NULL,
      `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
      PRIMARY KEY (`id`),
      UNIQUE KEY `id` (`id`),
      KEY `ix_address_book_user` (`user_id`),
      KEY `ix_address_book_ref_user` (`ref_user_id`),
      KEY `ix_address_book_photo` (`photo_id`),
      CONSTRAINT `fk_address_book_photo` FOREIGN KEY (`photo_id`) 
            REFERENCES `file` (`id`) ON DELETE SET NULL,
      CONSTRAINT `fk_address_book_ref_user` FOREIGN KEY (`ref_user_id`) 
            REFERENCES `user` (`id`) ON DELETE SET NULL,
      CONSTRAINT `fk_address_book_user` FOREIGN KEY (`user_id`) 
            REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



CREATE TABLE `app_invitation_channel` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `display_name` varchar(255) DEFAULT NULL,
    `created_date` datetime(6) NOT NULL,
    `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_app_invitation_channel_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE `app_invitation_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_app_invitation_type_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE `app_invitation_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_app_invitation_status_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE `app_invitation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) unsigned NOT NULL,
  `channel_id` bigint(20) unsigned NOT NULL,
  `status_id` bigint(20) unsigned NOT NULL DEFAULT '100',
  `user_id` bigint(20) unsigned NOT NULL,
  `address_book_id` bigint(20) unsigned DEFAULT NULL,
  `invitee_user_id` bigint(20) unsigned DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `invitation_code` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `invited_count` int(11) DEFAULT NULL,
  `invited_date` datetime(6) DEFAULT NULL,
  `viewed_date` datetime(6) DEFAULT NULL,
  `ignored_date` datetime(6) DEFAULT NULL,
  `accepted_date` datetime(6) DEFAULT NULL,
  `registered_date` datetime(6) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_updated` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_app_invitation_code` (`invitation_code`),
  KEY `ix_app_invitation_email` (`email`),
  KEY `ix_app_invitation_mobile_number` (`mobile_number`),
  KEY `ix_app_invitation_type` (`type_id`),
  KEY `ix_app_invitation_channel` (`channel_id`),
  KEY `ix_app_invitation_status` (`status_id`),
  KEY `ix_app_invitation_user` (`user_id`),
  KEY `ix_app_invitation_address_book` (`address_book_id`),
  KEY `ix_app_invitation_invitee_user` (`invitee_user_id`),
  CONSTRAINT `fk_app_invitation_address_book` FOREIGN KEY (`address_book_id`) 
        REFERENCES `address_book` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_app_invitation_channel` FOREIGN KEY (`channel_id`) 
        REFERENCES `app_invitation_channel` (`id`),
  CONSTRAINT `fk_app_invitation_invitee_user` FOREIGN KEY (`invitee_user_id`) 
        REFERENCES `user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_app_invitation_status` FOREIGN KEY (`status_id`) 
        REFERENCES `app_invitation_status` (`id`),
  CONSTRAINT `fk_app_invitation_type` FOREIGN KEY (`type_id`) 
        REFERENCES `app_invitation_type` (`id`),
  CONSTRAINT `fk_app_invitation_user` FOREIGN KEY (`user_id`) 
        REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-12  8:05:10
