package it.exp75.gestionefatture.model;

public class Misure {

	
	private Integer id;
	private String nome_misura;
	
	
	@Override
	public String toString() {
		return nome_misura;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return nome_misura;
	}
	public void setTipo(String tipo) {
		this.nome_misura = tipo;
	}
	
}
