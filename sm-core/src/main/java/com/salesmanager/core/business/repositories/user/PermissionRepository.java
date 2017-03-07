package com.salesmanager.core.business.repositories.user;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.user.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>, PermissionRepositoryCustom {

	
	@Query("select p from Permission as p where p.id = ?1")
	Permission findOne(Integer id);
	
	@Query("select p from Permission as p order by p.id")
	List<Permission> findAll();
	
	@Query("select distinct p from Permission as p join fetch p.groups groups where groups.id in (?1)")
	List<Permission> findByGroups(Set<Integer> groupIds);
}
