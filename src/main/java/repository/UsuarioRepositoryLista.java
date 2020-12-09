package repository;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioRepositoryLista implements UsuarioRepository {
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	@Override
	public void cadastrar(Usuario usuario) {
		usuarios.add(usuario);
	}
	@Override
	public void excluir(int indice) {
		usuarios.remove(indice);
	}
	@Override
	public void alterar(int indice, Usuario usuario) {
		usuarios.set(indice, usuario);
	}
	@Override
	public String obterTodosUsuarios() {
		/*
		 * Montando o Json com todos os usuarios
		 */
		String usuariosJson = "[";
		for (int i = 0; i < usuarios.size(); i++) {
			if (i != 0) {
				usuariosJson += ",";
			}
			Usuario usu = usuarios.get(i);
			usuariosJson += "{\"nome\":\"" +usu.getNome()+ "\",\"senha\":\"" +usu.getSenha()+ "\"}";
		}
		usuariosJson += "]";
		/*
		 * 
		 */
		return usuariosJson;
	}
				
		
	
}


