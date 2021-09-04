package com.salesmanager.core.model.shipping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * Object saved in the database maintaining various shipping options
 * @author casams1
 *
 */
public class ShippingConfiguration implements JSONAware {
	
	//enums
	private ShippingType shippingType = ShippingType.NATIONAL;
	private ShippingBasisType shippingBasisType = ShippingBasisType.SHIPPING;
	private ShippingOptionPriceType shippingOptionPriceType = ShippingOptionPriceType.ALL;
	private ShippingPackageType shippingPackageType = ShippingPackageType.ITEM;
	private ShippingDescription shippingDescription = ShippingDescription.SHORT_DESCRIPTION;
	private ShippingType freeShippingType = null;
	
	private int boxWidth = 0;
	private int boxHeight = 0;
	private int boxLength = 0;
	private double boxWeight = 0;
	private double maxWeight = 0;
	
	//free shipping
	private boolean freeShippingEnabled = false;
	private BigDecimal orderTotalFreeShipping = null;
	
	private List<Package> packages = new ArrayList<Package>();

	
	
	private BigDecimal handlingFees = null;
	private boolean taxOnShipping = false;
	
	
	//JSON bindings
	private String shipType;
	private String shipBaseType;
	private String shipOptionPriceType = ShippingOptionPriceType.ALL.name();
	private String shipPackageType;
	private String shipDescription;
	private String shipFreeType;
	
	//Transient
	private String orderTotalFreeShippingText = null;
	private String handlingFeesText = null;
	
	
	public String getShipType() {
		return shipType;
	}


	public String getShipBaseType() {
		return shipBaseType;
	}


	public String getShipOptionPriceType() {
		return shipOptionPriceType;
	}



	public void setShippingOptionPriceType(ShippingOptionPriceType shippingOptionPriceType) {
		this.shippingOptionPriceType = shippingOptionPriceType;
		this.shipOptionPriceType = this.shippingOptionPriceType.name();
	}


	public ShippingOptionPriceType getShippingOptionPriceType() {
		return shippingOptionPriceType;
	}


	public void setShippingBasisType(ShippingBasisType shippingBasisType) {
		this.shippingBasisType = shippingBasisType;
		this.shipBaseType = this.shippingBasisType.name();
	}


	public ShippingBasisType getShippingBasisType() {
		return shippingBasisType;
	}


	public void setShippingType(ShippingType shippingType) {
		this.shippingType = shippingType;
		this.shipType = this.shippingType.name();
	}


	public ShippingType getShippingType() {
		return shippingType;
	}
	
	public ShippingPackageType getShippingPackageType() {
		return shippingPackageType;
	}


	public void setShippingPackageType(ShippingPackageType shippingPackageType) {
		this.shippingPackageType = shippingPackageType;
		this.shipPackageType = shippingPackageType.name();
	}
	
	
	public String getShipPackageType() {
		return shipPackageType;
	}

	
	/** JSON bindding **/
	public void setShipType(String shipType) {
		this.shipType = shipType;
		ShippingType sType = ShippingType.NATIONAL;
		if(shipType.equals(ShippingType.INTERNATIONAL.name())) {
			sType = ShippingType.INTERNATIONAL;
		}
		setShippingType(sType);
	}


	public void setShipOptionPriceType(String shipOptionPriceType) {
		this.shipOptionPriceType = shipOptionPriceType;
		ShippingOptionPriceType sType = ShippingOptionPriceType.ALL;
		if(shipOptionPriceType.equals(ShippingOptionPriceType.HIGHEST.name())) {
			sType = ShippingOptionPriceType.HIGHEST;
		}
		if(shipOptionPriceType.equals(ShippingOptionPriceType.LEAST.name())) {
			sType = ShippingOptionPriceType.LEAST;
		}
		setShippingOptionPriceType(sType);
	}


	public void setShipBaseType(String shipBaseType) {
		this.shipBaseType = shipBaseType;
		ShippingBasisType sType = ShippingBasisType.SHIPPING;
		if(shipBaseType.equals(ShippingBasisType.BILLING.name())) {
			sType = ShippingBasisType.BILLING;
		}
		setShippingBasisType(sType);
	}



	public void setShipPackageType(String shipPackageType) {
		this.shipPackageType = shipPackageType;
		ShippingPackageType sType = ShippingPackageType.ITEM;
		if(shipPackageType.equals(ShippingPackageType.BOX.name())) {
			sType = ShippingPackageType.BOX;
		}
		this.setShippingPackageType(sType);
	}
	
	public void setShipDescription(String shipDescription) {
		this.shipDescription = shipDescription;
		ShippingDescription sType = ShippingDescription.SHORT_DESCRIPTION;
		if(shipDescription.equals(ShippingDescription.LONG_DESCRIPTION.name())) {
			sType = ShippingDescription.LONG_DESCRIPTION;
		}
		this.setShippingDescription(sType);
	}
	
	public void setShipFreeType(String shipFreeType) {
		this.shipFreeType = shipFreeType;
		ShippingType sType = ShippingType.NATIONAL;
		if(shipFreeType.equals(ShippingType.INTERNATIONAL.name())) {
			sType = ShippingType.INTERNATIONAL;
		}
		setFreeShippingType(sType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toJSONString() {
		JSONObject data = new JSONObject();
		data.put("shipBaseType", this.getShippingBasisType().name());
		data.put("shipOptionPriceType", this.getShippingOptionPriceType().name());
		data.put("shipType", this.getShippingType().name());
		data.put("shipPackageType", this.getShippingPackageType().name());
		if(shipFreeType!=null) {
			data.put("shipFreeType", this.getFreeShippingType().name());
		}
		data.put("shipDescription", this.getShippingDescription().name());
		
		
		data.put("boxWidth", this.getBoxWidth());
		data.put("boxHeight", this.getBoxHeight());
		data.put("boxLength", this.getBoxLength());
		data.put("boxWeight", this.getBoxWeight());
		data.put("maxWeight", this.getMaxWeight());
		data.put("freeShippingEnabled", this.freeShippingEnabled);
		data.put("orderTotalFreeShipping", this.orderTotalFreeShipping);
		data.put("handlingFees", this.handlingFees);
		data.put("taxOnShipping", this.taxOnShipping);
		
		
		JSONArray jsonArray = new JSONArray();

		for(Package p : this.getPackages()) {
			jsonArray.add(transformPackage(p));
		}
		
		data.put("packages", jsonArray);
		
		
		return data.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject transformPackage(Package p) {
		JSONObject data = new JSONObject();
		data.put("boxWidth", p.getBoxWidth());
		data.put("boxHeight", p.getBoxHeight());
		data.put("boxLength", p.getBoxLength());
		data.put("boxWeight", p.getBoxWeight());
		data.put("maxWeight", p.getMaxWeight());
		data.put("treshold", p.getTreshold());
		data.put("code", p.getCode());
		data.put("shipPackageType", p.getShipPackageType().name());
		data.put("defaultPackaging", p.isDefaultPackaging());
		return data;
	}


	public int getBoxWidth() {
		return boxWidth;
	}


	public void setBoxWidth(int boxWidth) {
		this.boxWidth = boxWidth;
	}


	public int getBoxHeight() {
		return boxHeight;
	}


	public void setBoxHeight(int boxHeight) {
		this.boxHeight = boxHeight;
	}


	public int getBoxLength() {
		return boxLength;
	}


	public void setBoxLength(int boxLength) {
		this.boxLength = boxLength;
	}


	public double getBoxWeight() {
		return boxWeight;
	}


	public void setBoxWeight(double boxWeight) {
		this.boxWeight = boxWeight;
	}


	public double getMaxWeight() {
		return maxWeight;
	}


	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}


	public boolean isFreeShippingEnabled() {
		return freeShippingEnabled;
	}


	public void setFreeShippingEnabled(boolean freeShippingEnabled) {
		this.freeShippingEnabled = freeShippingEnabled;
	}


	public BigDecimal getOrderTotalFreeShipping() {
		return orderTotalFreeShipping;
	}


	public void setOrderTotalFreeShipping(BigDecimal orderTotalFreeShipping) {
		this.orderTotalFreeShipping = orderTotalFreeShipping;
	}


	public void setHandlingFees(BigDecimal handlingFees) {
		this.handlingFees = handlingFees;
	}


	public BigDecimal getHandlingFees() {
		return handlingFees;
	}


	public void setTaxOnShipping(boolean taxOnShipping) {
		this.taxOnShipping = taxOnShipping;
	}


	public boolean isTaxOnShipping() {
		return taxOnShipping;
	}





	public String getShipDescription() {
		return shipDescription;
	}


	public void setShippingDescription(ShippingDescription shippingDescription) {
		this.shippingDescription = shippingDescription;
	}


	public ShippingDescription getShippingDescription() {
		return shippingDescription;
	}


	public void setFreeShippingType(ShippingType freeShippingType) {
		this.freeShippingType = freeShippingType;
	}


	public ShippingType getFreeShippingType() {
		return freeShippingType;
	}



	public String getShipFreeType() {
		return shipFreeType;
	}


	public void setOrderTotalFreeShippingText(String orderTotalFreeShippingText) {
		this.orderTotalFreeShippingText = orderTotalFreeShippingText;
	}


	public String getOrderTotalFreeShippingText() {
		return orderTotalFreeShippingText;
	}


	public void setHandlingFeesText(String handlingFeesText) {
		this.handlingFeesText = handlingFeesText;
	}


	public String getHandlingFeesText() {
		return handlingFeesText;
	}


	public List<Package> getPackages() {
		return packages;
	}


	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}











}


