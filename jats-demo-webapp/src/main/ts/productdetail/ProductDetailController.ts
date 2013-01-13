class ProductDetailController {

    public static compositionManager:CompositionManager ;

    public static product:Product;

    public static composition:Composition;

    public static init() {
        CacheManager.init();
        CacheManager.load(function () {
            compositionManager = new CompositionManager(new CompositionConfigurator());
            product = CacheManager.getObject(1);
            composition = compositionManager.createDefaultComposition(product.getVariants()[0], null);
            alert(composition.toString());
        });
    }


}