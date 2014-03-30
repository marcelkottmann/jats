package com.github.pepe79.tsgenerator.descriptor;

public class TypeDescriptor
{
	public static final String NUMBER = "number";

	public static final String BOOL = "boolean";

	public static final String STRING = "string";

	public static final String ANY = "any";

	public static final String ARRAY = "TYPE[]";

	public static final String ARRAY_OF_ARRAY = "TYPE[][]";

	public static final String ANY_ARRAY = "any[]";

	public static final String MAP = "{}";
	
	private String type;

	private boolean extern;

	private String componentType;

	public String getComponentType()
	{
		return componentType;
	}

	public String getType()
	{
		return type;
	}

	public boolean isExtern()
	{
		return extern;
	}

	public void setComponentType(String componentType)
	{
		this.componentType = componentType;
	}

	public void setExtern(boolean extern)
	{
		this.extern = extern;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
