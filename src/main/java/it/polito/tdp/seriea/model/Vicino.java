package it.polito.tdp.seriea.model;

public class Vicino implements Comparable <Vicino> {

	private Season stagione;
	private Integer comuni;
	
	
	public Vicino(Season stagione, Integer comuni) {
		super();
		this.stagione = stagione;
		this.comuni = comuni;
	}


	public Season getStagione() {
		return stagione;
	}


	public void setStagione(Season stagione) {
		this.stagione = stagione;
	}


	public Integer getComuni() {
		return comuni;
	}


	public void setComuni(Integer comuni) {
		this.comuni = comuni;
	}


	@Override
	public int compareTo(Vicino o) {
		return this.stagione.getSeason()-o.getStagione().getSeason();
	}
	
	
	
}
