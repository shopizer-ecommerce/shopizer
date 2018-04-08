package com.salesmanager.core.business.repositories.catalog.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.category.Category;


public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
	

	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cd.seUrl like ?2 and cm.id = ?1 order by c.sortOrder asc")
	List<Category> listByFriendlyUrl(Integer storeId, String friendlyUrl);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cd.seUrl=?2 and cm.id = ?1")
	Category findByFriendlyUrl(Integer storeId, String friendlyUrl);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cd.name like %?2% and cdl.id=?3 and cm.id = ?1 order by c.sortOrder asc")
	List<Category> findByName(Integer storeId, String name, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where c.code=?2 and cm.id = ?1")
	Category findByCode(Integer storeId, String code);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where c.code in (?2) and cdl.id=?3 and cm.id = ?1 order by c.sortOrder asc")
	List<Category> findByCodes(Integer storeId, List<String> codes, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where c.id in (?2) and cdl.id=?3 and cm.id = ?1 order by c.sortOrder asc")
	List<Category> findByIds(Integer storeId, List<Long> ids, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cdl.id=?2 and c.id = ?1")
	Category findById(Long categoryId, Integer languageId);

	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.code=?1 and c.code=?2")
	public Category findByCode(String merchantStoreCode, String code);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where c.id=?1")
	public Category findOne(Long categoryId);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=?1 and c.lineage like %?2% order by c.lineage, c.sortOrder asc")
	public List<Category> findByLineage(Integer merchantId, String linenage);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.code= ?1 and c.lineage like %?2% order by c.lineage, c.sortOrder asc")
	public List<Category> findByLineage(String storeCode, String linenage);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=?1 and c.depth >= ?2 order by c.lineage, c.sortOrder asc")
	public List<Category> findByDepth(Integer merchantId, int depth);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=?1 and cdl.id=?3 and c.depth >= ?2 order by c.lineage, c.sortOrder asc")
	public List<Category> findByDepth(Integer merchantId, int depth, Integer languageId);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=?1 and cdl.id=?3 and c.depth >= ?2 and c.featured=true order by c.lineage, c.sortOrder asc")
	public List<Category> findByDepthFilterByFeatured(Integer merchantId, int depth, Integer languageId);

	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm left join fetch c.parent cp where cp.id=?1 and cdl.id=?2 order by c.lineage, c.sortOrder asc")
	public List<Category> findByParent(Long parentId, Integer languageId);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=?1 and cdl.id=?2 order by c.lineage, c.sortOrder asc")
	public List<Category> findByStore(Integer merchantId, Integer languageId);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=?1 order by c.lineage, c.sortOrder asc")
	public List<Category> findByStore(Integer merchantId);


	
}
