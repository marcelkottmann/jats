class Component
{
	private price : number;

	private requiredComponents : Component[];

	private optionalComponents : Component[];

	private modifiers : Modifier[];

	private id : number;

	private label : string;

	private quantity : Quantity;

	public getPrice() { return this.price; }
	public setPrice( price : number ) { this.price = price; }

	public getRequiredComponents() { return this.requiredComponents; }
	public setRequiredComponents( requiredComponents : Component[] ) { this.requiredComponents = requiredComponents; }

	public getOptionalComponents() { return this.optionalComponents; }
	public setOptionalComponents( optionalComponents : Component[] ) { this.optionalComponents = optionalComponents; }

	public getModifiers() { return this.modifiers; }
	public setModifiers( modifiers : Modifier[] ) { this.modifiers = modifiers; }

	public getId() { return this.id; }
	public setId( id : number ) { this.id = id; }

	public getLabel() { return this.label; }
	public setLabel( label : string ) { this.label = label; }

	public getQuantity() { return this.quantity; }
	public setQuantity( quantity : Quantity ) { this.quantity = quantity; }


}
