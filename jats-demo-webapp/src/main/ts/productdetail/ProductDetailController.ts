class ProductDetailController {

    public static compositionManager: CompositionManager;

    public static product: Product;

    public static composition: Composition;

    public static init() {
        CacheManager.init();
        CacheManager.load(function() {
            ProductDetailController.compositionManager = new CompositionManager(new CompositionConfigurator());
            ProductDetailController.product = CacheManager.getObject(1);
            ProductDetailController.composition = ProductDetailController.compositionManager.createDefaultComposition(ProductDetailController.product.getVariants()[0], null);
            ProductDetailController.reRender();
        });
    }

    public static reRender() {
        renderProduct(ProductDetailController.product, ProductDetailController.composition);
        renderComposition(ProductDetailController.composition);
    }


}