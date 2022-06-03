/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Adiacenti;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGeni"
    private ComboBox<Genes> cmbGeni; // Value injected by FXMLLoader

    @FXML // fx:id="btnGeniAdiacenti"
    private Button btnGeniAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtIng"
    private TextField txtIng; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.cmbGeni.getItems().clear();
    	model.creaGrafo();
    	txtResult.setText("Grafo creato\n");
    	txtResult.appendText("#vertici "+model.vertici());
    	txtResult.appendText("#archi "+model.archi());
    	this.cmbGeni.getItems().addAll(model.getAllVertici());
    	this.btnGeniAdiacenti.setDisable(false);
    	this.cmbGeni.setDisable(false);
    	this.btnSimula.setDisable(false);
    }

    @FXML
    void doGeniAdiacenti(ActionEvent event) {
    	Genes g=this.cmbGeni.getValue();
    	if(g==null) {
    		txtResult.setText("Inserire gene!");
    		return;
    	}
    	List<Adiacenti> res=model.getAdiacenti(g);
    	
		txtResult.appendText("\n\nGeni adiacenti a "+g.getGeneId()+"\n");
    	for(Adiacenti a:res) {
    		txtResult.appendText(""+a.getG2()+" "+a.getPeso()+"\n");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	int n=0;
    	Genes g=this.cmbGeni.getValue();
    	if(g==null) {
    		txtResult.setText("Inserire gene!");
    		return;
    	}
    	try {
    		n=Integer.parseInt(this.txtIng.getText());
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    	}
    	List<Genes> gen=new ArrayList<>(model.simula(g, n));
    	for(Genes gi:gen)
    		this.txtResult.appendText("\n"+gi.getGeneId());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGeni != null : "fx:id=\"cmbGeni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGeniAdiacenti != null : "fx:id=\"btnGeniAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtIng != null : "fx:id=\"txtIng\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        btnSimula.setDisable(true);
        this.btnGeniAdiacenti.setDisable(true);
        this.cmbGeni.setDisable(true);
        
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
}
