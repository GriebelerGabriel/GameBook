package br.GameBook.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;

import br.GameBook.dao.postDAO;
import br.GameBook.domain.post;
import br.GameBook.util.JSFUtil;

@ManagedBean(name = "MBpost")
@ViewScoped
public class postBean 
{
	private ListDataModel<post> itens;
	private post p = new post();

	public post getPost() {
		return p;
	}

	public void setPost(post p) {
		this.p = p;
	}

	public ListDataModel<post> getItens() 
	{
		return itens;
	}

	public void setItens(ListDataModel<post> itens) 
	{
		this.itens = itens;
	}
	
	@PostConstruct
	public void prepararPesquisa()
	{
		try
		{
			postDAO dao = new postDAO();
			ArrayList<post> lista = dao.listar();
			itens = new ListDataModel<post>(lista);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void prepararNovo()
	{
		p = new post();
	}
	
	public void novo()
	{
		try
		{
			postDAO dao = new postDAO();
			dao.salvar(p);
			ArrayList<post> lista = dao.listar();
			itens = new ListDataModel<post>(lista);
			JSFUtil.adicionarMensagemSucesso("Post salvo com sucesso!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void excluir()
	{
		try
		{
			postDAO dao = new postDAO();
			dao.excluir(p);
			ArrayList<post> lista = dao.listar();
			itens = new ListDataModel<post>(lista);
			JSFUtil.adicionarMensagemSucesso("Post removido com sucesso!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	public void editar(){
		try {
			postDAO dao = new postDAO();
			dao.editar(p);
			ArrayList<post>lista=dao.listar();
			itens = new ListDataModel<post>(lista);
			JSFUtil.adicionarMensagemSucesso("Post editado com sucesso");
			
			
		}catch (SQLException e){
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
			
			
		}
	}

}
