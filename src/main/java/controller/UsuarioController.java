package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import repository.UsuarioRepository;
import repository.UsuarioRepositoryBanco;
import repository.UsuarioRepositoryLista;


/*
 * O comando @WebServlet equivale a um xml que configura as caracteristias da classe
 *  que está abaixo dele. Uma informação importante  é o urlPatterns (é um array), que especifa
 *  quais serão os possíveis nomes que o client poderá usar para invocar essa classe.
 *  Para ver outras possibilidades, ctrl + clique no @WebServlet
*/
@WebServlet (urlPatterns = {"/usucontroller", "/usuariocontroller"})
public class UsuarioController extends HttpServlet {
	
	UsuarioRepository usuarioRepository = new UsuarioRepositoryBanco();
	
	//Métodos HTTP
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Obtem dados do Request
		String nome = req.getParameter("nome");
		String senha = req.getParameter("senha");
		// Instancia Usuario
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setSenha(senha);
		// Grava Usuario
		usuarioRepository.cadastrar(usuario);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//obtem por ID
		if (req.getParameter("id") != null) {
			int id = Integer.parseInt(req.getParameter("id"));
			Usuario usu = usuarioRepository.obterPorId(id);
			String usuJson = "{\"id\":\"" +usu.getId()+ "\",\"nome\":\"" +usu.getNome()+ "\",\"senha\":\"" +usu.getSenha()+ "\"}";
			// Devolve o Json para o Client
			resp.getWriter().println(usuJson);
		} else {
			//obtem todos os usuarios
			List<Usuario> lista = usuarioRepository.obterTodosUsuarios();
			
			/* Colocando no formato JSON
			 * [ {'nome':"jao","senha":"123"},{"nome":"josé","senha":"456"} ]
			 * Para testar o json ir no site "json formatter & validator"
			*/
			String usuariosJson = "[";
			for (int i = 0; i < lista.size(); i++) {
				if (i != 0) {
					usuariosJson += ",";
				}
				Usuario usu = lista.get(i);
				usuariosJson += "{\"id\":\"" +usu.getId()+ "\",\"nome\":\"" +usu.getNome()+ "\",\"senha\":\"" +usu.getSenha()+ "\"}";
			}
			usuariosJson += "]";
			// Devolve o Json para o Client
			resp.getWriter().println(usuariosJson);
		}
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Obtem dados do Request
		int id = Integer.parseInt(req.getParameter("id"));
		String nome = req.getParameter("nome");
		String senha = req.getParameter("senha");
		// Instancia Usuario
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNome(nome);
		usuario.setSenha(senha);
		// Altera Usuario
		usuarioRepository.alterar(usuario);
		
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Obtem id 
		int id = Integer.parseInt(req.getParameter("id"));
		// Exlui usuario pelo id
		usuarioRepository.excluir(id);
	}
	@Override
	public void init() throws ServletException {
		/* Insere 3 usuários no início quando trabalhar com lista
		Usuario us1 = new Usuario();
		us1.setNome("jao");
		us1.setSenha("123");
		usuarioRepository.cadastrar(us1);
		Usuario us2 = new Usuario();
		us2.setNome("jose");
		us2.setSenha("234");
		usuarioRepository.cadastrar(us2);
		Usuario us3 = new Usuario();
		us3.setNome("maria");
		us3.setSenha("789");
		usuarioRepository.cadastrar(us3);
		 */
	}
}
