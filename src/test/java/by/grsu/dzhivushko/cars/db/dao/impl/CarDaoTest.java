package by.grsu.dzhivushko.cars.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dzhivushko.cars.db.dao.IDao;
import by.grsu.dzhivushko.cars.db.model.Brand;
import by.grsu.dzhivushko.cars.db.model.Car;
import by.grsu.dzhivushko.cars.db.model.Model;
import by.grsu.dzhivushko.cars.db.model.UserAccount;

public class CarDaoTest extends AbstractTest {
	private static final IDao<Integer, Brand> brandDao = BrandDaoImpl.INSTANCE;
	private static final IDao<Integer, Model> modelDao = ModelDaoImpl.INSTANCE;
	private static final IDao<Integer, Car> carDao = CarDaoImpl.INSTANCE;
	private static final IDao<Integer, UserAccount> userAccountDao = UserAccountDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Car entity = new Car();
		entity.setModelId(saveModel("audi", "Q5").getId());
		entity.setVin("WAUZZZ8K0BA003806");
		entity.setOwnerId(saveUserAccount().getId());
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		carDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testInsertWithoutOwner() {
		Car entity = new Car();
		entity.setModelId(saveModel("audi", "Q5").getId());
		entity.setVin("WAUZZZ8K0BA003806");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		carDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Car entity = new Car();
		entity.setModelId(saveModel("audi", "Q5").getId());
		entity.setVin("WAUZZZ8K0BA003806");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		carDao.insert(entity);

		Model newModel = saveModel("opel", "corsa");
		entity.setVin("new_WAUZZZ8K0BA003806");
		entity.setModelId(newModel.getId());
		entity.setUpdated(getCurrentTime());
		carDao.update(entity);

		Car updatedEntity = carDao.getById(entity.getId());
		Assertions.assertEquals(newModel.getId(), updatedEntity.getModelId());
		Assertions.assertEquals("WAUZZZ8K0BA003806", updatedEntity.getVin()); // VIN should stay unchanged as DAO doesn't update it
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() {
		Car entity = new Car();
		entity.setModelId(saveModel("audi", "Q5").getId());
		entity.setVin("WAUZZZ8K0BA003806");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		carDao.insert(entity);

		carDao.delete(entity.getId());

		Assertions.assertNull(carDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Car entity = new Car();
		entity.setModelId(saveModel("audi", "Q5").getId());
		entity.setVin("WAUZZZ8K0BA003806");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		carDao.insert(entity);

		Car selectedEntity = carDao.getById(entity.getId());

		Assertions.assertEquals(entity.getModelId(), selectedEntity.getModelId());
		Assertions.assertEquals(entity.getVin(), selectedEntity.getVin());
		Assertions.assertEquals(0, selectedEntity.getOwnerId());
		Assertions.assertEquals(entity.getCreated(), selectedEntity.getCreated());
		Assertions.assertEquals(entity.getUpdated(), selectedEntity.getUpdated());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Car entity = new Car();
			entity.setModelId(saveModel("audi"+i, "Q5"+i).getId());
			entity.setVin("WAUZZZ8K0BA003806"+i);
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			carDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, carDao.getAll().size());
	}

	private UserAccount saveUserAccount() {
		UserAccount entity = new UserAccount();
		entity.setFirstName("Ivan");
		entity.setLastName("Ivanov");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		userAccountDao.insert(entity);
		return entity;
	}

	private Model saveModel(String brand, String model) {
		Brand brandEntity = new Brand();
		brandEntity.setName(brand);
		brandEntity.setCreated(getCurrentTime());
		brandEntity.setUpdated(getCurrentTime());
		brandDao.insert(brandEntity);

		Model modelEntity = new Model();
		modelEntity.setName(model);
		modelEntity.setActual(true);
		modelEntity.setBrandId(brandEntity.getId());
		modelEntity.setCreated(getCurrentTime());
		modelEntity.setUpdated(getCurrentTime());
		modelDao.insert(modelEntity);

		return modelEntity;
	}
}
