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

CREATE TABLE `core__account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `stripe_uid` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `verified` tinyint(1) NOT NULL DEFAULT '0',
  `retired_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__account_name` (`name`),

  UNIQUE KEY `ux_core__account_uid` (`uid`),

  UNIQUE KEY `ux_core__account_owner` (`owner_id`),

  CONSTRAINT `fk_core__account_owner` FOREIGN KEY (`owner_id`) 
        REFERENCES `core__user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__api_log` (
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
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__api_log_uid` (`uid`),

  KEY `ix_core__api_log_owner` (`owner_id`),

  KEY `ix_core__api_log_app` (`app_id`),

  KEY `ix_core__api_log_version` (`version_id`),

  KEY `ix_core__api_log_client` (`client_id`),

  KEY `ix_core__api_log_user` (`user_id`),

  CONSTRAINT `fk_core__api_log_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_core__api_log_client` FOREIGN KEY (`client_id`) 
        REFERENCES `core__app_creds` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_core__api_log_owner` FOREIGN KEY (`owner_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_core__api_log_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_core__api_log_version` FOREIGN KEY (`version_id`) 
        REFERENCES `core__api_version` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__api_version` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `published_date` datetime(6) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__api_version_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__app` (
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
  `retired_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__app_name` (`name`),

  UNIQUE KEY `ux_core__app_uid` (`uid`),

  KEY `ix_core__app_type` (`type_id`),

  KEY `ix_core__app_user` (`user_id`),

  KEY `ix_core__app_logo` (`logo_id`),

  CONSTRAINT `fk_core__app_logo` FOREIGN KEY (`logo_id`) 
        REFERENCES `core__file` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_core__app_type` FOREIGN KEY (`type_id`) 
        REFERENCES `core__app_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,

  CONSTRAINT `fk_core__app_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__app_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `env` enum('dev','qa','beta','sandbox','prd') DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(4000) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `core__app_config_env_name` (`app_id`,`env`,`name`),

  KEY `ix_core__app_config_app` (`app_id`),

  CONSTRAINT `fk_core__app_config_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__app_creds` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `api_version_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `redirect_uri` varchar(255) DEFAULT NULL,
  `scope` varchar(255) NOT NULL DEFAULT 'read,write',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `access_token_timeout` int(11) unsigned DEFAULT NULL, -- seconds / needs to be integer
  `refresh_token_timeout` int(11) unsigned DEFAULT NULL, -- second / needs to be integer
  `retired_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__app_creds_name` (`app_id`,`name`),

  UNIQUE KEY `ux_core__app_creds_client_id` (`client_id`),

  UNIQUE KEY `ux_core__app_creds_client_secret` (`client_secret`),

  KEY `ix_core__app_creds_api_version` (`api_version_id`),

  CONSTRAINT `fk_core__app_creds_api_version` FOREIGN KEY (`api_version_id`) 
        REFERENCES `core__api_version` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_core__app_creds_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__app_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__app_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__app_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `token_id` bigint(20) unsigned DEFAULT NULL,
  `app_user_id` varchar(255) DEFAULT NULL,
  `revoked_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__app_user_app_user` (`app_id`,`user_id`),

  KEY `ix_core__user_app_user` (`user_id`),

  KEY `ix_core__user_app_token` (`token_id`),

  CONSTRAINT `fk_core__app_user_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_core__app_user_token` FOREIGN KEY (`token_id`) 
        REFERENCES `core__token` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_core__app_user_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__app_webhook` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `events` varchar(2000) DEFAULT NULL,
  `url` varchar(2000) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__app_webhook_uid` (`uid`),

  UNIQUE KEY `ux_core__app_webhook_app_name` (`app_id`,`name`),

  KEY `ix_core__app_webhook_app` (`app_id`),

  CONSTRAINT `fk_core__app_webhook_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__auth_code` (
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
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__auth_code_code` (`code`),

  KEY `ix_core__auth_code_type` (`type_id`),

  KEY `ix_core__auth_code_app` (`app_id`),

  KEY `ix_core__auth_code_user` (`user_id`),

  CONSTRAINT `fk_core__auth_code_type` FOREIGN KEY (`type_id`) 
        REFERENCES `core__auth_code_type` (`id`),

  CONSTRAINT `fk_core__auth_code_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`),

  CONSTRAINT `fk_core__auth_code_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__file` (
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
  `temp_file` tinyint(1) NOT NULL DEFAULT '0',
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
  `retired_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__file_uid` (`uid`),

  KEY `ix_core__file_type` (`type_id`),

  KEY `ix_core__file_parent` (`parent_id`),

  KEY `ix_core__file_user` (`user_id`),

  KEY `ix_core__file_token` (`token_id`),

  KEY `ix_core__file_thumb` (`thumb_id`),

  KEY `ix_core__file_access` (`access_id`),

  KEY `ix_core__file_url_path` (`url_path`),

  CONSTRAINT `fk_core__file_access` FOREIGN KEY (`access_id`) 
        REFERENCES `core__file_access` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_core__file_parent` FOREIGN KEY (`parent_id`) 
        REFERENCES `core__file` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_core__file_thumb` FOREIGN KEY (`thumb_id`) 
        REFERENCES `core__file` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_core__file_token` FOREIGN KEY (`token_id`) 
        REFERENCES `core__token` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_core__file_type` FOREIGN KEY (`type_id`) 
        REFERENCES `core__file_type` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_core__file_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__file_access` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__file_access_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__file_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__file_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__redirect` (
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
  `requested_date` datetime(6) NOT NULL,
  `redirected_date` datetime(6) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  KEY `ix_core__redirect_short_url` (`short_url_id`),

  KEY `ix_core__redirect_promo` (`promo_id`),

  CONSTRAINT `fk_core__redirect_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `sales__promo` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_core__redirect_short_url` FOREIGN KEY (`short_url_id`) 
        REFERENCES `core__short_url` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__registration` (
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
  `reminded_date` datetime(6) DEFAULT NULL,
  `registered_date` datetime(6) DEFAULT NULL,
  `deactivated_date` datetime(6) DEFAULT NULL,
  `deleted_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  KEY `ix_core__registration_app` (`app_id`),

  KEY `ix_core__registration_user` (`user_id`),

  KEY `ix_core__registration_campaign` (`campaign_id`),

  KEY `ix_core__registration_partner` (`partner_id`),

  KEY `ix_core__registration_promo` (`promo_id`),

  KEY `ix_core__registration_referred_by` (`referred_by_id`),

  CONSTRAINT `fk_core__registration_referred_by` FOREIGN KEY (`referred_by_id`) 
        REFERENCES `core__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_core__registration_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`),

  CONSTRAINT `fk_core__registration_campaign` FOREIGN KEY (`campaign_id`) 
        REFERENCES `core__campaign` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_core__registration_partner` FOREIGN KEY (`partner_id`) 
        REFERENCES `core__partner` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_core__registration_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `sales__promo` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_core__registration_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__remote_service` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__remote_service_uid` (`uid`),

  UNIQUE KEY `ux_core__remote_service_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__remote_service_app_creds` (
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
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__remote_service_app_creds_app_remote_service` (`app_id`,`remote_service_id`),

  KEY `ix_core__remote_service_app_creds_remote_service` (`remote_service_id`),

  CONSTRAINT `fk_core__remote_service_app_creds_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_core__remote_service_app_creds_remote_service` FOREIGN KEY (`remote_service_id`) 
        REFERENCES `core__remote_service` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__remote_service_user_creds` (
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
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__remote_service_user_creds_app_account_name` (`app_id`,`account_id`,`name`),

  KEY `ix_core__remote_service_user_creds_account` (`account_id`),

  KEY `ix_core__remote_service_user_creds_user` (`user_id`),

  KEY `ix_core__remote_service_user_creds_remote_service` (`remote_service_id`),

  KEY `ix_core__remote_service_user_creds_remote_service_user` (`remote_service_user_id`),

  KEY `ix_core__remote_service_user_creds_remote_service_screen_name` (`remote_service_screen_name`),

  CONSTRAINT `fk_core__remote_service_user_creds_account` FOREIGN KEY (`account_id`) 
        REFERENCES `core__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_core__remote_service_user_creds_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_core__remote_service_user_creds_remote_service` FOREIGN KEY (`remote_service_id`) 
        REFERENCES `core__remote_service` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_core__remote_service_user_creds_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(4000) NOT NULL,
  `overwrite_global` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__setting_user_name` (`user_id`,`name`),

  KEY `ix_core__setting_user` (`user_id`),

  KEY `ix_core__setting_name` (`name`),

  CONSTRAINT `fk_core__setting_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__short_url` (
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
  `expired_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__short_url_short_url` (`short_url`),

  UNIQUE KEY `ux_core__short_url_path` (`path`),

  KEY `ix_core__short_url_app` (`app_id`),

  KEY `ix_core__short_url_user` (`user_id`),

  KEY `ix_core__short_url_promo` (`promo_id`),

  KEY `ix_core__short_url_long_url` (`long_url`(255)),

  KEY `ix_core__short_url_partner` (`partner_id`),

  CONSTRAINT `fk_core__short_url_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`),

  CONSTRAINT `fk_core__short_url_partner` FOREIGN KEY (`partner_id`) 
        REFERENCES `sales__partner` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_core__short_url_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `sales__promo` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_core__short_url_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__token` (
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
  `access_expiration_date` datetime(6) DEFAULT NULL,
  `refresh_expiration_date` datetime(6) DEFAULT NULL,
  `retired_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__token_access_token` (`access_token`),

  UNIQUE KEY `ux_core__token_refresh_token` (`refresh_token`),

  KEY `ix_core__token_client` (`client_id`),

  KEY `ix_core__token_app` (`app_id`),

  KEY `ix_core__token_user` (`user_id`),

  KEY `ix_core__token_type` (`type_id`),

  KEY `ix_core__token_hostname` (`hostname`),

  CONSTRAINT `fk_core__token_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`),

  CONSTRAINT `fk_core__token_client` FOREIGN KEY (`client_id`) 
        REFERENCES `core__app_creds` (`client_id`),

  CONSTRAINT `fk_core__token_type` FOREIGN KEY (`type_id`) 
        REFERENCES `core__token_type` (`id`),

  CONSTRAINT `fk_core__token_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__token_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__token_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `type_id` bigint(20) unsigned DEFAULT NULL,
  `roles` set('SYSTEM','ADMIN','USER', 'GUEST') NOT NULL DEFAULT 'GUEST',
  `account_id` bigint(20) unsigned NOT NULL,
  `status_id` bigint(20) unsigned DEFAULT NULL,
  `presence_id` bigint(20) unsigned DEFAULT NULL,
  `photo_id` bigint(20) unsigned DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL, 
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
  `retired_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__user_username` (`username`),

  UNIQUE KEY `ux_core__user_uid` (`uid`),

  UNIQUE KEY `ux_core__user_email` (`email`),

  UNIQUE KEY `ux_core__user_mobile_number` (`mobile_number`),

  KEY `ix_core__user_parent` (`parent_id`),

  KEY `ix_core__user_photo` (`photo_id`),

  KEY `ix_core__user_type` (`type_id`),

  KEY `ix_core__user_status` (`status_id`),

  KEY `ix_core__user_account` (`account_id`),

  KEY `ix_core__user_coords` (`coords`(255)),

  KEY `ix_core__user_presence` (`presence_id`),

  CONSTRAINT `fk_core__user_account` FOREIGN KEY (`account_id`) 
        REFERENCES `core__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_core__user_parent` FOREIGN KEY (`parent_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_core__user_photo` FOREIGN KEY (`photo_id`) 
        REFERENCES `core__user_media` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_core__user_status` FOREIGN KEY (`status_id`) 
        REFERENCES `core__user_status` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_core__user_presence` FOREIGN KEY (`presence_id`) 
        REFERENCES `core__user_presence` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_core__user_type` FOREIGN KEY (`type_id`) 
        REFERENCES `core__user_type` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__user_auth` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `question1` varchar(255) DEFAULT NULL,
  `answer1` varchar(255) DEFAULT NULL,
  `question2` varchar(255) DEFAULT NULL,
  `answer2` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ,
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_core__user_auth_user` (`user_id`),

  CONSTRAINT `fk_core__user_auth_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__user_media` (
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
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__user_media_uid` (`uid`),

  UNIQUE KEY `ux_core__user_media_file` (`file_id`),

  KEY `ix_core__user_media_user` (`user_id`),

  CONSTRAINT `fk_core__user_media_file` FOREIGN KEY (`file_id`) 
        REFERENCES `core__file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_core__user_media_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__user_presence` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__user_presence_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__user_role_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__user_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__user_status_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__user_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__user_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__auth_code_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL, 
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__auth_code_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__entity_name_rule` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pattern` varchar(255) DEFAULT NULL,
  `black_listed` tinyint(1) NOT NULL DEFAULT '0',
  `reserved` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__entity_name_rule` (`pattern`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__position` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `sample_no` bigint(20) unsigned DEFAULT NULL,
  `network` varchar(255) DEFAULT NULL,
  `battery` int(11) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `indoor_floor` int(11) unsigned DEFAULT NULL,
  `indoor_detail` varchar(2000) DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `accuracy` double DEFAULT NULL,
  `altitude_accuracy` double DEFAULT NULL,
  `heading` double DEFAULT NULL,
  `speed` double DEFAULT NULL,
  `timestamp` bigint(20) unsigned DEFAULT NULL,
  `error_code` varchar(255) DEFAULT NULL,
  `error_msg` varchar(255) DEFAULT NULL,
  `error_date` datetime DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  KEY `ix_core__position_app` (`app_id`),

  KEY `ix_core__position_user` (`user_id`),

  CONSTRAINT `fk_core__position_app` FOREIGN KEY (`app_id`) 
        REFERENCES `core__app` (`id`),

  CONSTRAINT `fk_core__position_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



-- --------------------------------------------------------------------------

CREATE TABLE `core__notification_channel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_core__notification_channel_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `core__notification` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `event` text default NULL,
  `event_date` datetime(6) default NULL,
  `last_viewed_date` datetime(6) default NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE `ux_core__notification_uid` (`uid`),

  KEY `ix_core__notification_user` (`user_id`),

  CONSTRAINT `fk_core__notification_user` FOREIGN KEY (`user_id`)
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `core__notification_delivery` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `notification_id` bigint(20) unsigned NOT NULL,
  `channel_id` bigint(20) unsigned NOT NULL,
  `code` varchar(255) NOT NULL,
  `delivered_date` datetime(6) default NULL,
  `viewed_date` datetime(6) default NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE `ux_core__notification_delivery_code` (`code`),

  KEY `ux_core__notification_delivery_notification` (`notification_id`),

  KEY `ix_core__notification_delivery_channel` (`channel_id`),

  CONSTRAINT `fk_core__notification_delivery_channel` FOREIGN KEY (`channel_id`)
        REFERENCES `core__notification_channel` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_core__notification_delivery_notification` FOREIGN KEY (`notification_id`)
        REFERENCES `core__notification` (`id`) ON DELETE CASCADE

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

