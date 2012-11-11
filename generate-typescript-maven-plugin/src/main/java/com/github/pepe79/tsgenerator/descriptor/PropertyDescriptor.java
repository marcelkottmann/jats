package com.github.pepe79.tsgenerator.descriptor;


public class PropertyDescriptor
{
	private String readMethodName;

	private String writeMethodName;

	private TypeDescriptor type;

	private String name;

	public String getName()
	{
		return name;
	}

	public String getReadMethodName()
	{
		return readMethodName;
	}

	public TypeDescriptor getType()
	{
		return type;
	}

	public String getWriteMethodName()
	{
		return writeMethodName;
	}

	public boolean isReadable()
	{
		return readMethodName != null;
	}

	public boolean isWritable()
	{
		return writeMethodName != null;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setReadMethodName(String readMethodName)
	{
		this.readMethodName = readMethodName;
	}

	public void setType(TypeDescriptor type)
	{
		this.type = type;
	}

	public void setWriteMethodName(String writeMethodName)
	{
		this.writeMethodName = writeMethodName;
	}

}
