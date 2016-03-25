package application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddAuthorController {
	
	DataAccessFacade dataAccessFacade  = SystemController.getDataAccessFacadeInstance();
	
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField credential;

    @FXML
    private TextField phone;

    @FXML
    private TextField street;

    @FXML
    private TextField city;

    @FXML
    private TextField state;

    @FXML
    private TextField zip;

    @FXML
    private Button btnAddAuthor;

    @FXML
    private Button btnBackToMainFromAddAuthor;

    @FXML
    void addAuthor(ActionEvent event) {
    	
    	try{
    		String vfirstName = firstName.getText();
    		String vlastName = lastName.getText();
    		String vphone = phone.getText();
    		String vcredential = credential.getText();
    		
    		String vstreet = street.getText();
    		String vcity = city.getText();
    		String vstate = state.getText();
    		String vzip = zip.getText();
    		
    		Address address = new Address(vstreet, vcity, vstate, vzip);		
    		Author author = new Author(vfirstName,vlastName,vphone, address, vcredential);
    		dataAccessFacade.addAuthor(author.getAuthorId(), author);
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText("Success");
        	alert.setContentText(" Author added successful !");
        	alert.showAndWait();
    		
    		
    		Stage stage = (Stage) btnBackToMainFromAddAuthor.getScene().getWindow();
    		Parent root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
    		Scene scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage = (Stage) btnBackToMainFromAddAuthor.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}
