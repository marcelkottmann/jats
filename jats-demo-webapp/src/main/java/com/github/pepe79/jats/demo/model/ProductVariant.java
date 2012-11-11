package com.github.pepe79.jats.demo.model;

import java.util.List;

public class ProductVariant
{
	private int id;

	private Product mainProduct;

	private List<Component> requiredComponents;

	private List<Component> optionalComponents;

	private Uom uom;
	
	public Uom getUom()
	{
		return uom;
	}

	public void setUom(Uom uom)
	{
		this.uom = uom;
	}

	public Product getMainProduct()
	{
		return mainProduct;
	}

	public void setMainProduct(Product mainProduct)
	{
		this.mainProduct = mainProduct;
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

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

}
