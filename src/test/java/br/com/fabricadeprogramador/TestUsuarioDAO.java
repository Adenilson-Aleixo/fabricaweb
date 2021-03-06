package br.com.fabricadeprogramador;


import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		//testBuscaPorId();
		//testBuscaTodos();
		//testCadastrar();
		testAutenticar();
	}

	public static void testCadastrar() {
		Usuario usu = new Usuario();

		usu.setNome("Usuario Teste");
		usu.setLogin("teste");
		usu.setSenha("123");

		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);

		System.out.println("Cadastrado com sucesso.");
	}

	public static void alterarCadastrar() {
		Usuario usu = new Usuario();
		usu.setId(1);
		usu.setNome("Adenilson");
		usu.setLogin("ade");
		usu.setSenha("123456");

		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);

		System.out.println("Alterado com sucesso.");
	}

	public static void testExcluir() {
		Usuario usu = new Usuario();
		usu.setId(1);

		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);

		System.out.println("Excluido com sucesso.");
	}

	public static void testSalvar() {
		Usuario usu = new Usuario();
		usu.setId(2);
		usu.setNome("Adenilson");
		usu.setLogin("ade");
		usu.setSenha("123");

		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		
		System.out.println("Salvo com sucesso.");
	}
	
	public static void testBuscaPorId(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		
		Usuario usu = usuDAO.buscaPorId(2);
		
		System.out.println(usu);
		
	}
	
	public static void testBuscaTodos(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		
		List<Usuario> lista = usuDAO.buscaTodos();
		for(Usuario u: lista ){
			System.out.println(u);
		}
		
	}
	
	private static void testAutenticar (){
		UsuarioDAO usuDAO = new UsuarioDAO();
		
		Usuario usuario = new Usuario();
		usuario.setLogin("teste");
		usuario.setSenha("123");
		
		Usuario usuRetorno = usuDAO.autenticar(usuario);
		
		System.out.println(usuRetorno);
	}

}
