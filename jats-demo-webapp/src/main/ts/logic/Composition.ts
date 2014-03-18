class Composition {

    private component: Component = null;

    private quantity: number = 0;

    private subComponents: Composition[] = [];

    constructor(component: Component) {
        this.component = component;
    }

    public getComponentId() {
        return this.component.getId();
    }

    public getComponent() {
        return this.component;
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

    public getPrice(): number {
        var p: number = this.getQuantity() * this.getComponent().getPrice();
        this.subComponents.forEach(subc => {
            p += this.getQuantity() * subc.getPrice();
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