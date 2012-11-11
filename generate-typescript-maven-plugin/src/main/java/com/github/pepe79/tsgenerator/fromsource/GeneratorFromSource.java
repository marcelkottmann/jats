package com.github.pepe79.tsgenerator.fromsource;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import java.io.File;

import com.github.pepe79.tsgenerator.descriptor.ClassDescriptor;


public class GeneratorFromSource
{

	public static ClassDescriptor createClassDescriptor(File file)
	{
		CompilationUnit cu = null;
		try
		{
			cu = JavaParser.parse(file);
		}
		catch (Exception e)
		{
			throw new RuntimeException("error", e);
		}

		ClassDescriptor cd = new ClassDescriptor();
		BeanInfoExtractor extractor = new BeanInfoExtractor();
		extractor.visit(cu, cd);

		cd.setEnums(extractor.getInnerEnums());

		return cd;
	}

}