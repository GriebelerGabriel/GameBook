package br.GameBook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.GameBook.domain.Jogo;
import br.GameBook.domain.empresa;
import br.GameBook.factory.ConexaoFactory;

public class GameDAO 
{
	public void salvar(Jogo jogo) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO jogo");
		sql.append("(jogoNome, jogoDataLancamento, jogoGenero, empresaID) ");
		sql.append("VALUES (?, ?, ?, ?) ");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		comandoSql.setString(1,  jogo.getJogoNome());
		comandoSql.setString(2, jogo.getJogoDataLancamento());
		comandoSql.setString(3,  jogo.getJogoGenero());
		comandoSql.setLong(4,  jogo.getEmpresa().getEmpresaID());
		
		comandoSql.executeUpdate();
	}
	
	public ArrayList<Jogo> listar() throws SQLException
	{	
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT `jogo`.`jogoID`,`jogo`.`jogoNome`,`jogo`.`jogoDataLancamento`,`jogo`.`jogoGenero`,`jogo`.`empresaID`");
		sql.append("FROM jogo");
		
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comandoSql.executeQuery();
		
		ArrayList<Jogo> itens = new ArrayList<Jogo>();
		
		while(resultado.next())
		{
			empresa e = new empresa();
			e.setEmpresaID(resultado.getInt("jogo.empresaID"));
			
			Jogo jogo = new Jogo();
			jogo.setJogoID(resultado.getInt("jogo.jogoID"));
			jogo.setJogoNome(resultado.getString("jogo.jogoNome"));
			jogo.setJogoDataLancamento(resultado.getString("jogo.jogoDataLancamento"));
			jogo.setJogoGenero(resultado.getString("jogo.jogoGenero"));
			
			jogo.setEmpresa(e);

			itens.add(jogo);
		}
		
		return itens;
	}
	
	public void excluir(Jogo jogo) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM jogo ");
		sql.append("WHERE jogoID=?");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		comandoSql.setLong(1, jogo.getJogoID());
		
		comandoSql.executeUpdate();
	}
	
	public void editar(Jogo jogo) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE jogo ");
		sql.append("SET jogoNome=?, jogoDataLancamento=?, jogoGenero=? ");
		sql.append("WHERE jogoID=?");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		comandoSql.setString(1, jogo.getJogoNome());
		comandoSql.setString(2, jogo.getJogoDataLancamento());
		comandoSql.setString(3, jogo.getJogoGenero());
		
		comandoSql.executeUpdate();
	}
	
	public List<SelectItem> getAllJogos() throws SQLException
	{
		GameDAO dao = new GameDAO();
		List<Jogo> jogoList = dao.listar();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		
		for(Jogo jogo: jogoList)
		{
			itens.add(new SelectItem(jogo.getJogoID(), jogo.getJogoNome()));		
		}
		return itens;
	}
}
