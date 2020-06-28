package it.polito.tdp.seriea;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.Punti;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Squalifica;
import it.polito.tdp.seriea.model.Vicino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

//controller turno B --> switchare al branch master_turnoA o master_turnoC per turno A o C

public class FXMLController {
	
	private Model model;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Season> boxSquadra;

    @FXML
    private Button btnCalcolaConnessioniSquadra;

    @FXML
    private Button btnSimulaEspulsi;

    @FXML
    private Button btnAnalizzaStagioni;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalizzaStagioni(ActionEvent event) {
    	this.txtResult.clear();
    	
    	this.model.creaGrafo();
    	this.txtResult.appendText("Grafo creato");
    	
    	this.boxSquadra.getItems().addAll(this.model.getSeason());
    }

    @FXML
    void doCalcolaConnessioniStagione(ActionEvent event) {
    	this.txtResult.clear();
    	
    	Season selezionata = this.boxSquadra.getValue();
    	
    	if(selezionata == null) {
    		this.txtResult.appendText("ERRORE, SELEZIONA UNA STAGIONE!");
    		return;
    	}
    	List<Vicino>result = this.model.getSquadreComuni(selezionata);
    	
    	this.txtResult.appendText("Le stagioni con squadre comuni a quella selezionata sono: \n");
    	
    	for(Vicino v: result) {
    		this.txtResult.appendText(v.getStagione().getDescription()+" ("+v.getComuni()+")\n");
    	}
    }

    @FXML
    void doSimulaEspulsi(ActionEvent event) {
    	Season scelta = this.boxSquadra.getValue();
    	
    	model.eseguiSimulazione(scelta);
    	List<Squalifica>squalifica = model.getSqualifiche();
    	List<Punti>punti= model.getPunti();
    	
    	for(Squalifica s: squalifica) 
    		this.txtResult.appendText(s.getSquadra()+" numero squalifiche "+s.getNumGiornate()+"\n");
    		
    	this.txtResult.appendText("\n \n \n \n CLASSIFICA \n\n");
    	
    	for(Punti p: punti)
    		this.txtResult.appendText(p.getSquadra()+" punti: "+p.getPunti()+"\n");
    	}
    

    @FXML
    void initialize() {
        assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnCalcolaConnessioniSquadra != null : "fx:id=\"btnCalcolaConnessioniSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSimulaEspulsi != null : "fx:id=\"btnSimulaEspulsi\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnAnalizzaStagioni != null : "fx:id=\"btnAnalizzaStagioni\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}