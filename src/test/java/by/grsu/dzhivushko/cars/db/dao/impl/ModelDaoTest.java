package by.grsu.dzhivushko.cars.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dzhivushko.cars.db.dao.IDao;
import by.grsu.dzhivushko.cars.db.model.Brand;
import by.grsu.dzhivushko.cars.db.model.Model;

public class ModelDaoTest extends AbstractTest {
	private static final IDao<Integer, Brand> brandDao = BrandDaoImpl.INSTANCE;
	private static final IDao<Integer, Model> modelDao = ModelDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Model entity = new Model();
		entity.setBrandId(saveBrand("VW").getId());
		entity.setName("Passat");
		entity.setActual(true);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		modelDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Model entity = new Model();
		entity.setBrandId(saveBrand("VW").getId());
		entity.setName("Passat");
		entity.setActual(true);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		modelDao.insert(entity);

		String newName = "Golf";
		entity.setName(newName);
		entity.setActual(false);
		entity.setUpdated(getCurrentTime());
		modelDao.update(entity);

		Model updatedEntity = modelDao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
		Assertions.assertEquals(false, updatedEntity.getActual());
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() {
		Model entity = new Model();
		entity.setBrandId(saveBrand("VW").getId());
		entity.setName("Passat");
		entity.setActual(true);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		modelDao.insert(entity);

		modelDao.delete(entity.getId());

		Assertions.assertNull(modelDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Model entity = new Model();
		entity.setBrandId(saveBrand("VW").getId());
		entity.setName("Passat");
		entity.setActual(true);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		modelDao.insert(entity);

		Model selectedEntity = modelDao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getBrandId(), selectedEntity.getBrandId());
		Assertions.assertEquals(entity.getCreated(), selectedEntity.getCreated());
		Assertions.assertEquals(entity.getUpdated(), selectedEntity.getUpdated());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Model entity = new Model();
			entity.setBrandId(saveBrand("VW" + i).getId());
			entity.setName("Passat" + i);
			entity.setActual(true);
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			modelDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, modelDao.getAll().size());
	}

	private Brand saveBrand(String name) {
		Brand entity = new Brand();
		entity.setName(name);
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		brandDao.insert(entity);
		return entity;
	}
}
