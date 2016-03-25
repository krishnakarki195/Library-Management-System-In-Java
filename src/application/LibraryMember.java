package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class LibraryMember extends Person implements Serializable {
	private int memberId;
	private CheckoutRecord checkoutRecord;

	public LibraryMember(String firstName, String lastName, String phone, Address address, int memberId,
			CheckoutRecord checkoutRecord) {
		super(firstName, lastName, phone, address);
		this.memberId = memberId;
		this.checkoutRecord = checkoutRecord;
	}

	public int getMemberId() {
		return memberId;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}
	
	void setCheckoutRecord(CheckoutRecord c){
		checkoutRecord = c;
	}

	public String toString(){
		return "\n Member ID: " + memberId + "\n"
				+ super.toString() + "\n"
				+ checkoutRecord.toString();

	}

}
