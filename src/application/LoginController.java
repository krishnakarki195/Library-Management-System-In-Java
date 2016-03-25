package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
    private Button btnLogn;

    @FXML
    private PasswordField password;

    @FXML
    private Label loginStatus;

    @FXML
    private TextField userId;

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
    	
    	String userName =  userId.getText();
    	String userPassword = password.getText();
    	
    	if(userName =="" || userId == null){
    		
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning Dialog");
    		alert.setHeaderText("Please Enter User Name !");
    		alert.showAndWait();
    	}
    	
    	if(userPassword == "" || password == null ){
    		
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning Dialog");
    		alert.setHeaderText("Please Enter User Password !");
    		alert.showAndWait();
    	}
    	
    	if(userName.equals("librarian") && userPassword.equals("123")){
    		
    		SerializeAll se = new SerializeAll();
    		se.deSerializeData();
    		
    		Stage stage;
        	Parent root;
        	stage = (Stage) btnLogn.getScene().getWindow();
    		root = FXMLLoader.load(getClass().getResource(Resource.LIBRARIAN));
        	Scene scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    	}
    	
    	else if(userName.equals("admin") && userPassword.equals("123")){
    		
    		SerializeAll se = new SerializeAll();
    		se.deSerializeData();
    		
    		Stage stage;
        	Parent root;
        	stage = (Stage) btnLogn.getScene().getWindow();
    		root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
        	Scene scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    	}
    	
    	else{
    		
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning Dialog");
    		alert.setHeaderText("Please Enter Correct User Name or Password !");
    		alert.showAndWait();
    	}

    }

}
