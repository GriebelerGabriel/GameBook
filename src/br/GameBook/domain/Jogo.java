package br.GameBook.domain;


public class Jogo {
	
	private int jogoID;
	private String jogoNome;
	private String jogoDataLancamento;
	private String jogoGenero;
	private empresa empresa = new empresa();

	public int getJogoID() {
		return jogoID;
	}

	public void setJogoID(int jogoID) {
		this.jogoID = jogoID;
	}

	public String getJogoNome() {
		return jogoNome;
	}

	public void setJogoNome(String JogoNome) {
		this.jogoNome = JogoNome;
	}
	

	public String getJogoDataLancamento() {
		return jogoDataLancamento;
	}

	public void setJogoDataLancamento(String jogoDataLancamento) {
		this.jogoDataLancamento = jogoDataLancamento;
	}

	public String getJogoGenero() {
		return jogoGenero;
	}

	public void setJogoGenero(String jogoGenero) {
		this.jogoGenero = jogoGenero;
	}

	public empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(empresa empresa) {
		this.empresa = empresa;
	}
	
}
