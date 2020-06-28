package it.polito.tdp.seriea.model;

public class Punti implements Comparable <Punti> {

	private String squadra;
	private Integer punti;
	
	
	public Punti(String squadra, Integer punti) {
		super();
		this.squadra = squadra;
		this.punti = punti;
	}


	public String getSquadra() {
		return squadra;
	}


	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}


	public Integer getPunti() {
		return punti;
	}


	public void setPunti(Integer punti) {
		this.punti = punti;
	}


	public void aggiungiPunti(int i) {
		this.punti += i;
	}


	@Override
	public int compareTo(Punti o) {
		return this.punti-o.getPunti();
	}
	
}
