package com.tpjava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpjava.model.Componente;
import com.tpjava.model.Marca;

public class MarcaDAO {
	
	private String jdbcURL= "jdbc:mysql://localhost:3306/tpJava?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcContra = "";

	private static final String SELECT_ALL_MARCAS = "select * from marcas where borrado!= 1";

	private static final String SELECT_ALL_MARCAS_BORRADAS = "select * from marcas where borrado=1";
	
	private static final String DELETE_MARCA = "UPDATE marcas SET borrado = 1 where id = ?";
	
	private static final String RESTAURAR_MARCA = "UPDATE marcas SET borrado = 0 where id = ?";
	
	private static final String SELECT_MARCA_BY_ID = "select * from marcas where id = ?";
	
	private static final String UPDATE_MARCA = "update marcas set descripcion = ?,borrado =? where id = ?;";
	
	private static final String INSERT_MARCA = "INSERT INTO marcas" + " (descripcion, borrado) VALUES " + "(?, ?);";
	
	public MarcaDAO() {

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

	public List<Marca> selectBorradas(){
		List<Marca> marcas = new ArrayList<Marca>();
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAS_BORRADAS)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int borrado = rs.getInt("borrado");
				marcas.add(new Marca(id, descripcion, borrado));
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return marcas;
	}
	
	public List<Marca> selectAll(){
		List<Marca> marcas = new ArrayList<Marca>();
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MARCAS)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int borrado = rs.getInt("borrado");
				marcas.add(new Marca(id, descripcion, borrado));
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return marcas;
	}
	
	public void add(Marca marca) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MARCA)) {
			preparedStatement.setString(1, marca.getDescripcion());
			preparedStatement.setInt(2, 0);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();}
	}
	
	public boolean update(Marca marca) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MARCA)) {
			preparedStatement.setString(1, marca.getDescripcion());
			preparedStatement.setInt(2, marca.getBorrado());
			preparedStatement.setInt(3, marca.getId());
			System.out.println(preparedStatement);

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public Marca selectMarca(int id) {
		Marca marcaExistente = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MARCA_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int idMarca = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int borrado = rs.getInt("borrado");
				marcaExistente = new Marca(idMarca,descripcion,borrado);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marcaExistente;
	}
	
	public boolean delete(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_MARCA)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean restaurar(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(RESTAURAR_MARCA)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
}
