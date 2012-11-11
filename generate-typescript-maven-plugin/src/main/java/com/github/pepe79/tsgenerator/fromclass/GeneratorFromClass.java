package com.github.pepe79.tsgenerator.fromclass;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ClassUtils;

import com.github.pepe79.tsgenerator.descriptor.ClassDescriptor;
import com.github.pepe79.tsgenerator.descriptor.TypeDescriptor;


public class GeneratorFromClass
{
	public static final Set<String> BLACKLIST = Collections.unmodifiableSet(new HashSet<String>()
			{
				{
					add("class");
				}
			});
	
	private static TypeDescriptor convertType(Class<?> propertyType)
	{
		StringBuffer typeDeclaration = new StringBuffer();
		boolean array = false;
		if (propertyType.isArray())
		{
			array = true;
			propertyType = propertyType.getComponentType();
		}

		if (List.class.isAssignableFrom(propertyType))
		{
			array = true;
		}

		if (propertyType.isPrimitive())
		{
			propertyType = ClassUtils.primitiveToWrapper(propertyType);
		}

		if (String.class.isAssignableFrom(propertyType))
		{
			typeDeclaration.append(TypeDescriptor.STRING);
		}
		else if (Number.class.isAssignableFrom(propertyType))
		{
			typeDeclaration.append(TypeDescriptor.NUMBER);
		}
		else if (Boolean.class.isAssignableFrom(propertyType))
		{
			typeDeclaration.append(TypeDescriptor.BOOL);
		}
		else
		{
			typeDeclaration.append(TypeDescriptor.ANY);
		}

		String componentType = typeDeclaration.toString();

		if (array)
		{
			typeDeclaration.append("[]");
		}

		TypeDescriptor td = new TypeDescriptor();
		td.setType(typeDeclaration.toString());
		td.setComponentType(componentType);

		return td;
	}

	public static ClassDescriptor createClassDescriptor(Class<?> clazz)
	{
		try
		{
			ClassDescriptor cd = new ClassDescriptor();
			BeanInfo info = Introspector.getBeanInfo(clazz);
			cd.setName(info.getBeanDescriptor().getBeanClass().getSimpleName());

			Class<?> superClass = info.getBeanDescriptor().getBeanClass().getSuperclass();
			if (!superClass.equals(Object.class))
			{
				cd.setSuperClass(superClass.getSimpleName());
			}

			for (PropertyDescriptor pd : info.getPropertyDescriptors())
			{
				if (!BLACKLIST.contains(pd.getName())
					&& (pd.getReadMethod() != null || pd.getWriteMethod() != null))
				{
					com.github.pepe79.tsgenerator.descriptor.PropertyDescriptor pdd = new com.github.pepe79.tsgenerator.descriptor.PropertyDescriptor();
					pdd.setName(pd.getName());
					pdd.setReadMethodName(pd.getReadMethod() != null ? pd.getReadMethod().getName() : null);
					pdd.setWriteMethodName(pd.getWriteMethod() != null ? pd.getWriteMethod().getName() : null);
					pdd.setType(convertType(pd.getPropertyType()));
					cd.getProperties().put(pdd.getName(), pdd);
				}
			}

			return cd;
		}
		catch (IntrospectionException e)
		{
			return null;
		}
	}

	public static ClassDescriptor createClassDescriptor(String clazzName)
	{
		try
		{
			return createClassDescriptor(Class.forName(clazzName));
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException("error", e);
		}
	}

}
