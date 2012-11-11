package com.github.pepe79.tsgenerator.generator;

import com.github.pepe79.tsgenerator.descriptor.ClassDescriptor;
import com.github.pepe79.tsgenerator.descriptor.EnumDescriptor;
import com.github.pepe79.tsgenerator.descriptor.PropertyDescriptor;
import com.github.pepe79.tsgenerator.descriptor.TypeDescriptor;


public class TypeScriptGenerator
{
	

	

	public static String generate(EnumDescriptor descriptor, String containerName)
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append("class ");
		buffer.append(containerName + "_" + descriptor.getName());
		buffer.append("\n{\n");
		for (String value : descriptor.getValues())
		{
			buffer.append("\tpublic static ");
			buffer.append(value);
			buffer.append(" = ");
			buffer.append("\"");
			buffer.append(value);
			buffer.append("\";\n");
		}
		buffer.append("\n");
		buffer.append("\tprivate value: string;\n");
		buffer.append("\n");
		buffer.append("\tpublic equals(other: any) {\n");
		buffer.append("\t\tif (other && other instanceof ");
		buffer.append(containerName + "_" + descriptor.getName());
		buffer.append(") {\n");
		buffer.append("\t\t\treturn this.value == (<");
		buffer.append(containerName + "_" + descriptor.getName());
		buffer.append(">other).getValue();\n");
		buffer.append("\t\t} else {\n");
		buffer.append("\t\t\treturn this.value == other;\n");
		buffer.append("\t\t}\n");
		buffer.append("\t}\n");
		buffer.append("\n");
		buffer.append("\tpublic getValue() { return this.value; }\n");
		buffer.append("}\n");

		return buffer.toString();
	}

	public static String generate(GenerationContext context, ClassDescriptor descriptor)
	{
		StringBuffer buffer = new StringBuffer();

		context.addGeneratedType(descriptor.getName());

		if (descriptor.getSuperClass() != null)
		{
			TypeDescriptor sct = new TypeDescriptor();
			sct.setComponentType(descriptor.getSuperClass());
			sct.setType(descriptor.getSuperClass());
			sct.setExtern(true);
			context.addExternalType(sct.getComponentType(), descriptor.getName());
		}

		for (PropertyDescriptor pd : descriptor.getProperties().values())
		{
			if (pd.getType().isExtern() && !descriptor.getEnums().containsKey(pd.getType().getComponentType()))
			{
				context.addExternalType(pd.getType().getComponentType(), null);
			}

			if (descriptor.getEnums().containsKey(pd.getType().getComponentType()))
			{
				pd.getType().setType(descriptor.getName() + "_" + pd.getType().getType());
			}
		}

		for (EnumDescriptor enumDescriptor : descriptor.getEnums().values())
		{
			buffer.append(generate(enumDescriptor, descriptor.getName()));
		}

		if (descriptor.getEnums().size() > 0)
		{
			buffer.append("\n");
		}

		buffer.append("class ");
		buffer.append(descriptor.getName());
		if (descriptor.getSuperClass() != null)
		{
			buffer.append(" extends ");
			buffer.append(descriptor.getSuperClass());
		}
		buffer.append("\n{\n");

		for (EnumDescriptor enumDescriptor : descriptor.getEnums().values())
		{
			buffer.append("\tpublic static " + enumDescriptor.getName() + " = " + descriptor.getName() + "_"
				+ enumDescriptor.getName() + ";\n\n");
		}

		for (PropertyDescriptor pd : descriptor.getProperties().values())
		{
			buffer.append("\tprivate ");
			buffer.append(pd.getName());
			if (pd.getType() != null)
			{
				buffer.append(" : ");
				buffer.append(pd.getType().getType());
			}
			buffer.append(";\n\n");
		}

		for (PropertyDescriptor pd : descriptor.getProperties().values())
		{
			if (pd.isReadable())
			{
				buffer.append("\tpublic ");
				buffer.append(pd.getReadMethodName());
				buffer.append("() { return this.");
				buffer.append(pd.getName());
				buffer.append("; }\n");
			}

			if (pd.isWritable())
			{
				buffer.append("\tpublic ");
				buffer.append(pd.getWriteMethodName());
				buffer.append("( ");
				buffer.append(pd.getName());
				if (pd.getType() != null)
				{
					buffer.append(" : ");
					buffer.append(pd.getType().getType());
				}
				buffer.append(" ) { this.");
				buffer.append(pd.getName());
				buffer.append(" = ");
				buffer.append(pd.getName());
				buffer.append("; }\n");
			}

			buffer.append("\n");
		}

		buffer.append("\n}\n");

		context.addExternalType(descriptor.getName(), null);

		return buffer.toString();
	}

	public static String generateReferences(GenerationContext context)
	{
		StringBuffer buffer = new StringBuffer();
		for (String type : context.getExternalTypes())
		{
			buffer.append(generateReferences(type + ".ts"));
		}
		return buffer.toString();
	}

	public static String generateReferences(String fileName)
	{
		return "///<reference path='" + fileName + "' />\n";
	}

}
