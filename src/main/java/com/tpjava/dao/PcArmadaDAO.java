package com.tpjava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpjava.model.PcArmada;
import com.tpjava.model.Componente;
import com.tpjava.model.Marca;
import com.tpjava.model.Socket;
import com.tpjava.model.Tamano;
import com.tpjava.model.Tipo;
import com.tpjava.model.Usuario;

public class PcArmadaDAO {
	
	private String jdbcURL= "jdbc:mysql://localhost:3306/tpJava?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcContra = "";
	MarcaDAO marcaDAO = new MarcaDAO();
	TamanoDAO tamanoDAO = new TamanoDAO();
	TipoDAO tipoDAO = new TipoDAO();
	SocketDAO socketDAO = new SocketDAO();
	ComponenteDAO componenteDAO = new ComponenteDAO();
	
	
	private static final String INSERT_PCARMADA = "INSERT INTO pcArmadas" + " (cpu, gpu, memoria, gabinete, fuente) VALUES " + "(?, ?, ?, ?, ?);";
	
	private static final String SELECT_ALL_PCARMADAS = "select * from pcArmadas where entregado = 0 and borrado = 0";
	
	private static final String SELECT_ALL_PCARMADAS_ENTREGADAS = "select * from pcArmadas where entregado = 1";
	
	private static final String ENTREGA_PCARMADA = "UPDATE pcArmadas SET entregado = 1 where id = ?";
	
	private static final String DELETE_PCARMADA = "UPDATE pcArmadas SET borrado = 1 where id = ?";
	
	private static final String SELECT_PCARMADA_BY_ID = "select * from pcArmadas where id = ?";
	
	private static final String UPDATE_PCARMADA = "update pcArmadas set cpu = ?,gpu= ?, memoria=?, gabinete=?, fuente=? where id = ?;";
	
	private static final String SELECT_WHERE_COMPONENTE = "select count(id) as count from pcArmadas where cpu = ? or gpu = ? or memoria = ? or gabinete = ? or fuente = ? and entregado = 1";
	
	public PcArmadaDAO(){
		
	}

	public int countWhereComponente(int id){
		int count = 0;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WHERE_COMPONENTE)) {
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
	
	public void add(PcArmada pcArmada) throws SQLException {
		System.out.println(INSERT_PCARMADA);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PCARMADA)) {
			List<Integer> componentes = new ArrayList<Integer>();
			
			int cpu = pcArmada.getCpu().getId();
			int gpu = pcArmada.getGpu().getId();
			int memoria = pcArmada.getMemoria().getId();
			int gabinete = pcArmada.getGabinete().getId();
			int fuente = pcArmada.getFuente().getId();
			
			componentes.add(cpu);
			componentes.add(gpu);
			componentes.add(memoria);
			componentes.add(gabinete);
			componentes.add(fuente);
			
			preparedStatement.setInt(1, cpu);
			preparedStatement.setInt(2, gpu);
			preparedStatement.setInt(3, memoria);
			preparedStatement.setInt(4, gabinete);
			preparedStatement.setInt(5, fuente);
			
			for(int id: componentes) {				
				Componente componenteSeleccionado = componenteDAO.selectComponente(id);
				componenteSeleccionado.setStock(componenteSeleccionado.getStock() - 1);
				componenteDAO.updateComponente(componenteSeleccionado);
			}
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();}
	}
	
	// A FUTURO: CREAR EL EDIT DE LA PC... (POR LOGICA DE STOCK)
	
	public boolean marcarComoEntregada(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(ENTREGA_PCARMADA)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean delete(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PCARMADA)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		//DEBERIAMOS RESTAURAR EL STOCK DE LOS COMPONENTES +1
		List<Componente> componentes = new ArrayList<Componente>();
		
		PcArmada pcSeleccionada = this.selectPcArmada(id);
		Componente cpu = pcSeleccionada.getCpu();
		Componente gpu = pcSeleccionada.getGpu();
		Componente memoria = pcSeleccionada.getMemoria();
		Componente gabinete = pcSeleccionada.getGabinete();
		Componente fuente = pcSeleccionada.getFuente();
		
		componentes.add(cpu);
		componentes.add(gpu);
		componentes.add(memoria);
		componentes.add(gabinete);
		componentes.add(fuente);
		
		for(Componente componente: componentes) {
			componente.setStock(componente.getStock() + 1);
			componenteDAO.updateComponente(componente);
		}
		
		return rowDeleted;
	}
	
	//selectPcArmada
	public PcArmada selectPcArmada(int id) {
		PcArmada pcArmadaExistente = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PCARMADA_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int idPcArmada = rs.getInt("id");
				int cpu = rs.getInt("cpu");
				int gpu = rs.getInt("gpu");
				int memoria = rs.getInt("memoria");
				int gabinete = rs.getInt("gabinete");
				int fuente = rs.getInt("fuente");
				int borrado = rs.getInt("borrado");
				int entregado = rs.getInt("entregado");

				Componente cpuObj = componenteDAO.selectComponente(cpu);
				Componente gpuObj = componenteDAO.selectComponente(gpu);
				Componente memoriaObj = componenteDAO.selectComponente(memoria);
				Componente gabineteObj = componenteDAO.selectComponente(gabinete);
				Componente fuenteObj = componenteDAO.selectComponente(fuente);
				pcArmadaExistente = new PcArmada(id, cpuObj, gpuObj, memoriaObj, gabineteObj, fuenteObj, borrado, entregado);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pcArmadaExistente;
	}

	
	public List<PcArmada> selectAll() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<PcArmada> pcArmadas = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PCARMADAS)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int idPcArmada = rs.getInt("id");
				int cpu = rs.getInt("cpu");
				int gpu = rs.getInt("gpu");
				int memoria = rs.getInt("memoria");
				int gabinete = rs.getInt("gabinete");
				int fuente = rs.getInt("fuente");
				int borrado = rs.getInt("borrado");
				int entregado = rs.getInt("entregado");

				Componente cpuObj = componenteDAO.selectComponente(cpu);
				Componente gpuObj = componenteDAO.selectComponente(gpu);
				Componente memoriaObj = componenteDAO.selectComponente(memoria);
				Componente gabineteObj = componenteDAO.selectComponente(gabinete);
				Componente fuenteObj = componenteDAO.selectComponente(fuente);
				pcArmadas.add(new PcArmada(idPcArmada, cpuObj, gpuObj, memoriaObj, gabineteObj, fuenteObj, borrado, entregado));
			}
	} catch (SQLException e) {
		System.out.println("Hubo un error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return pcArmadas;
}
	
	public List<PcArmada> selectPcArmadasEntregadas() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<PcArmada> pcArmadas = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PCARMADAS_ENTREGADAS)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int idPcArmada = rs.getInt("id");
				int cpu = rs.getInt("cpu");
				int gpu = rs.getInt("gpu");
				int memoria = rs.getInt("memoria");
				int gabinete = rs.getInt("gabinete");
				int fuente = rs.getInt("fuente");
				int borrado = rs.getInt("borrado");
				int entregado = rs.getInt("entregado");

				Componente cpuObj = componenteDAO.selectComponente(cpu);
				Componente gpuObj = componenteDAO.selectComponente(gpu);
				Componente memoriaObj = componenteDAO.selectComponente(memoria);
				Componente gabineteObj = componenteDAO.selectComponente(gabinete);
				Componente fuenteObj = componenteDAO.selectComponente(fuente);
				pcArmadas.add(new PcArmada(idPcArmada, cpuObj, gpuObj, memoriaObj, gabineteObj, fuenteObj, borrado, entregado));
			}
	} catch (SQLException e) {
		System.out.println("Hubo un error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return pcArmadas;
}
	
}
