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
import com.tpjava.model.Socket;
import com.tpjava.model.Tamano;
import com.tpjava.model.Tipo;
import com.tpjava.model.Usuario;

public class ComponenteDAO {
	
	private String jdbcURL= "jdbc:mysql://localhost:3306/tpJava?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcContra = "";
	MarcaDAO marcaDAO = new MarcaDAO();
	TamanoDAO tamanoDAO = new TamanoDAO();
	TipoDAO tipoDAO = new TipoDAO();
	SocketDAO socketDAO = new SocketDAO();
	
	
	private static final String INSERT_COMPONENTE = "INSERT INTO componentes" + " (descripcion, tamano, socket, consumo, precio, marca, tipo, borrado, stock) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	private static final String SELECT_ALL_COMPONENTES = "select * from componentes c left join marcas m on c.marca = m.id where c.borrado!= 1 and c.stock > 0";
	
	private static final String SELECT_ALL_COMPONENTES_SIN_STOCK = "select * from componentes where stock = 0 && borrado = 0";

	private static final String SELECT_ALL_COMPONENTES_BORRADOS = "select * from componentes where borrado=1";
	
	private static final String DELETE_COMPONENTE = "UPDATE componentes SET borrado = 1 where id = ?";
	
	private static final String RESTAURAR_COMPONENTE = "UPDATE componentes SET borrado = 0 where id = ?";
	
	private static final String SELECT_COMPONENTE_BY_ID = "select * from componentes where id = ?";
	
	private static final String UPDATE_COMPONENTE = "update componentes set descripcion = ?,tamano= ?, socket =?, consumo =?, precio =?, marca =?, tipo =?, stock=?, borrado =? where id = ?;";
	
	private static final String SELECT_WHERE_MARCA = "select count(id) as count from componentes where marca = ? and borrado = 0";
	
	private static final String SELECT_WHERE_TIPO = "select count(id) as count from componentes where tipo = ? and borrado = 0";
	
	private static final String SELECT_WHERE_TAMANO = "select count(id) as count from componentes where tamano = ? and borrado = 0";
	
	private static final String SELECT_WHERE_SOCKET = "select count(id) as count from componentes where socket = ? and borrado = 0";
	
	private static final String SELECT_SEARCH = "select co.* from componentes co left join tipos ti on ti.id = co.tipo left join tamanos ta on ta.id = co.tamano left join marcas ma on ma.id = co.marca left join sockets so on so.id = co.socket where co.descripcion like ? OR ti.descripcion like ? OR ta.descripcion like ? OR ma.descripcion like ? OR so.descripcion like ?"; 
	
	private static final String SELECT_COMPONENTE_TIPO = "select co.* from componentes co left join tipos ti on ti.id = co.tipo where ti.descripcion like ? and co.stock > 0";
	
	
	public List<Componente> selectComponenteByTipo(String tipoParam) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Componente> componentesByTipo = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPONENTE_TIPO)) {
			preparedStatement.setString(1, '%' + tipoParam + '%');
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int tamano = rs.getInt("tamano");
				int socket = rs.getInt("socket");
				int consumo = rs.getInt("consumo");
				int precio = rs.getInt("precio");
				int marca = rs.getInt("marca");
				int tipo = rs.getInt("tipo");
				int stock=rs.getInt("stock");
				Marca marcaObj = marcaDAO.selectMarca(marca);
				Tamano tamanoObj = tamanoDAO.selectTamano(tamano);
				Socket socketObj = socketDAO.selectSocket(socket);
				Tipo tipoObj = tipoDAO.selectTipo(tipo);
				componentesByTipo.add(new Componente(id,descripcion,tamanoObj,socketObj,consumo,precio,marcaObj,tipoObj,stock));
			}
	} catch (SQLException e) {
		System.out.println("Hubo un error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return componentesByTipo;
}
	
	public List<Componente> searchComponente(String search) {
		Componente componenteExistente = null;
		List<Componente> componentes = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SEARCH);) {
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setString(2, "%" + search + "%");
			preparedStatement.setString(3, "%" + search + "%");
			preparedStatement.setString(4, "%" + search + "%");
			preparedStatement.setString(5, "%" + search + "%");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int idComponente = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int tamano = rs.getInt("tamano");
				int socket = rs.getInt("socket");
				int consumo = rs.getInt("consumo");
				int precio = rs.getInt("precio");
				int marca = rs.getInt("marca");
				int tipo=rs.getInt("tipo");
				int stock=rs.getInt("stock");
				Marca marcaObj = marcaDAO.selectMarca(marca);
				Tamano tamanoObj = tamanoDAO.selectTamano(tamano);
				Socket socketObj = socketDAO.selectSocket(socket);
				Tipo tipoObj = tipoDAO.selectTipo(tipo);
				componenteExistente = new Componente(idComponente,descripcion,tamanoObj,socketObj,consumo,precio,marcaObj,tipoObj,stock);
				componentes.add(componenteExistente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return componentes;
	}
	
	public ComponenteDAO(){
		
	}
	
	public int countWhereSocket(int id){
		int count = 0;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WHERE_SOCKET)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
			System.out.println(count);
	} catch (SQLException e) {
		System.out.println("Hubo un error contando los registros");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return count;
}
	
	public int countWhereTamano(int id){
		int count = 0;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WHERE_TAMANO)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
			System.out.println(count);
	} catch (SQLException e) {
		System.out.println("Hubo un error contando los registros");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return count;
}
	
	public int countWhereTipo(int id){
		int count = 0;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WHERE_TIPO)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
			System.out.println(count);
	} catch (SQLException e) {
		System.out.println("Hubo un error contando los registros");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return count;
}
	
	public int countWhereMarca(int id){
			int count = 0;
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WHERE_MARCA)) {
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					count = rs.getInt("count");
				}
				System.out.println(count);
		} catch (SQLException e) {
			System.out.println("Hubo un error contando los registros");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
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
	
	public void add(Componente componente) throws SQLException {
		System.out.println(INSERT_COMPONENTE);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMPONENTE)) {
			preparedStatement.setString(1, componente.getDescripcion());
			preparedStatement.setInt(2, componente.getTamano().getId());
			preparedStatement.setInt(3, componente.getSocket().getId());
			preparedStatement.setInt(4, componente.getConsumo());
			preparedStatement.setInt(5, componente.getPrecio());
			preparedStatement.setInt(6, componente.getMarca().getId());
			preparedStatement.setInt(7, componente.getTipo().getId());
			preparedStatement.setInt(8, 0);
			preparedStatement.setInt(9, componente.getStock());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();}
	}
	
	public boolean updateComponente(Componente componente) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMPONENTE);) {
			preparedStatement.setString(1, componente.getDescripcion());
			preparedStatement.setInt(2, componente.getTamano().getId());
			preparedStatement.setInt(3, componente.getSocket().getId());
			preparedStatement.setInt(4, componente.getConsumo());
			preparedStatement.setInt(5, componente.getPrecio());
			preparedStatement.setInt(6, componente.getMarca().getId());
			preparedStatement.setInt(7, componente.getTipo().getId());
			preparedStatement.setInt(8, componente.getStock());
			preparedStatement.setInt(9, componente.getBorrado());
			preparedStatement.setInt(10, componente.getId());
			System.out.println(preparedStatement);

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public List<Componente> selectComponentesSS() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Componente> componentesND = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMPONENTES_SIN_STOCK)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int tamano = rs.getInt("tamano");
				int socket = rs.getInt("socket");
				int consumo = rs.getInt("consumo");
				int precio = rs.getInt("precio");
				int marca = rs.getInt("marca");
				int tipo=rs.getInt("tipo");
				int stock=rs.getInt("stock");
				Marca marcaObj = marcaDAO.selectMarca(marca);
				Tamano tamanoObj = tamanoDAO.selectTamano(tamano);
				Socket socketObj = socketDAO.selectSocket(socket);
				Tipo tipoObj = tipoDAO.selectTipo(tipo);
				componentesND.add(new Componente(id,descripcion,tamanoObj,socketObj,consumo,precio,marcaObj,tipoObj,stock));
			}
	} catch (SQLException e) {
		System.out.println("Hubo un error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return componentesND;
}
	
	public boolean delete(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_COMPONENTE)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean restaurar(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(RESTAURAR_COMPONENTE)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	
	public List<Componente> selectComponentesBorrados() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Componente> componentesBorrados = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMPONENTES_BORRADOS)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int tamano = rs.getInt("tamano");
				int socket = rs.getInt("socket");
				int consumo = rs.getInt("consumo");
				int precio = rs.getInt("precio");
				int marca = rs.getInt("marca");
				int tipo=rs.getInt("tipo");
				int stock=rs.getInt("stock");
				Marca marcaObj = marcaDAO.selectMarca(marca);
				Tamano tamanoObj = tamanoDAO.selectTamano(tamano);
				Socket socketObj = socketDAO.selectSocket(socket);
				Tipo tipoObj = tipoDAO.selectTipo(tipo);
				componentesBorrados.add(new Componente(id,descripcion,tamanoObj,socketObj,consumo,precio,marcaObj,tipoObj,stock));
			}
	} catch (SQLException e) {
		System.out.println("Hubo un error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return componentesBorrados;
}
	
	//selectComponente
	public Componente selectComponente(int id) {
		Componente componenteExistente = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPONENTE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int idComponente = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int tamano = rs.getInt("tamano");
				int socket = rs.getInt("socket");
				int consumo = rs.getInt("consumo");
				int precio = rs.getInt("precio");
				int marca = rs.getInt("marca");
				int tipo=rs.getInt("tipo");
				int stock=rs.getInt("stock");
				int borrado=rs.getInt("borrado");
				Marca marcaObj = marcaDAO.selectMarca(marca);
				Tamano tamanoObj = tamanoDAO.selectTamano(tamano);
				Socket socketObj = socketDAO.selectSocket(socket);
				Tipo tipoObj = tipoDAO.selectTipo(tipo);
				componenteExistente = new Componente(id,descripcion,tamanoObj,socketObj,consumo,precio,marcaObj,tipoObj,stock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return componenteExistente;
	}

	
	public List<Componente> selectAll() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Componente> componentes = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMPONENTES)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				int tamano = rs.getInt("tamano");
				int socket = rs.getInt("socket");
				int consumo = rs.getInt("consumo");
				int precio = rs.getInt("precio");
				int marca = rs.getInt("marca");
				int tipo=rs.getInt("tipo");
				int stock=rs.getInt("stock");
				Marca marcaObj = marcaDAO.selectMarca(marca);
				Tamano tamanoObj = tamanoDAO.selectTamano(tamano);
				Socket socketObj = socketDAO.selectSocket(socket);
				Tipo tipoObj = tipoDAO.selectTipo(tipo);
				componentes.add(new Componente(id,descripcion,tamanoObj,socketObj,consumo,precio,marcaObj,tipoObj,stock));
			}
	} catch (SQLException e) {
		System.out.println("Hubo un error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return componentes;
}
}
