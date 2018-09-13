package br.GameBook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.GameBook.domain.empresa;
import br.GameBook.factory.ConexaoFactory;

public class empresaDAO 
{
	public void salvar(empresa p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO empresa");
		sql.append("(empresaNome, empresaHistoria, empresaDataCriacao) ");
		sql.append("VALUES (?, ?, ?) ");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		comandoSql.setString(1,  p.getEmpresaNome());
		comandoSql.setString(2,  p.getEmpresaHistoria());
		comandoSql.setString(3, p.getEmpresaDataCriacao());
		
		comandoSql.executeUpdate();
	}
	
	public ArrayList<empresa> listar() throws SQLException
	{	
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT `empresa`.`empresaID`,`empresa`.`empresaNome`,`empresa`.`empresaHistoria`,`empresa`.`empresaDataCriacao` ");
		sql.append("FROM `gamebookbd`.`empresa` ");
		
		
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comandoSql.executeQuery();
		
		ArrayList<empresa> itens = new ArrayList<empresa>();
		
		while(resultado.next())
		{
			
			empresa x = new empresa();
			x.setEmpresaID(resultado.getInt("empresa.empresaID"));
			x.setEmpresaNome(resultado.getString("empresa.empresaNome"));
			x.setEmpresaHistoria(resultado.getString("empresa.empresaHistoria"));
			x.setEmpresaDataCriacao(resultado.getString("empresa.empresaDataCriacao"));
			

			itens.add(x);
		}
		
		return itens;
	}
	
	public void excluir(empresa p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM empresa ");
		sql.append("WHERE empresaID=?");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		comandoSql.setInt(1, p.getEmpresaID());
		
		comandoSql.executeUpdate();
	}
	
	public void editar(empresa p) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE empresa ");
		sql.append("SET empresaNome=?, empresaDataCriacao=?, empresaHistoria=? ");
		sql.append("WHERE empresaID=?");
		
		Connection conexao = ConexaoFactory.getConexao();
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql.toString());
		
		comandoSql.setString(1, p.getEmpresaNome());
		comandoSql.setString(2, p.getEmpresaDataCriacao());
		comandoSql.setString(3, p.getEmpresaHistoria());
		comandoSql.setInt(4, p.getEmpresaID());

		comandoSql.executeUpdate();
	}
}
