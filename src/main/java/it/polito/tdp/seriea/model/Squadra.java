package it.polito.tdp.seriea.model;

public class Squadra {

	private String squadra;
	private Integer giocatori;
	private Integer countRientro;
	
	
	public Squadra(String squadra, Integer giocatori, Integer countRientro) {
		super();
		this.squadra = squadra;
		this.giocatori = giocatori;
		this.countRientro = countRientro;
	}


	public String getSquadra() {
		return squadra;
	}


	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}


	public Integer getGiocatori() {
		return giocatori;
	}


	public void setGiocatori(Integer giocatori) {
		this.giocatori = giocatori;
	}


	public Integer getCountRientro() {
		return countRientro;
	}


	public void setCountRientro(Integer countRientro) {
		this.countRientro = countRientro;
	}
	
	public void decrementaCount() {
		this.countRientro -= 1;
	}


	public void togliGiocatore(Integer giornate) {
		this.giocatori -=1;
		this.countRientro = giornate;
	}


	public void aggiungiGiocatore() {
		this.giocatori += 1;
	}
}
