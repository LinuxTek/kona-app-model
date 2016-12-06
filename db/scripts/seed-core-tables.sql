-- --------------------------------------------------------------------------

INSERT INTO `core__app_type` 
VALUES 
    (100,'INTERNAL','Internal',now(), now()),
    (200,'PARTNER','Partner',now(), now()),
    (300,'PUBLIC','Public',now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `core__file_access` 
VALUES 
    (100,'SYSTEM','System', now(), now()),
    (200,'OWNER','Owner',now(), now()),
    (300,'FRIEND','Friend',now(), now()),
    (400,'APP','App',now(),now()),
    (500,'PUBLIC','Public',now(),now()),
    (999,'NONE','Not Accessible',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `core__file_type` 
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
INSERT INTO `core__token_type` 
VALUES 
    (100,'BASIC','Basic',now(),now()),
    (200,'BEARER','Bearer',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `core__user_presence` 
VALUES 
    (100,'ONLINE','Online',now(),now()),
    (200,'AWAY','Away',now(),now()),
    (300,'BUSY','Busy',now(),now()),
    (400,'INVISIBLE','Invisible',now(),now()),
    (999,'OFFLINE','Offline',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `core__user_role` 
VALUES 
    (1,'SYSTEM','System',now(),now()),
    (2,'ADMIN','Admin',now(),now()),
    (4,'USER','User',now(),now()),
    (8,'GUEST','Guest',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `core__user_status` 
VALUES 
    (100,'ENABLED','Enabled',now(),now()),
    (200,'LOCKED','Locked',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `core__user_type` 
VALUES 
    (100,'SYSTEM','System',now(),now()), -- system user
    (200,'USER','User',now(),now()), -- regular user
    (300,'TEST','Test',now(),now()); -- test user

-- --------------------------------------------------------------------------

INSERT INTO `core__auth_code_type` 
VALUES 
    (100,'EMAIL_CONFIRMATION','Email Confirmation',now(),now()), 
    (200,'MOBILE_CONFIRMATION','Mobile Confirmation',now(),now()), 
    (300,'PHONE_CONFIRMATION','Phone Confirmation',now(),now()), 
    (400,'PASSWORD_RESET','Password Reset',now(),now()); 

-- --------------------------------------------------------------------------

