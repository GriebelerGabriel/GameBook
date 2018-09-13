package br.GameBook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.GameBook.domain.post;
import br.GameBook.domain.usuario;
import br.GameBook.factory.ConexaoFactory;

public class postDAO 
{
	public void salvar(post p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO post");
		sql.append("(postLegenda, usuarioID) ");
		sql.append("VALUES (?, ?) ");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());

		comandoSql.setString(1,  p.getPostLegenda());
		comandoSql.setLong(2,  p.getUsuario().getUsuarioID());
		
		
		comandoSql.executeUpdate();
	}
	
	public ArrayList<post> listar() throws SQLException
	{	
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT `post`.`postID`,`post`.`postLegenda`,`post`.`usuarioID`");
		sql.append("FROM post, usuario where post.usuarioID=usuario.usuarioID");
	
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comandoSql.executeQuery();
		
		ArrayList<post> itens = new ArrayList<post>();
		
		while(resultado.next())
		{
			
			
			post p = new post();
			p.setPostID(resultado.getInt("post.postID"));
			p.setPostLegenda(resultado.getString("post.postLegenda"));
			usuario u = new usuario();
			u.setUsuarioID(resultado.getInt("post.usuarioID"));
			p.setUsuario(u);

			itens.add(p);
			
		}
		
		return itens;
	}
	
	public void excluir(post p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM post ");
		sql.append("WHERE postID=?");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		comandoSql.setLong(1, p.getPostID());
		
		comandoSql.executeUpdate();
	}
	
	public void editar(post p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE post ");
		sql.append("SET post.postLegenda=?, usuarioID=?");
		sql.append("WHERE postID=?");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		comandoSql.setString(1, p.getPostLegenda());
		comandoSql.setLong(2, p.getUsuario().getUsuarioID());
		
		comandoSql.executeUpdate();
	}
}
