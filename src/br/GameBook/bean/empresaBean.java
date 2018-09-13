package br.GameBook.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import br.GameBook.dao.empresaDAO;
import br.GameBook.domain.empresa;
import br.GameBook.util.JSFUtil;

@ManagedBean(name = "MBempresa")
@ViewScoped
public class empresaBean 
{
	private ListDataModel<empresa> itens;
	private empresa p = new empresa();

	public empresa getEmpresa() {
		return p;
	}

	public void setEmpresa(empresa p) {
		this.p = p;
	}

	public ListDataModel<empresa> getItens() 
	{
		return itens;
	}

	public void setItens(ListDataModel<empresa> itens) 
	{
		this.itens = itens;
	}
	
	@PostConstruct
	public void prepararPesquisa()
	{
		try
		{
			empresaDAO dao = new empresaDAO();
			ArrayList<empresa> lista = dao.listar();
			itens = new ListDataModel<empresa>(lista);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void prepararNovo()
	{
		p = new empresa();
	}
	
	public void novo()
	{
		try
		{
			empresaDAO dao = new empresaDAO();
			dao.salvar(p);
			ArrayList<empresa> lista = dao.listar();
			itens = new ListDataModel<empresa>(lista);
			JSFUtil.adicionarMensagemSucesso("Empresa salva com sucesso!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	public List<SelectItem> getAllEmpresas() throws SQLException
	{
		empresaDAO dao = new empresaDAO();
		List<empresa> empresaList = dao.listar();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		
		for(empresa empresa: empresaList)
		{
			itens.add(new SelectItem(empresa.getEmpresaID(), empresa.getEmpresaNome()));		
		}
		return itens;
	}
	
	public void excluir()
	{
		try
		{
			empresaDAO dao = new empresaDAO();
			dao.excluir(p);
			ArrayList<empresa> lista = dao.listar();
			itens = new ListDataModel<empresa>(lista);
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
			empresaDAO dao = new empresaDAO();
			dao.editar(p);
			ArrayList<empresa> lista = dao.listar();
			itens = new ListDataModel<empresa>(lista);
			JSFUtil.adicionarMensagemSucesso("Empresa editado com sucesso");
			
			
		}catch (SQLException e){
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
			
			
		}
	}

}
