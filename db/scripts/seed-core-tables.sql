-- --------------------------------------------------------------------------

INSERT INTO `app_type` 
VALUES 
    (100,'INTERNAL','Internal',now(), now()),
    (200,'PARTNER','Partner',now(), now()),
    (300,'PUBLIC','Public',now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `file_access` 
VALUES 
    (100,'SYSTEM','System', now(), now()),
    (200,'OWNER','Owner',now(), now()),
    (300,'FRIEND','Friend',now(), now()),
    (400,'APP','App',now(),now()),
    (500,'PUBLIC','Public',now(),now()),
    (999,'NONE','Not Accessible',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `file_type` 
VALUES 
    (100,'FOLDER','Folder',now(),now()),
    (200,'THUMBNAIL','Thumbnail',now(),now()),
    (300,'IMAGE','Image',now(),now()),
    (400,'AUDIO','Audio',now(),now()),
    (500,'VIDEO','Video',now(),now()),
    (600,'DOCUMENT','Document',now(),now()),
    (700,'ARCHIVE','Archive',now(),now()),
    (800,'EXECUTABLE','Executable',now(),now()),
    (999,'OTHER','Other',now(),now());

-- --------------------------------------------------------------------------

--  BASIC - issued to an app (client) to identify it and allow access to public resources
--  BEARER - issued to an app (client) on behalf of a user to access user specific resources
INSERT INTO `token_type` 
VALUES 
    (100,'BASIC','Basic',now(),now()),
    (200,'BEARER','Bearer',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `user_presence` 
VALUES 
    (100,'ONLINE','Online',now(),now()),
    (200,'AWAY','Away',now(),now()),
    (300,'BUSY','Busy',now(),now()),
    (400,'INVISIBLE','Invisible',now(),now()),
    (999,'OFFLINE','Offline',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `user_role` 
VALUES 
    (1,'SYSTEM','System',now(),now()),
    (2,'ADMIN','Admin',now(),now()),
    (4,'USER','User',now(),now()),
    (8,'GUEST','Guest',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `user_status` 
VALUES 
    (100,'ENABLED','Enabled',now(),now()),
    (200,'LOCKED','Locked',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `user_type` 
VALUES 
    (100,'SYSTEM','System',now(),now()), -- system user
    (200,'USER','User',now(),now()), -- regular user
    (300,'TEST','Test',now(),now()); -- test user

-- --------------------------------------------------------------------------

INSERT INTO `auth_code_type` 
VALUES 
    (100,'EMAIL_CONFIRMATION','Email Confirmation',now(),now()), 
    (200,'MOBILE_CONFIRMATION','Mobile Confirmation',now(),now()), 
    (300,'PHONE_CONFIRMATION','Phone Confirmation',now(),now()), 
    (400,'PASSWORD_RESET','Password Reset',now(),now()); 

-- --------------------------------------------------------------------------

INSERT INTO `email_event_type`
VALUES
    (100,'ATTEMPTED','Attempted',now()),
    (110,'FAILED', 'Failed', now()),
    (200,'DELIVERED','Delivered',now()),
    (300,'BOUNCED','Bounced',now()),
    (400,'COMPLAINED','Complained',now()),
    (500,'UNSUBSCRIBED','Unsubscribed',now()),
    (600,'OPENED','Opened',now()),
    (700,'FORWARDED','Forwarded',now()),
    (800,'PRINTED','Printed',now()),
    (900,'CLICKED','Clicked',now());

-- --------------------------------------------------------------------------

INSERT INTO `invitation_type` 
VALUES 
    (100,'ACCOUNT','Account',now(),now()),
    (200,'FRIEND','Friend',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `invitation_channel` 
VALUES 
    (100,'IN_APP','In App',now(),now()),
    (200,'EMAIL','Email',now(),now()),
    (300,'SMS','Sms',now(),now()),
    (400,'TWITTER','Twitter',now(),now()),
    (500,'FACEBOOK','Facebook',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `invitation_status` 
VALUES 
    (100,'PENDING','Pending',now(),now()),
    (200,'ACCEPTED','Accepted',now(),now()),
    (300,'DECLINED','Declined',now(),now()),
    (400,'IGNORED','Ignored',now(),now());


-- --------------------------------------------------------------------------

INSERT INTO `friendship_status` 
VALUES 
    (100,'NONE','None',now(),now()),
    (200,'FRIENDS','Friends',now(),now()),
    (300,'FOLLOWING','Following',now(),now()),
    (400,'FOLLOWED','Followed',now(),now()),
    (500,'BLOCKING','Blocking',now(),now()),
    (600,'BLOCKED','Blocked',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `friendship_event_type` 
VALUES 
    (100,'FOLLOW','Follow',now(),now()),
    (200,'UNFOLLOW','Unfollow',now(),now()),
    (300,'BLOCK','Block',now(),now()),
    (400,'UNBLOCK','Unblock',now(),now()),
    (500,'FRIENDSHIP_REQUEST','Request Friendship',now(),now()),
    (600,'FRIENDSHIP_ACCEPT','Accept Friendship',now(),now()),
    (700,'FRIENDSHIP_REJECT','Reject Friendship',now(),now()),
    (800,'FRIENDSHIP_REVOKE','Revoke Friendship',now(),now());


