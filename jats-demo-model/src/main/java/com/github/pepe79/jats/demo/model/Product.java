package com.github.pepe79.jats.demo.model;

import java.util.List;

public class Product
{
	private int id;
	
	private String label; 

	private List<Component> variants;

	public List<Component> getVariants()
	{
		return variants;
	}

	public void setVariants(List<Component> variants)
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

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	
}
