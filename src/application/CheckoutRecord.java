package application;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CheckoutRecord implements Serializable {
	
	
	private LinkedList<CheckoutRecordEntry>  checkoutRecordEntry = new LinkedList<CheckoutRecordEntry>();
	
	public CheckoutRecord(){

	}

	public LinkedList<CheckoutRecordEntry> getCheckoutRecordEntry() {
		return checkoutRecordEntry;
	}

	public String toString(){
		return "";
	}

}
