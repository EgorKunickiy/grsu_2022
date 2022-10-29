package by.grsu.dzhivushko.cars.db.dao.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;

import by.grsu.dzhivushko.cars.db.dao.AbstractDao;

public abstract class AbstractTest {
	@BeforeAll
	private static void setup() {
		AbstractDao.init("test-db");
		AbstractDao.deleteDb();
		AbstractDao.createDbSchema();
	}
	
	protected Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}
}
