package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioRepositoryBanco implements UsuarioRepository{
	
	Connection conexao = ConexaoFactory.criarConexao();

	@Override
	public void cadastrar(Usuario usuario) {
		try {
			PreparedStatement preparedSQL = conexao.prepareStatement
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
	public void excluir(int id) {
		
		try {
			PreparedStatement preparedSQL = conexao.prepareStatement
					("DELETE FROM usuario where id = ? ;");
			preparedSQL.setInt(1, id);
			preparedSQL.execute();
			preparedSQL.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void alterar(Usuario usuario) {
	
		try {
			PreparedStatement preparedSQL = conexao.prepareStatement
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
	public List<Usuario> obterTodosUsuarios() {
		
		List<Usuario> usuarios = new ArrayList<>();
		
		PreparedStatement preparedSQL;
		try {
			preparedSQL = conexao.prepareStatement
					("SELECT * from usuario order by nome;");
			ResultSet resultSet = preparedSQL.executeQuery();
			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				usuarios.add(usuario);
			}
			preparedSQL.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}

	@Override
	public Usuario obterPorId(int id) {
		
		Usuario usuario = new Usuario();
		
		PreparedStatement preparedSQL;
		try {
			preparedSQL = conexao.prepareStatement
					("SELECT * from usuario where id = ?;");
			preparedSQL.setInt(1, id);
			ResultSet resultSet = preparedSQL.executeQuery();
			if (resultSet.next()) {
				usuario.setId(resultSet.getInt("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
			}
			preparedSQL.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
		

	}
	
	

}
