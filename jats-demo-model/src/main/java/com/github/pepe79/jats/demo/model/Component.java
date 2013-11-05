package com.github.pepe79.jats.demo.model;

import java.util.List;

public class Component
{
	private int id;

	private String label;

	private float price;

	private List<Component> requiredComponents;

	private List<Component> optionalComponents;

	private List<Modifier> modifiers;

	private Quantity quantity;

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public List<Component> getRequiredComponents()
	{
		return requiredComponents;
	}

	public void setRequiredComponents(List<Component> requiredComponents)
	{
		this.requiredComponents = requiredComponents;
	}

	public List<Component> getOptionalComponents()
	{
		return optionalComponents;
	}

	public void setOptionalComponents(List<Component> optionalComponents)
	{
		this.optionalComponents = optionalComponents;
	}

	public List<Modifier> getModifiers()
	{
		return modifiers;
	}

	public void setModifiers(List<Modifier> modifiers)
	{
		this.modifiers = modifiers;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public Quantity getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Quantity quantity)
	{
		this.quantity = quantity;
	}

}
