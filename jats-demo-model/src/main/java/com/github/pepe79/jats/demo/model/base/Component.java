package com.github.pepe79.jats.demo.model.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Component implements Customizable {
	private int id;

	private String label;

	private List<Modifier> modifiers;

	private Map<String, Object> custom = new LinkedHashMap<String, Object>();

	private List<Component> variants;

	private List<VariantAttribute> variantAttributes;

	public List<VariantValueList> getVariantValueList() {
		List<VariantValueList> values = new ArrayList<VariantValueList>();

		if (variantAttributes != null) {
			for (VariantAttribute variantAttribute : variantAttributes) {
				Map<Object, VariantValue> variantValueCollector = new LinkedHashMap<Object, VariantValue>();
				for (Component variant : variants) {
					Object value = getFieldValue(variant,
							variantAttribute.getProperty());
					VariantValue variantValue = variantValueCollector
							.get(value);
					if (variantValue == null) {
						variantValue = new VariantValue();
						variantValue.setValue(value);
						variantValue.setVariants(new ArrayList<Component>());
						variantValueCollector.put(value, variantValue);
					}
					variantValue.getVariants().add(variant);
				}

				VariantValueList variantValueList = new VariantValueList();
				variantValueList.setVariantAttribute(variantAttribute);
				variantValueList.setVariantValues(new ArrayList<VariantValue>(
						variantValueCollector.values()));
				values.add(variantValueList);

			}
		}
		return values;
	}

	public List<Object> getVariantValues() {
		List<Object> values = new ArrayList<Object>();
		if (variantAttributes != null) {
			for (VariantAttribute variantAttribute : variantAttributes) {
				values.add(getFieldValue(this, variantAttribute.getProperty()));
			}
		}
		return values;
	}

	private Object getFieldValue(Component variant, String property) {
		String customPrefix = "custom.";
		if (property.startsWith(customPrefix)) {
			return variant.getCustom().get(
					property.substring(customPrefix.length()));
		} else {
			try {
				Field field = variant.getClass().getDeclaredField(property);
				field.setAccessible(true);
				return field.get(variant);
			} catch (Exception e) {
				throw new RuntimeException(
						"An error occured while accessing field " + property
								+ ".", e);
			}
		}
	}

	public List<Component> getVariants() {
		return variants;
	}

	public void setVariants(List<Component> variants) {
		this.variants = variants;
	}

	public List<VariantAttribute> getVariantAttributes() {
		return variantAttributes;
	}

	public void setVariantAttributes(List<VariantAttribute> variantAttributes) {
		this.variantAttributes = variantAttributes;
	}

	public List<Modifier> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<Modifier> modifiers) {
		this.modifiers = modifiers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public Map<String, Object> getCustom() {
		return custom;
	}

	protected void setCustom(Map<String, Object> custom) {
		this.custom = custom;
	}

}
