package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Book implements Serializable{

	private String ISBN;
	private String title;
	private int maxCheckoutLength;
	private LinkedList<BookCopy> listOfBookCopy;
	private LinkedList<Author> listOfAuthors;

	public Book(String iSBN, String title, int maxCheckoutLength, LinkedList<Author> listOfAuthors,
			LinkedList<BookCopy> bookCopy) {
		ISBN = iSBN;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.listOfBookCopy = bookCopy;
		this.listOfAuthors = listOfAuthors;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getTitle() {
		return title;
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	public LinkedList<BookCopy> getListOfBookCopy() {
		return listOfBookCopy;
	}

	public List<Author> getListOfAuthors() {
		return listOfAuthors;
	}
	
	public void addBookCopy( BookCopy bc){
		listOfBookCopy.add(bc);
	}

}
