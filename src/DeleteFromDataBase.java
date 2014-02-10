import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class DeleteFromDataBase {

	public DeleteFromDataBase(){
	}
	
	/*
	 * Erases a Database entry via the parameter
	 * @params, 1= nameToDelete - name of phone to delete
	 */
	public void eraseDatabaseEvent(String aName){
		
		try{
			String name = aName;
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cellPhones", "root", "1Patrick");
			Statement st0 = connection.createStatement();
			
			//Deletes the event into the calendar via the input they provided
			PreparedStatement stat1 = connection.prepareStatement("DELETE FROM Phones WHERE name = '"+ name +"'");
			stat1.execute();
			
			System.out.println("The event has been deleted from the DataBase");
			
			connection.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
}

