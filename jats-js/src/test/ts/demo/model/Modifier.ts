class Modifier
{
	private modifierEntries : ModifierEntry[];

	private id : number;

	private min : number;

	private max : number;

	public getModifierEntries() { return this.modifierEntries; }
	public setModifierEntries( modifierEntries : ModifierEntry[] ) { this.modifierEntries = modifierEntries; }

	public getId() { return this.id; }
	public setId( id : number ) { this.id = id; }

	public getMin() { return this.min; }
	public setMin( min : number ) { this.min = min; }

	public getMax() { return this.max; }
	public setMax( max : number ) { this.max = max; }


}
