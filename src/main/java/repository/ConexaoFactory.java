package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	private static Connection conexao = null;
	
	public static Connection criarConexao() {
		try {
			Class.forName("org.postgresql.Driver"); //Carrega o drive na memória
			// garantindo o padrão Singleton - só uma conexao
			if (conexao == null) {	
			 conexao = DriverManager.getConnection
					 ("jdbc:postgresql://localhost:5432/cursojavaweb","william","will");
			}
			return conexao;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}	

}
