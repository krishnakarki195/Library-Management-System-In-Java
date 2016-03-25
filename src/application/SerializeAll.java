package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeAll {
	
	private DataAccessFacade tempDataAccessFacade;

	public  void serializeData(){
		
		try
	      {
	         FileOutputStream fileOut = new FileOutputStream(Resource.DATA);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         tempDataAccessFacade = SystemController.getDataAccessFacadeInstance();
	         out.writeObject(tempDataAccessFacade);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public static void deSerializeData(){

	      try
	      {
	         FileInputStream fileIn = new FileInputStream(Resource.DATA);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         SystemController.setDataAccessFacade((DataAccessFacade) in.readObject());
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Data class not found");
	         c.printStackTrace();
	         return;
	      }
		
	}

}
