package com.github.pepe79.jats.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.pepe79.jats.demo.model.Component;
import com.github.pepe79.jats.demo.model.Modifier;
import com.github.pepe79.jats.demo.model.ModifierEntry;
import com.github.pepe79.jats.demo.model.Product;
import com.github.pepe79.jats.repository.Repository;

public class DemoProductRepository implements Repository<Product>
{

	public Product createComplexProduct()
	{
		IdGenerator idGen = new IdGenerator();

		Product p = new Product();
		p.setId(idGen.nextId());
		p.setLabel("Pizza Hawaii");

		Component variant = new Component();
		p.setVariants(new ArrayList<Component>());
		p.getVariants().add(variant);

		variant.setId(idGen.nextId());
		variant.setLabel("Pizza Hawaii Groß");
		variant.setPrice(5.50f);

		List<Modifier> modifiers = new ArrayList<Modifier>();
		modifiers.add(createModifier("Niederwertige Zutaten", 0, 3, idGen,
				0.5f, "Peperoni", "Champignons", "Tomaten", "Paprika", "Käse"));
		modifiers.add(createModifier("Höherwertige Zutaten", 0, 1, idGen,
				0.75f, "Scampi", "Rinderstreifen", "Thunfisch"));
		variant.setModifiers(modifiers);

		variant.setRequiredComponents(new ArrayList<Component>());
		Component requiredComponent = new Component();
		requiredComponent.setId(idGen.nextId());
		requiredComponent.setLabel("Teig");
		variant.getRequiredComponents().add(requiredComponent);

		variant.setOptionalComponents(new ArrayList<Component>());
		Component optionalComponent = new Component();
		optionalComponent.setId(idGen.nextId());
		optionalComponent.setLabel("Würzige Tomatensauce");
		variant.getOptionalComponents().add(optionalComponent);

		return p;
	}

	private Modifier createModifier(String baseLabel, int min, int max,
			IdGenerator idGen, float price, String... labels)
	{
		Modifier m = new Modifier();
		m.setId(idGen.nextId());
		m.setMin(min);
		m.setMax(max);

		List<ModifierEntry> modifierEntries = new ArrayList<ModifierEntry>();
		for (String label : labels)
		{
			ModifierEntry e = new ModifierEntry();
			e.setId(idGen.nextId());
			e.setComponent(createSimpleComponent(label, idGen, price));
			modifierEntries.add(e);
		}
		m.setModifierEntries(modifierEntries);

		return m;
	}

	private Component createSimpleComponent(String label, IdGenerator idGen,
			float price)
	{
		Component component = new Component();
		component.setId(idGen.nextId());
		component.setLabel(label);
		component.setPrice(price);
		return component;
	}

	@Override
	public Collection<Product> find(String type, String id)
	{
		List<Product> products = new ArrayList<Product>();
		products.add(createComplexProduct());

		return products;
	}

}
