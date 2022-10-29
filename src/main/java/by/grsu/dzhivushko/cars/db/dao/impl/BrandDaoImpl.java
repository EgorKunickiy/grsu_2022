package by.grsu.dzhivushko.cars.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.dzhivushko.cars.db.dao.AbstractDao;
import by.grsu.dzhivushko.cars.db.dao.IDao;
import by.grsu.dzhivushko.cars.db.model.Brand;

public class BrandDaoImpl extends AbstractDao implements IDao<Integer, Brand> {

	// single instance of this class to be used by the all consumers
	public static final BrandDaoImpl INSTANCE = new BrandDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private BrandDaoImpl() {
		super();
	}

	public void insert(Brand entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into brand(name, created, updated) values(?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setTimestamp(2, entity.getCreated());
			pstmt.setTimestamp(3, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "brand"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Brand entity", e);
		} 
	}

	public void update(Brand entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update brand set name=?, updated=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setTimestamp(2, entity.getUpdated());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Brand entity", e);
		}
	}

	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from brand where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Brand entity", e);
		}

	}

	public Brand getById(Integer id) {
		Brand entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from brand where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToBrand(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Brand entity by id", e);
		}

		return entity;
	}

	public List<Brand> getAll() {
		List<Brand> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from brand");
			while (rs.next()) {
				Brand entity = rowToBrand(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Brand entitiesÏ", e);
		}

		return entitiesList;
	}

	private Brand rowToBrand(ResultSet rs) throws SQLException {
		Brand entity = new Brand();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setCreated(rs.getTimestamp("created"));
		entity.setUpdated(rs.getTimestamp("updated"));
		return entity;
	}

}
