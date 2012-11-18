package com.github.pepe79.jats.demo.model;

public class Quantity
{
	private Uom uom;

	private double quantity;

	public Uom getUom()
	{
		return uom;
	}

	public void setUom(Uom uom)
	{
		this.uom = uom;
	}

	public double getQuantity()
	{
		return quantity;
	}

	public void setQuantity(double quantity)
	{
		this.quantity = quantity;
	}

	public String getDisplayQuantity()
	{
		return quantity + " " + uom.getCode();
	}

}
