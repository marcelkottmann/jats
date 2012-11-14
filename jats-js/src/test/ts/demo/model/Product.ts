class Product
{
	private variants : ProductVariant[];

	private id : number;

	public getVariants() { return this.variants; }
	public setVariants( variants : ProductVariant[] ) { this.variants = variants; }

	public getId() { return this.id; }
	public setId( id : number ) { this.id = id; }


}
