package com.salesmanager.core.model.catalog.product.attribute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;


@Entity
@Table(name="PRODUCT_OPTION_VALUE", schema=SchemaConstant.SALESMANAGER_SCHEMA, indexes = { @Index(name="PRD_OPTION_VAL_CODE_IDX", columnList = "PRODUCT_OPTION_VAL_CODE")}, uniqueConstraints=
	@UniqueConstraint(columnNames = {"MERCHANT_ID", "PRODUCT_OPTION_VAL_CODE"}))
public class ProductOptionValue extends SalesManagerEntity<Long, ProductOptionValue> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRODUCT_OPTION_VALUE_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_OPT_VAL_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Column(name="PRODUCT_OPT_VAL_SORT_ORD")
	private Integer productOptionValueSortOrder;
	
	@Column(name="PRODUCT_OPT_VAL_IMAGE")
	private String productOptionValueImage;
	
	@Column(name="PRODUCT_OPT_FOR_DISP")
	private boolean productOptionDisplayOnly=false;
	
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z0-9_]*$")
	@Column(name="PRODUCT_OPTION_VAL_CODE")
	private String code;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productOptionValue")
	private Set<ProductOptionValueDescription> descriptions = new HashSet<ProductOptionValueDescription>();
	
	@Transient
	private MultipartFile image = null;
	
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	@Transient
	private List<ProductOptionValueDescription> descriptionsList = new ArrayList<ProductOptionValueDescription>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MERCHANT_ID", nullable=false)
	private MerchantStore merchantStore;
	
	public ProductOptionValue() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getProductOptionValueSortOrder() {
		return productOptionValueSortOrder;
	}

	public void setProductOptionValueSortOrder(Integer productOptionValueSortOrder) {
		this.productOptionValueSortOrder = productOptionValueSortOrder;
	}

	public String getProductOptionValueImage() {
		return productOptionValueImage;
	}

	public void setProductOptionValueImage(String productOptionValueImage) {
		this.productOptionValueImage = productOptionValueImage;
	}

	public Set<ProductOptionValueDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Set<ProductOptionValueDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public MerchantStore getMerchantStore() {
		return merchantStore;
	}

	public void setMerchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
	}

	public void setDescriptionsList(List<ProductOptionValueDescription> descriptionsList) {
		this.descriptionsList = descriptionsList;
	}

	public List<ProductOptionValueDescription> getDescriptionsList() {
		return descriptionsList; 
	}
	
	public List<ProductOptionValueDescription> getDescriptionsSettoList() {
		if(descriptionsList==null || descriptionsList.size()==0) {
			descriptionsList = new ArrayList<ProductOptionValueDescription>(this.getDescriptions());
		} 
		return descriptionsList;
	}

	public boolean isProductOptionDisplayOnly() {
		return productOptionDisplayOnly;
	}

	public void setProductOptionDisplayOnly(boolean productOptionDisplayOnly) {
		this.productOptionDisplayOnly = productOptionDisplayOnly;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}




}
