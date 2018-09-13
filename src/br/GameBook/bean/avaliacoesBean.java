package br.GameBook.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import br.GameBook.dao.avaliacoesDAO;
import br.GameBook.domain.avaliacao;
import br.GameBook.util.JSFUtil;

@ManagedBean(name = "MBavaliacao")
@ViewScoped
public class avaliacoesBean 
{
	private ListDataModel<avaliacao> itens;
	private avaliacao p = new avaliacao();

	public avaliacao getAvaliacao() {
		return p;
	}

	public void setAvaliacao(avaliacao p) {
		this.p = p;
	}

	public ListDataModel<avaliacao> getItens() 
	{
		return itens;
	}

	public void setItens(ListDataModel<avaliacao> itens) 
	{
		this.itens = itens;
	}
	
	@PostConstruct
	public void prepararPesquisa()
	{
		try
		{
			avaliacoesDAO dao = new avaliacoesDAO();
			ArrayList<avaliacao> lista = dao.listar();
			itens = new ListDataModel<avaliacao>(lista);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void prepararNovo()
	{
		p = new avaliacao();
	}
	
	public void novo()
	{
		try
		{
			avaliacoesDAO dao = new avaliacoesDAO();
			dao.salvar(p);
			ArrayList<avaliacao> lista = dao.listar();
			itens = new ListDataModel<avaliacao>(lista);
			JSFUtil.adicionarMensagemSucesso("Avaliação salva com sucesso!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	public List<SelectItem> getAllEmpresas() throws SQLException
	{
		avaliacoesDAO dao = new avaliacoesDAO();
		List<avaliacao> avaliacaoList = dao.listar();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		
		for(avaliacao avaliacao: avaliacaoList)
		{
			itens.add(new SelectItem(avaliacao.getAvaliacaoID(), avaliacao.getAvaliacaoComentario()));		
		}
		return itens;
	}
	
	public void excluir()
	{
		try
		{
			avaliacoesDAO dao = new avaliacoesDAO();
			dao.excluir(p);
			ArrayList<avaliacao> lista = dao.listar();
			itens = new ListDataModel<avaliacao>(lista);
			JSFUtil.adicionarMensagemSucesso("Empresa removido com sucesso!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	public void editar(){
		try {
			avaliacoesDAO dao = new avaliacoesDAO();
			dao.editar(p);
			ArrayList<avaliacao>lista=dao.listar();
			itens = new ListDataModel<avaliacao>(lista);
			JSFUtil.adicionarMensagemSucesso("Empresa editado com sucesso");
			
			
		}catch (SQLException e){
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
			
			
		}
	}

}

