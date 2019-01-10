package com.salesmanager.shop.model.references;

import java.util.List;

public class SizeReferences {
	
	private List<WeightUnit> weights;
	private List<MeasureUnit> measures;
	public List<WeightUnit> getWeights() {
		return weights;
	}
	public void setWeights(List<WeightUnit> weights) {
		this.weights = weights;
	}
	public List<MeasureUnit> getMeasures() {
		return measures;
	}
	public void setMeasures(List<MeasureUnit> measures) {
		this.measures = measures;
	}

}
