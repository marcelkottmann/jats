package com.github.pepe79.tsgenerator.descriptor;

import java.util.List;


public class EnumDescriptor
{
	private String name;

	private List<String> values;

	public String getName()
	{
		return name;
	}

	public List<String> getValues()
	{
		return values;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setValues(List<String> values)
	{
		this.values = values;
	}

}
