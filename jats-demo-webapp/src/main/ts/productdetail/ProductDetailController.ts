class ProductDetailController {

    public static compositionConfigurator: CompositionConfigurator;

    public static product: Component;

    public static composition: Composition;

    public static init() {
        JATS.find((categories: any[]) => {
            renderNavi(categories[0]);
        }, "Category", 2000, "productlist");

        ProductDetailController.compositionConfigurator = new CompositionConfigurator();
        ProductDetailController.loadProduct("1");
    }

    public static setProduct(product: Component) {
        ProductDetailController.product = product;
        ProductDetailController.composition = ProductDetailController.compositionConfigurator.createDefaultComposition(product, null);
        ProductDetailController.reRender();
    }

    public static loadProduct(id: string) {
        JATS.find(function(products: Component[]) {
            ProductDetailController.setProduct(products[0]);
        }, "Component", id);
    }

    public static getVariant(vvalue: any[]): Component {
        for (var i = 0; i < ProductDetailController.product.getVariants().length; i++) {
            var variant = ProductDetailController.product.getVariants()[i];
            var equal = 0;
            for (var v = 0; v < vvalue.length; v++) {
                if (variant.getVariantValues()[v] == vvalue[v]) {
                    equal++;
                }
            }
            if (equal == vvalue.length) {
                return variant;
            }
        }
        return null;
    }

    public static add(type: string, id: string, quantity: number) {
        JATS.find((components: any[]) => {
            ProductDetailController.compositionConfigurator.addComponent(
                ProductDetailController.composition, components[0], quantity);
            ProductDetailController.reRender();
        }, type, id);
    }

    public static reRender() {
        renderProduct(ProductDetailController.product, ProductDetailController.composition);
        renderComposition(ProductDetailController.composition);
    }

}