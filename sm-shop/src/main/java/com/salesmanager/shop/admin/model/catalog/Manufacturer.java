package com.salesmanager.shop.admin.model.catalog;

import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Manufacturer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4531526676134574984L;

	/**
	 * 
	 */

	//provides wrapping to the main Manufacturer entity
	private com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer manufacturer;
	
	@Valid
	private List<ManufacturerDescription> descriptions = new ArrayList<ManufacturerDescription>();
	
	private Integer order = new Integer(0);
	private MultipartFile image = null;
	@NotNull
	private String code;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(
			com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer manufacturer) {
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
