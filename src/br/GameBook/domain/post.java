package br.GameBook.domain;

public class post {
	private int postID;
	private String postLegenda;
	private usuario usuario = new usuario();
	
	
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public String getPostLegenda() {
		return postLegenda;
	}
	public void setPostLegenda(String postLegenda) {
		this.postLegenda = postLegenda;
	}
	public usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(usuario usuario) {
		this.usuario = usuario;
	}
	
}
