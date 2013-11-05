class CompositionManager {

    private compositionConfigurator: CompositionConfigurator;

    constructor(compositionConfigurator: CompositionConfigurator) {
        this.compositionConfigurator = compositionConfigurator;
    }

    public createDefaultComposition(component: Component, template: Composition): Composition {

        var defaultComposition = new Composition(component);
        defaultComposition.setQuantity(1);

        //create required components
        component.getRequiredComponents().forEach((requiredComponent) => {
            this.compositionConfigurator.addComponent(defaultComposition, requiredComponent, 1);
        });

        component.getOptionalComponents().forEach((optionalComponent) => {
            this.compositionConfigurator.addComponent(defaultComposition, optionalComponent, 1);
        });

        return defaultComposition;
    }

    public getConfigurator(): CompositionConfigurator {
        return this.compositionConfigurator;
    }




}