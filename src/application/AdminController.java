
package application;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class AdminController {

	@FXML
    private Button btnOpenAddBookForm;

    @FXML
    private Button btnAddLibraryMember;

    @FXML
    private Button btnViewMember;

    @FXML
    private Button btnEditMember;

    @FXML
    private Button btnOpenAddAuthorForm;

    @FXML
    private Button btnOpenBookCopyForm;

    @FXML
    private Button btnOpenBookView;
    
    @FXML
    private Button btnLogout;

	@FXML
	void openAddMemberForm(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) btnAddLibraryMember.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource(Resource.ADDMEMBER));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void viewMember(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) btnViewMember.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource(Resource.MEMBERVIEW));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void editMember(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) btnEditMember.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource(Resource.MEMBEREDIT));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openAddBookForm(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) btnAddLibraryMember.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource(Resource.ADDBOOK));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openAddAuthorForm(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) btnOpenAddAuthorForm.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource(Resource.ADDAUTHOR));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
    void openAddBookCopyForm(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) btnOpenBookCopyForm.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource(Resource.ADDBOOKCOPY));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

	@FXML
    void openBookView(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) btnOpenBookCopyForm.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource(Resource.BOOKVIEW));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
	
	@FXML
    void logout(ActionEvent event) throws IOException {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Please Confirm....");
		alert.setContentText("Are you sure for Logout ?");

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK){
			
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

}