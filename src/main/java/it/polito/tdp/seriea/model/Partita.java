package it.polito.tdp.seriea.model;

import java.time.LocalDate;

public class Partita {

	private LocalDate time;
	private String home;
	private String away;
	private Integer fthg;
	private Integer ftag;
	
	public Partita(LocalDate time, String home, String away, Integer fthg, Integer ftag) {
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

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getAway() {
		return away;
	}

	public void setAway(String away) {
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
	
	
}
