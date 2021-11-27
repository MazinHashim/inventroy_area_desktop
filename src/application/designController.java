package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class designController implements Initializable{

    @FXML
    private JFXTextField DaysText;
    @FXML
    private TextArea RefrigArea,LeadArea,SimulArea;
    private static designController instance;
    
    private double x=0,y=0;
    public designController () {
    	instance = this;
    }
    public static designController getInstance() {
    	return instance;
    }
    public void setRefProp(String text) {
    	RefrigArea.setText(RefrigArea.getText() + text);
    }
    public void setLeadProp(String text) {
    	LeadArea.setText(LeadArea.getText() + text);
    }
    public void setSimProp(String text) {
    	SimulArea.setText(SimulArea.getText() + text);
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		justANumberVaild();
		SimulationProcess.refDemandProb[0] *=100;
		for(int i=1; i<SimulationProcess.refDemandProb.length; i++) {
			SimulationProcess.refDemandProb[i] = SimulationProcess.refDemandProb[i] * 100 + SimulationProcess.refDemandProb[i-1];
		}
		SimulationProcess.leadTimeProb[0] *=10;
		for(int i=1; i<SimulationProcess.leadTimeProb.length; i++) {
			SimulationProcess.leadTimeProb[i] = SimulationProcess.leadTimeProb[i] * 10 + SimulationProcess.leadTimeProb[i-1];
		}
		designController.getInstance().setRefProp(SimulationProcess.display(SimulationProcess.refDemandProb,"Refrigrators",0));
		designController.getInstance().setLeadProp(SimulationProcess.display(SimulationProcess.leadTimeProb,"Lead time",1));
	}
	
	public void Simulation(ActionEvent event) {
		try {
			SimulationProcess.SimAlgorithm(Integer.parseInt(DaysText.getText()));
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Please Enter a digit number");
		}
    	DaysText.clear();
    }
    @FXML
    private void clearAllArea(ActionEvent event) {
    	SimulArea.clear();
    }
	@FXML
    private void closeWindow(ActionEvent event) {
    	Platform.exit();
    	System.exit(0);
    }
	
    
    @FXML
    private void dragged(MouseEvent event) {
    	Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setX(event.getScreenX()-x);
		stage.setY(event.getScreenY()-y);
    }
    @FXML
    private void pressed(MouseEvent event) {
    	x = event.getSceneX();
		y = event.getSceneY();
    }
    
	private void justANumberVaild() {
		NumberValidator validator = new NumberValidator();
    	validator.setMessage("just a number...");
    	validator.setStyle("-fx-text-fill:white;");
    	//validator.setIcon(new ImageView("error.png"));
    	DaysText.getValidators().add(validator);
    	DaysText.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					if(!DaysText.validate()) {
						DaysText.clear();
					}
				}
			}
		});
	}
}
