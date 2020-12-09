package repository;

import model.Usuario;

public interface UsuarioRepository {
	
		public void cadastrar(Usuario usuario);
		
		public void excluir(int indice);
		
		public void alterar(Usuario usuario);
		
		public String obterTodosUsuarios();
		
	}

