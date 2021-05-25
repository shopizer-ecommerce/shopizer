package com.salesmanager.core.business.repositories.content;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentType;

public interface ContentRepository extends JpaRepository<Content, Long>,  ContentRepositoryCustom  {

	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.contentType = ?1 and cm.id = ?2 and cd.language.id = ?3 order by c.sortOrder asc")
	List<Content> findByType(ContentType contentType, Integer storeId, Integer languageId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.contentType = ?1 and cm.id = ?2 order by c.sortOrder asc")
	List<Content> findByType(ContentType contentType, Integer storeId);
	
	@Query("select c from Content c join fetch c.merchantStore cm where c.code = ?1 and c.contentType = ?2 and cm.id = ?3")
	Content findByCodeAndType(String code, ContentType contentType, Integer storeId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.contentType in (?1) and cm.id = ?2 and cd.language.id = ?3 order by c.sortOrder asc")
	List<Content> findByTypes(List<ContentType> contentTypes, Integer storeId, Integer languageId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.contentType in (?1) and cm.id = ?2 order by c.sortOrder asc")
	List<Content> findByTypes(List<ContentType> contentTypes, Integer storeId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.code = ?1 and cm.id = ?2")
	Content findByCode(String code, Integer storeId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.contentType = ?1 and cm.id=?3 and c.code like ?2 and cd.language.id = ?4")
	List<Content> findByCodeLike(ContentType contentType, String code, Integer storeId, Integer languageId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.code = ?1 and cm.id = ?2 and cd.language.id = ?3")
	Content findByCode(String code, Integer storeId, Integer languageId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.id = ?1 and cd.language.id = ?2")
	Content findByIdAndLanguage(Long contentId, Integer languageId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.id = ?1")
	Content findOne(Long contentId);


}
