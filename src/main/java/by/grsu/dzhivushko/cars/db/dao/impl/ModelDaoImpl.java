package by.grsu.dzhivushko.cars.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.dzhivushko.cars.db.dao.AbstractDao;
import by.grsu.dzhivushko.cars.db.dao.IDao;
import by.grsu.dzhivushko.cars.db.model.Model;

public class ModelDaoImpl extends AbstractDao implements IDao<Integer, Model> {
	public static final ModelDaoImpl INSTANCE = new ModelDaoImpl();

	private ModelDaoImpl() {
		super();
	}

	@Override
	public void insert(Model entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into model(name, brand_id, actual, created, updated) values(?,?,?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getBrandId());
			pstmt.setBoolean(3, entity.getActual());
			pstmt.setTimestamp(4, entity.getCreated());
			pstmt.setTimestamp(5, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "model"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Model entity", e);
		}

	}

	@Override
	public void update(Model entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update model set name=?, brand_id=?, actual=?, updated=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getBrandId());
			pstmt.setBoolean(3, entity.getActual());
			pstmt.setTimestamp(4, entity.getUpdated());
			pstmt.setInt(5, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Model entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from model where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Model entity", e);
		}
	}

	@Override
	public Model getById(Integer id) {
		Model entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from model where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Model entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Model> getAll() {
		List<Model> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from model");
			while (rs.next()) {
				Model entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Model entities", e);
		}

		return entitiesList;
	}

	private Model rowToEntity(ResultSet rs) throws SQLException {
		Model entity = new Model();
		entity.setId(rs.getInt("id"));
		entity.setBrandId(rs.getInt("brand_id"));
		entity.setName(rs.getString("name"));
		entity.setActual(rs.getBoolean("actual"));
		entity.setCreated(rs.getTimestamp("created"));
		entity.setUpdated(rs.getTimestamp("updated"));
		return entity;
	}

}
