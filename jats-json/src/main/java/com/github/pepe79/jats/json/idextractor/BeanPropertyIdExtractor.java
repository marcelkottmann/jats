package com.github.pepe79.jats.json.idextractor;

import org.apache.commons.beanutils.BeanUtils;

public class BeanPropertyIdExtractor implements IdExtractor
{
	private String propertyName;

	private boolean ignoreExceptions;

	public BeanPropertyIdExtractor(String propertyName, boolean ignoreExceptions)
	{
		this.propertyName = propertyName;
		this.ignoreExceptions = ignoreExceptions;
	}

	@Override
	public Object extractId(Object obj)
	{
		try
		{
			return BeanUtils.getProperty(obj, propertyName);
		}
		catch (Exception e)
		{
			if (!ignoreExceptions)
			{
				throw new RuntimeException(
						"An error occured while getting property '"
								+ propertyName + "'.", e);
			}
		}
		return null;
	}

}
