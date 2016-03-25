package application;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CheckoutRecordHistory implements Initializable {
	
	DataAccessFacade dataAccessFacade  = SystemController.getDataAccessFacadeInstance();

    @FXML
    private Button btngoBack;

    @FXML
    private TableView<CheckoutRecordEntry> tbleViewHistory;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> copyNumber;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> issueDate;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> dueDate;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) btngoBack.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.CHECKOUTRECORDVIEW));
		Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		int memberId = dataAccessFacade.getMemberId();
		LibraryMember lm = dataAccessFacade.getLibraryMemberById(memberId);
		
		if(lm == null){
			System.out.println("No member with the given Member ID found.");
			return;
		}
		
		CheckoutRecord cr = lm.getCheckoutRecord();
		LinkedList<CheckoutRecordEntry> cre = cr.getCheckoutRecordEntry();
		ObservableList<CheckoutRecordEntry> datas = FXCollections.observableArrayList();
		for(CheckoutRecordEntry c: cre){
			datas.add(c);
		}
		
		
//		copyNumber.setCellValueFactory(new Callback<CellDataFeatures<CheckoutRecordEntry, String>, ObservableValue<String>>() {
//   	     public ObservableValue<String> call(CellDataFeatures<CheckoutRecordEntry, String> p) {
//   	         String bookCopy = p.getValue();
//   	         return new SimpleStringProperty(rState);
//   	     }
//   	  });
		
		
		copyNumber.setCellValueFactory(new Callback<CellDataFeatures<CheckoutRecordEntry, String>, ObservableValue<String>>() {
	     public ObservableValue<String> call(CellDataFeatures<CheckoutRecordEntry, String> p) {
	         String bookCopyId = p.getValue().getBookCopy().getCopyNum();
	         return new SimpleStringProperty(bookCopyId);
	     }
	  });
		
		
		issueDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("checkoutDate"));
		dueDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("dueDate"));
		
		tbleViewHistory.setItems(datas);
		
	}

}


/*import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CheckoutRecordHistory implements Initializable {
	
	DataAccessFacade dataAccessFacade  = SystemController.getDataAccessFacadeInstance();

	@FXML
    private TableView<CheckoutRecordEntry> tbleViewHistory;

    @FXML
    private TableColumn<BookCopy, String> copyNumber;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> issueDate;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> dueDate;

    @FXML
    private Button btngoBack;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) btngoBack.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.CHECKOUTRECORDVIEW));
		Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		int memberId = dataAccessFacade.getMemberId();
		LibraryMember lm = dataAccessFacade.getLibraryMemberById(memberId);
		CheckoutRecord cr = lm.getCheckoutRecord();
		LinkedList<CheckoutRecordEntry> cre = cr.getCheckoutRecordEntry();
		ObservableList<CheckoutRecordEntry> datas = FXCollections.observableArrayList();
		for(CheckoutRecordEntry c: cre){
			datas.add(c);
		}		
		
		copyNumber.setCellValueFactory(new Callback<CellDataFeatures<BookCopy, String>, ObservableValue<String>>() {
   	     public ObservableValue<String> call(CellDataFeatures<BookCopy, String> p) {
   	         String rState = p.getValue().getCopyNum();
   	         return new SimpleStringProperty(rState);
   	     }
   	  });
		
		
		issueDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("checkoutDate"));
		dueDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("dueDate"));
		
		tbleViewHistory.setItems(datas);
		
		
	}

}
*/