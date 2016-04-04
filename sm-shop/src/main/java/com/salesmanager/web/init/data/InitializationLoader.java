package com.salesmanager.web.init.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.reference.init.service.InitializationDatabase;
import com.salesmanager.core.business.system.model.SystemConfiguration;
import com.salesmanager.core.business.system.service.SystemConfigurationService;
import com.salesmanager.core.business.user.model.Group;
import com.salesmanager.core.business.user.model.Permission;
import com.salesmanager.core.business.user.model.PermissionList;
import com.salesmanager.core.business.user.service.GroupService;
import com.salesmanager.core.business.user.service.PermissionService;
import com.salesmanager.core.constants.SystemConstants;
import com.salesmanager.core.utils.CoreConfiguration;
import com.salesmanager.web.admin.security.WebUserServices;
import com.salesmanager.web.constants.ApplicationConstants;
import com.salesmanager.web.utils.AppConfiguration;



@Component
public class InitializationLoader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitializationLoader.class);
	
	@Autowired
	private AppConfiguration appConfiguration;

	
	@Autowired
	private InitializationDatabase initializationDatabase;
	
	@Autowired
	private com.salesmanager.web.init.data.InitData initData;
	
	@Autowired
	private SystemConfigurationService systemConfigurationService;
	
	@Autowired
	private WebUserServices userDetailsService;
	
	@Autowired
	protected PermissionService  permissionService;
	
	@Autowired
	protected GroupService   groupService;
	
	@Autowired
	private CoreConfiguration configuration;
	
	
	@Autowired
	private XMLConverter xmlConverter;
	
	@PostConstruct
	public void init() {
		
		try {
			
			
			if (initializationDatabase.isEmpty()) {
				LOGGER.info(String.format("%s : Shopizer database is empty, populate it....", "sm-shop"));
		
				initializationDatabase.populate("sm-shop");
				 
				//security groups and permissions

				Map<String,Group>  groupAlreadyCreated=new HashMap<>();
				PermissionList permissionList = (PermissionList) xmlConverter.doUnMarshaling(ApplicationConstants.PERMISSIONS_XML_FILE_LOCATION);
				if (Optional.ofNullable(permissionList).isPresent()) {
						savePermissionWithGroup(groupAlreadyCreated,permissionList);
				}
				
				userDetailsService.createDefaultAdmin();
				  
				loadData();

			}
			
		} catch (Exception e) {
			LOGGER.error("Error in the init method",e);
		}
		
	}

	private void savePermissionWithGroup(Map<String, Group> groupAlreadyCreated,PermissionList permissionList) throws ServiceException {
		
		List<Permission> permissions = permissionList.getPermissions();
		
		for (Permission permission : permissions) {
			
			for (int index = 0; index < permission.getGroups().size(); index++) {
				
				Group group = permission.getGroups().get(index);
			
				if (!groupAlreadyCreated.containsKey(group.getGroupName())) {
					
					groupService.create(group);
					
					groupAlreadyCreated.put(group.getGroupName(), group);
					
				} else {
					
					permission.getGroups().remove(index);
					
					permission.getGroups().add(index,groupAlreadyCreated.get(group.getGroupName()));
				}
			}
			
			permissionService.create(permission);
		}
	}
	
	private void loadData() throws ServiceException {
		
		String loadTestData = configuration.getProperty(ApplicationConstants.POPULATE_TEST_DATA);
		boolean loadData =  !StringUtils.isBlank(loadTestData) && loadTestData.equals(SystemConstants.CONFIG_VALUE_TRUE);

		
		if(loadData) {
			
			SystemConfiguration configuration = systemConfigurationService.getByKey(ApplicationConstants.TEST_DATA_LOADED);
		
			if(configuration!=null) {
					if(configuration.getKey().equals(ApplicationConstants.TEST_DATA_LOADED)) {
						if(configuration.getValue().equals(SystemConstants.CONFIG_VALUE_TRUE)) {
							return;		
						}
					}		
			}
			
			initData.initInitialData();
			
			configuration = new SystemConfiguration();
			configuration.getAuditSection().setModifiedBy(SystemConstants.SYSTEM_USER);
			configuration.setKey(ApplicationConstants.TEST_DATA_LOADED);
			configuration.setValue(SystemConstants.CONFIG_VALUE_TRUE);
			systemConfigurationService.create(configuration);
			
			
		}
	}



}
