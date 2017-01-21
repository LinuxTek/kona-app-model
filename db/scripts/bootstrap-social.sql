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

CREATE TABLE `social__friendship` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `friend_id` bigint(20) unsigned DEFAULT NULL,
  `circle_id` bigint(20) unsigned DEFAULT NULL,
  `status_id` bigint(20) unsigned NOT NULL,
  `friendship_requested` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_social__friendship_uid` (`uid`),

  UNIQUE KEY `ux_social__friendship_user_friend` (`user_id`,`friend_id`),

  KEY `ix_social__friendship_user` (`user_id`),

  KEY `ix_social__friendship_friend` (`friend_id`),

  KEY `ix_social__friendship_circle` (`circle_id`),

  KEY `ix_social__friendship_status` (`status_id`),

  CONSTRAINT `fk_social__friendship_friend` FOREIGN KEY (`friend_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_social__friendship_status` FOREIGN KEY (`status_id`) 
        REFERENCES `social__friendship_status` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_social__friendship_circle` FOREIGN KEY (`circle_id`) 
        REFERENCES `social__friendship_circle` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_social__friendship_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



-- --------------------------------------------------------------------------

CREATE TABLE `social__friendship_event` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) unsigned NOT NULL,
  `friendship_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `friend_id` bigint(20) unsigned DEFAULT NULL,
  `event` varchar(2000) DEFAULT NULL,
  `event_date` datetime(6) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  KEY `ix_social__friendship_event_type` (`type_id`),

  KEY `ix_social__friendship_event_friendship` (`friendship_id`),

  KEY `ix_social__friendship_event_user` (`user_id`),

  KEY `ix_social__friendship_event_friend` (`friend_id`),

  CONSTRAINT `fk_social__friendship_event_friend` FOREIGN KEY (`friend_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_social__friendship_event_friendship` FOREIGN KEY (`friendship_id`) 
        REFERENCES `social__friendship` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_social__friendship_event_type` FOREIGN KEY (`type_id`) 
        REFERENCES `social__friendship_event_type` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_social__friendship_event_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `social__friendship_event_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_social__friendship_event_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `social__friendship_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_social__friendship_status_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `social__friendship_circle` (
      `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
      `uid` varchar(255) NOT NULL,
      `user_id` bigint(20) unsigned NOT NULL,
      `name` varchar(255) NOT NULL,
      `display_name` varchar(255) NOT NULL,
      `default_circle` tinyint(1) NOT NULL DEFAULT '0',
      `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
      `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

      PRIMARY KEY (`id`),

      UNIQUE KEY `id` (`id`),
    
      UNIQUE KEY `ux_social__friendship_circle_uid` (`uid`),

      UNIQUE KEY `ux_social__friendship_circle_name` (`user_id`,`name`),

      CONSTRAINT `fk_social__circle_user` FOREIGN KEY (`user_id`) 
            REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `social__address_book` (
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
      `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
      `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

      PRIMARY KEY (`id`),

      UNIQUE KEY `id` (`id`),

      KEY `ix_social__address_book_user` (`user_id`),

      KEY `ix_social__address_book_ref_user` (`ref_user_id`),

      KEY `ix_social__address_book_photo` (`photo_id`),

      CONSTRAINT `fk_social__address_book_photo` FOREIGN KEY (`photo_id`) 
            REFERENCES `core__file` (`id`) ON DELETE SET NULL,

      CONSTRAINT `fk_social__address_book_ref_user` FOREIGN KEY (`ref_user_id`) 
            REFERENCES `core__user` (`id`) ON DELETE SET NULL,

      CONSTRAINT `fk_social__address_book_user` FOREIGN KEY (`user_id`) 
            REFERENCES `core__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


-- --------------------------------------------------------------------------

CREATE TABLE `social__invitation_channel` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `display_name` varchar(255) DEFAULT NULL,
    `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),

    UNIQUE KEY `ux_social__invitation_channel_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `social__invitation_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_social__invitation_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `social__invitation_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_social__invitation_status_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------------------------

CREATE TABLE `social__invitation` (
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
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_social__invitation_code` (`invitation_code`),

  KEY `ix_social__invitation_email` (`email`),

  KEY `ix_social__invitation_mobile_number` (`mobile_number`),

  KEY `ix_social__invitation_type` (`type_id`),

  KEY `ix_social__invitation_channel` (`channel_id`),

  KEY `ix_social__invitation_status` (`status_id`),

  KEY `ix_social__invitation_user` (`user_id`),

  KEY `ix_social__invitation_address_book` (`address_book_id`),

  KEY `ix_social__invitation_invitee_user` (`invitee_user_id`),

  CONSTRAINT `fk_social__invitation_address_book` FOREIGN KEY (`address_book_id`) 
        REFERENCES `social__address_book` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_social__invitation_channel` FOREIGN KEY (`channel_id`) 
        REFERENCES `social__invitation_channel` (`id`),

  CONSTRAINT `fk_social__invitation_invitee_user` FOREIGN KEY (`invitee_user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_social__invitation_status` FOREIGN KEY (`status_id`) 
        REFERENCES `social__invitation_status` (`id`),

  CONSTRAINT `fk_social__invitation_type` FOREIGN KEY (`type_id`) 
        REFERENCES `social__invitation_type` (`id`),

  CONSTRAINT `fk_social__invitation_user` FOREIGN KEY (`user_id`) 
        REFERENCES `core__user` (`id`) ON DELETE CASCADE

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

