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
import com.tpjava.dao.SocketDAO;
import com.tpjava.model.Socket;

/**
 * Servlet implementation class ServletSocket
 */
@WebServlet("/sockets")
public class ServletSocket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Socket socket = new Socket();
	SocketDAO socketDAO = new SocketDAO();
	ComponenteDAO componenteDAO = new ComponenteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSocket() {
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
			int cantidadComponentesConSocket;
				cantidadComponentesConSocket = componenteDAO.countWhereSocket(id);
			if(cantidadComponentesConSocket < 1) {				
				try {
					socketDAO.delete(id);
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
				socketDAO.restaurar(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("editar") != null) {					
			int id = Integer.parseInt(request.getParameter("editar"));
			Socket socketExistente = socketDAO.selectSocket(id);
			request.setAttribute("socketExistente", socketExistente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("amSocket.jsp");
			dispatcher.forward(request, response);
		}else if(request.getParameter("crear")!=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("amSocket.jsp");
			dispatcher.forward(request, response);
		}else{
        	List<Socket> listaSockets = socketDAO.selectAll();
        	request.setAttribute("listaSockets", listaSockets);
        	List<Socket> listaSocketsBorradas = socketDAO.selectBorradas();
        	request.setAttribute("listaSocketsBorradas", listaSocketsBorradas);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sockets.jsp");
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
			Socket socketCreada = new Socket(id, descripcion);
			try {
				socketDAO.update(socketCreada);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {			
			// TODO Auto-generated method stub
			String descripcion = request.getParameter("descripcion");
			Socket socketCreada = new Socket(descripcion,0);
			try {
				socketDAO.add(socketCreada);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("sockets");
	}
}
