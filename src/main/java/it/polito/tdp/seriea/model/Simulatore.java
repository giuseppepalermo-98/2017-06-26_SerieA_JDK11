package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.seriea.db.SerieADAO;

public class Simulatore {

	//VALORI DI OUTPUT
	private Map<String, Squalifica> squalifiche;
	private Map<String, Punti> classifica;
	private Map<String, Squadra> idMapSquadre;
	
	//Varibili utili
	private SerieADAO dao;
	private List<Partita>partitaStorica;
	
	//EVENTO
	private PriorityQueue<Event> queue;
	
	
	//MEOTDO DI INIZIALIZZAZIONE DELLE VARIABILI
	public void init(Season scelta) {
		this.squalifiche = new HashMap<>();
		this.classifica = new HashMap<>();
		this.idMapSquadre = new HashMap<>();
		
		dao = new SerieADAO();
		
		this.partitaStorica = new ArrayList<>();
		
		queue = new PriorityQueue<>();
		
		for(String s: dao.getSquadreBySeason(scelta))
			this.idMapSquadre.put(s,  new Squadra(s, 11,0));
		
		for(String s: dao.getSquadreBySeason(scelta))
			this.classifica.put(s,  new Punti(s, 0));
		
		for(String s: dao.getSquadreBySeason(scelta))
			this.squalifiche.put(s,  new Squalifica(s, 0));
		
		for(Partita p: dao.getPartiteBySeason(scelta))
			this.partitaStorica.add(p);
		
		for(Partita p: partitaStorica)
			this.queue.add(new Event(p.getTime(), new Squadra(p.getHome(), 11, 0), new Squadra(p.getAway(), 11, 0), p.getFthg(), p.getFtag()));
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		Integer goalCasa=e.getFthg();
		Integer goalOspite= e.getFtag();
		Squadra casa=this.idMapSquadre.get(e.getHome().getSquadra());
		Squadra ospite= this.idMapSquadre.get(e.getAway().getSquadra());
		
		
		if(casa.getCountRientro()>0) {
			casa.decrementaCount();
			if(casa.getCountRientro() == 0) {
				casa.aggiungiGiocatore();
			}
		}
		
		if(ospite.getCountRientro()>0) {
			ospite.decrementaCount();
			if(ospite.getCountRientro() == 0) {
				ospite.aggiungiGiocatore();
			}
		}
		
		
		
		if(goalCasa>goalOspite) {
			//FATTORE CORREZIONE 
			if(casa.getGiocatori()<ospite.getGiocatori()) {
				Double probabilita = 1 - (double) casa.getGiocatori()/ospite.getGiocatori() ;
				
				if(Math.random()<probabilita)
					goalCasa--;
			}
		}
		
		if(goalCasa<goalOspite) {
			//FATTORE CORREZIONE
			if(casa.getGiocatori()>ospite.getGiocatori()) {
				Double probabilita = 1 - (double) ospite.getGiocatori()/casa.getGiocatori() ;
				
				if(Math.random()<probabilita)
					goalOspite--;
			}
		}
		
		//DOPO AVER CORRETTO I GOAL DEL RISULTATO STORICO CONTINUO CON LE SQUALIFICHE E ASSEGNAZIONE DEI PUNTI
		
		if(goalCasa == goalOspite) {
			this.classifica.get(casa.getSquadra()).aggiungiPunti(1);
			this.classifica.get(ospite.getSquadra()).aggiungiPunti(1);
		}
		
		if(goalCasa>goalOspite) {
			this.classifica.get(casa.getSquadra()).aggiungiPunti(3);
			 //PROBABILE SQUALIFICA CON 0.20 DATA DAL TESTO
			if(Math.random() < 0.20) {
				Integer giornate= (int) Math.random()*4+1;
				this.squalifiche.get(ospite.getSquadra()).aggiungiGiornate(giornate);
				ospite.togliGiocatore(giornate);
			}
		}
		
		if(goalCasa<goalOspite) {
			this.classifica.get(ospite.getSquadra()).aggiungiPunti(3);
			 //PROBABILE SQUALIFICA CON 0.20 DATA DAL TESTO
			if(Math.random() < 0.20) {
				Integer giornate= (int) Math.random()*4+1;
				this.squalifiche.get(casa.getSquadra()).aggiungiGiornate(giornate);
				casa.togliGiocatore(giornate);
			}
		}
			
	}
	
	
	public List<Squalifica> getSqualifica(){
		List<Squalifica> result = new ArrayList<>();
		
		for(Squalifica s: this.squalifiche.values())
			result.add(s);
		System.out.print(result);
		Collections.sort(result);
		return result;
	}
	
	public List<Punti> getPunti(){
		List<Punti> result = new ArrayList<>();
		
		for(Punti p: this.classifica.values())
			result.add(p);
		System.out.print(result);
		Collections.sort(result);
		
		return result;
	}
	
}
