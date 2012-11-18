package com.github.pepe79.jats.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.util.DefaultPrettyPrinter;

import com.github.pepe79.jats.json.idextractor.IdExtractor;

public class JatsJsonSerializer<T> extends JsonSerializer<T>
{

	private JsonSerializer<T> wrapped;

	private IdExtractor idExtractor;

	private Map<Object, Object> idToObjects = new HashMap<Object, Object>();

	private boolean pretty;

	public JatsJsonSerializer(JsonSerializer<T> wrapped, IdExtractor idExtractor)
	{
		this.wrapped = wrapped;
		this.idExtractor = idExtractor;
	}

	@Override
	public void serialize(T obj, JsonGenerator generator, SerializerProvider provider) throws IOException,
			JsonProcessingException
	{
		JsonGenerator delegate = generator;
		if (!(delegate instanceof JatsJsonGenerator))
		{
			delegate = new JatsJsonGenerator(generator);
			if (pretty)
			{
				generator.useDefaultPrettyPrinter();
			}
		}

		if (wrapped != null)
		{
			Object id = null;

			if (idExtractor != null)
			{
				id = idExtractor.extractId(obj);
			}

			Object reference = null;
			if (id != null)
			{
				reference = idToObjects.get(id);
			}

			if (reference != null)
			{
				((JatsJsonGenerator) delegate).addTypeName(null);
				delegate.writeStartObject();
				delegate.writeFieldName("$ref");
				delegate.writeString(id.toString());
				delegate.writeEndObject();
			}
			else
			{
				((JatsJsonGenerator) delegate).addTypeName(obj.getClass().getSimpleName());
				if (id != null)
				{
					idToObjects.put(id, obj);
				}
				wrapped.serialize(obj, delegate, provider);
			}
		}
		else
		{
			((JatsJsonGenerator) delegate).addTypeName(obj.getClass().getSimpleName());
			delegate.writeStartObject();
			delegate.writeEndObject();
		}
	}

	public boolean isPretty()
	{
		return pretty;
	}

	public void setPretty(boolean pretty)
	{
		this.pretty = pretty;
	}

}
