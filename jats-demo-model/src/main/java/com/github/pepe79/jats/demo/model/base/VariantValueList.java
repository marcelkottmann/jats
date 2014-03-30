package com.github.pepe79.jats.demo.model.base;

import java.util.List;

public class VariantValueList {
	
	private VariantAttribute variantAttribute;
	
	private List<VariantValue> variantValues;

	public VariantAttribute getVariantAttribute() {
		return variantAttribute;
	}

	public void setVariantAttribute(VariantAttribute variantAttribute) {
		this.variantAttribute = variantAttribute;
	}

	public List<VariantValue> getVariantValues() {
		return variantValues;
	}

	public void setVariantValues(List<VariantValue> variantValues) {
		this.variantValues = variantValues;
	}
	
	
}
