package com.epam.rd.autotasks;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

	private static final String URL = "jdbc:mysql://localhost:3307//epam";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "MyBalls.88";

	private DbManager() {
		throw new UnsupportedOperationException();
	}

	public static int callCountDepartments(Connection connection) throws SQLException {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			CallableStatement cs = conn.prepareCall("{CALL COUNT_DEPARTMENTS(?)}");
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.execute();
			return cs.getInt(1);
		}
	}

	public static int callCountEmployees(Connection connection) throws SQLException {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			CallableStatement cs = conn.prepareCall("{CALL COUNT_EMPLOYEES(?)}");
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.execute();
			return cs.getInt(1);
		}
	}

	public static int callCountEmployeesByDepartmentId(Connection connection, int departmentId) throws SQLException {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			CallableStatement cs = conn.prepareCall("{CALL COUNT_EMPLOYEES_BY_DEPARTMENT_ID(?, ?)}");
			cs.setInt(1, departmentId);
			cs.registerOutParameter(2, java.sql.Types.INTEGER);
			cs.execute();
			return cs.getInt(2);
		}
	}
}