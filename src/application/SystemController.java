package application;

import java.util.Random;


public class SystemController {

	private static DataAccessFacade dataAccessFacade = null;

	/*
	 * private constructor for singleton class
	 */
	private SystemController() {
	}

	/*
	 * method for get Instance of Singleton Class
	 */

	public static DataAccessFacade getDataAccessFacadeInstance() {
		if (dataAccessFacade == null) {
			dataAccessFacade = new DataAccess();
		}
		return dataAccessFacade;
	}

	public static void setDataAccessFacade(DataAccessFacade f) {
		dataAccessFacade = f;
	}

	public static String getRandom() {
		String myRandomNumber;
		Random random = new Random();
		myRandomNumber = Long.toString(System.currentTimeMillis()) + Integer.toString(random.nextInt(10000));
		return myRandomNumber;
	}

}
