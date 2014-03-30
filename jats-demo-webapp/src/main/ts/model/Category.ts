class Category
{
	private id : number;

	private products : Component[];

	public getId() { return this.id; }
	public setId( id : number ) { this.id = id; }

	public getProducts() { return this.products; }
	public setProducts( products : Component[] ) { this.products = products; }


}
