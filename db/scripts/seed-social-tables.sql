-- --------------------------------------------------------------------------

INSERT INTO `social__invitation_type` 
VALUES 
    (100,'ACCOUNT','Account',now(),now()),
    (200,'FRIEND','Friend',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `social__invitation_channel` 
VALUES 
    (100,'IN_APP','In App',now(),now()),
    (200,'EMAIL','Email',now(),now()),
    (300,'SMS','Sms',now(),now()),
    (400,'TWITTER','Twitter',now(),now()),
    (500,'FACEBOOK','Facebook',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `social__invitation_status` 
VALUES 
    (100,'PENDING','Pending',now(),now()),
    (200,'ACCEPTED','Accepted',now(),now()),
    (300,'DECLINED','Declined',now(),now()),
    (400,'IGNORED','Ignored',now(),now());


-- --------------------------------------------------------------------------

INSERT INTO `social__friendship_status` 
VALUES 
    (100,'NONE','None',now(),now()),
    (200,'FRIENDS','Friends',now(),now()),
    (300,'PENDING','Pending',now(),now()),
    (400,'FOLLOWING','Following',now(),now()),
    (500,'FOLLOWED','Followed',now(),now()),
    (600,'BLOCKING','Blocking',now(),now()),
    (700,'BLOCKED','Blocked',now(),now());

-- --------------------------------------------------------------------------

INSERT INTO `social__friendship_event_type` 
VALUES 
    (100,'FOLLOW','Follow',now(),now()),
    (200,'UNFOLLOW','Unfollow',now(),now()),
    (300,'BLOCK','Block',now(),now()),
    (400,'UNBLOCK','Unblock',now(),now()),
    (500,'FRIENDSHIP_REQUEST','Request Friendship',now(),now()),
    (600,'FRIENDSHIP_ACCEPT','Accept Friendship',now(),now()),
    (700,'FRIENDSHIP_REJECT','Reject Friendship',now(),now()),
    (800,'FRIENDSHIP_REVOKE','Revoke Friendship',now(),now());


