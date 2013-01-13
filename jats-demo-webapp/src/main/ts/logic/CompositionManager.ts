class CompositionManager {

    private compositionConfigurator: CompositionConfigurator;

    constructor(compositionConfigurator:CompositionConfigurator) {
        this.compositionConfigurator = compositionConfigurator;
    }

    public createDefaultComposition(component : Component, template : Composition) : Composition {
        
        var defaultComposition = new Composition(component.getId());

        //create required components
        component.getRequiredComponents().forEach((requiredComponent) =>
        {
            this.compositionConfigurator.addComponent(defaultComposition, requiredComponent.getId(), 1);
        });

        component.getOptionalComponents().forEach((optionalComponent) =>
        {
            this.compositionConfigurator.addComponent(defaultComposition, optionalComponent.getId(), 1);
        });

        return defaultComposition;
    }

}