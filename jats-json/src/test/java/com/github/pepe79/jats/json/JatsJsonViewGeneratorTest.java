package com.github.pepe79.jats.json;

import java.io.StringWriter;

import org.junit.Test;

import com.github.pepe79.jats.json.idextractor.BeanPropertyIdExtractor;

public class JatsJsonViewGeneratorTest
{


	public class SimpleObject
	{
		private int id;

		private String name;

		private SimpleObject simpleObject;

		public SimpleObject(int id, String name, SimpleObject simpleObject)
		{
			this.id = id;
			this.name = name;
			this.simpleObject = simpleObject;
		}

		public String getName()
		{
			return name;
		}

		public int getId()
		{
			return id;
		}

		public SimpleObject getSimpleObject()
		{
			return simpleObject;
		}
	}

	@Test
	public void testToJsonSimpleObject()
	{
		JastJsonViewGenerator viewGenerator = new JastJsonViewGenerator();
		StringWriter sw = new StringWriter();
		viewGenerator.toJson(sw, new SimpleObject(1, "Nr. 1", null), null, false);
		System.out.println(sw.toString());
	}

	@Test
	public void testToJsonSimpleObjects()
	{
		JastJsonViewGenerator viewGenerator = new JastJsonViewGenerator();
		StringWriter sw = new StringWriter();

		SimpleObject so1 = new SimpleObject(1, "Nr. 1", null);
		SimpleObject so2 = new SimpleObject(2, "Nr. 2", so1);

		SimpleObject[] objects = new SimpleObject[]
		{ so1, so2 };

		viewGenerator.toJson(sw, objects, null, false);

		System.out.println(sw.toString());
	}

	@Test
	public void testToJsonSimpleObjectsWithIdExtractor()
	{
		// construct a simple object
		SimpleObject so1 = new SimpleObject(1, "Nr. 1", null);

		// construct a reference to so1:
		SimpleObject so2 = new SimpleObject(2, "Nr. 2", so1);

		// create array of simple objexts so1 and so2
		SimpleObject[] objects = new SimpleObject[]
		{ so1, so2 };

		// construct a view generator with a BeanPropertyIdExtractor for
		// extracting ids from the given objects
		JastJsonViewGenerator viewGenerator = new JastJsonViewGenerator(new BeanPropertyIdExtractor("id", true), null);

		StringWriter sw = new StringWriter();

		// generate json and write it to stringwriter sw.
		viewGenerator.toJson(sw, objects, null, false);

		System.out.println(sw.toString());
	}
}
