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
	public void excluir(int id) {
		usuarios.remove(id);
	}
	@Override
	public void alterar(Usuario usuario) {
		usuarios.set(usuario.getId(), usuario);
	}
	@Override
	public List<Usuario> obterTodosUsuarios() {
		
		return usuarios;
		
	}
	@Override
	public Usuario obterPorId(int id) {
		return null;
	}
				
		
	
}


