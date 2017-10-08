package br.com.ecc.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe para criação da conexão com o banco de dados"
 * @author Weverson Munir Almeida de Souza
 * @since 01/06/2014
 * */

public class ConnectionFactory {
	
	public static Connection getConnection() throws Exception{
		try{
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/astec_bd","postgres","postgres");
		}
		catch (Exception e){
		   throw new Exception(e.getMessage());
		}
	}

	
	public static void closeConnection(Connection conn) throws Exception{
		try{
			if (conn!=null) conn.close();
		}
		catch (Exception e){
			   throw new Exception(e.getMessage());
		}
	}
}
