package com.github.pepe79.jats.js;

import java.io.FileNotFoundException;

import javax.script.ScriptException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ScriptUnitTest extends ScriptTest
{

	@Before
	public void setup() throws FileNotFoundException, ScriptException
	{
		engine = createScriptEngine(new String[]
		{ "target/test-classes/underscore.js", "target/js/jats.js" });
	}

	@Test
	public void testJsLoad() throws FileNotFoundException, ScriptException
	{
		Assert.assertTrue((Boolean) engine.eval("typeof JATS==='function'"));
		Assert.assertTrue((Boolean) engine.eval("typeof JATS.fromJson === 'function'"));

		engine.eval("var obj=JATS.fromJson({id:1})");
		Assert.assertTrue((Boolean) engine.eval("obj.id===1"));
	}

	@Test
	public void testFromJsonSimple() throws FileNotFoundException, ScriptException
	{
		Assert.assertEquals("1", engine.eval("JATS.fromJson({id:'1'}).id"));
	}

	@Test
	public void testFromJsonReference() throws FileNotFoundException, ScriptException
	{
		engine.eval("var obj=JATS.fromJson({id:'1', me:{$ref:'1'}})");
		Assert.assertTrue((Boolean) engine.eval("obj.me===obj"));
	}

}
