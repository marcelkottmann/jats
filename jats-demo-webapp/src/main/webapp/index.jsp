<html>
<body>
<h2>Hello World!</h2>
<script type="text/javascript" src="/lib/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/lib/js/underscore.js"></script>
<script type="text/javascript" src="/lib/js/jats.js"></script>
<script type="text/javascript" src="/js/demo.js"></script>

<script type="text/javascript">
show = function(data)
{
	var product=data[0];
	alert(product.getLabel());
};

JATS.find(show, "products",0);
</script>
</body>
</html>
