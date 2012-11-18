class Quantity
{
	private uom : Uom;

	private quantity : number;

	private displayQuantity : string;

	public getUom() { return this.uom; }
	public setUom( uom : Uom ) { this.uom = uom; }

	public getQuantity() { return this.quantity; }
	public setQuantity( quantity : number ) { this.quantity = quantity; }

	public getDisplayQuantity() { return this.displayQuantity; }


}
