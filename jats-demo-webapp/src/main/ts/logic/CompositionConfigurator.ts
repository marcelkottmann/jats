class CompositionConfigurator {

//    private cacheManager: CacheManager;

    private findEntry(composition:Composition, subComponentId:number): Composition {
        for (var i = composition.getSubComponents().length - 1; i>=0; i--)
        {
            if (subComponentId === composition.getSubComponents()[i].getComponentId()) {
                return composition.getSubComponents()[i];
            }
        }
        return null;
    }

    public addComponent(composition : Composition, id : number, quantity:number) {
        var subComposition = this.findEntry(composition, id);
        if (subComposition == null) {
            subComposition = new Composition(id);
            composition.getSubComponents().push(subComposition);
        }
        subComposition.setQuantity(subComposition.getQuantity() + quantity);
    }


}