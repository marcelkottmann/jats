class Product
{
	private variants : Component[];

	private id : number;

	private label : string;

	public getVariants() { return this.variants; }
	public setVariants( variants : Component[] ) { this.variants = variants; }

	public getId() { return this.id; }
	public setId( id : number ) { this.id = id; }

	public getLabel() { return this.label; }
	public setLabel( label : string ) { this.label = label; }


}
