class Composition {

    private componentId: number = 0;

    private quantity: number = 0;

    private subComponents: Composition[] = new Array();

    constructor (componentId : number) {
        this.componentId = componentId;
    }

    public getComponentId() {
        return this.componentId;
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

    public toString(): string {
        var str = this.quantity + "x" + this.componentId + "[";
        for (var i = 0; i < this.subComponents.length; i++) {
            str = str + this.subComponents[i].toString();
        }
        str = str + "]";
        return str;
    }
}