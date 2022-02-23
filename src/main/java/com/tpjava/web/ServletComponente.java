package com.tpjava.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpjava.dao.ComponenteDAO;
import com.tpjava.dao.MarcaDAO;
import com.tpjava.dao.SocketDAO;
import com.tpjava.dao.TamanoDAO;
import com.tpjava.dao.TipoDAO;
import com.tpjava.model.Componente;
import com.tpjava.model.Marca;
import com.tpjava.model.Socket;
import com.tpjava.model.Tamano;
import com.tpjava.model.Tipo;

/**
 * Servlet implementation class ServletComponente
 */
@WebServlet("/componentes")
public class ServletComponente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Componente componente = new Componente();
	ComponenteDAO componenteDAO = new ComponenteDAO();
	MarcaDAO marcaDAO = new MarcaDAO();
	TamanoDAO tamanoDAO = new TamanoDAO();
	SocketDAO socketDAO = new SocketDAO();
	TipoDAO tipoDAO = new TipoDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletComponente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				if(request.getParameter("id") != null) {					
					int id = Integer.parseInt(request.getParameter("id"));
					try {
						componenteDAO.delete(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(request.getParameter("restaurar") != null) {					
					int id = Integer.parseInt(request.getParameter("restaurar"));
					try {
						componenteDAO.restaurar(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(request.getParameter("editar") != null) {					
					int id = Integer.parseInt(request.getParameter("editar"));
					Componente componenteExistente = componenteDAO.selectComponente(id);
					request.setAttribute("componenteExistente", componenteExistente);
					
					List<Marca> marcas = marcaDAO.selectAll();
					request.setAttribute("marcas", marcas);
					
					List<Socket> sockets = socketDAO.selectAll();
					request.setAttribute("sockets", sockets);
					
					List<Tamano> tamanos = tamanoDAO.selectAll();
					request.setAttribute("tamanos", tamanos);
					
					List<Tipo> tipos = tipoDAO.selectAll();
					request.setAttribute("tipos", tipos);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("amComponente.jsp");
					dispatcher.forward(request, response);
					return;
				}else if(request.getParameter("crear")!=null) {
					
					List<Marca> marcas = marcaDAO.selectAll();
					request.setAttribute("marcas", marcas);
					
					List<Socket> sockets = socketDAO.selectAll();
					request.setAttribute("sockets", sockets);
					
					List<Tamano> tamanos = tamanoDAO.selectAll();
					request.setAttribute("tamanos", tamanos);
					
					List<Tipo> tipos = tipoDAO.selectAll();
					request.setAttribute("tipos", tipos);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("amComponente.jsp");
					dispatcher.forward(request, response);
					return;
				} if(request.getParameter("searchInput")!= null) {
					
					
					
					String searchInput = request.getParameter("searchInput");
					List<Componente> componentesEncontrados = componenteDAO.searchComponente(searchInput);
					request.setAttribute("listaComponentes", componentesEncontrados);
					request.setAttribute("listaComponentesSS", null);
					request.setAttribute("listaComponentesBorrados", null);
					RequestDispatcher dispatcher = request.getRequestDispatcher("componentes.jsp");
					dispatcher.forward(request, response);
					
					
					
				}else {
					
					List<Componente> listaComponentes = componenteDAO.selectAll();
					request.setAttribute("listaComponentes", listaComponentes);
					List<Componente> listaComponentesSS = componenteDAO.selectComponentesSS();
					request.setAttribute("listaComponentesSS", listaComponentesSS);
					List<Componente> listaComponentesBorrados = componenteDAO.selectComponentesBorrados();
					request.setAttribute("listaComponentesBorrados", listaComponentesBorrados);
					RequestDispatcher dispatcher = request.getRequestDispatcher("componentes.jsp");
					dispatcher.forward(request, response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			String descripcion = request.getParameter("descripcion");
			String tamano = request.getParameter("tamano");
			String socket = request.getParameter("socket");
			String consumo = request.getParameter("consumo");
			String precio = request.getParameter("precio");
			String marca = request.getParameter("marca");
			if(marca == null) {
				Componente componenteAux = componenteDAO.selectComponente(id);
				marca = componenteAux.getMarca().getDescripcion();
			}
			String tipo = request.getParameter("tipo");
			String stock = request.getParameter("stock");
			String borrado = request.getParameter("borrado");
			System.out.println(borrado);
			Marca marcaObj = marcaDAO.selectMarca(Integer.parseInt(marca));
			Tamano tamanoObj = tamanoDAO.selectTamano(Integer.parseInt(tamano));
			Socket socketObj = socketDAO.selectSocket(Integer.parseInt(socket));
			Tipo tipoObj = tipoDAO.selectTipo(Integer.parseInt(tipo));
			Componente componenteCreado = new Componente(id, descripcion, tamanoObj,socketObj,Integer.parseInt(consumo),Integer.parseInt(precio),marcaObj,tipoObj,Integer.parseInt(stock), Integer.parseInt(borrado));
			try {
				componenteDAO.updateComponente(componenteCreado);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{	
			// TODO Auto-generated method stub
			String descripcion = request.getParameter("descripcion");
			String tamano = request.getParameter("tamano");
			String socket = request.getParameter("socket");
			String consumo = request.getParameter("consumo");
			String precio = request.getParameter("precio");
			String marca = request.getParameter("marca");
			String tipo = request.getParameter("tipo");
			String stock = request.getParameter("stock");
			Marca marcaObj = marcaDAO.selectMarca(Integer.parseInt(marca));
			Tamano tamanoObj = tamanoDAO.selectTamano(Integer.parseInt(tamano));
			Socket socketObj = socketDAO.selectSocket(Integer.parseInt(socket));
			Tipo tipoObj = tipoDAO.selectTipo(Integer.parseInt(tipo));
			Componente componenteCreado = new Componente( descripcion, tamanoObj,socketObj,Integer.parseInt(consumo),Integer.parseInt(precio),marcaObj,tipoObj,Integer.parseInt(stock));
			try {
				componenteDAO.add(componenteCreado);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("componentes");
	}

}
