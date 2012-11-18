package com.github.pepe79.jats.demo;

public class IdGenerator
{
	private int id = 0;

	public int nextId()
	{
		return ++id;
	}
}
