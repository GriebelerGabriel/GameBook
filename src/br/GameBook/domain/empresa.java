package br.GameBook.domain;

public class empresa {
	private int empresaID;
	private String empresaNome;
	private String empresaDataCriacao;
	private String empresaHistoria;
	
	public int getEmpresaID() {
		return empresaID;
	}
	public void setEmpresaID(int empresaID) {
		this.empresaID = empresaID;
	}
	public String getEmpresaNome() {
		return empresaNome;
	}
	public void setEmpresaNome(String empresaNome) {
		this.empresaNome = empresaNome;
	}
	
	public String getEmpresaDataCriacao() {
		return empresaDataCriacao;
	}
	public void setEmpresaDataCriacao(String empresaDataCriacao) {
		this.empresaDataCriacao = empresaDataCriacao;
	}
	
	public String getEmpresaHistoria() {
		return empresaHistoria;
	}
	public void setEmpresaHistoria(String empresaHistoria) {
		this.empresaHistoria = empresaHistoria;
	}

}
