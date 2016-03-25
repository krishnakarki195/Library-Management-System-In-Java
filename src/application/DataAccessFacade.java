package application;

import java.util.HashMap;
import java.util.List;

import javafx.scene.control.TextField;

public interface DataAccessFacade {
	
	public void saveNewMember(int memberId, LibraryMember libraryMember);

	public HashMap<Integer, LibraryMember> getLibraryMember();
	
	public LibraryMember getLibraryMemberById(int id);

	public void addBook(String ISBN, Book book);

	public HashMap<String, Book> getBook();
	
	public void addAuthor(String str, Author author);
	
	public HashMap<String, Author> getAuthor();
	
	public BookCopy getAvailableBookCopy(String isbn);
	
	public void setBookCopyAsNotAvailable(String isbn, String copyNumber);
	
	public int getMemberId();
	
	public void setMemberId(int id);

}
