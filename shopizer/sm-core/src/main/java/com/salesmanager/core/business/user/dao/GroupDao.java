package com.salesmanager.core.business.user.dao;

import java.util.List;
import java.util.Set;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.user.model.Group;
import com.salesmanager.core.business.user.model.GroupType;

public interface GroupDao extends SalesManagerEntityDao<Integer, Group> {

	List<Group> getGroupsListBypermissions(Set<Integer> ids);

	List<Group> listGroup(GroupType groupType);

	List<Group> listGroupByIds(Set<Integer> ids);
}
