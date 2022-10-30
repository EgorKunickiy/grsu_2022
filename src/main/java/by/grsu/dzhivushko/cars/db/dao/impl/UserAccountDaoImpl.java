package by.grsu.dzhivushko.cars.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import by.grsu.dzhivushko.cars.db.dao.AbstractDao;
import by.grsu.dzhivushko.cars.db.dao.IDao;
import by.grsu.dzhivushko.cars.db.model.UserAccount;

public class UserAccountDaoImpl extends AbstractDao implements IDao<Integer, UserAccount> {

	public static final UserAccountDaoImpl INSTANCE = new UserAccountDaoImpl();

	private UserAccountDaoImpl() {
		super();
	}

	@Override
	public void insert(UserAccount entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into user_account(first_name, last_name, created, updated) values(?,?,?,?)");
			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.setTimestamp(3, entity.getCreated());
			pstmt.setTimestamp(4, entity.getUpdated());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "user_account"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert UserAccount entity", e);
		}
	}

	@Override
	public void update(UserAccount entity) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void delete(Integer id) {
		throw new RuntimeException("not implemented");

	}

	@Override
	public UserAccount getById(Integer id) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public List<UserAccount> getAll() {
		throw new RuntimeException("not implemented");
	}
}
