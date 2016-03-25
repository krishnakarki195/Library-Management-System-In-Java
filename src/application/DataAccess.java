package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DataAccess implements DataAccessFacade, Serializable {

	private HashMap<Integer,LibraryMember> libraryMembers = new HashMap<Integer,LibraryMember>();
	private HashMap<String,Book> books = new HashMap<String,Book>();
	private HashMap<String,Author> authors = new HashMap<String,Author>();
	private int memberId;

	@Override
	public void saveNewMember(int memberId, LibraryMember libraryMember){
		libraryMembers.put(memberId,libraryMember);
	}

	@Override
	public HashMap<Integer, LibraryMember> getLibraryMember(){
		return (HashMap<Integer, LibraryMember>)libraryMembers;
	}

	@Override
	public void addBook(String ISBN, Book book) {
		books.put(ISBN, book);

	}

	@Override
	public HashMap<String, Book> getBook() {
		return books;
	}

	@Override
	public HashMap<String, Author> getAuthor() {
		return (HashMap<String, Author>) authors;
	}

	@Override
	public void addAuthor(String str, Author author){
		authors.put(str, author);
	}

	@Override
	public BookCopy getAvailableBookCopy(String isbn) {
		
		HashMap<String,Book> books = getBook();

		Book book = books.get(isbn);
		
		BookCopy bc = null;
		
		if(book != null){
			List<BookCopy> bookCopyList = book.getListOfBookCopy();
			for(BookCopy b : bookCopyList){
				if(b.getIsAvailable()){		
					return b;
				}
			}
		}
		return bc;
	}

	@Override
	public LibraryMember getLibraryMemberById(int Id) {
		LibraryMember libraryMember = null;
		HashMap<Integer,LibraryMember> libraryMembers = getLibraryMember();
		libraryMember = libraryMembers.get(Id);
		return libraryMember;
	}

	@Override
	public void setBookCopyAsNotAvailable(String isbn, String copyNumber) {
		
		HashMap<String, Book> hasMap = getBook();		
		for(BookCopy bc: hasMap.get(isbn).getListOfBookCopy()){			
			if(bc.getCopyNum().equals(copyNumber)){
				bc.setAvailable(false);
				break;
			}
		}
	}

	@Override
	public int getMemberId() {
		return memberId;
	}

	@Override
	public void setMemberId(int id) {
		this.memberId = id;
		
	}
	
}
