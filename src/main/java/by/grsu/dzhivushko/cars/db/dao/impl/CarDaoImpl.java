package by.grsu.dzhivushko.cars.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.dzhivushko.cars.db.dao.AbstractDao;
import by.grsu.dzhivushko.cars.db.dao.IDao;
import by.grsu.dzhivushko.cars.db.model.Car;

public class CarDaoImpl extends AbstractDao implements IDao<Integer, Car> {
	public static final CarDaoImpl INSTANCE = new CarDaoImpl();

	private CarDaoImpl() {
		super();
	}

	@Override
	public void insert(Car entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into car(vin, model_id, owner_id, created, updated) values(?,?,?,?,?)");
			pstmt.setString(1, entity.getVin());
			pstmt.setInt(2, entity.getModelId());
			// owner_id has type Integer, but if it can be null we have to use setObject()
			// instead of setInt()
			pstmt.setObject(3, entity.getOwnerId());
			pstmt.setTimestamp(4, entity.getCreated());
			pstmt.setTimestamp(5, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "car"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Car entity", e);
		}

	}

	@Override
	public void update(Car entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update car set model_id=?, owner_id=?,  updated=? where id=?");
			pstmt.setInt(1, entity.getModelId());
			pstmt.setObject(2, entity.getOwnerId());
			pstmt.setTimestamp(3, entity.getUpdated());
			pstmt.setInt(4, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Car entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from car where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Car entity", e);
		}
	}

	@Override
	public Car getById(Integer id) {
		Car entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from car where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Car entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Car> getAll() {
		List<Car> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from car");
			while (rs.next()) {
				Car entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Car entities", e);
		}

		return entitiesList;
	}

	private Car rowToEntity(ResultSet rs) throws SQLException {
		Car entity = new Car();
		entity.setId(rs.getInt("id"));
		entity.setModelId(rs.getInt("model_id"));
		entity.setVin(rs.getString("vin"));
		// getObject() is unsupported by current JDBC driver. We will get "0" if field is NULL in DB
		entity.setOwnerId(rs.getInt("owner_id"));
		entity.setCreated(rs.getTimestamp("created"));
		entity.setUpdated(rs.getTimestamp("updated"));
		return entity;
	}
}
