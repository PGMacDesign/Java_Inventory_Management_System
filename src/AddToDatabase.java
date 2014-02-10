import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class AddToDatabase {

	public AddToDatabase(){
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
}
