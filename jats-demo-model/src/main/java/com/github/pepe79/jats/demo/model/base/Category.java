package com.github.pepe79.jats.demo.model.base;

import java.util.List;

public class Category {

	private Integer id;

	private List<Component> products;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Component> getProducts() {
		return products;
	}

	public void setProducts(List<Component> products) {
		this.products = products;
	}

}
