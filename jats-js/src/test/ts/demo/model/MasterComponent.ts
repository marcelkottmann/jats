class MasterComponent
{
	private variantValueList : VariantValueList[];

	private variants : Component[];

	private id : number;

	private variantAttributes : VariantAttribute[];

	public getVariantValueList() { return this.variantValueList; }

	public getVariants() { return this.variants; }
	public setVariants( variants : Component[] ) { this.variants = variants; }

	public getId() { return this.id; }
	public setId( id : number ) { this.id = id; }

	public getVariantAttributes() { return this.variantAttributes; }
	public setVariantAttributes( variantAttributes : VariantAttribute[] ) { this.variantAttributes = variantAttributes; }


}
