package com.github.pepe79.jats.json;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;
import org.codehaus.jackson.map.ser.BeanSerializerModifier;

import com.github.pepe79.jats.json.idextractor.IdExtractor;

public class JatsBeanSerializerModifier extends BeanSerializerModifier
{

	private Set<String> propertyWhiteList;

	private IdExtractor idExtractor;

	private boolean pretty;

	public JatsBeanSerializerModifier(IdExtractor idExtractor, Set<String> propertyWhiteList)
	{
		this.propertyWhiteList = propertyWhiteList;
		this.idExtractor = idExtractor;
	}

	@Override
	public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BasicBeanDescription beanDesc,
			List<BeanPropertyWriter> beanProperties)
	{
		{
			Iterator<BeanPropertyWriter> it = beanProperties.iterator();
			while (it.hasNext())
			{
				BeanPropertyWriter bpw = it.next();

				if (propertyWhiteList != null && !propertyWhiteList.contains(bpw.getName()))
				{
					it.remove();
				}
			}
		}

		return beanProperties;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonSerializer<Object> modifySerializer(SerializationConfig config, BasicBeanDescription beanDesc,
			JsonSerializer<?> serializer)
	{
		JatsJsonSerializer<Object> jatsSerializer = new JatsJsonSerializer<Object>((JsonSerializer<Object>) serializer,
				idExtractor);
		jatsSerializer.setPretty(pretty);
		return jatsSerializer;
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
