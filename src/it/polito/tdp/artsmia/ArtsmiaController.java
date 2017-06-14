package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Exhibition;
import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {

    	if(boxAnno.getValue()==null){
    		txtResult.setText("Errore seleziona una casella");
    		return;
    	}
    	txtResult.clear();
    	Exhibition e = model.creaGrafo(boxAnno.getValue());
    	txtResult.appendText("l'opera"+e.toString()+" ha il numero maggiore di opere : "+e.getOpere().size()+"\n");
    	boolean b = model.isConnected();
    	if(b==true){
    		txtResult.appendText("il grafo è connesso \n");
    	}
    	else{
    		txtResult.appendText("il grafo non  è connesso \n");
    	}


    }

    @FXML
    void handleSimula(ActionEvent event) {

    	if(boxAnno.getValue()==null){
    		txtResult.setText("Errore seleziona una casella");
    		return;
    	}
    	String num = txtFieldStudenti.getText();

		if (num.length() == 0) {
			txtResult.appendText("ERRORE: devi inserire un numero compreso fra 1 e 3\n");
			return;
		}

		int numero;
		try {
			numero = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			txtResult.appendText("ERRORE: il numero deve essere in formato numerico\n");
			return;
		}
    	model.creaGrafo(boxAnno.getValue());
    	List<Studente> studenti = model.creaStudente(numero,boxAnno.getValue());
    	for(Studente s : studenti){
    		txtResult.appendText(s.toString()+s.getOpere().size());
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxAnno.getItems().addAll(model.getDate());
	}


}
