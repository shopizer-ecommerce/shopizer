package com.salesmanager.core.model.catalog.product.instance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.salesmanager.core.model.common.audit.AuditListener;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * Extra properties on a group of instances
 * @author carlsamson
 *
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name="PRODUCT_INSTANCE_GROUP")
public class ProductInstanceGroup extends SalesManagerEntity<Long, ProductInstanceGroup> {

	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "PRODUCT_INSTANCE_GROUP_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", 
	table = "SM_SEQUENCER", 
	pkColumnName = "SEQ_NAME", 
	valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_INST_GROUP_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="productInstanceGroup")
	private List<ProductInstanceImage> images = new ArrayList<ProductInstanceImage>();

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH }, mappedBy = "productInstanceGroup")
	private Set<ProductInstance> productInstances = new HashSet<ProductInstance>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MERCHANT_ID", nullable=false)
	private MerchantStore merchantStore;
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
		
	}

	public List<ProductInstanceImage> getImages() {
		return images;
	}

	public void setImages(List<ProductInstanceImage> images) {
		this.images = images;
	}

	public Set<ProductInstance> getProductInstances() {
		return productInstances;
	}

	public void setProductInstances(Set<ProductInstance> productInstances) {
		this.productInstances = productInstances;
	}

	public MerchantStore getMerchantStore() {
		return merchantStore;
	}

	public void setMerchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
	}


}
