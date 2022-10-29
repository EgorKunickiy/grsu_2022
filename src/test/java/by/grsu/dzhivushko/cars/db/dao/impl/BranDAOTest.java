package by.grsu.dzhivushko.cars.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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

	@Test
	public void testUpdate() {
		Brand entity = new Brand();
		entity.setName("VW");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		String newName = "VW_NEW";
		entity.setName(newName);
		entity.setUpdated(getCurrentTime());
		dao.update(entity);

		Brand updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(updatedEntity.getName(), newName);
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() {
		Brand entity = new Brand();
		entity.setName("VW");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Brand entity = new Brand();
		entity.setName("VW");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		Brand selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getCreated(), selectedEntity.getCreated());
		Assertions.assertEquals(entity.getUpdated(), selectedEntity.getUpdated());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Brand entity = new Brand();
			entity.setName("VW");
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}
