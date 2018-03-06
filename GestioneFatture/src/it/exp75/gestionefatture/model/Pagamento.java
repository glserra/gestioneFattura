package it.exp75.gestionefatture.model;

public class Pagamento {
	
	private Integer id;
	private String tipo;
	
	
	@Override
	public String toString() {
		return tipo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
