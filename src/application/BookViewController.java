package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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

public class BookViewController implements Initializable {

	DataAccessFacade dataAccessFacade = SystemController.getDataAccessFacadeInstance();

	@FXML
	private TableColumn<Book, Integer> tblMaxChecKLength;

	@FXML
	private TableView<Book> tblBookList;

	@FXML
	private TableColumn<Book, String> tblISBN;

	@FXML
	private Button btnBackToMainFromBookView;

	@FXML
	private TableColumn<Book, String> tblTitle;

	@FXML
	void goBack(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBackToMainFromBookView.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		HashMap<String, Book> bookHashMap = dataAccessFacade.getBook();

		ObservableList<Book> bookData = FXCollections.observableArrayList();

		Set set = bookHashMap.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			bookData.add(bookHashMap.get(iter.next()));
		}

		tblISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
		tblTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		tblMaxChecKLength.setCellValueFactory(new PropertyValueFactory<Book, Integer>("maxCheckoutLength"));
		
		tblBookList.setItems(bookData);
	}

}
