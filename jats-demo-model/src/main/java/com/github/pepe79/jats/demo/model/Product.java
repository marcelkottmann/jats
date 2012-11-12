package com.github.pepe79.jats.demo.model;

import java.util.List;

public class Product
{
	private int id;

	private List<ProductVariant> variants;

	public List<ProductVariant> getVariants()
	{
		return variants;
	}

	public void setVariants(List<ProductVariant> variants)
	{
		this.variants = variants;
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
