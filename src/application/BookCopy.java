package application;

import java.io.Serializable;

public class BookCopy implements Serializable {
	
	private String copyNum;
	boolean isAvailable;
	
	public BookCopy(boolean isAvailable) {
		
		this.copyNum = SystemController.getRandom();
		setAvailable(isAvailable);
		
	}

	public String getCopyNum() {
		return copyNum;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}
