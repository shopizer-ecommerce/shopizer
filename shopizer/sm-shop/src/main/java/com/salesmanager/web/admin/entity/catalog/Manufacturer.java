package com.salesmanager.web.admin.entity.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.salesmanager.core.business.catalog.product.model.image.ProductImage;
import com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription;


public class Manufacturer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4531526676134574984L;

	/**
	 * 
	 */

	//provides wrapping to the main Manufacturer entity
	@Valid
	private com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer manufacturer;
	
	@Valid
	private List<ManufacturerDescription> descriptions = new ArrayList<ManufacturerDescription>();
	
	private Integer order = new Integer(0);
	private MultipartFile image = null;
	private ProductImage productImage = null;
	
	
	
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public ProductImage getProductImage() {
		return productImage;
	}

	public void setProductImage(ProductImage productImage) {
		this.productImage = productImage;
	}

	public com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(
			com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<ManufacturerDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<ManufacturerDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	
	

}
