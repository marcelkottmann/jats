package com.github.pepe79.jats.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.pepe79.jats.demo.model.base.Category;
import com.github.pepe79.jats.demo.model.base.Component;
import com.github.pepe79.jats.demo.model.base.Modifier;
import com.github.pepe79.jats.demo.model.base.ModifierEntry;
import com.github.pepe79.jats.demo.model.base.VariantAttribute;
import com.github.pepe79.jats.demo.model.fashion.FashionProduct;
import com.github.pepe79.jats.repository.Repository;
import com.github.pepe79.jats.repository.RepositoryFactory;

public class DemoProductRepositoryFactory implements
		RepositoryFactory<Component> {

	@Override
	public Repository<Component> createRepository() {
		Map<Integer, Component> products = createPizzaProducts();
		products.putAll(createFashionProducts());
		products.putAll(createTechnicalProducts());

		Map<Integer, Category> categories = createRootCategory(products);
		return new DemoProductRepository(products, categories);
	}

	private Map<Integer, Category> createRootCategory(
			Map<Integer, Component> products) {
		Category root = new Category();
		root.setId(2000);
		root.setProducts(new ArrayList<Component>(products.values()));

		Map<Integer, Category> categories = new HashMap<Integer, Category>();
		categories.put(root.getId(), root);
		return categories;
	}

	public Map<Integer, Component> createPizzaProducts() {
		IdGenerator idGen = new IdGenerator(1000);

		List<Modifier> modifiers = new ArrayList<Modifier>();
		modifiers.add(createModifier("Niederwertige Zutaten", 0, 3, idGen, 0,
				0.5f, "Peperoni", "Champignons", "Tomaten", "Paprika", "Käse"));
		modifiers.add(createModifier("Höherwertige Zutaten", 0, 1, idGen, 0,
				0.75f, "Scampi", "Rinderstreifen", "Thunfisch"));

		Component requiredComponent = new Component();
		requiredComponent.setId(idGen.nextId());
		requiredComponent.setLabel("Teig");
		Modifier requiredModifier = new Modifier();
		requiredModifier.setId(idGen.nextId());
		requiredModifier.setMinPerModifier(1);
		requiredModifier.setMaxPerModifier(1);
		requiredModifier.setDefaultQuantity(1);
		requiredModifier.setDefaultIncluded(true);
		requiredModifier.setModifierEntries(createModifierEntries(idGen,
				requiredComponent));
		modifiers.add(requiredModifier);

		Map<Integer, Component> products = new LinkedHashMap<Integer, Component>();

		List<Modifier> hawaii = new ArrayList<Modifier>(modifiers);
		hawaii.add(createModifier("Ingredients", 0, 1, idGen, 1, 0.5f,
				"Schinken", "Ananas", "Oregano", "Würzige Tomatensauce"));
		List<Component> p1 = createComplexProducts(1, "Pizza Hawaii Groß", 2,
				"Pizza Hawaii Klein", hawaii);
		for (Component p : p1) {
			products.put(p.getId(), p);
		}

		List<Modifier> salami = new ArrayList<Modifier>(modifiers);
		salami.add(createModifier("Ingredients", 0, 1, idGen, 1, 0.5f,
				"Salami", "Champignons", "Würzige Tomatensauce"));
		List<Component> p2 = createComplexProducts(3, "Pizza Salami Groß", 4,
				"Pizza Salami Klein", salami);
		for (Component p : p2) {
			products.put(p.getId(), p);
		}
		return products;
	}

	public Map<Integer, Component> createFashionProducts() {
		Map<Integer, Component> products = new HashMap<Integer, Component>();
		IdGenerator idGen = new IdGenerator(3000);
		FashionProduct fashionProduct1 = new FashionProduct();
		fashionProduct1.setColor("red");
		fashionProduct1.setSize("38");
		fashionProduct1.setLabel("Cardigan");
		fashionProduct1.setId(idGen.nextId());

		FashionProduct fashionProduct2 = new FashionProduct();
		fashionProduct2.setColor("blue");
		fashionProduct2.setSize("38");
		fashionProduct2.setLabel("Cardigan");
		fashionProduct2.setId(idGen.nextId());

		FashionProduct fashionProduct3 = new FashionProduct();
		fashionProduct3.setColor("blue");
		fashionProduct3.setSize("39");
		fashionProduct3.setLabel("Cardigan");
		fashionProduct3.setId(idGen.nextId());

		products.put(fashionProduct1.getId(), fashionProduct1);
		products.put(fashionProduct2.getId(), fashionProduct2);
		products.put(fashionProduct3.getId(), fashionProduct3);

		List<Component> variants = new ArrayList<Component>(products.values());
		fashionProduct1.setVariants(variants);
		fashionProduct2.setVariants(variants);
		fashionProduct3.setVariants(variants);

		List<VariantAttribute> variantAttributes = new ArrayList<VariantAttribute>();

		VariantAttribute color = new VariantAttribute();
		color.setLabel("Color");
		color.setProperty("color");
		variantAttributes.add(color);

		VariantAttribute size = new VariantAttribute();
		size.setLabel("Size");
		size.setProperty("size");
		variantAttributes.add(size);

		fashionProduct1.setVariantAttributes(variantAttributes);
		fashionProduct2.setVariantAttributes(variantAttributes);
		fashionProduct3.setVariantAttributes(variantAttributes);

		return products;
	}

	public Map<Integer, Component> createTechnicalProducts() {
		Map<Integer, Component> products = new HashMap<Integer, Component>();
		IdGenerator idGen = new IdGenerator(4000);

		List<VariantAttribute> variantAttributes = new ArrayList<VariantAttribute>();
		VariantAttribute memory = new VariantAttribute();
		memory.setLabel("Memory");
		memory.setProperty("custom.memory");
		variantAttributes.add(memory);

		VariantAttribute material = new VariantAttribute();
		material.setLabel("Material");
		material.setProperty("custom.material");
		variantAttributes.add(material);

		VariantAttribute color = new VariantAttribute();
		color.setLabel("Color");
		color.setProperty("custom.color");
		variantAttributes.add(color);

		Component tProduct1 = new Component();
		tProduct1.setId(idGen.nextId());
		tProduct1.setLabel("MP3 Player");
		tProduct1.getCustom().put("memory", "8 GB");
		tProduct1.getCustom().put("material", "Metal");
		tProduct1.getCustom().put("color", "Silver");

		Component tProduct2 = new Component();
		tProduct2.setId(idGen.nextId());
		tProduct2.setLabel("MP3 Player");
		tProduct2.getCustom().put("memory", "16 GB");
		tProduct2.getCustom().put("material", "Metal");
		tProduct2.getCustom().put("color", "Black");

		Component tProduct3 = new Component();
		tProduct3.setId(idGen.nextId());
		tProduct3.setLabel("MP3 Player");
		tProduct3.getCustom().put("memory", "16 GB");
		tProduct3.getCustom().put("material", "Plastic");
		tProduct3.getCustom().put("color", "Black");

		products.put(tProduct1.getId(), tProduct1);
		products.put(tProduct2.getId(), tProduct2);
		products.put(tProduct3.getId(), tProduct3);

		List<Component> variants = new ArrayList<Component>(products.values());
		tProduct1.setVariants(variants);
		tProduct2.setVariants(variants);
		tProduct3.setVariants(variants);

		tProduct1.setVariantAttributes(variantAttributes);
		tProduct2.setVariantAttributes(variantAttributes);
		tProduct3.setVariantAttributes(variantAttributes);

		return products;
	}

	private List<ModifierEntry> createModifierEntries(IdGenerator idgen,
			Component... components) {
		List<ModifierEntry> entries = new ArrayList<ModifierEntry>();
		for (Component component : components) {
			entries.add(createModifierEntry(idgen, component));
		}
		return entries;
	}

	private ModifierEntry createModifierEntry(IdGenerator idgen,
			Component component) {
		ModifierEntry entry = new ModifierEntry();
		entry.setComponent(component);
		entry.setId(idgen.nextId());
		return entry;
	}

	public List<Component> createComplexProducts(int variantId,
			String variantLabel, int variantId2, String variantLabel2,
			List<Modifier> modifiers) {

		Component variant = new Component();
		variant.setId(variantId);
		variant.setLabel(variantLabel);
		variant.setModifiers(modifiers);

		Component variant2 = new Component();
		variant2.setId(variantId2);
		variant2.setLabel(variantLabel2);
		variant2.setModifiers(modifiers);

		List<Component> variants = new ArrayList<Component>();
		variants.add(variant);
		variants.add(variant2);

		List<VariantAttribute> variantAttributes = new ArrayList<VariantAttribute>();
		VariantAttribute variantAttribute = new VariantAttribute();
		variantAttribute.setLabel("Name");
		variantAttribute.setProperty("label");
		variantAttributes.add(variantAttribute);

		variant.setVariantAttributes(variantAttributes);
		variant.setVariants(variants);

		variant2.setVariantAttributes(variantAttributes);
		variant2.setVariants(variants);

		return variants;
	}

	private Modifier createModifier(String baseLabel, int min, int max,
			IdGenerator idGen, int defaultQuantity, float price,
			String... labels) {
		Modifier m = new Modifier();
		m.setId(idGen.nextId());
		m.setMinPerComponent(min);
		m.setMaxPerComponent(max);
		m.setMaxPerModifier(Integer.MAX_VALUE);
		m.setDefaultQuantity(defaultQuantity);
		m.setDefaultIncluded(true);

		List<ModifierEntry> modifierEntries = new ArrayList<ModifierEntry>();
		for (String label : labels) {
			ModifierEntry e = new ModifierEntry();
			e.setId(idGen.nextId());
			e.setComponent(createSimpleComponent(label, idGen, price));
			modifierEntries.add(e);
		}
		m.setModifierEntries(modifierEntries);

		return m;
	}

	private Component createSimpleComponent(String label, IdGenerator idGen,
			float price) {
		Component component = new Component();
		component.setId(idGen.nextId());
		component.setLabel(label);
		return component;
	}

}
