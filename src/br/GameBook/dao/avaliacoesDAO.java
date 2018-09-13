package br.GameBook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.GameBook.domain.Jogo;
import br.GameBook.domain.avaliacao;
import br.GameBook.factory.ConexaoFactory;

public class avaliacoesDAO 
{
	public void salvar(avaliacao p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO avaliacao");
		sql.append("(avaliacaoComentario, avaliacao, jogoID) ");
		sql.append("VALUES (?, ?, ?) ");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		comandoSql.setString(1,  p.getAvaliacaoComentario());
		comandoSql.setInt(2, p.getAvaliacao());
		comandoSql.setLong(3,  p.getJogo().getJogoID());
		
		comandoSql.executeUpdate();
	}
	
	public ArrayList<avaliacao> listar() throws SQLException
	{	
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT `avaliacao`.`avaliacaoID`,`avaliacao`.`avaliacao`,`avaliacao`.`avaliacaoComentario`,`avaliacao`.`jogoID`");
		sql.append("FROM `gamebookbd`.`avaliacao`");
		
		
	
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comandoSql.executeQuery();
		
		ArrayList<avaliacao> itens = new ArrayList<avaliacao>();
		
		while(resultado.next())
		{
			
			Jogo j = new Jogo();
			j.setJogoID(resultado.getInt("avaliacao.JogoID"));
			
			avaliacao x = new avaliacao();
			x.setAvaliacaoID(resultado.getInt(1));
			x.setAvaliacao(resultado.getInt(2));
			x.setAvaliacaoComentario(resultado.getString(3));
			
			x.setJogo(j);


			itens.add(x);
		}
		
		return itens;
	}
	
	public void excluir(avaliacao p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM avaliacao ");
		sql.append("WHERE avaliacaoID=?");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		comandoSql.setInt(1, p.getAvaliacaoID());
		
		comandoSql.executeUpdate();
	}
	
	public void editar(avaliacao p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE avaliacao ");
		sql.append("SET avaliacao=?, avaliacaoComentario=?, jogoID=? ");
		sql.append("WHERE avaliacaoID=?");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		comandoSql.setString(2, p.getAvaliacaoComentario());
		comandoSql.setInt(1, p.getAvaliacao());
		comandoSql.setLong(3,  p.getJogo().getJogoID());
		comandoSql.setLong(4,  p.getAvaliacaoID());
		comandoSql.executeUpdate();
	}
}
