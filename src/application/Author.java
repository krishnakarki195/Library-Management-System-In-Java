package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Author extends Person implements Serializable{

	private String credentials;
	private String authorId;
	
	//private ArrayList<Book> listOfBooks = new ArrayList<Book>();

	public Author(String firstName, String lastName, String phone, Address address, String credentials) {
		super(firstName, lastName, phone, address);
		this.credentials = credentials;
		this.authorId = SystemController.getRandom();
	}

	public String getCredentials() {
		return credentials;
	}

	void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getAuthorId() {
		return authorId;
	}
	
}

