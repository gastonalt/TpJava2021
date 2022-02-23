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

import com.tpjava.dao.UserDAO;
import com.tpjava.model.Usuario;

//Hacer middleware para ver si está loggeado el user y mostrarle (o no) la pag.

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
    public Usuario user;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init() {
		userDAO = new UserDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		System.out.println(action);
		switch (action) {
			case "/signup":
				signUp(request,response);
				break;
			case "/login":
				login(request,response);
				break;
			case "/logout":
				logout(request,response);
				break;
			case "/verusuarios":
				verUsers(request,response);
				break;
			case "/edit":
			try {
				formularioEdit(request,response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "/delete":
			try {
				delete(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			default:
				index(request,response);
				break;
			}	
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("verusuarios");
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isAdmin = request.getParameter("isAdmin") != null;
		System.out.println(isAdmin);
		Usuario usuarioActualizado = new Usuario(id, username, password, isAdmin);
		userDAO.updateUser(usuarioActualizado);
		response.sendRedirect("verusuarios");
		//hacer validacion si mete mal el password
	}
	
	private void formularioEdit(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario usuarioExistente = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("registro.jsp");
		request.setAttribute("user", usuarioExistente);
		dispatcher.forward(request, response);
	}
	
	private void verUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(user!=null) {			
			List<Usuario> listaUsuarios = userDAO.selectAllUsers();
			request.setAttribute("listaUsuarios", listaUsuarios);
			RequestDispatcher dispatcher = request.getRequestDispatcher("listado.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("login");
		}
		}
	
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Esta página solo se debe acceder si se está logueado
		user = null;
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Esta página no se debe ver si se está logueado
		RequestDispatcher dispatcher = request.getRequestDispatcher("registro.jsp");
		dispatcher.forward(request, response);
	}
	
	private void signUpFw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Esta página no se debe ver si se está logueado
		//MOVER ESTO A POST
		//Capturamos el valor de los campos:
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repeatpassword = request.getParameter("repeatpassword");
		boolean esAdmin = request.getParameter("isAdmin") !=null;
		System.out.println(esAdmin);
		if(password.equals(repeatpassword)) {
			user = new Usuario(username,password,esAdmin);
			try {
				userDAO.insertUser(user);
				System.out.println("REGISTRADO");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Llamamos al UsuarioDAO para AGREGAR esto al DDBB
			response.sendRedirect("verusuarios");
		}else {
			//Seteamos bandera de error para mostrar con c:if
			request.setAttribute("error", "Las contraseñas no coinciden.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("erorr.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Esta página no se debe ver si se está logueado
		RequestDispatcher dispatcher = request.getRequestDispatcher("ingreso.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loginFw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Esta página no se debe ver si se está logueado
		//MOVER ESTO A POST
		//Login logic
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//aca llamamos al CheckUser del userDao (@Query("SELECT * FROM users WHERE username == username && password == password))
		if(userDAO.login(username, password)!=null) { //si el login es correcto y la db nos devuelve un registro
			user = userDAO.login(username, password);
			System.out.println("LOGUEADO CORRECTAMENTE");
			response.sendRedirect("verusuarios");
			//user = {}; Lo que nos traiga de la db lo metemos a user (así o con construct o usando setters)
		}else {
			request.setAttribute("usr", username);
			request.setAttribute("pwd", password);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ingreso.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String action = request.getServletPath();
		switch (action) {
			case "/signUpFw":
				//Pagina a donde apunta el action del formulario para REGISTRARSE
				signUpFw(request,response);
				break;
			case "/loginFw":
				//Pagina donde apunta el action del formulario para LOGIN
				loginFw(request,response);
				break;
			case "/update":
				try {
					updateUser(request,response);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
			default:
				break;
		}
	}
}
