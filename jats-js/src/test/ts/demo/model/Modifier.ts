class Modifier
{
	private modifierEntries : ModifierEntry[];

	private id : number;

	private minPerComponent : number;

	private maxPerComponent : number;

	private minPerModifier : number;

	private maxPerModifier : number;

	private defaultQuantity : number;

	private defaultIncluded : boolean;

	public getModifierEntries() { return this.modifierEntries; }
	public setModifierEntries( modifierEntries : ModifierEntry[] ) { this.modifierEntries = modifierEntries; }

	public getId() { return this.id; }
	public setId( id : number ) { this.id = id; }

	public getMinPerComponent() { return this.minPerComponent; }
	public setMinPerComponent( minPerComponent : number ) { this.minPerComponent = minPerComponent; }

	public getMaxPerComponent() { return this.maxPerComponent; }
	public setMaxPerComponent( maxPerComponent : number ) { this.maxPerComponent = maxPerComponent; }

	public getMinPerModifier() { return this.minPerModifier; }
	public setMinPerModifier( minPerModifier : number ) { this.minPerModifier = minPerModifier; }

	public getMaxPerModifier() { return this.maxPerModifier; }
	public setMaxPerModifier( maxPerModifier : number ) { this.maxPerModifier = maxPerModifier; }

	public getDefaultQuantity() { return this.defaultQuantity; }
	public setDefaultQuantity( defaultQuantity : number ) { this.defaultQuantity = defaultQuantity; }

	public isDefaultIncluded() { return this.defaultIncluded; }
	public setDefaultIncluded( defaultIncluded : boolean ) { this.defaultIncluded = defaultIncluded; }


}
