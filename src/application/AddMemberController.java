package application;

import java.io.IOException;
import java.util.HashMap;

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

public class AddMemberController {

	DataAccessFacade dataAccessFacade  = SystemController.getDataAccessFacadeInstance();

	@FXML
	private TextField memberId;

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private TextField phoneNumber;

	@FXML
	private TextField street;
	
	@FXML
	private TextField city;
	
	@FXML
	private TextField zip;

	@FXML
	private TextField state;

	@FXML
	private Button btnBackFromAddMemberToMain;
	
	@FXML
	private Button btnAddMember;

	@FXML
	void addMember(ActionEvent event) throws IOException {
		try {
			String vstreet = street.getText();
			String vcity = city.getText();
			String vstate = state.getText();
			String vzip = zip.getText();
			Address address = new Address(vstreet, vcity, vstate, vzip);

			int vmemberId = Integer.parseInt(memberId.getText());
			String vfirstName = firstName.getText();
			String vlastName = lastName.getText();
			String vphoneNumber = phoneNumber.getText();
			LibraryMember libraryMember = new LibraryMember(vfirstName, vlastName, vphoneNumber, address, vmemberId,
					new CheckoutRecord());

			dataAccessFacade.saveNewMember(libraryMember.getMemberId(), libraryMember);
			
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Information Dialog");
	    	alert.setHeaderText("Success");
	    	alert.setContentText(" Member added successful !");
	    	alert.showAndWait();

		} catch (Exception e) {
			e.printStackTrace();

		}
		Stage stage = (Stage) btnAddMember.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void goBackToMain(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBackFromAddMemberToMain.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
