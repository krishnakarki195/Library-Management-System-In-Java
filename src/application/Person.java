package application;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Person implements Serializable{

	private String firstName;
	private String lastName;
	private String phone;
	private Address address;

	public Person(String firstName, String lastName, String phone, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;//new Address(address.getStreet(), address.getCity(), address.getState(), address.getZip());
	}

	public String getFirstName() {
		return firstName;
	}

	void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	void setAddress(Address address) {
		this.address = address;
	}

	public String toString() {
		return "\n FirstName: " + firstName + "\n" + " LastName: " + lastName + "\n" + " Phone: " + phone
				+ address.toString();
	}

}
