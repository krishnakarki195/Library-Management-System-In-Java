package application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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

public class BookCheckinController {
	
	DataAccessFacade dataAccessFacade  = SystemController.getDataAccessFacadeInstance();

    @FXML
    private TextField memberId;

    @FXML
    private Button btnAddCheckin;

    @FXML
    private Button btnGoBack;

    @FXML
    private TextField bookCopyNumber;

    @FXML
    void addCheckinRecord(ActionEvent event) {
    	
    	boolean process1 = false;
    	boolean process2 = false;
    	
    	int mId = Integer.parseInt(memberId.getText()); 	
    	String bookCopyId = bookCopyNumber.getText();
    	
    	LibraryMember lm = dataAccessFacade.getLibraryMemberById(mId);
    	CheckoutRecord cr = lm.getCheckoutRecord();
    	LinkedList<CheckoutRecordEntry> cre = cr.getCheckoutRecordEntry();
    	for(CheckoutRecordEntry e : cre){
    		if(e.getBookCopy().getCopyNum().equals(bookCopyId)){
    			cre.remove(e);
    			process1 = true;
    			break;
    		}
    	}
    	
    	HashMap<String,Book> books = dataAccessFacade.getBook();    	
    	LinkedList<BookCopy> bookCopyList = new LinkedList<BookCopy>();    	
    	Set set = books.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			Book b = books.get(iter.next());
			bookCopyList = b.getListOfBookCopy();
			for(BookCopy bc : bookCopyList){
				if(bc.getCopyNum().equals(bookCopyId)){
					bc.setAvailable(true);
					process2 = true;
					break;
				}
			}
		}
		
		if(process1 && process2){
			
			Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText("Success");
        	alert.setContentText(" BookChecin successful !");
        	alert.showAndWait();
		}
		else{
			
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText("Something wrong");
        	alert.setContentText(" Unsuccessful !");
        	alert.showAndWait();
			
		}

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) btnGoBack.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.LIBRARIAN));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}
