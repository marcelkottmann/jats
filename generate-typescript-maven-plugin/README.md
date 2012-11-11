<h1>generate-typescript-maven-plugin</h1>
The generate-typescript-maven-plugin generates typescript classes from java classes.

The input may be java source code (preferable) or (compiled) 
java class-files (if you don't have the source code).

The typescript-generator generates all java-bean-properties from your java class to the corresponding 
typescript class with corresponding getter and setter methods. 

Depending on the input (source-file or class-file) the typescript-generator will map the java types to the 
best matching typescript data type. The main difference between source- or class-file as input of the 
generator is that the component types of generic classes can't be extracted from compiled java-classes 
(because of type erasure).


usage:
add following snippet to the build-plugins section of your pom.xml 

`
<plugin>
	<groupId>com.github.pepe79.tsgenerator</groupId>
	<artifactId>generate-typescript-maven-plugin</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<configuration>
		<targetDirectory>src/main/ts</targetDirectory>
		<sourceDirectory>src/main/java</sourceDirectory>
		<packageDirectory>[PACKAGE_DIRECTORY_IN_SOURCE_PATH]</packageDirectory>
		
		<!-- Exclude list of simple class names (without package) -->
		<!--
		<excludes>
			<exclude></exclude>
			<exclude></exclude>
			<exclude></exclude>
		</excludes>
		-->

		<!-- Include sources outside of your configured source package -->
		<!--
		<includeSources>
			<includeSource>package/directory/to/source/Source.java</includeSource>
		</includeSources>
		-->

		<!-- Include compiled classes (dont forget to add the corresponding jar to the plugin dependencies) -->
		<!--
		<classes>
			<class>full.qualified.Classname.Here</class>
			<class>...</class>
		</classes>
		-->
		
	</configuration>
	<dependencies>
		<!-- If you have included classes for generation you have to configure the corresponding jars, 
		where this classes can be found. -->
		<!--
		<dependency>
			<groupId>...</groupId>
			<artifactId>...</artifactId>
			<version>...</version>
		</dependency>
		-->
	</dependencies>
</plugin>
`


