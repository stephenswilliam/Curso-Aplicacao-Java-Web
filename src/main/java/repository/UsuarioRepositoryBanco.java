package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioRepositoryBanco implements UsuarioRepository{
	
	Connection conxao = ConexaoFactory.criarConexao();

	@Override
	public void cadastrar(Usuario usuario) {
		try {
			PreparedStatement preparedSQL = conxao.prepareStatement
					("INSERT INTO usuario (nome, senha) VALUES (?, ?);");
			preparedSQL.setString(1, usuario.getNome());
			preparedSQL.setString(2, usuario.getSenha());
			preparedSQL.execute();
			preparedSQL.close(); // libera o recurso imediatamente, sem esperar que seja feito automaticamente
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(int indice) {
		
		
	}

	@Override
	public void alterar(Usuario usuario) {
	
		try {
			PreparedStatement preparedSQL = conxao.prepareStatement
					("UPDATE usuario SET nome= ? , senha= ? WHERE id = ? ;");
			preparedSQL.setString(1, usuario.getNome());
			preparedSQL.setString(2, usuario.getSenha());
			preparedSQL.setInt(3, usuario.getId());	
			preparedSQL.execute();
			preparedSQL.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public String obterTodosUsuarios() {
		
		return null;
	}
	
	

}
