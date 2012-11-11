package com.github.pepe79.tsgenerator.maven;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import com.github.pepe79.tsgenerator.descriptor.ClassDescriptor;
import com.github.pepe79.tsgenerator.fromclass.GeneratorFromClass;
import com.github.pepe79.tsgenerator.fromsource.GeneratorFromSource;
import com.github.pepe79.tsgenerator.generator.GenerationContext;
import com.github.pepe79.tsgenerator.generator.TypeScriptGenerator;

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
	 * @parameter expression="${ts.generatedClassesDirectory}"
	 *            default-value="model"
	 * @required
	 */
	private String generatedClassesDirectory;

	/**
	 * @parameter expression="${ts.generatedProjectTsDirectory}"
	 *            default-value="project"
	 * @required
	 */
	private String generatedProjectTsDirectory;

	/**
	 * @parameter expression="${ts.useRelativePathInProjectTs}"
	 *            default-value="../"
	 * @required
	 */
	private String useRelativePathInProjectTs;

	/**
	 * @parameter expression="${ts.sourceDirectory}"
	 *            default-value="src/main/java"
	 */
	private File sourceDirectory;

	/**
	 * @parameter expression="${ts.packageDirectories.packageDirectory}"
	 * @required
	 */
	private String[] packageDirectories;

	/**
	 * @parameter expression="${ts.projectTsFileName}"
	 *            default-value="project.ts"
	 */
	private String projectTsFileName;

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
		File targetModelDirectory = new File(targetDirectory,
				generatedClassesDirectory);
		File targetProjectTsDirectory = new File(targetDirectory,
				generatedProjectTsDirectory);

		prepare(targetDirectory);
		prepare(targetModelDirectory);
		prepare(targetProjectTsDirectory);

		int generatedFiles = 0;
		List<File> collectedSourceFiles = new ArrayList<File>();
		for (String packageDirectory : packageDirectories)
		{
			File modelDirectory = new File(sourceDirectory, packageDirectory);

			getLog().info(
					"Searching for java files in directory: " + modelDirectory);
			collectedSourceFiles.addAll(FileUtils.listFiles(modelDirectory,
					new String[]
					{ "java" }, true));
		}
		getLog().info(collectedSourceFiles.size() + " java files found.");

		if (includeSources != null)
		{
			for (String includeSource : includeSources)
			{
				collectedSourceFiles.add(new File(sourceDirectory,
						includeSource));
			}
		}

		GenerationContext context = new GenerationContext();
		for (File file : collectedSourceFiles)
		{
			try
			{
				String targetFileName = FilenameUtils
						.removeExtension(FilenameUtils.getName(file.getName()))
						+ ".ts";
				File targetFile = new File(targetModelDirectory, targetFileName)
						.getAbsoluteFile();

				getLog().info("input file: " + file);
				getLog().info("output file: " + targetFile);

				if (file.exists())
				{
					compile(context, file, targetFile);
					getLog().info(
							String.format("Generated: %s", targetFileName));
					generatedFiles++;
				}
			} catch (Exception e)
			{
				getLog().error("error", e);
				throw new RuntimeException("Error", e);
			}
		}

		for (String clazz : classes)
		{
			ClassDescriptor cd = GeneratorFromClass
					.createClassDescriptor(clazz);
			if (!isInExclude(cd.getName()))
			{
				String s = TypeScriptGenerator.generate(context, cd);
				File targetFile = new File(targetModelDirectory, cd.getName()
						+ ".ts").getAbsoluteFile();
				writeStringToFile(s, targetFile);
				generatedFiles++;
			}
		}

		// create references file of compiled files.
		File referencesFile = new File(targetProjectTsDirectory,
				projectTsFileName).getAbsoluteFile();
		getLog().info(
				String.format("Generating references: %s",
						referencesFile.toString()));
		generateReferences(context, referencesFile);
		generatedFiles++;

		getLog().info(String.format("%s files generated.", generatedFiles));
	}

	private void generateReferences(GenerationContext context, File target)
	{
		String relativePath = useRelativePathInProjectTs
				+ generatedClassesDirectory;
		if (StringUtils.isNotEmpty(relativePath) && !relativePath.endsWith("/"))
		{
			relativePath = relativePath + "/";
		}

		String s = TypeScriptGenerator
				.generateReferences(relativePath, context);
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

	public String[] getPackageDirectories()
	{
		return packageDirectories;
	}

	public void setPackageDirectories(String[] packageDirectories)
	{
		this.packageDirectories = packageDirectories;
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
		} catch (IOException e)
		{
			throw new RuntimeException("error", e);
		} finally
		{
			IOUtils.closeQuietly(fw);
		}
	}

	public String getGeneratedClassesDirectory()
	{
		return generatedClassesDirectory;
	}

	public void setGeneratedClassesDirectory(String generatedClassesDirectory)
	{
		this.generatedClassesDirectory = generatedClassesDirectory;
	}

	public String getGeneratedProjectTsDirectory()
	{
		return generatedProjectTsDirectory;
	}

	public void setGeneratedProjectTsDirectory(
			String generatedProjectTsDirectory)
	{
		this.generatedProjectTsDirectory = generatedProjectTsDirectory;
	}

	public String getUseRelativePathInProjectTs()
	{
		return useRelativePathInProjectTs;
	}

	public void setUseRelativePathInProjectTs(String useRelativePathInProjectTs)
	{
		this.useRelativePathInProjectTs = useRelativePathInProjectTs;
	}

	public String getProjectTsFileName()
	{
		return projectTsFileName;
	}

	public void setProjectTsFileName(String projectTsFileName)
	{
		this.projectTsFileName = projectTsFileName;
	}

}
