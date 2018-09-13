package br.GameBook.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import br.GameBook.dao.GameDAO;
import br.GameBook.domain.Jogo;
import br.GameBook.util.JSFUtil;

@ManagedBean(name = "MBGame")
@ViewScoped
public class GameBean 
{
	private ListDataModel<Jogo> itens;
	private Jogo Game = new Jogo();

	public Jogo getJogo() {
		return Game;
	}

	public void setJogo(Jogo Game) {
		this.Game = Game;
	}

	public ListDataModel<Jogo> getItens() 
	{
		return itens;
	}

	public void setItens(ListDataModel<Jogo> itens) 
	{
		this.itens = itens;
	}
	
	@PostConstruct
	public void prepararPesquisa()
	{
		try
		{
			GameDAO dao = new GameDAO();
			ArrayList<Jogo> lista = dao.listar();
			itens = new ListDataModel<Jogo>(lista);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void prepararNovo()
	{
		Game = new Jogo();
	}
	
	public void novo()
	{
		try
		{
			GameDAO dao = new GameDAO();
			dao.salvar(Game);
			ArrayList<Jogo> lista = dao.listar();
			itens = new ListDataModel<Jogo>(lista);
			JSFUtil.adicionarMensagemSucesso("Jogo salvo com sucesso!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	public List<SelectItem> getAllJogos() throws SQLException
	{
		GameDAO dao = new GameDAO();
		List<Jogo> gameList = dao.listar();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		
		for(Jogo Game: gameList)
		{
			itens.add(new SelectItem(Game.getJogoID(), Game.getJogoNome()));		
		}
		return itens;
	}
	
	public void excluir()
	{
		try
		{
			GameDAO dao = new GameDAO();
			dao.excluir(Game);
			ArrayList<Jogo> lista = dao.listar();
			itens = new ListDataModel<Jogo>(lista);
			JSFUtil.adicionarMensagemSucesso("Jogo removido com sucesso!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	public void editar(){
		try {
			GameDAO dao = new GameDAO();
			dao.editar(Game);
			ArrayList<Jogo>lista=dao.listar();
			itens = new ListDataModel<Jogo>(lista);
			JSFUtil.adicionarMensagemSucesso("Jogo editado com sucesso!");
			
			
		}catch (SQLException e){
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
			
			
		}
	}

}

