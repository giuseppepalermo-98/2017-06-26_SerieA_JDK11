package it.polito.tdp.seriea.model;

public class Adiacenza {

	private Season stagione1;
	private Season stagione2;
	private Integer peso;
	
	
	public Adiacenza(Season stagione1, Season stagione2, Integer peso) {
		super();
		this.stagione1 = stagione1;
		this.stagione2 = stagione2;
		this.peso = peso;
	}


	public Season getStagione1() {
		return stagione1;
	}


	public void setStagione1(Season stagione1) {
		this.stagione1 = stagione1;
	}


	public Season getStagione2() {
		return stagione2;
	}


	public void setStagione2(Season stagione2) {
		this.stagione2 = stagione2;
	}


	public Integer getPeso() {
		return peso;
	}


	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
}
