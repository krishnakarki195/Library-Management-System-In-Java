package application;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class CheckoutRecordEntry implements Serializable {

	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy bookCopy;

	public CheckoutRecordEntry(LocalDate checkoutDate, LocalDate localDate, BookCopy bookCopy) {
		
		this.checkoutDate = checkoutDate;
		this.dueDate = localDate;
		this.bookCopy = bookCopy;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

}
