<h1>jats-json</h1>

Generates json from given java beans. The generator handles cyclic references in the object 
tree by generating "$ref"-references into the json data.

Consider the following "SimpleObject"

<pre>
public class SimpleObject
{
	private int id;

	private String name;

	private SimpleObject simpleObject;

	public SimpleObject(int id, String name, SimpleObject simpleObject)
	{
		this.id = id;
		this.name = name;
		this.simpleObject = simpleObject;
	}

	public String getName()
	{
		return name;
	}

	public int getId()
	{
		return id;
	}

	public SimpleObject getSimpleObject()
	{
		return simpleObject;
	}
}
</pre>


Now you can generate json wih the following few lines of code:
<pre>
// construct a simple object
SimpleObject so1 = new SimpleObject(1, "Nr. 1", null);

// construct a reference to so1:
SimpleObject so2 = new SimpleObject(2, "Nr. 2", so1);

// create array of simple objexts so1 and so2
SimpleObject[] objects = new SimpleObject[]
{ so1, so2 };

// construct a view generator with a BeanPropertyIdExtractor for
// extracting ids from the given objects
JastJsonViewGenerator viewGenerator = new JastJsonViewGenerator(new BeanPropertyIdExtractor("id", true), null);

StringWriter sw = new StringWriter();

// generate json and write it to stringwriter sw.
viewGenerator.toJson(sw, objects, null);

System.out.println(sw.toString());
</pre>


This outputs following json data (note the generated "$ref"-properties and the "jatsType"-property):
<pre>
[{"jatsType":SimpleObject,"id":1,"name":"Nr. 1"},{"jatsType":SimpleObject,"id":2,"name":"Nr. 2","simpleObject":{"$ref":"1"}}]
</pre>
