package com.github.pepe79.jats.demo.model.base;

import java.util.List;

public class VariantValue {

	private List<Component> variants;

	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public List<Component> getVariants() {
		return variants;
	}

	public void setVariants(List<Component> variants) {
		this.variants = variants;
	}

}
