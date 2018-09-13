package br.GameBook.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory 
{
	private static final String usuario = "root";
	private static final String senha = "";
	private static final String url = "jdbc:mysql://localhost:3306/gamebookbd";
	
	public static Connection getConexao() throws SQLException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		return conexao;
	}
	
}
