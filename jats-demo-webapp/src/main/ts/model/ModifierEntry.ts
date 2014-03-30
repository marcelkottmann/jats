class ModifierEntry
{
	private id : number;

	private component : Component;

	private uom : Uom;

	public getId() { return this.id; }
	public setId( id : number ) { this.id = id; }

	public getComponent() { return this.component; }
	public setComponent( component : Component ) { this.component = component; }

	public getUom() { return this.uom; }
	public setUom( uom : Uom ) { this.uom = uom; }


}
