package com.tpjava.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpjava.dao.PcArmadaDAO;
import com.tpjava.dao.ComponenteDAO;
import com.tpjava.dao.MarcaDAO;
import com.tpjava.dao.SocketDAO;
import com.tpjava.dao.TamanoDAO;
import com.tpjava.dao.TipoDAO;
import com.tpjava.model.PcArmada;
import com.tpjava.model.Componente;
import com.tpjava.model.Marca;
import com.tpjava.model.Socket;
import com.tpjava.model.Tamano;
import com.tpjava.model.Tipo;

/**
 * Servlet implementation class ServletPcArmada
 */
@WebServlet("/pcarmada")
public class ServletPcArmada extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PcArmada pcArmada = new PcArmada();
	PcArmadaDAO pcArmadaDAO = new PcArmadaDAO();
	MarcaDAO marcaDAO = new MarcaDAO();
	TamanoDAO tamanoDAO = new TamanoDAO();
	SocketDAO socketDAO = new SocketDAO();
	TipoDAO tipoDAO = new TipoDAO();
	ComponenteDAO componenteDAO = new ComponenteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPcArmada() {
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
						pcArmadaDAO.delete(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.sendRedirect("pcarmada");
				}
				else if(request.getParameter("entregado") != null) {					
					int id = Integer.parseInt(request.getParameter("entregado"));
					try {
						pcArmadaDAO.marcarComoEntregada(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.sendRedirect("pcarmada");
				}
				else if(request.getParameter("crear")!=null) {
					
					List<Componente> cpus = componenteDAO.selectComponenteByTipo("cpu");
					request.setAttribute("cpus", cpus);
					
					List<Componente> gpus = componenteDAO.selectComponenteByTipo("gpu");
					request.setAttribute("gpus", gpus);
					
					List<Componente> memos = componenteDAO.selectComponenteByTipo("memo");
					request.setAttribute("memos", memos);
					
					List<Componente> mothers = componenteDAO.selectComponenteByTipo("mother");
					request.setAttribute("mothers", mothers);
					
					List<Componente> gabinete = componenteDAO.selectComponenteByTipo("gabinete");
					request.setAttribute("gabinetes", gabinete);
					
					List<Componente> fuente = componenteDAO.selectComponenteByTipo("fuente");
					request.setAttribute("fuentes", fuente);

					
					RequestDispatcher dispatcher = request.getRequestDispatcher("amPcArmada.jsp");
					dispatcher.forward(request, response);
					return;
				} else {
					List<PcArmada> listaPcArmadas = pcArmadaDAO.selectAll();
					request.setAttribute("listaPcArmadas", listaPcArmadas);
					List<PcArmada> listaPcArmadasEntregadas = pcArmadaDAO.selectPcArmadasEntregadas();
					request.setAttribute("listaPcArmadasEntregadas", listaPcArmadasEntregadas);
					RequestDispatcher dispatcher = request.getRequestDispatcher("pcArmadas.jsp");
					dispatcher.forward(request, response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String cpu = request.getParameter("cpu");
			String gpu = request.getParameter("gpu");
			String motherboard = request.getParameter("motherboard");
			String memoria= request.getParameter("memoria");
			String gabinete = request.getParameter("gabinete");
			String fuente = request.getParameter("fuente");
			String observaciones = request.getParameter("observaciones");
			Componente cpuComponente = componenteDAO.selectComponente(Integer.parseInt(cpu));
			Componente gpuComponente = componenteDAO.selectComponente(Integer.parseInt(gpu));
			Componente memoriaComponente = componenteDAO.selectComponente(Integer.parseInt(memoria));
			Componente motherboardComponente = componenteDAO.selectComponente(Integer.parseInt(motherboard));
			Componente gabineteComponente = componenteDAO.selectComponente(Integer.parseInt(gabinete));
			Componente fuenteComponente = componenteDAO.selectComponente(Integer.parseInt(fuente));
			
			PcArmada pcArmadaCreado = new PcArmada(cpuComponente, gpuComponente, memoriaComponente, motherboardComponente, gabineteComponente, fuenteComponente,0 ,0, observaciones);
			
			try {
				pcArmadaDAO.add(pcArmadaCreado);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("pcarmada");
		}
}
