class CompositionConfigurator {

    private findSubComposition(composition: Composition, subComponentId: number): Composition {
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
        var subComposition = this.findSubComposition(composition, component.getId());
        if (subComposition == null) {
            subComposition = new Composition(component, null);
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

    public static getIncludedQuantity(component: Component, componentId: number): number {
        var m = CompositionConfigurator.getModifierByComponent(component, componentId);
        if (m != null && m.isDefaultIncluded()) {
            return m.getDefaultQuantity();
        }
        return 0;
    }

    public static getModifierByComponent(component: Component, componentId: number): Modifier {
        for (var m = 0; m < component.getModifiers().length; m++) {
            var modifier: Modifier = component.getModifiers()[m];
            for (var e = 0; e < modifier.getModifierEntries().length; e++) {
                var entry: ModifierEntry = modifier.getModifierEntries()[e];
                if (componentId == entry.getComponent().getId()) {
                    return modifier;
                }
            }
        }
        return null;
    }

    public canadd(composition: Composition, componentId: number): boolean {
        var modifier: Modifier = CompositionConfigurator.getModifierByComponent(composition.getComponent(), componentId);
        var already = 0;
        modifier.getModifierEntries().forEach(entry => {
            var id: number = entry.getComponent().getId();
            var comp: Composition = this.findSubComposition(composition, id);
            if (comp != null) {
                already += comp.getQuantity();
            }
        });

        return modifier.getMaxPerModifier() > already;
    }

    public canreduce(composition: Composition, componentId: number): boolean {
        var subComposition = this.findSubComposition(composition, componentId);
        var currentQuantity = (subComposition == null ? 0 : subComposition.getQuantity());

        var modifier = CompositionConfigurator.getModifierByComponent(composition.getComponent(), componentId);
        var minimumQuantity = modifier == null ? 0 : modifier.getMinPerModifier();

        return currentQuantity > minimumQuantity;
    }

    public createDefaultComposition(component: Component, template: Composition): Composition {
        var defaultComposition = new Composition(component, null);
        defaultComposition.setQuantity(1);
        if (component.getModifiers() != null) {
            component.getModifiers().forEach((m) => {
                m.getModifierEntries().forEach((e) => {
                    this.addComponent(defaultComposition, e.getComponent(), m.getDefaultQuantity());
                });
            });
        }
        return defaultComposition;
    }


}