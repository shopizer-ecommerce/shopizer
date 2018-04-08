package com.salesmanager.core.business.services.user;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.user.GroupRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.GroupType;


@Service("groupService")
public class GroupServiceImpl extends
		SalesManagerEntityServiceImpl<Integer, Group> implements GroupService {

	GroupRepository groupRepository;


	@Inject
	public GroupServiceImpl(GroupRepository groupRepository) {
		super(groupRepository);
		this.groupRepository = groupRepository;

	}


	@Override
	public List<Group> listGroup(GroupType groupType) throws ServiceException {
		try {
			return groupRepository.findByType(groupType);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Group> listGroupByIds(Set<Integer> ids) throws ServiceException {
		try {
			return groupRepository.findByIds(ids);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public Group findByName(String groupName) throws ServiceException {
		return groupRepository.findByGroupName(groupName);
	}


}
