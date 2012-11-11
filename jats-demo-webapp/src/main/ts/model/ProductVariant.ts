class ProductVariant
{
	private uom : Uom;

	private mainProduct : Product;

	private requiredComponents : Component[];

	private optionalComponents : Component[];

	private id : number;

	public getUom() { return this.uom; }
	public setUom( uom : Uom ) { this.uom = uom; }

	public getMainProduct() { return this.mainProduct; }
	public setMainProduct( mainProduct : Product ) { this.mainProduct = mainProduct; }

	public getRequiredComponents() { return this.requiredComponents; }
	public setRequiredComponents( requiredComponents : Component[] ) { this.requiredComponents = requiredComponents; }

	public getOptionalComponents() { return this.optionalComponents; }
	public setOptionalComponents( optionalComponents : Component[] ) { this.optionalComponents = optionalComponents; }

	public getId() { return this.id; }
	public setId( id : number ) { this.id = id; }


}
