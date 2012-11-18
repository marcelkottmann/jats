package com.github.pepe79.jats.demo;

import com.github.pepe79.jats.demo.model.Product;
import com.github.pepe79.jats.repository.Repository;
import com.github.pepe79.jats.repository.RepositoryFactory;

public class DemoProductRepositoryFactory implements RepositoryFactory<Product>{

	@Override
	public Repository<Product> createRepository()
	{
		return new DemoProductRepository();
	}
}
