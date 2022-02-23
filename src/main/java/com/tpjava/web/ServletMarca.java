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
import com.tpjava.model.Componente;
import com.tpjava.model.Marca;

/**
 * Servlet implementation class ServletMarca
 */
@WebServlet("/marcas")
public class ServletMarca extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Marca marca = new Marca();
	MarcaDAO marcaDAO = new MarcaDAO();
	ComponenteDAO componenteDAO = new ComponenteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMarca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("eliminar") != null) {
			int id = Integer.parseInt(request.getParameter("eliminar"));
			int cantidadComponentesConMarca;
				cantidadComponentesConMarca = componenteDAO.countWhereMarca(id);
			if(cantidadComponentesConMarca < 1) {				
				try {
					marcaDAO.delete(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				request.setAttribute("alertFKDelete", true);
			}
		}
		if(request.getParameter("restaurar") != null) {					
			int id = Integer.parseInt(request.getParameter("restaurar"));
			try {
				marcaDAO.restaurar(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("editar") != null) {					
			int id = Integer.parseInt(request.getParameter("editar"));
			Marca marcaExistente = marcaDAO.selectMarca(id);
			request.setAttribute("marcaExistente", marcaExistente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("amMarca.jsp");
			dispatcher.forward(request, response);
		}else if(request.getParameter("crear")!=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("amMarca.jsp");
			dispatcher.forward(request, response);
		}else{
        	List<Marca> listaMarcas = marcaDAO.selectAll();
        	request.setAttribute("listaMarcas", listaMarcas);
        	List<Marca> listaMarcasBorradas = marcaDAO.selectBorradas();
        	request.setAttribute("listaMarcasBorradas", listaMarcasBorradas);
			RequestDispatcher dispatcher = request.getRequestDispatcher("marcas.jsp");
			dispatcher.forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			String descripcion = request.getParameter("descripcion");
			Marca marcaCreada = new Marca(id, descripcion);
			try {
				marcaDAO.update(marcaCreada);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {			
			// TODO Auto-generated method stub
			String descripcion = request.getParameter("descripcion");
			Marca marcaCreada = new Marca(descripcion,0);
			try {
				marcaDAO.add(marcaCreada);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("marcas");
	}
}
