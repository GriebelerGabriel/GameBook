package br.GameBook.domain;


public class usuario {
	
	private int usuarioID;
	private String usuarioNome;
	private String usuarioDataNascimento;
	private String usuarioEmail;
	private String usuarioSenha;
	private String usuarioAdmin;
	
	public int getUsuarioID() {
		return usuarioID;
	}
	public void setUsuarioID(int usuarioID) {
		this.usuarioID = usuarioID;
	}
	public String getUsuarioNome() {
		return usuarioNome;
	}
	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}
	
	public String getUsuarioDataNascimento() {
		return usuarioDataNascimento;
	}
	public void setUsuarioDataNascimento(String usuarioDataNascimento) {
		this.usuarioDataNascimento = usuarioDataNascimento;
	}
	public String getUsuarioEmail() {
		return usuarioEmail;
	}
	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
	public String getUsuarioSenha() {
		return usuarioSenha;
	}
	public void setUsuarioSenha(String usuarioSenha) {
		this.usuarioSenha = usuarioSenha;
	}
	public String getUsuarioAdmin() {
		return usuarioAdmin;
	}
	public void setUsuarioAdmin(String usuarioAdmin) {
		this.usuarioAdmin = usuarioAdmin;
	}
	

}
