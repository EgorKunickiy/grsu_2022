package by.grsu.dzhivushko.cars.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String sql = "insert into brand(name, created, updated) values(?,?,?)";
		Connection c = createConnection();
		try {
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, entity.getName());
			pstmt.setTimestamp(2, entity.getCreated());
			pstmt.setTimestamp(3, entity.getUpdated());
			pstmt.executeUpdate();

			final Integer id = getGeneratedId(c);
			entity.setId(id);
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Brand entityÏ", e);
		}
	}

	private Integer getGeneratedId(Connection c) throws SQLException {
		PreparedStatement pstmt;
		String selectIdSql = "select max(id) as max_id from brand";
		pstmt = c.prepareStatement(selectIdSql);
		pstmt.execute();
		ResultSet rs = pstmt.getResultSet();
		
		final Integer id = rs.getInt("max_id");
		return id;
	}

	public void update(Brand entity) {
		// TODO Auto-generated method stub
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	public Brand getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Brand> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
