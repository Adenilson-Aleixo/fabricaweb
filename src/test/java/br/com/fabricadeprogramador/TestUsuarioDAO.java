package br.com.fabricadeprogramador;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		testExcluir();
	}

	public static void testCadastrar() {
		Usuario usu = new Usuario();

		usu.setNome("Adenilson");
		usu.setLogin("ade");
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

}
