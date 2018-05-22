package com.salesmanager.shop.utils;

import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.User;

import java.util.List;

public class UserUtils {
	
	public static boolean userInGroup(User user,String groupName) {
		
		
		
		List<Group> logedInUserGroups = user.getGroups();
		for(Group group : logedInUserGroups) {
			if(group.getGroupName().equals(groupName)) {
				return true;
			}
		}
		
		return false;
		
	}

}
