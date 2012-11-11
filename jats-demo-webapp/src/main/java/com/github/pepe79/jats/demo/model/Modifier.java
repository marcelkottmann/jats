package com.github.pepe79.jats.demo.model;

import java.util.List;

public class Modifier
{
	private int id;
	
	private int min;
	
	private int max;

	private List<ModifierEntry> modifierEntries;

	public List<ModifierEntry> getModifierEntries()
	{
		return modifierEntries;
	}

	public void setModifierEntries(List<ModifierEntry> modifierEntries)
	{
		this.modifierEntries = modifierEntries;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getMin()
	{
		return min;
	}

	public void setMin(int min)
	{
		this.min = min;
	}

	public int getMax()
	{
		return max;
	}

	public void setMax(int max)
	{
		this.max = max;
	}

}
