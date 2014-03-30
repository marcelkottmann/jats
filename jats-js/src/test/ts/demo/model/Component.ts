class Component
{
	private variantValueList : VariantValueList[];

	private variantValues : any[];

	private variants : Component[];

	private variantAttributes : VariantAttribute[];

	private modifiers : Modifier[];

	private id : number;

	private label : string;

	private custom : {};

	public getVariantValueList() { return this.variantValueList; }

	public getVariantValues() { return this.variantValues; }

	public getVariants() { return this.variants; }
	public setVariants( variants : Component[] ) { this.variants = variants; }

	public getVariantAttributes() { return this.variantAttributes; }
	public setVariantAttributes( variantAttributes : VariantAttribute[] ) { this.variantAttributes = variantAttributes; }

	public getModifiers() { return this.modifiers; }
	public setModifiers( modifiers : Modifier[] ) { this.modifiers = modifiers; }

	public getId() { return this.id; }
	public setId( id : number ) { this.id = id; }

	public getLabel() { return this.label; }
	public setLabel( label : string ) { this.label = label; }

	public getCustom() { return this.custom; }


}
