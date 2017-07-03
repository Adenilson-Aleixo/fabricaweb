package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

//http://localhost:8080/fabricaweb/usucontroller.do
@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {

	public UsuarioController() {
		System.out.println("Contrutor");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		String acao = req.getParameter("acao");

		if (acao.equals("exc")) {

			String id = req.getParameter("id");
			Usuario usu = new Usuario();
			if (id != null)
				usu.setId(Integer.parseInt(id));
			UsuarioDAO usuDAO = new UsuarioDAO();
			usuDAO.excluir(usu);

			System.out.println("Ecluido com sucesso.");
		} else if (acao.equals("lis")) {
			UsuarioDAO usuDAO = new UsuarioDAO();
			List<Usuario> lista = usuDAO.buscaTodos(); 
			
			for(Usuario u: lista){
				resp.getWriter().println(u + "<br>");
			}
			
			
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		Usuario usu = new Usuario();
		if (id != null)
			usu.setId(Integer.parseInt(id));
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);

		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);

		resp.getWriter().println("Salvo com sucesso.");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
}
