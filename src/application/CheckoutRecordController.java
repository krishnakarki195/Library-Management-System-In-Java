package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

public class CheckoutRecordController {
	
	DataAccessFacade dataAccessFacade  = SystemController.getDataAccessFacadeInstance();
	
    @FXML
    private TextField memberId;

    @FXML
    private TextField bookISBN;

    @FXML
    private Button btnAddCheckoutEntry;

    @FXML
    private Button btnBackToMainFromAddCheckoutRecord;

    @FXML
    void addCheckoutRecord(ActionEvent event) {
    	
    	int id = Integer.parseInt(memberId.getText());
    	String isbn = bookISBN.getText();
 
    	boolean bookCopyFound = false;
    	boolean libraryMemberFound = false;
    	
    	Book book = null;
    	BookCopy bookCopy = null;
    	LibraryMember libraryMember = null;
    	
    	
    	bookCopy = dataAccessFacade.getAvailableBookCopy(isbn);
    	if(bookCopy != null){
    		bookCopyFound = true;
    	}
    	
    	libraryMember = dataAccessFacade.getLibraryMemberById(id);
    	if(libraryMember != null){
    		libraryMemberFound = true;
    	}
 
    	if(bookCopyFound && libraryMemberFound){
    		CheckoutRecord checkoutRecord = libraryMember.getCheckoutRecord();
        	if( checkoutRecord == null){
        		checkoutRecord = new CheckoutRecord();
        	}
        	
        	List<CheckoutRecordEntry> entries = checkoutRecord.getCheckoutRecordEntry();
        	CheckoutRecordEntry entry = new CheckoutRecordEntry(LocalDate.now(), LocalDate.now().plusDays(21), bookCopy);
        	entries.add(entry);
        	
        	
        	dataAccessFacade.setBookCopyAsNotAvailable(isbn, bookCopy.getCopyNum());
        	
        	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Information Dialog");
	    	alert.setHeaderText("Success");
	    	alert.setContentText(" Book Checkout successful !");
	    	alert.showAndWait();
        	
    	}

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) btnBackToMainFromAddCheckoutRecord.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.LIBRARIAN));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }

}
