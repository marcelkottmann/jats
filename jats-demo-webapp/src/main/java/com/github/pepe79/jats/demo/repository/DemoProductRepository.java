package com.github.pepe79.jats.demo.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.github.pepe79.jats.demo.model.base.Category;
import com.github.pepe79.jats.demo.model.base.Component;
import com.github.pepe79.jats.repository.Repository;

public class DemoProductRepository implements Repository<Component> {

	private Map<Integer, Component> products;

	private Map<Integer, Category> categories;

	public DemoProductRepository(Map<Integer, Component> products,
			Map<Integer, Category> categories) {

		this.products = products;
		this.categories = categories;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection find(String type, String id) {
		int iid = Integer.parseInt(id);
		if (Component.class.getSimpleName().equals(type)) {
			Collection<Component> result = new ArrayList<Component>();
			result.add(products.get(iid));
			return result;
		} else if (Category.class.getSimpleName().equals(type)) {
			Collection<Category> result = new ArrayList<Category>();
			result.add(categories.get(iid));
			return result;
		}
		throw new RuntimeException("Unknown type.");
	}

}
