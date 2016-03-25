package application;

import java.awt.List;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AddBookController implements Initializable {

	DataAccessFacade dataAccessFacade = SystemController.getDataAccessFacadeInstance();

	@FXML
	private TextField isbn;

	@FXML
	private TextField title;

	@FXML
	private TextField maxCheckoutLength;

	@FXML
	private TextField copyNum;

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
	private Button btnAddBook;

	@FXML
	private Button btnBackToMainFromAddBook;

	@FXML
	private ListView<String> listView;


	LinkedList<Author> bookAthor = new LinkedList<Author>();

	@FXML
	void addBook(ActionEvent event) throws IOException {

		LinkedList<BookCopy> booCopy = new LinkedList<BookCopy>();

		String visbn = isbn.getText();
		String vtitle = title.getText();
		int vmaxCheckoutLength = Integer.parseInt(maxCheckoutLength.getText());

		Book book = new Book(visbn, vtitle, vmaxCheckoutLength, bookAthor, booCopy);
		dataAccessFacade.addBook(book.getISBN(), book);
		
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
    	alert.setHeaderText("Success");
    	alert.setContentText(" Book added successful !");
    	alert.showAndWait();

		Stage stage = (Stage) btnBackToMainFromAddBook.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void goBack(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBackToMainFromAddBook.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		HashMap<String, Author> hashMap = dataAccessFacade.getAuthor();
		ObservableList<String> exactAuthorList = FXCollections.observableArrayList();

		Set set = hashMap.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			Author a = hashMap.get(iter.next());
			exactAuthorList
					.add(a.getAuthorId() +
							" ,    Name: " + a.getFirstName() + " "
							+ a.getLastName());
		}
		listView.setItems(exactAuthorList);
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		listView.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                ObservableList<String> selectedItems =  listView.getSelectionModel().getSelectedItems();
                for(String s : selectedItems){
                    String[] ch = s.split(" ");
                    String authorId = ch[0];
                    Author a = hashMap.get(authorId);
                    bookAthor.add(a);
                }
            }

        });


	}

}
