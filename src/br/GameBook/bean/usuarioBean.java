package br.GameBook.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import br.GameBook.dao.usuarioDAO;
import br.GameBook.domain.usuario;
import br.GameBook.util.JSFUtil;

@ManagedBean(name = "MBusuario")
@ViewScoped
public class usuarioBean 
{
	private ListDataModel<usuario> itens;
	private usuario p = new usuario();

	public usuario getUsuario() {
		return p;
	}

	public void setUsuario(usuario p) {
		this.p = p;
	}

	public ListDataModel<usuario> getItens() 
	{
		return itens;
	}

	public void setItens(ListDataModel<usuario> itens) 
	{
		this.itens = itens;
	}
	
	@PostConstruct
	public void prepararPesquisa()
	{
		try
		{
			usuarioDAO dao = new usuarioDAO();
			ArrayList<usuario> lista = dao.listar();
			itens = new ListDataModel<usuario>(lista);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void prepararNovo()
	{
		p = new usuario();
	}
	
	public void novo()
	{
		try
		{
			usuarioDAO dao = new usuarioDAO();
			dao.salvar(p);
			ArrayList<usuario> lista = dao.listar();
			itens = new ListDataModel<usuario>(lista);
			JSFUtil.adicionarMensagemSucesso("Usuário salvo com sucesso!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public List<SelectItem> getAllUsuarios() throws SQLException
	{
		usuarioDAO dao = new usuarioDAO();
		List<usuario> usuarioList = dao.listar();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		
		for(usuario usuario: usuarioList)
		{
			itens.add(new SelectItem(usuario.getUsuarioID(), usuario.getUsuarioNome()));		
		}
		return itens;
	}
	
	public void excluir()
	{
		try
		{
			usuarioDAO dao = new usuarioDAO();
			dao.excluir(p);
			ArrayList<usuario> lista = dao.listar();
			itens = new ListDataModel<usuario>(lista);
			JSFUtil.adicionarMensagemSucesso("Usuario removido com sucesso!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	public void editar(){
		try {
			usuarioDAO dao = new usuarioDAO();
			dao.editar(p);
			ArrayList<usuario>lista=dao.listar();
			itens = new ListDataModel<usuario>(lista);
			JSFUtil.adicionarMensagemSucesso("Usuario editado com sucesso");
			
			
		}catch (SQLException e){
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
			
			
		}
	}

}
