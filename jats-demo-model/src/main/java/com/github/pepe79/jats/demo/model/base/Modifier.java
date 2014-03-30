package com.github.pepe79.jats.demo.model.base;

import java.util.List;

public class Modifier {
	private int id;

	private int minPerModifier;

	private int maxPerModifier;

	private int minPerComponent;

	private int maxPerComponent;

	private int defaultQuantity;

	private boolean defaultIncluded;

	private List<ModifierEntry> modifierEntries;

	public List<ModifierEntry> getModifierEntries() {
		return modifierEntries;
	}

	public void setModifierEntries(List<ModifierEntry> modifierEntries) {
		this.modifierEntries = modifierEntries;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMinPerComponent() {
		return minPerComponent;
	}

	public void setMinPerComponent(int minPerComponent) {
		this.minPerComponent = minPerComponent;
	}

	public int getMaxPerComponent() {
		return maxPerComponent;
	}

	public void setMaxPerComponent(int maxPerComponent) {
		this.maxPerComponent = maxPerComponent;
	}

	public int getMinPerModifier() {
		return minPerModifier;
	}

	public void setMinPerModifier(int minPerModifier) {
		this.minPerModifier = minPerModifier;
	}

	public int getMaxPerModifier() {
		return maxPerModifier;
	}

	public void setMaxPerModifier(int maxPerModifier) {
		this.maxPerModifier = maxPerModifier;
	}

	public int getDefaultQuantity() {
		return defaultQuantity;
	}

	public void setDefaultQuantity(int defaultQuantity) {
		this.defaultQuantity = defaultQuantity;
	}

	public boolean isDefaultIncluded() {
		return defaultIncluded;
	}

	public void setDefaultIncluded(boolean defaultIncluded) {
		this.defaultIncluded = defaultIncluded;
	}

}
