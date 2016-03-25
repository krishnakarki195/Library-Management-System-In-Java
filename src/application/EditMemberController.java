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

public class EditMemberController {
	
	DataAccessFacade dataAccessFacade = SystemController.getDataAccessFacadeInstance();

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField street;

    @FXML
    private TextField city;

    @FXML
    private TextField state;

    @FXML
    private TextField zip;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button btnEditMember;

    @FXML
    private Button btnBackFromAddMemberToMain;

    @FXML
    private TextField memberId;

    @FXML
    void editMember(ActionEvent event) throws IOException {
    	
    	try {
			String vstreet = street.getText();
			String vcity = city.getText();
			String vstate = state.getText();
			String vzip = zip.getText();
			Address address = new Address(vstreet, vcity, vstate, vzip);

			int vmemberId = Integer.parseInt(memberId.getText());
			
			HashMap<Integer, LibraryMember> memberList = dataAccessFacade.getLibraryMember();
			
			if(memberList.containsKey(vmemberId)){
				
				
				LibraryMember lm = dataAccessFacade.getLibraryMemberById(vmemberId);
				CheckoutRecord cr = lm.getCheckoutRecord();
				memberList.remove(vmemberId);
				String vfirstName = firstName.getText();
				String vlastName = lastName.getText();
				String vphoneNumber = phoneNumber.getText();
				LibraryMember libraryMember = new LibraryMember(vfirstName, vlastName, vphoneNumber, address, vmemberId,
						cr);

				dataAccessFacade.saveNewMember(libraryMember.getMemberId(), libraryMember);
				
				Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Information Dialog");
		    	alert.setHeaderText("Success");
		    	alert.setContentText(" Member edit successful !");
		    	alert.showAndWait();
		    	
		    	Stage stage = (Stage) btnEditMember.getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			else{
				Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Information Dialog");
		    	alert.setHeaderText("Somthing Wrong !");
		    	alert.setContentText(" Member edit Unsuccessful ! Enter Corret Member ID");
		    	alert.showAndWait();
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		}

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
