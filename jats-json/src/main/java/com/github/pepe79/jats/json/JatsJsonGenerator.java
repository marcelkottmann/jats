package com.github.pepe79.jats.json;

import java.io.IOException;
import java.util.Stack;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.util.JsonGeneratorDelegate;

public class JatsJsonGenerator extends JsonGeneratorDelegate
{

	private Stack<String> typeNames = new Stack<String>();

	public JatsJsonGenerator(JsonGenerator d)
	{
		super(d);
	}

	public void addTypeName(String typeName)
	{
		typeNames.add(typeName);
	}

	@Override
	public void writeEndObject() throws IOException, JsonGenerationException
	{
		super.writeEndObject();
		if (!typeNames.isEmpty())
		{
			typeNames.pop();
		}
	}

	@Override
	public void writeStartObject() throws IOException, JsonGenerationException
	{
		super.writeStartObject();
		if (!typeNames.isEmpty())
		{
			String typeName = typeNames.peek();
			if (typeName != null)
			{
				writeType(typeName);
			}
		}
	}

	private void writeType(String typeName) throws JsonGenerationException,
			IOException
	{
		writeFieldName("$type");
		writeString(typeName);
	}

}
