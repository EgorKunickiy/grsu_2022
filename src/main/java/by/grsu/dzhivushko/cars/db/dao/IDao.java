package by.grsu.dzhivushko.cars.db.dao;

import java.util.List;

public interface IDao<ID, TYPE> {

	void insert(TYPE t);

	void update(TYPE t);

	void delete(ID id);

	TYPE getById(ID id);
	
	List<TYPE> getAll();

}
