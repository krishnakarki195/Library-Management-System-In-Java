package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookCopyController implements Initializable {
	
	DataAccessFacade dataAccessFacade = SystemController.getDataAccessFacadeInstance();

    @FXML
    private Button btnAddBookCopy;

    @FXML
    private Button btnBackToMainFromAddBookCopy;

    @FXML
    private ListView<String> isbn;
    
    @FXML
    private TextField copyNumber;
    
    Book parentBook;

    @FXML
    void addBookCopy(ActionEvent event) throws IOException {
    	
    	int no = Integer.parseInt(copyNumber.getText());
    	for(int i = 1; i<= no; i++)
    		parentBook.addBookCopy( new BookCopy(true));
    	
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
    	alert.setHeaderText("Success");
    	alert.setContentText(no + " copy of Book added successful !");
    	alert.showAndWait();
    	
    	Stage stage = (Stage) btnBackToMainFromAddBookCopy.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }


    @FXML
    void goBack(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) btnBackToMainFromAddBookCopy.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		HashMap<String, Book> hashMap = dataAccessFacade.getBook();
		ObservableList<String> exactBookCopyList = FXCollections.observableArrayList();

		Set set = hashMap.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			Book b = hashMap.get(iter.next());
			exactBookCopyList.add(b.getISBN());
		}
		isbn.setItems(exactBookCopyList);
		
		isbn.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
            	
                ObservableList<String> selectedItem =  isbn.getSelectionModel().getSelectedItems();
                for(String s : selectedItem)
                {
                	parentBook = hashMap.get(s);
                }
               
            }
        });
		
	}

}
