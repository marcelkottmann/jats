class Composition {

    private component: Component = null;

    private uom: Uom = null;

    private quantity: number = 0;

    private subComponents: Composition[] = [];

    constructor(component: Component, uom: Uom) {
        this.component = component;
        this.uom = uom;
    }

    public getComponentId() {
        return this.component.getId();
    }

    public getComponent() {
        return this.component;
    }

    public getUom() {
        return this.uom;
    }

    public getSubComponents(): Composition[] {
        return this.subComponents;
    }

    public getQuantity(): number {
        return this.quantity;
    }

    public setQuantity(quantity: number) {
        this.quantity = quantity;
    }

    public getPrice(q?: number): number {
        var compontPrice = PriceManager.findPrice(this.component, this.uom);
        q = (q == null ? this.getQuantity() : q);
        var p: number = q * compontPrice;
        this.subComponents.forEach(subc => {
            var includedQuantity = CompositionConfigurator.getIncludedQuantity(this.component, subc.getComponentId());
            p += q * subc.getPrice(Math.max(0, subc.getQuantity() - includedQuantity));
        });
        return p;
    }


    public toString(): string {
        var str = this.quantity + "x" + this.getComponentId() + "[";
        for (var i = 0; i < this.subComponents.length; i++) {
            str = str + this.subComponents[i].toString();
            if (i < this.subComponents.length - 1) {
                str += ",";
            }
        }
        str = str + "]";
        return str;
    }
}