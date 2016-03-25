package application;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LibrarianController {

	@FXML
	private Button btnViewCheckoutDetails;

	@FXML
	private Button btnCheckin;

	@FXML
	private Button btnCheckout;

	@FXML
	private Button btnLogout;

	@FXML
	void logout(ActionEvent event) throws IOException {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Please Confirm....");
		alert.setContentText("Are you sure for Logout ?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			SerializeAll ser = new SerializeAll();
			
			
			ser.serializeData();

			Stage stage;
			Parent root;
			stage = (Stage) btnLogout.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource(Resource.LOGIN));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} else {
			// Stay login
		}
	}

	@FXML
	void openCheckinForm(ActionEvent event) throws IOException {
		
		Stage stage = (Stage) btnCheckin.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.CHECKINBOOK));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}

	@FXML
	void openCheckoutForm(ActionEvent event) throws IOException {
		
		Stage stage = (Stage) btnCheckout.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.ADDCHECKOUTRECORD));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openViewCheckoutDetailsForm(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnViewCheckoutDetails.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.CHECKOUTRECORDVIEW));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
