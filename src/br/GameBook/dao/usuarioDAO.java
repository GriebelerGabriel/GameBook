package br.GameBook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.GameBook.domain.usuario;
import br.GameBook.factory.ConexaoFactory;

public class usuarioDAO 
{
	public void salvar(usuario p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO usuario");
		sql.append("(usuarioNome, usuarioNascimento, usuarioEmail, usuarioSenha, usuarioAdmin) ");
		sql.append("VALUES (?, ?, ?, ?, ?) ");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		comandoSql.setString(1,  p.getUsuarioNome());
		comandoSql.setString(2, p.getUsuarioDataNascimento());
		comandoSql.setString(3,  p.getUsuarioEmail());
		comandoSql.setString(4,  p.getUsuarioSenha());
		comandoSql.setString(5,  p.getUsuarioAdmin());
		
	
		comandoSql.executeUpdate();
	}
	
	public ArrayList<usuario> listar() throws SQLException
	{	
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT `usuario`.`usuarioID`,`usuario`.`usuarioNome`,`usuario`.`usuarioNascimento`,`usuario`.`usuarioEmail`,`usuario`.`usuarioAdmin`");
		sql.append("FROM `gamebookbd`.`usuario`");
		
		Connection conexao = ConexaoFactory.getConexao();

		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comandoSql.executeQuery();
		
		ArrayList<usuario> itens = new ArrayList<usuario>();
		
		while(resultado.next())
		{
			
			usuario x = new usuario();
			x.setUsuarioID(resultado.getInt(1));
			x.setUsuarioNome(resultado.getString(2));
			x.setUsuarioDataNascimento(resultado.getString(3));
			x.setUsuarioEmail(resultado.getString(4));
			x.setUsuarioAdmin(resultado.getString(5));

			itens.add(x);
		}
		
		return itens;
	}
	
	public void excluir(usuario p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM usuario ");
		sql.append("WHERE usuarioID=?");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		comandoSql.setInt(1, p.getUsuarioID());
		
		comandoSql.executeUpdate();
	}
	
	public void editar(usuario p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE usuario ");
		sql.append("SET 'usuario.usuarioNome=?', 'usuario.usuarioDataNascimento=?', 'usuario.usuarioEmail', 'usuario.usuarioSenha' ");
		sql.append("WHERE jogoID=?");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		comandoSql.setString(1, p.getUsuarioNome());
		comandoSql.setString(2, p.getUsuarioDataNascimento());
		comandoSql.setString(3, p.getUsuarioEmail());
		comandoSql.setString(4, p.getUsuarioSenha());
		
		comandoSql.executeUpdate();
	}
}
