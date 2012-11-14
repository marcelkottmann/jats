package com.github.pepe79.jats.js;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public abstract class ScriptTest
{
	protected ScriptEngine engine;

	public ScriptEngine createScriptEngine(String[] scripts) throws ScriptException, FileNotFoundException
	{
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("ECMAScript");
		engine.eval("console={log:println};");

		loadScripts(engine, scripts);

		return engine;
	}

	public void loadScripts(ScriptEngine engine, String[] scripts) throws FileNotFoundException, ScriptException
	{
		for (String script : scripts)
		{
			engine.eval(new FileReader(new File(script)));
		}
	}
}
