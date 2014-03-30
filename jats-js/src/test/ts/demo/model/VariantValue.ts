class VariantValue
{
	private value : any;

	private variants : Component[];

	public getValue() { return this.value; }
	public setValue( value : any ) { this.value = value; }

	public getVariants() { return this.variants; }
	public setVariants( variants : Component[] ) { this.variants = variants; }


}
