package repository;

import java.util.List;

import model.Usuario;

public interface UsuarioRepository {
	
		public void cadastrar(Usuario usuario);
		
		public void excluir(int indice);
		
		public void alterar(Usuario usuario);
		
		public List<Usuario> obterTodosUsuarios();

		public Usuario obterPorId(int id);
		
	}

