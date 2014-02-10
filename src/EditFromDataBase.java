import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class EditFromDataBase {

	public EditFromDataBase(){
	}
	
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
			
			System.out.println("The item has been edited in the database");
			connection.close();
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
