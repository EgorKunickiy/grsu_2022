package by.grsu.dzhivushko.cars.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dzhivushko.cars.db.dao.IDao;
import by.grsu.dzhivushko.cars.db.model.Brand;

public class BranDAOTest extends AbstractTest {
	private static final IDao<Integer, Brand> dao = BrandDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Brand entity = new Brand();
		entity.setName("VW");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}
}
