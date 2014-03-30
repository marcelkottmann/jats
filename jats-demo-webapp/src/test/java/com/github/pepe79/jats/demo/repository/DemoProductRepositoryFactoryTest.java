package com.github.pepe79.jats.demo.repository;

import junit.framework.Assert;

import org.junit.Test;

import com.github.pepe79.jats.demo.model.base.Component;
import com.github.pepe79.jats.demo.model.base.VariantValueList;
import com.github.pepe79.jats.repository.Repository;

public class DemoProductRepositoryFactoryTest {

	@Test
	public void testCreateRepository() {
		Repository<Component> repo = new DemoProductRepositoryFactory()
				.createRepository();
		Component master = repo
				.find(Component.class.getSimpleName(), "1").iterator()
				.next();

		Assert.assertEquals(2, master.getVariants().size());

		Component v1 = master.getVariants().get(0);
		Component v2 = master.getVariants().get(1);

		Assert.assertEquals("Pizza Hawaii Groß", v1.getLabel());
		Assert.assertEquals("Pizza Hawaii Klein", v2.getLabel());

		Assert.assertEquals(1, master.getVariantAttributes().size());

		Assert.assertEquals(1, master.getVariantValueList().size());

		VariantValueList values = master.getVariantValueList().iterator()
				.next();
		Assert.assertEquals(2, values.getVariantValues().size());

		Assert.assertEquals("Pizza Hawaii Groß",
				values.getVariantValues().get(0).getValue());
		Assert.assertEquals(1, values.getVariantValues().get(0).getVariants()
				.size());
		Assert.assertTrue(values.getVariantValues().get(0).getVariants()
				.iterator().next() == v1);

		Assert.assertEquals("Pizza Hawaii Klein", values.getVariantValues()
				.get(1).getValue());
		Assert.assertEquals(1, values.getVariantValues().get(1).getVariants()
				.size());
		Assert.assertTrue(values.getVariantValues().get(1).getVariants()
				.iterator().next() == v2);
		
		Assert.assertEquals(1, v1.getVariantValues().size());
	}

}
