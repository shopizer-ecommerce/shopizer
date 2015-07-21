package com.salesmanager.core.modules.integration.shipping.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shipping.model.PackageDetails;
import com.salesmanager.core.business.shipping.model.ShippingConfiguration;
import com.salesmanager.core.business.shipping.model.ShippingOrigin;
import com.salesmanager.core.business.shipping.model.ShippingQuote;
import com.salesmanager.core.business.system.model.IntegrationConfiguration;
import com.salesmanager.core.business.system.model.IntegrationModule;
import com.salesmanager.core.modules.constants.Constants;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuotePreProcessModule;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * Decides which shipping method is going to be used based on a decision table
 * @author carlsamson
 *
 */
public class ShippingDecisionPreProcessorImpl implements ShippingQuotePreProcessModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingDecisionPreProcessorImpl.class);
	
	private StatelessKnowledgeSession shippingMethodDecision;
	
	private KnowledgeBase kbase;
	
	@Override
	public void preProcessShippingQuotes(ShippingQuote quote,
			List<PackageDetails> packages, BigDecimal orderTotal,
			Delivery delivery, ShippingOrigin origin, MerchantStore store,
			IntegrationConfiguration globalShippingConfiguration,
			IntegrationModule currentModule,
			ShippingConfiguration shippingConfiguration,
			List<IntegrationModule> allModules, Locale locale)
			throws IntegrationException {
		
		
		Validate.notNull(delivery, "Delivery cannot be null");
		Validate.notNull(currentModule, "IntegrationModule cannot be null");
		Validate.notNull(delivery.getCountry(), "Delivery.country cannot be null");
		Validate.notNull(allModules, "List<IntegrationModule> cannot be null");
		Validate.notNull(packages, "packages cannot be null");
		Validate.notEmpty(packages, "packages cannot be empty");
		
		Long distance = null;
		
		if(quote!=null) {
			//look if distance has been calculated
			if(quote.getQuoteInformations()!=null) {
				if(quote.getQuoteInformations().containsKey(Constants.DISTANCE_KEY)) {
					distance = (Long)quote.getQuoteInformations().get(Constants.DISTANCE_KEY);
				}
			}
		}
		
		//calculate volume (L x W x H)
		Double volume = null;
		Double weight = 0D;
		Double size = null;
		//calculate weight, volume and largest size
		for(PackageDetails pack : packages) {
			weight = weight + pack.getShippingWeight();
			Double tmpVolume = pack.getShippingHeight() * pack.getShippingLength() * pack.getShippingWidth();
			if(volume == null || tmpVolume.doubleValue() > volume.doubleValue()) { //take the largest volume
				volume = tmpVolume;
			} 
			//largest size
			List<Double> sizeList = new ArrayList<Double>();
			sizeList.add(pack.getShippingHeight());
			sizeList.add(pack.getShippingLength());
			sizeList.add(pack.getShippingWidth());
			Double maxSize = (Double)Collections.max(sizeList);
			if(size==null || maxSize.doubleValue() > size.doubleValue()) {
				size = maxSize.doubleValue();
			}
		}
		
		//Build a ShippingInputParameters
		ShippingInputParameters inputParameters = new ShippingInputParameters();
		
		inputParameters.setWeight((long)weight.doubleValue());
		inputParameters.setCountry(delivery.getCountry().getIsoCode());
		inputParameters.setProvince(delivery.getZone().getCode());
		//inputParameters.setModuleName(currentModule.getCode());
		
		if(delivery.getZone().getCode()!=null) {
			inputParameters.setProvince(delivery.getZone().getCode());
		}
		
		if(size!=null) {
			inputParameters.setSize((long)size.doubleValue());
		}
		
		if(distance!=null) {
			inputParameters.setDistance(distance);
		}
		
		if(volume!=null) {
			inputParameters.setVolume((long)volume.doubleValue());
		}
		
		LOGGER.debug("Setting input parameters " + inputParameters.toString());
		System.out.println(inputParameters.toString());
		
		shippingMethodDecision.execute(Arrays.asList(new Object[] { inputParameters }));
		
		LOGGER.debug("Using shipping nodule " + inputParameters.getModuleName());
		
		if(!StringUtils.isBlank(inputParameters.getModuleName())) {
			for(IntegrationModule toBeUsed : allModules) {
				if(toBeUsed.getCode().equals(inputParameters.getModuleName())) {
					quote.setCurrentShippingModule(toBeUsed);
					break;
				}
			}
		}
		
	}

	public StatelessKnowledgeSession getShippingMethodDecision() {
		return shippingMethodDecision;
	}

	public void setShippingMethodDecision(StatelessKnowledgeSession shippingMethodDecision) {
		this.shippingMethodDecision = shippingMethodDecision;
	}

	public KnowledgeBase getKbase() {
		return kbase;
	}

	public void setKbase(KnowledgeBase kbase) {
		this.kbase = kbase;
	}
	







}
