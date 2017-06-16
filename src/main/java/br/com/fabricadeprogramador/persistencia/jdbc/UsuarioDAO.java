package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {

	private Connection con = ConexaoFactory.getConnection();

	public void cadastrar(Usuario usu) {
		String sql = "insert into usuario (nome,login,senha) values(?,?,?)";

		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void alterar(Usuario usu) {
		String sql = "update usuario set nome = ?,login = ?,senha = ? where id = ?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());

			preparador.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void excluir(Usuario usu) {
		String sql = "delete from usuario where id = ?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, usu.getId());
			preparador.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void salvar(Usuario usu) {

		if (usu.getId() != null) {
			alterar(usu);
		} else {
			cadastrar(usu);
		}

	}

	/**
	 * Busca de um registro no banco de dados por um ID do usuario
	 * 
	 * @param id
	 *            identificacao do usuario
	 * @return Um objeto usuario quando encontra e nullo quando não encontra
	 */
	public Usuario buscaPorId(Integer id) {

		String sql = "select * from usuario where id = ?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();

			// Posicionando o cursor no primeiro registro
			if (resultado.next()) {
				Usuario usu = new Usuario();
				usu.setId(id);
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));

				return usu;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Realiza a busca de todos os registros da tabela de usuarios
	 * @return Uma lista de objetos usuario contendo 0 elementos quando tiver 
	 * resgistro ou n elementos quando tiver resultado
	 */
	public List<Usuario> buscaTodos() {

		String sql = "select * from usuario";
		
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			

			ResultSet resultado = preparador.executeQuery();

			// Posicionando o cursor no primeiro registro
			while (resultado.next()) {
				Usuario usu = new Usuario();
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));
				//adicionando o usuario na lista
				lista.add(usu);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
	
	public Usuario autenticar (Usuario usuConsulta){
		String sql = "select * from usuario where login=? and senha=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				return usuario;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
		
	}
	
}
