class CompositionConfigurator {

    //    private cacheManager: CacheManager;

    private findEntry(composition: Composition, subComponentId: number): Composition {
        if (subComponentId === composition.getComponentId()) {
            return composition;
        }

        for (var i = composition.getSubComponents().length - 1; i >= 0; i--) {
            if (subComponentId === composition.getSubComponents()[i].getComponent().getId()) {
                return composition.getSubComponents()[i];
            }
        }

        return null;
    }

    private removeEntry(composition: Composition, subComponentId: number) {
        for (var i = composition.getSubComponents().length - 1; i >= 0; i--) {
            if (subComponentId === composition.getSubComponents()[i].getComponent().getId()) {
                composition.getSubComponents().splice(i, 1);
            }
        }
    }

    public addComponent(composition: Composition, component: Component, quantity: number= 1): number {
        var subComposition = this.findEntry(composition, component.getId());
        if (subComposition == null) {
            subComposition = new Composition(component);
            subComposition.setQuantity(0);
            composition.getSubComponents().push(subComposition);
        }
        var q: number = subComposition.getQuantity() + quantity;
        if (q > 0) {
            subComposition.setQuantity(q);
        } else {
            this.removeEntry(composition, subComposition.getComponentId());
        }
        return q;
    }

    public getModifierByComponent(component: Component, componentId: number): Modifier {
        for (var m in component.getModifiers()) {
            var modifier: Modifier = component.getModifiers()[m];
            for (var e in modifier.getModifierEntries()) {
                var entry: ModifierEntry = modifier.getModifierEntries()[e];
                if (componentId == entry.getComponent().getId()) {
                    return modifier;
                }
            }
        }
        return null;
    }

    public canadd(composition: Composition, componentId: number): boolean {
        var modifier: Modifier = this.getModifierByComponent(composition.getComponent(), componentId);
        var already = 0;
        modifier.getModifierEntries().forEach(entry => {
            var id: number = entry.getComponent().getId();
            var comp: Composition = this.findEntry(composition, id);
            if (comp != null) {
                already += comp.getQuantity();
            }
        });

        return modifier.getMax() > already;
    }

    public canreduce(composition: Composition, componentId: number): boolean {
        var comp = this.findEntry(composition, componentId);
        return (comp == null ? 0 : comp.getQuantity()) > 0;
    }

}