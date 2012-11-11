package com.github.pepe79.tsgenerator.maven;

import com.github.pepe79.tsgenerator.descriptor.ClassDescriptor;
import com.github.pepe79.tsgenerator.fromclass.GeneratorFromClass;
import com.github.pepe79.tsgenerator.fromsource.GeneratorFromSource;
import com.github.pepe79.tsgenerator.generator.GenerationContext;
import com.github.pepe79.tsgenerator.generator.TypeScriptGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;


/**
 * @goal generate-ts
 * @phase compile
 */
public class GenerateTsMojo extends AbstractMojo
{
	/**
	 * @parameter expression="${ts.targetDirectory}" default-value="src/main/ts"
	 * @required
	 */
	private File targetDirectory;

	/**
	 * @parameter expression="${ts.sourceDirectory}" default-value="src/main/java"
	 */
	private File sourceDirectory;

	/**
	 * @parameter expression="${ts.packageDirectory}"
	 * @required
	 */
	private String packageDirectory;

	/**
	 * @parameter expression="${ts.includeSources.includeSource}"
	 */
	private String[] includeSources;

	/**
	 * @parameter expression="${ts.classes.class}"
	 */
	private String[] classes;

	/**
	 * @parameter expression="${ts.excludes.exclude}"
	 */
	private String[] excludes;

	/**
	 * Encoding for files
	 * 
	 * @parameter expression="${project.build.sourceEncoding}
	 */
	private String encoding = "utf-8";

	private void compile(GenerationContext context, File source, File target)
	{
		ClassDescriptor cd = GeneratorFromSource.createClassDescriptor(source);

		if (!isInExclude(cd.getName()))
		{
			String s = TypeScriptGenerator.generate(context, cd);
			writeStringToFile(s, target);
		}
	}

	@Override
	public void execute() throws MojoExecutionException
	{
		File modelDirectory = new File(sourceDirectory, packageDirectory);
		prepare(modelDirectory);
		prepare(targetDirectory);

		int compiledFiles = 0;
		getLog().info("Searching for java files in modelDirectory: " + modelDirectory);
		Collection<File> files = FileUtils.listFiles(modelDirectory, new String[]{"java"}, true);
		getLog().info(files.size() + " java files found in modelDirectory: " + modelDirectory);

		if (includeSources != null)
		{
			for (String includeSource : includeSources)
			{
				files.add(new File(sourceDirectory, includeSource));
			}
		}

		GenerationContext context = new GenerationContext();
		for (File file : files)
		{
			try
			{
				String targetFileName = FilenameUtils.removeExtension(FilenameUtils.getName(file.getName())) + ".ts";
				File targetFile = new File(targetDirectory, targetFileName).getAbsoluteFile();

				getLog().info("input file: " + file);
				getLog().info("output file: " + targetFile);

				if (file.exists())
				{
					getLog().info(String.format("Compiling: %s", file));
					compile(context, file, targetFile);
					getLog().info(String.format("Generated: %s", targetFileName));
					compiledFiles++;
				}
			}
			catch (Exception e)
			{
				getLog().error("error", e);
				throw new RuntimeException("Error", e);
			}
		}

		for (String clazz : classes)
		{
			ClassDescriptor cd = GeneratorFromClass.createClassDescriptor(clazz);
			if (!isInExclude(cd.getName()))
			{
				String s = TypeScriptGenerator.generate(context, cd);
				File targetFile = new File(targetDirectory, cd.getName() + ".ts").getAbsoluteFile();
				writeStringToFile(s, targetFile);
			}
		}

		// create references file of compiled files.
		File referencesFile = new File(targetDirectory, "project.ts").getAbsoluteFile();
		getLog().info(String.format("Generating references: %s", referencesFile.toString()));
		generateReferences(context, referencesFile);

		if (compiledFiles == 0)
		{
			getLog().info("Nothing to compile.");
		}
		else
		{
			getLog().info(String.format("Compiled %s files", compiledFiles));
		}
	}

	private void generateReferences(GenerationContext context, File target)
	{
		String s = TypeScriptGenerator.generateReferences(context);
		writeStringToFile(s, target);
	}

	public String[] getClasses()
	{
		return classes;
	}

	public String getEncoding()
	{
		return encoding;
	}

	public String[] getExcludes()
	{
		return excludes;
	}

	public String[] getIncludeSources()
	{
		return includeSources;
	}

	public String getPackageDirectory()
	{
		return packageDirectory;
	}

	public File getSourceDirectory()
	{
		return sourceDirectory;
	}

	public File getTargetDirectory()
	{
		return targetDirectory;
	}

	private boolean isInExclude(String name)
	{
		if (excludes != null)
		{
			for (String exclude : excludes)
			{
				if (exclude.equals(name))
				{
					return true;
				}
			}
		}

		return false;
	}

	private void prepare(File f)
	{
		if (!f.exists())
		{
			f.mkdirs();
		}
	}

	public void setClasses(String[] classes)
	{
		this.classes = classes;
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

	public void setExcludes(String[] excludes)
	{
		this.excludes = excludes;
	}

	public void setIncludeSources(String[] includeSources)
	{
		this.includeSources = includeSources;
	}

	public void setPackageDirectory(String packageDirectory)
	{
		this.packageDirectory = packageDirectory;
	}

	public void setSourceDirectory(File sourceDirectory)
	{
		this.sourceDirectory = sourceDirectory;
	}

	public void setTargetDirectory(File targetDirectory)
	{
		this.targetDirectory = targetDirectory;
	}

	private void writeStringToFile(String s, File target)
	{
		FileWriter fw = null;
		try
		{
			fw = new FileWriter(target);
			IOUtils.copy(new StringReader(s), fw);
		}
		catch (IOException e)
		{
			throw new RuntimeException("error", e);
		}
		finally
		{
			IOUtils.closeQuietly(fw);
		}
	}

}
