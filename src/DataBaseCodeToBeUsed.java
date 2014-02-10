import java.util.*;
import java.sql.*;

/*
 *This program performs operations on a database created under CreateDatabase.Java. Run it first to create the Cell Phone Database
 */
public class DataBaseCodeToBeUsed {

	public DataBaseCodeToBeUsed(){
	}
	
	/*
	 * Edits a Database entry via the parameters
	 * @params, 1= nameField - name to be entered in the name section of the database
	 * @params, 2= number - ID Number to be entered in the IDNumber section of the database
	 * @params, 3= stockQuantity - quantity amount to be entered in the quantity section of the database
	 * @params, 4= aPrice - price to be entered in the price section of the database
	 * @params, 5= aProduuctDescription - description of the phone to be entered in the aProduuctDescription section of the database
	 */
	public void addItem(String nameField, int number, long stockQuantity, double aPrice, String aProductDescription){
		
		try{
			String name = nameField;
			int IDNumber = number;
			long quantity = stockQuantity;
			double price = aPrice;
			String productDescription = aProductDescription;

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cellPhones", "root", "1Patrick");
			Statement st0 = connection.createStatement();

			
			//Adds the event into the calendar via the input they provided
			PreparedStatement stat1 = connection.prepareStatement("INSERT INTO Phones(" + "name," + "IDNumber," + "quantity," + "price," + "productDescription)" + "VALUES(?,?,?,?,?)"); 
			
			stat1.setString(1, name);
			stat1.setInt(2, IDNumber);
			stat1.setLong(3, quantity);
			stat1.setDouble(4, price);
			stat1.setString(5, productDescription);
			stat1.execute();
			
			System.out.println("The item has been added to the database");
			
			connection.close();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	//--------------------------------------------------------------------------------------------------------------------
	
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
	
	//--------------------------------------------------------------------------------------------------------------------
	
	/*
	 * Edits a Database entry via the parameters
	 * @params, 1= nameToDelete - name of phone to edit
	 * @params, 2= nameField - name to be entered in the name section of the database
	 * @params, 3= number - ID Number to be entered in the IDNumber section of the database
	 * @params, 4= stockQuantity - quantity amount to be entered in the quantity section of the database
	 * @params, 5= aPrice - price to be entered in the price section of the database
	 * @params, 6= aProduuctDescription - description of the phone to be entered in the aProduuctDescription section of the database
	 */
	public void editDataBaseEvent(String nameToDelete, String nameField, int number, long stockQuantity, double aPrice, String aProductDescription){

		try{

			String nameToDelete1 = nameToDelete;
			String name = nameField;
			int IDNumber = number;
			long quantity = stockQuantity;
			double price = aPrice;
			String productDescription = aProductDescription;
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cellPhones", "root", "1Patrick");
			Statement st0 = connection.createStatement();
			
			PreparedStatement stat1 = connection.prepareStatement("DELETE FROM Phones WHERE name = '"+ nameToDelete1 +"'");
			stat1.execute();
			
			PreparedStatement stat2 = connection.prepareStatement("INSERT INTO Phones(" + "name," + "IDNumber," + "quantity," + "price," + "productDescription)" + "VALUES(?,?,?,?,?)"); 
			
			stat2.setString(1, name);
			stat2.setInt(2, IDNumber);
			stat2.setLong(3, quantity);
			stat2.setDouble(4, price);
			stat2.setString(5, productDescription);
			stat2.execute();
			
			
			connection.close();
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//Main class - Not used
	public static void main(String[] args) {
		DataBaseCodeToBeUsed ed1 = new DataBaseCodeToBeUsed();
	
		ed1.addItem("Iphone 5s", 3310, 200, 200, "Apple Smartphone, 2013");
	}
}
