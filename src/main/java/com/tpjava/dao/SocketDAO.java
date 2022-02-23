package com.tpjava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpjava.model.Componente;
import com.tpjava.model.Socket;

public class SocketDAO {
	
	private String jdbcURL= "jdbc:mysql://localhost:3306/tpJava?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcContra = "";

	private static final String SELECT_ALL_SOCKETS = "select * from sockets where borrado!= 1";

	private static final String SELECT_ALL_SOCKETS_BORRADAS = "select * from sockets where borrado=1";
	
	private static final String DELETE_SOCKET = "UPDATE sockets SET borrado = 1 where id = ?";
	
	private static final String RESTAURAR_SOCKET = "UPDATE sockets SET borrado = 0 where id = ?";
	
	private static final String SELECT_SOCKET_BY_ID = "select * from sockets where id = ?";
	
	private static final String UPDATE_SOCKET = "update sockets set descripcion = ?,borrado =? where id = ?;";
	
	private static final String INSERT_SOCKET = "INSERT INTO sockets" + " (descripcion, borrado) VALUES " + "(?, ?);";
	
	public SocketDAO() {

	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcContra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public List<Socket> selectBorradas(){
		List<Socket> sockets = new ArrayList<Socket>();
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SOCKETS_BORRADAS)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int borrado = rs.getInt("borrado");
				sockets.add(new Socket(id, descripcion, borrado));
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return sockets;
	}
	
	public List<Socket> selectAll(){
		List<Socket> sockets = new ArrayList<Socket>();
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SOCKETS)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int borrado = rs.getInt("borrado");
				sockets.add(new Socket(id, descripcion, borrado));
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return sockets;
	}
	
	public void add(Socket socket) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SOCKET)) {
			preparedStatement.setString(1, socket.getDescripcion());
			preparedStatement.setInt(2, 0);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();}
	}
	
	public boolean update(Socket socket) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SOCKET)) {
			preparedStatement.setString(1, socket.getDescripcion());
			preparedStatement.setInt(2, socket.getBorrado());
			preparedStatement.setInt(3, socket.getId());
			System.out.println(preparedStatement);

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public Socket selectSocket(int id) {
		Socket socketExistente = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SOCKET_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int idSocket = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int borrado = rs.getInt("borrado");
				socketExistente = new Socket(idSocket,descripcion,borrado);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return socketExistente;
	}
	
	public boolean delete(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SOCKET)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean restaurar(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(RESTAURAR_SOCKET)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
}
