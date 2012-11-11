package com.github.pepe79.jats.demo.model;

public class ModifierEntry
{
	private int id;

	private Component component;

	private Uom uom;

	public Uom getUom()
	{
		return uom;
	}

	public void setUom(Uom uom)
	{
		this.uom = uom;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Component getComponent()
	{
		return component;
	}

	public void setComponent(Component component)
	{
		this.component = component;
	}

}
