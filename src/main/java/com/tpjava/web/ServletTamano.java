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
import com.tpjava.dao.TamanoDAO;
import com.tpjava.model.Tamano;

/**
 * Servlet implementation class ServletTamano
 */
@WebServlet("/tamanos")
public class ServletTamano extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Tamano tamano = new Tamano();
	TamanoDAO tamanoDAO = new TamanoDAO();
	ComponenteDAO componenteDAO = new ComponenteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTamano() {
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
			int cantidadComponentesConTamano;
				cantidadComponentesConTamano = componenteDAO.countWhereTamano(id);
			if(cantidadComponentesConTamano < 1) {				
				try {
					tamanoDAO.delete(id);
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
				tamanoDAO.restaurar(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("editar") != null) {					
			int id = Integer.parseInt(request.getParameter("editar"));
			Tamano tamanoExistente = tamanoDAO.selectTamano(id);
			request.setAttribute("tamanoExistente", tamanoExistente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("amTamano.jsp");
			dispatcher.forward(request, response);
		}else if(request.getParameter("crear")!=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("amTamano.jsp");
			dispatcher.forward(request, response);
		}else{
        	List<Tamano> listaTamanos = tamanoDAO.selectAll();
        	request.setAttribute("listaTamanos", listaTamanos);
        	List<Tamano> listaTamanosBorradas = tamanoDAO.selectBorradas();
        	request.setAttribute("listaTamanosBorradas", listaTamanosBorradas);
			RequestDispatcher dispatcher = request.getRequestDispatcher("tamanos.jsp");
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
			Tamano tamanoCreada = new Tamano(id, descripcion);
			try {
				tamanoDAO.update(tamanoCreada);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {			
			// TODO Auto-generated method stub
			String descripcion = request.getParameter("descripcion");
			Tamano tamanoCreada = new Tamano(descripcion,0);
			try {
				tamanoDAO.add(tamanoCreada);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("tamanos");
	}
}
