package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import java.util.ResourceBundle;
import java.util.Set;

import javafx.beans.property.SimpleIntegerProperty;
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

public class MemberViewController implements Initializable {

	DataAccessFacade dataAccessFacade  = SystemController.getDataAccessFacadeInstance();

	@FXML
    private TableView<LibraryMember> tblMemberList;

    @FXML
    private TableColumn<LibraryMember, Integer> ID;

    @FXML
    private TableColumn<LibraryMember, String> firstName;

    @FXML
    private TableColumn<LibraryMember, String> lastName;

    @FXML
    private TableColumn<LibraryMember, String> phone;

    @FXML
    private TableColumn<LibraryMember, String> street;

    @FXML
    private TableColumn<LibraryMember, String> city;

    @FXML
    private TableColumn<LibraryMember, String> zip;

    @FXML
    private TableColumn<LibraryMember, String> state;

    @FXML
    private Button btnBackToMainFromView;


    @FXML
    void goBack(ActionEvent event) throws IOException {

    	Stage stage = (Stage) btnBackToMainFromView.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Resource.ADMIN));
		Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	HashMap<Integer,LibraryMember> hashMap = new HashMap<Integer,LibraryMember>();
    	hashMap = dataAccessFacade.getLibraryMember();

    	ObservableList<LibraryMember> personData = FXCollections.observableArrayList();

    	Set set = hashMap.keySet();
        Iterator iter = set.iterator();
    	while(iter.hasNext()){
    		personData.add(hashMap.get(iter.next()));
    	}

    	ID.setCellValueFactory(new PropertyValueFactory<LibraryMember,Integer>("memberId"));

    	firstName.setCellValueFactory( new PropertyValueFactory<LibraryMember,String>("firstName"));
    	lastName.setCellValueFactory( new PropertyValueFactory<LibraryMember,String>("lastName"));
    	phone.setCellValueFactory( new PropertyValueFactory<LibraryMember,String>("phone"));


    	 state.setCellValueFactory(new Callback<CellDataFeatures<LibraryMember, String>, ObservableValue<String>>() {
    	     public ObservableValue<String> call(CellDataFeatures<LibraryMember, String> p) {
    	         String rState = p.getValue().getAddress().getState();
    	         return new SimpleStringProperty(rState);
    	     }
    	  });

    	 street.setCellValueFactory(new Callback<CellDataFeatures<LibraryMember, String>, ObservableValue<String>>() {
    	     public ObservableValue<String> call(CellDataFeatures<LibraryMember, String> p) {
    	         String rStreet = p.getValue().getAddress().getStreet();
    	         return new SimpleStringProperty(rStreet);
    	     }
    	  });

    	 city.setCellValueFactory(new Callback<CellDataFeatures<LibraryMember, String>, ObservableValue<String>>() {
    	     public ObservableValue<String> call(CellDataFeatures<LibraryMember, String> p) {
    	         String rCity = p.getValue().getAddress().getCity();
    	         return new SimpleStringProperty(rCity);
    	     }
    	  });

    	 zip.setCellValueFactory(new Callback<CellDataFeatures<LibraryMember, String>, ObservableValue<String>>() {
    	     public ObservableValue<String> call(CellDataFeatures<LibraryMember, String> p) {
    	         String rZip = p.getValue().getAddress().getZip();
    	         return new SimpleStringProperty(rZip);
    	     }
    	  });

    	tblMemberList.setItems(personData);
	}

}
