package com.tpjava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpjava.model.Componente;
import com.tpjava.model.Tipo;

public class TipoDAO {
	
	private String jdbcURL= "jdbc:mysql://localhost:3306/tpJava?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcContra = "";

	private static final String SELECT_ALL_TipoS = "select * from TipoS where borrado!= 1";

	private static final String SELECT_ALL_TipoS_BORRADAS = "select * from TipoS where borrado=1";
	
	private static final String DELETE_Tipo = "UPDATE TipoS SET borrado = 1 where id = ?";
	
	private static final String RESTAURAR_Tipo = "UPDATE TipoS SET borrado = 0 where id = ?";
	
	private static final String SELECT_Tipo_BY_ID = "select * from TipoS where id = ?";
	
	private static final String UPDATE_Tipo = "update TipoS set descripcion = ?,borrado =? where id = ?;";
	
	private static final String INSERT_Tipo = "INSERT INTO TipoS" + " (descripcion, borrado) VALUES " + "(?, ?);";
	
	public TipoDAO() {

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

	public List<Tipo> selectBorradas(){
		List<Tipo> TipoS = new ArrayList<Tipo>();
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TipoS_BORRADAS)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int borrado = rs.getInt("borrado");
				TipoS.add(new Tipo(id, descripcion, borrado));
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return TipoS;
	}
	
	public List<Tipo> selectAll(){
		List<Tipo> TipoS = new ArrayList<Tipo>();
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TipoS)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int borrado = rs.getInt("borrado");
				TipoS.add(new Tipo(id, descripcion, borrado));
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return TipoS;
	}
	
	public void add(Tipo Tipo) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Tipo)) {
			preparedStatement.setString(1, Tipo.getDescripcion());
			preparedStatement.setInt(2, 0);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();}
	}
	
	public boolean update(Tipo Tipo) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_Tipo)) {
			preparedStatement.setString(1, Tipo.getDescripcion());
			preparedStatement.setInt(2, Tipo.getBorrado());
			preparedStatement.setInt(3, Tipo.getId());
			System.out.println(preparedStatement);

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public Tipo selectTipo(int id) {
		Tipo TipoExistente = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Tipo_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int idTipo = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int borrado = rs.getInt("borrado");
				TipoExistente = new Tipo(idTipo,descripcion,borrado);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TipoExistente;
	}
	
	public boolean delete(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_Tipo)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean restaurar(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(RESTAURAR_Tipo)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
}
