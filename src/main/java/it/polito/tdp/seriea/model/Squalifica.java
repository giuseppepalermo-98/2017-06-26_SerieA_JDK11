package it.polito.tdp.seriea.model;

public class Squalifica implements Comparable <Squalifica> {

	private String squadra;
	private Integer numGiornate;
	
	
	public Squalifica(String squadra, Integer numGiornate) {
		super();
		this.squadra = squadra;
		this.numGiornate = numGiornate;
	}


	public String getSquadra() {
		return squadra;
	}


	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}


	public Integer getNumGiornate() {
		return numGiornate;
	}


	public void setNumGiornate(Integer numGiornate) {
		this.numGiornate = numGiornate;
	}


	public void aggiungiGiornate(Integer giornate) {
		this.numGiornate +=giornate;
	}


	@Override
	public int compareTo(Squalifica o) {
		return this.numGiornate-o.getNumGiornate();
	}
	
	
}
