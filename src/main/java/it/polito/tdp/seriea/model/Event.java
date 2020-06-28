package it.polito.tdp.seriea.model;

import java.time.LocalDate;

public class Event implements Comparable <Event> {

	private LocalDate time;
	private Squadra home;
	private Squadra away;
	private Integer fthg;
	private Integer ftag;
	
	public Event(LocalDate time, Squadra home, Squadra away, Integer fthg, Integer ftag) {
		super();
		this.time = time;
		this.home = home;
		this.away = away;
		this.fthg = fthg;
		this.ftag = ftag;
	}

	


	public LocalDate getTime() {
		return time;
	}




	public void setTime(LocalDate time) {
		this.time = time;
	}




	public Squadra getHome() {
		return home;
	}




	public void setHome(Squadra home) {
		this.home = home;
	}




	public Squadra getAway() {
		return away;
	}




	public void setAway(Squadra away) {
		this.away = away;
	}




	public Integer getFthg() {
		return fthg;
	}




	public void setFthg(Integer fthg) {
		this.fthg = fthg;
	}




	public Integer getFtag() {
		return ftag;
	}




	public void setFtag(Integer ftag) {
		this.ftag = ftag;
	}




	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.getTime());
	}
	
	
}
