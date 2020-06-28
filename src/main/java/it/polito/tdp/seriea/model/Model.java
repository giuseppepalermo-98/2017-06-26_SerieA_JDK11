package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {

	private SerieADAO dao;
	private Map<Integer,Season> idMapSeason;
	private List<Season> lStagioni;
	private List<Adiacenza> adiacenti;
	
	private Graph<Season, DefaultWeightedEdge> grafo;
	
	private Simulatore sim;
	
	public Model() {
		dao = new SerieADAO();
		idMapSeason = new HashMap<>();
		lStagioni = dao.listSeasons(idMapSeason);
	}
	
	public void creaGrafo() {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, lStagioni);
		
		adiacenti = dao.getAdiacenti(idMapSeason);
		
		for(Adiacenza a: adiacenti) {
			if(grafo.containsVertex(a.getStagione1()) && grafo.containsVertex(a.getStagione2()) && grafo.getEdge(a.getStagione1(),a.getStagione2()) == null)
				Graphs.addEdgeWithVertices(grafo, a.getStagione1(), a.getStagione2(), a.getPeso());
		}
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
		
	}
	
	public List<Season> getSeason(){
		Collections.sort(lStagioni);
		return lStagioni;
	}
	
	public List<Vicino> getSquadreComuni(Season selezionata){
		List<Vicino> result = new ArrayList<>();
		List<Season> vicini = Graphs.neighborListOf(this.grafo, selezionata);
		
		for(Season s: vicini) {
			result.add(new Vicino(s, (int) grafo.getEdgeWeight(grafo.getEdge(selezionata, s))));
		}
		Collections.sort(result);
		return result;
	}
	
	public void eseguiSimulazione(Season scelta) {
		sim = new Simulatore();
		sim.init(scelta);
		sim.run();
	}
	
	public List<Squalifica> getSqualifiche(){
		return sim.getSqualifica();
	}
	
	public List<Punti> getPunti(){
		return sim.getPunti();
	}
}
