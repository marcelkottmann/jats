package com.github.pepe79.jats.demo.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.github.pepe79.jats.demo.model.base.Component;
import com.github.pepe79.jats.view.ViewFactory;

public class DemoViewFactory implements ViewFactory {

	@Override
	public Map<String, Map<Class<?>, Set<String>>> createViews() {
		Map<Class<?>, Set<String>> productlistWhiteList = new HashMap<Class<?>, Set<String>>();
		productlistWhiteList.put(Component.class, new HashSet<String>(Arrays.asList("id", "label")));

		Map<String, Map<Class<?>, Set<String>>> views = new HashMap<String, Map<Class<?>, Set<String>>>();
		views.put("productlist", productlistWhiteList);

		return views;
	}
}
