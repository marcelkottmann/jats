package com.github.pepe79.tsgenerator.fromsource;

import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.EnumConstantDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.beans.Introspector;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.github.pepe79.tsgenerator.descriptor.ClassDescriptor;
import com.github.pepe79.tsgenerator.descriptor.EnumDescriptor;
import com.github.pepe79.tsgenerator.descriptor.PropertyDescriptor;
import com.github.pepe79.tsgenerator.descriptor.TypeDescriptor;


public class BeanInfoExtractor extends VoidVisitorAdapter<ClassDescriptor>
{

	private int classDepth = 0;

	private Map<String, EnumDescriptor> innerEnums = new LinkedHashMap<String, EnumDescriptor>();

	private static final Map<String, String> TYPEMAPPER = Collections.unmodifiableMap(new HashMap<String, String>()
	{
		{
			put("Object", TypeDescriptor.ANY);

			put("String", TypeDescriptor.STRING);

			put("Integer", TypeDescriptor.NUMBER);
			put("int", TypeDescriptor.NUMBER);

			put("Float", TypeDescriptor.NUMBER);
			put("float", TypeDescriptor.NUMBER);

			put("Double", TypeDescriptor.NUMBER);
			put("double", TypeDescriptor.NUMBER);

			put("Boolean", TypeDescriptor.BOOL);
			put("boolean", TypeDescriptor.BOOL);

			put("List", TypeDescriptor.ANY_ARRAY);
			put("List<(.*)>", TypeDescriptor.ARRAY);
			put("Map<(.*)>", TypeDescriptor.MAP);

		}
	});

	private static final String GET = "get";

	private static final String SET = "set";

	private static final String IS = "is";

	private TypeDescriptor convertType(String sourceType)
	{
		TypeDescriptor td = new TypeDescriptor();
		convertType(sourceType, td);
		return td;
	}

	private TypeDescriptor convertType(String sourceType, TypeDescriptor td)
	{
		int array = sourceType.indexOf("[]");
		String componentType = sourceType;
		if (array >= 0)
		{
			componentType = componentType.substring(0, array);
		}

		String type = TYPEMAPPER.get(componentType);

		if (type == null)
		{
			for (Map.Entry<String, String> entry : TYPEMAPPER.entrySet())
			{
				Matcher m = Pattern.compile(entry.getKey()).matcher(componentType);
				if (m.matches())
				{
					componentType = m.group(1);
					type = TYPEMAPPER.get(componentType);
					if (type == null)
					{
						type = componentType;
						td.setExtern(true);
					}

					type = entry.getValue().replace("TYPE", type);
					break;
				}
			}
		}

		if (type == null)
		{
			type = componentType;
			td.setExtern(true);
		}

		if (array >= 0)
		{
			type = type + sourceType.substring(array);
		}

		if (type.contains("<") || type.contains(">"))
		{
			type = TypeDescriptor.ANY;
			td.setExtern(false);
		}

		if (componentType.contains("<") || componentType.contains(">"))
		{
			componentType = TypeDescriptor.ANY;
			td.setExtern(false);
		}

		td.setType(type);
		td.setComponentType(componentType);
		return td;
	}

	private String extractPropertyName(String prefix, String methodName)
	{
		return Introspector.decapitalize(methodName.substring(prefix.length()));
	}

	public Map<String, EnumDescriptor> getInnerEnums()
	{
		return innerEnums;
	}

	private PropertyDescriptor getPropertyName(MethodDeclaration methodDeclaration)
	{
		PropertyDescriptor pd = null;
		int size = methodDeclaration.getParameters() == null ? 0 : methodDeclaration.getParameters().size();
		switch (size)
		{
			case 0:
				if (methodDeclaration.getName().startsWith(GET))
				{
					pd = new PropertyDescriptor();
					pd.setName(extractPropertyName(GET, methodDeclaration.getName()));
					pd.setReadMethodName(methodDeclaration.getName());
					pd.setType(convertType(methodDeclaration.getType().toString()));
				}
				else if (methodDeclaration.getName().startsWith(IS))
				{
					pd = new PropertyDescriptor();
					pd.setName(extractPropertyName(IS, methodDeclaration.getName()));
					pd.setReadMethodName(methodDeclaration.getName());
					pd.setType(convertType(methodDeclaration.getType().toString()));
				}
			break;
			case 1:
				if (methodDeclaration.getName().startsWith(SET))
				{
					pd = new PropertyDescriptor();
					pd.setName(extractPropertyName(SET, methodDeclaration.getName()));
					pd.setWriteMethodName(methodDeclaration.getName());
					pd.setType(convertType(methodDeclaration.getParameters().get(0).getType().toString()));
				}
			break;
			default:
		}
		return pd;
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration declaration, ClassDescriptor cd)
	{
		classDepth++;
		if (classDepth == 1)
		{
			cd.setName(declaration.getName());
			if (declaration.getExtends() != null && declaration.getExtends().size() > 0)
			{
				cd.setSuperClass(declaration.getExtends().get(0).getName());
			}
		}

		super.visit(declaration, cd);
		classDepth--;
	}

	@Override
	public void visit(EnumDeclaration declaration, ClassDescriptor arg1)
	{
		EnumDescriptor ed = new EnumDescriptor();
		ed.setName(declaration.getName());
		ed.setValues(new ArrayList<String>());
		for (EnumConstantDeclaration ecd : declaration.getEntries())
		{
			ed.getValues().add(ecd.getName());
		}
		innerEnums.put(ed.getName(), ed);
	}

	@Override
	public void visit(MethodDeclaration n, ClassDescriptor cd)
	{
		if (classDepth == 1)
		{
			if (Modifier.isPublic(n.getModifiers()))
			{
				PropertyDescriptor propertyDescriptor = getPropertyName(n);
				if (propertyDescriptor != null)
				{
					PropertyDescriptor pd = cd.getProperties().get(propertyDescriptor.getName());
					if (pd == null)
					{
						cd.getProperties().put(propertyDescriptor.getName(), propertyDescriptor);
					}
					else
					{
						if (!StringUtils.equals(pd.getType().getType(), propertyDescriptor.getType().getType()))
						{
							throw new RuntimeException("Return type and setter type do not match. Property " + pd.getName());
						}

						// merge
						if (propertyDescriptor.getWriteMethodName() != null)
						{
							pd.setWriteMethodName(propertyDescriptor.getWriteMethodName());
						}

						if (propertyDescriptor.getReadMethodName() != null)
						{
							pd.setReadMethodName(propertyDescriptor.getReadMethodName());
						}
					}
				}
			}
		}
		super.visit(n, cd);
	}
}