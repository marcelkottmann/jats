package com.github.pepe79.tsgenerator.descriptor;

import java.util.LinkedHashMap;
import java.util.Map;



public class ClassDescriptor
{
	private String name;

	private String superClass;

	private Map<String, PropertyDescriptor> properties = new LinkedHashMap<String, PropertyDescriptor>();

	private Map<String, EnumDescriptor> enums = new LinkedHashMap<String, EnumDescriptor>();

	public Map<String, EnumDescriptor> getEnums()
	{
		return enums;
	}

	public String getName()
	{
		return name;
	}

	public Map<String, PropertyDescriptor> getProperties()
	{
		return properties;
	}

	public String getSuperClass()
	{
		return superClass;
	}

	public void setEnums(Map<String, EnumDescriptor> enums)
	{
		this.enums = enums;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setProperties(Map<String, PropertyDescriptor> properties)
	{
		this.properties = properties;
	}

	public void setSuperClass(String superClass)
	{
		this.superClass = superClass;
	}

}
