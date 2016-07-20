set @accountId = 1;
set @accountName = 'system';
set @accountDisplayName = 'System';


-- ----------------------- 
-- Create account record
-- ----------------------- 
insert into account(
    id,
    uid,
    owner_id,
    name,
    display_name,
    enabled,
    active,
    verified,
    created_date
) values (
    @accountId,
    replace(uuid(),'-',''),
    null,
    @accountName,
    @accountDisplayName,
    1,
    1,
    1,
    now()
);


-- ----------------------- 
-- Create System user
-- This is strictly an internal account that's used to run
-- background jobs, etc. and has no user_auth record.
-- ----------------------- 
set @userId = 1;
set @username = 'system';
set @firstName = 'System';
set @lastName = 'User';
set @displayName = 'System';

set @typeId = 100; -- SYSTEM Type
set @roles = 1; -- SYSTEM Role
set @statusId = 100; -- ENABLED


insert into user(
    id, 
    uid, 
    type_id, 
    roles,
    account_id, 
    status_id, 
    username, 
    first_name, 
    last_name, 
    display_name,
    active, 
    created_date
) values (
    @userId, 
    replace(uuid(),'-',''),
    @typeId, 
    @roles, 
    @accountId, 
    @statusId, 
    @username, 
    @firstName, 
    @lastName, 
    @displayName, 
    1, 
    now()
);
