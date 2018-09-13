package br.GameBook.domain;

public class avaliacao {
	
	private int avaliacaoID;
	private String avaliacaoComentario;
	private int avaliacao;
	private Jogo jogo = new Jogo();
	
	public int getAvaliacaoID() {
		return avaliacaoID;
	}
	public void setAvaliacaoID(int avaliacaoID) {
		this.avaliacaoID = avaliacaoID;
	}
	public String getAvaliacaoComentario() {
		return avaliacaoComentario;
	}
	public void setAvaliacaoComentario(String avaliacaoComentario) {
		this.avaliacaoComentario = avaliacaoComentario;
	}
	public int getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	
}