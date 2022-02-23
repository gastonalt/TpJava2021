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
import com.tpjava.dao.TipoDAO;
import com.tpjava.model.Tipo;

/**
 * Servlet implementation class ServletTipo
 */
@WebServlet("/tipos")
public class ServletTipo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Tipo tipo = new Tipo();
	TipoDAO tipoDAO = new TipoDAO();
	ComponenteDAO componenteDAO = new ComponenteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTipo() {
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
			int cantidadComponentesConTipo;
				cantidadComponentesConTipo = componenteDAO.countWhereTipo(id);
			if(cantidadComponentesConTipo < 1) {				
				try {
					tipoDAO.delete(id);
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
				tipoDAO.restaurar(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("editar") != null) {					
			int id = Integer.parseInt(request.getParameter("editar"));
			Tipo tipoExistente = tipoDAO.selectTipo(id);
			request.setAttribute("tipoExistente", tipoExistente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("amTipo.jsp");
			dispatcher.forward(request, response);
		}else if(request.getParameter("crear")!=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("amTipo.jsp");
			dispatcher.forward(request, response);
		}else{
        	List<Tipo> listaTipos = tipoDAO.selectAll();
        	request.setAttribute("listaTipos", listaTipos);
        	List<Tipo> listaTiposBorradas = tipoDAO.selectBorradas();
        	request.setAttribute("listaTiposBorradas", listaTiposBorradas);
			RequestDispatcher dispatcher = request.getRequestDispatcher("tipos.jsp");
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
			Tipo tipoCreada = new Tipo(id, descripcion);
			try {
				tipoDAO.update(tipoCreada);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {			
			// TODO Auto-generated method stub
			String descripcion = request.getParameter("descripcion");
			Tipo tipoCreada = new Tipo(descripcion,0);
			try {
				tipoDAO.add(tipoCreada);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("tipos");
	}

}
