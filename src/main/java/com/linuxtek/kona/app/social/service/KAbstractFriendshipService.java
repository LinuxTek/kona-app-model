/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.social.entity.KFriendship;
import com.linuxtek.kona.app.social.entity.KFriendshipEvent;
import com.linuxtek.kona.app.social.entity.KFriendshipEventType;
import com.linuxtek.kona.app.social.entity.KFriendshipStatus;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractFriendshipService<FRIENDSHIP extends KFriendship, 
										         FRIENDSHIP_EXAMPLE,
										         FRIENDSHIP_EVENT extends KFriendshipEvent>
		extends KAbstractService<FRIENDSHIP,FRIENDSHIP_EXAMPLE>
		implements KFriendshipService<FRIENDSHIP> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractFriendshipService.class);

	// ----------------------------------------------------------------------------

	protected abstract FRIENDSHIP getNewFriendshipObject();
	
	protected abstract FRIENDSHIP_EVENT getNewFriendshipEventObject();

	protected abstract <S extends KFriendshipEventService<FRIENDSHIP_EVENT>> S getFriendshipEventService();
	
	protected abstract void notifyEvent(FRIENDSHIP friendship, KFriendshipEvent event);

	// ----------------------------------------------------------------------------

	@Override
	public void validate(FRIENDSHIP friendship) {
		if (friendship.getCreatedDate() == null) {
			friendship.setCreatedDate(new Date());
		}
		
		friendship.setUpdatedDate(new Date());
		
		if (friendship.getUid() == null) {
			friendship.setUid(uuid());
		}
	}
	
	// ----------------------------------------------------------------------
	
	@Override
	public FRIENDSHIP fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------
	
	@Override
	public List<FRIENDSHIP> fetchByCircleId(Long circleId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("circleId", circleId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------
	
	@Override
	public List<FRIENDSHIP> fetchByUserIdAndStatusId(Long userId, Long statusId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		if (statusId != null) {
			filter.put("statusId", statusId);
		}
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------

	@Override
	public List<FRIENDSHIP> fetchByFriendIdAndStatusId(Long friendId, Long statusId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("friendId", friendId);
		if (statusId != null) {
			filter.put("statusId", statusId);
		}
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------

	@Override
	public FRIENDSHIP fetchByUserIdAndFriendId(Long userId, Long friendId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		filter.put("friendId", friendId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------
	
	private FRIENDSHIP_EVENT addEvent(FRIENDSHIP friendship, KFriendshipEventType type) {
		FRIENDSHIP_EVENT event = getNewFriendshipEventObject();
		event.setFriendshipId(friendship.getId());
		event.setUserId(friendship.getUserId());
		event.setFriendId(friendship.getFriendId());
		event.setTypeId(type.getId());
		event.setEventDate(new Date());
		return getFriendshipEventService().add(event);
	}
	
	// ----------------------------------------------------------------------

	@Override 
	public FRIENDSHIP follow(Long userId, Long friendId, Long circleId) {
		return follow(userId, friendId, circleId, false, true);
	}
	
	private FRIENDSHIP follow(Long userId, Long friendId, Long circleId, boolean friendshipRequest, boolean notifyEvent) {
		// user following friend
		FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
		FRIENDSHIP_EVENT event =  null;
		
		if (friendship == null) {
			friendship = getNewFriendshipObject();
			friendship.setUserId(userId);
			friendship.setFriendId(friendId);
			friendship.setCircleId(circleId);
			friendship.setStatusId(KFriendshipStatus.FOLLOWING.getId());
			friendship.setFriendshipRequested(friendshipRequest);
			friendship = add(friendship);
			if (friendshipRequest) {
				event = addEvent(friendship, KFriendshipEventType.FRIENDSHIP_REQUEST);
			} else {
				event = addEvent(friendship, KFriendshipEventType.FOLLOW);
			}
			
			if (notifyEvent) {
				notifyEvent(friendship, event);
			}
		} else {
			// if friendship exists, make sure we're not blocked
			KFriendshipStatus status = KFriendshipStatus.getInstance(friendship.getStatusId());
			boolean dirty = false;
			switch (status) {
			case NONE:
				friendship.setStatusId(KFriendshipStatus.FOLLOWING.getId());
				friendship.setFriendshipRequested(friendshipRequest);
				friendship.setCircleId(circleId);
				dirty = true; 
				break;
				
			case FOLLOWED:
				friendship.setStatusId(KFriendshipStatus.FRIENDS.getId());
				friendship.setFriendshipRequested(friendshipRequest);
				friendship.setCircleId(circleId);
				dirty = true; 
				break;
				
			// do nothing if we're already 
			case FRIENDS:
			case FOLLOWING:
			case BLOCKING:
			case BLOCKED:
				break;
			}

			if (dirty) {
				friendship = update(friendship);
				
				if (friendshipRequest) {
					event = addEvent(friendship, KFriendshipEventType.FRIENDSHIP_REQUEST);
				} else {
					event = addEvent(friendship, KFriendshipEventType.FOLLOW);
				}
				
				if (notifyEvent) {
					notifyEvent(friendship, event);
				}
			}
		}

		
		// create the mirror entry; friend followed by user
		FRIENDSHIP other = fetchByUserIdAndFriendId(friendId, userId);
		if (other == null) {
			other = getNewFriendshipObject();
			other.setUserId(friendId);
			other.setFriendId(userId);
			other.setStatusId(KFriendshipStatus.FOLLOWED.getId());
			other = add(other);
		} else {
			// if friendship exists, make sure we're not blocked
			KFriendshipStatus status = KFriendshipStatus.getInstance(other.getStatusId());
			boolean dirty = false;
			switch (status) {
			case NONE:
				other.setStatusId(KFriendshipStatus.FOLLOWED.getId());
				dirty = true;
				break;
				
			case FOLLOWING:
				other.setStatusId(KFriendshipStatus.FRIENDS.getId());
				dirty = true;
				break;

				// do nothing if we're already 
			case FRIENDS:
			case FOLLOWED:
			case BLOCKING:
			case BLOCKED:
				break;
			}

			if (dirty) {
				other = update(other);
				
				if (friendshipRequest && status == KFriendshipStatus.FOLLOWING) {
					event = addEvent(other, KFriendshipEventType.FRIENDSHIP_ACCEPT);
					
					if (notifyEvent) {
						notifyEvent(other, event);
					}
				} 
			}
		}

		return friendship;
	}

	// ----------------------------------------------------------------------

	@Override
	public FRIENDSHIP block(Long userId, Long friendId) {
		// user blocking friend
		FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
		if (friendship == null) {
			friendship = getNewFriendshipObject();
			friendship.setUserId(userId);
			friendship.setFriendId(friendId);
			friendship.setStatusId(KFriendshipStatus.BLOCKING.getId());
			friendship = add(friendship);
			addEvent(friendship, KFriendshipEventType.BLOCK);

		} else {
			// if friendship exists
			KFriendshipStatus status = KFriendshipStatus.getInstance(friendship.getStatusId());
			boolean dirty = false;
			switch (status) {
			case NONE:
			case FRIENDS:
			case FOLLOWED:
			case FOLLOWING:
			case BLOCKED:
				friendship.setStatusId(KFriendshipStatus.BLOCKING.getId());
				dirty = true; 
				break;

				// do nothing if we're already 
			case BLOCKING:
				break;
			}

			if (dirty) {
				friendship = update(friendship);
				addEvent(friendship, KFriendshipEventType.BLOCK);
			}
		}


		// create the mirror entry; friend followed by user
		FRIENDSHIP other = fetchByUserIdAndFriendId(friendId, userId);
		if (other == null) {
			other = getNewFriendshipObject();
			other.setUserId(friendId);
			other.setFriendId(userId);
			other.setStatusId(KFriendshipStatus.BLOCKED.getId());
			other = add(other);
		} else {
			// if friendship exists
			KFriendshipStatus status = KFriendshipStatus.getInstance(other.getStatusId());
			boolean dirty = false;
			switch (status) {
			case NONE:
			case FRIENDS:
			case FOLLOWED:
			case FOLLOWING:
				other.setStatusId(KFriendshipStatus.BLOCKED.getId());
				dirty = true;
				break;

				// do nothing if we're already 
			case BLOCKED:
			case BLOCKING:
				break;
			}

			if (dirty) {
				other = update(other);
			}
		}

		return friendship;
	}


	// ----------------------------------------------------------------------

	@Override
	public FRIENDSHIP unfollow(Long userId, Long friendId) {
		return unfollow(userId, friendId, false);
	}
	
	private FRIENDSHIP unfollow(Long userId, Long friendId, boolean revokeFriendship) {
		FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
		if (friendship == null) {
			throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
		}
		
		FRIENDSHIP other = fetchByUserIdAndFriendId(friendId, userId);
		if (other == null) {
			throw new IllegalStateException("Friendship not found for userId: " + friendId + "  friendId: " + userId);
		}

		// user -> friend
		KFriendshipStatus status = KFriendshipStatus.getInstance(friendship.getStatusId());
		boolean dirty = false;
		switch (status) {
		case FRIENDS:
			friendship.setStatusId(KFriendshipStatus.FOLLOWED.getId());
			dirty = true; 
			break;
			
		case FOLLOWING:
			friendship.setStatusId(KFriendshipStatus.NONE.getId());
			dirty = true; 
			break;

		// do nothing if we're already 
		case NONE:
		case FOLLOWED:
		case BLOCKING:
		case BLOCKED:
			break;
		}

		if (dirty) {
			friendship = update(friendship);
			if (revokeFriendship) {
				addEvent(friendship, KFriendshipEventType.FRIENDSHIP_REVOKE);
			} else {
				addEvent(friendship, KFriendshipEventType.UNFOLLOW);
			}
		}


		// friend -> user
		status = KFriendshipStatus.getInstance(other.getStatusId());
		switch (status) {
		case FRIENDS:
			other.setStatusId(KFriendshipStatus.FOLLOWING.getId());
			dirty = true;
			break;

		case FOLLOWED:
			other.setStatusId(KFriendshipStatus.NONE.getId());
			dirty = true;
			break;

		case NONE:
		case FOLLOWING:
		case BLOCKED:
		case BLOCKING:
			break;
		}

		if (dirty) {
			other = update(other);
		}

		return friendship;
	}
	
	// ----------------------------------------------------------------------

	@Override 
	public FRIENDSHIP unblock(Long userId, Long friendId) {
		FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
		if (friendship == null) {
			throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
		}
		
		FRIENDSHIP other = fetchByUserIdAndFriendId(friendId, userId);
		if (other == null) {
			throw new IllegalStateException("Friendship not found for userId: " + friendId + "  friendId: " + userId);
		}

		
		// user -> friend
		KFriendshipStatus status = KFriendshipStatus.getInstance(friendship.getStatusId());
		boolean dirty = false;
		switch (status) {
		case BLOCKING:
			friendship.setStatusId(KFriendshipStatus.NONE.getId());
			dirty = true; 
			break;

		// do nothing if we're already 
		case NONE:
		case FRIENDS:
		case FOLLOWING:
		case FOLLOWED:
		case BLOCKED:
			break;
		}

		if (dirty) {
			friendship = update(friendship);
			addEvent(friendship, KFriendshipEventType.UNBLOCK);
		}


		// friend -> user
		status = KFriendshipStatus.getInstance(other.getStatusId());
		switch (status) {
		case BLOCKED:
			other.setStatusId(KFriendshipStatus.NONE.getId());
			dirty = true;
			break;

		case NONE:
		case FRIENDS:
		case FOLLOWED:
		case FOLLOWING:
		case BLOCKING:
			break;
		}

		if (dirty) {
			other = update(other);
		}

		return friendship;
	}
    
	// ----------------------------------------------------------------------

	@Override
	public FRIENDSHIP requestFriendship(Long userId, Long friendId, Long circleId) {
		return follow(userId, friendId, circleId, true, true);
	}
    
	// ----------------------------------------------------------------------
	
	@Override
	public FRIENDSHIP createFriendship(Long userId, Long friendId, Long circleId, boolean notifyUser) {
		 follow(userId, friendId, circleId, true, false);
		 acceptFriendship(friendId, userId, notifyUser);
		 
		 return fetchByUserIdAndFriendId(userId, friendId);
	}
	
	// ----------------------------------------------------------------------
	
	@Override
	public FRIENDSHIP acceptFriendship(Long userId, Long friendId) {
		return acceptFriendship(userId, friendId, true);
	}
	
	// ----------------------------------------------------------------------

	private FRIENDSHIP acceptFriendship(Long userId, Long friendId, boolean notifyEvent) {
		FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
		FRIENDSHIP_EVENT event =  null;
		
		if (friendship == null) {
			throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
		}
		
		FRIENDSHIP other = fetchByUserIdAndFriendId(friendId, userId);
		
		if (other == null) {
			throw new IllegalStateException("Friendship not found for userId: " + friendId + "  friendId: " + userId);
		}

		
		// user -> friend
		KFriendshipStatus status = KFriendshipStatus.getInstance(friendship.getStatusId());
		boolean dirty = false;
		switch (status) {
		case NONE:
		case FOLLOWING:
		case FOLLOWED:
			friendship.setStatusId(KFriendshipStatus.FRIENDS.getId());
			dirty = true; 
			break;

		// do nothing if we're already 
		case FRIENDS:
		case BLOCKING:
		case BLOCKED:
			break;
		}

		if (dirty) {
			friendship = update(friendship);
			event = addEvent(friendship, KFriendshipEventType.FRIENDSHIP_ACCEPT);
			
			if (notifyEvent) {
				notifyEvent(friendship, event);
			}
		}


		// friend -> user
		status = KFriendshipStatus.getInstance(other.getStatusId());
		switch (status) {
		case NONE:
		case FOLLOWED:
		case FOLLOWING:
			other.setStatusId(KFriendshipStatus.FRIENDS.getId());
			dirty = true;
			break;

		case FRIENDS:
		case BLOCKED:
		case BLOCKING:
			break;
		}

		if (dirty) {
			other = update(other);
		}

		return friendship;	
	}
    
	// ----------------------------------------------------------------------

    @Override
	public FRIENDSHIP rejectFriendship(Long userId, Long friendId) {
    	FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
		if (friendship == null) {
			throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
		}
		addEvent(friendship, KFriendshipEventType.FRIENDSHIP_REJECT);
		return friendship;
	}
    
	// ----------------------------------------------------------------------
    
    @Override
    public List<FRIENDSHIP> fetchFollowerList(Long userId) {
    	List<FRIENDSHIP> all = new ArrayList<FRIENDSHIP>();
        
		List<FRIENDSHIP> followers = fetchByUserIdAndStatusId(userId, KFriendshipStatus.FOLLOWED.getId());
		List<FRIENDSHIP> friends = fetchByUserIdAndStatusId(userId, KFriendshipStatus.FRIENDS.getId());
		all.addAll(followers);
		all.addAll(friends);
        return all;
    }
    
	// ----------------------------------------------------------------------
    
    @Override
    public List<FRIENDSHIP> fetchFollowingList(Long userId) {
    	List<FRIENDSHIP> all = new ArrayList<FRIENDSHIP>();
        
		List<FRIENDSHIP> following = fetchByUserIdAndStatusId(userId, KFriendshipStatus.FOLLOWING.getId());
		List<FRIENDSHIP> friends = fetchByUserIdAndStatusId(userId, KFriendshipStatus.FRIENDS.getId());
		all.addAll(following);
		all.addAll(friends);
        return all;
    }
    
	// ----------------------------------------------------------------------
    
    @Override
    public List<FRIENDSHIP> fetchFriendList(Long userId) {
    	List<FRIENDSHIP> all = new ArrayList<FRIENDSHIP>();
        
		List<FRIENDSHIP> friends = fetchByUserIdAndStatusId(userId, KFriendshipStatus.FRIENDS.getId());
		all.addAll(friends);
        return all;
    }
    
	// ----------------------------------------------------------------------
	
    @Override
	public FRIENDSHIP revokeFriendship(Long userId, Long friendId) {
		return unfollow(userId, friendId, true);	
	}
    
	// ----------------------------------------------------------------------
    
    @Override
	public Integer getFollowerCount(Long userId) {
        List<FRIENDSHIP> followers = fetchFollowerList(userId);
        return followers.size();
	}
    
	// ----------------------------------------------------------------------

    @Override
	public Integer getFollowingCount(Long userId) {
		List<FRIENDSHIP> following = fetchFollowingList(userId);
		return following.size();
	}
}
