import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateDatabase{

	public static void main(String[]args) {

		Connection con = null;
		String url = "localhost";
		String driverName = "mariadb-java-client-1.1.3.jar";
		
		try{
			
			Connection  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cellPhones", "GUEST_USER_NAME", "PASSWORD_GOES_HERE");
			Statement st = connection.createStatement();
			
			//Create Columns
			st.execute("CREATE TABLE Phones(name VARCHAR(100), IDNumber VARCHAR(4), quantity VARCHAR (10), price VARCHAR (10), productDescription VARCHAR(100))");

			//Create Rows
			st.execute("INSERT INTO Phones VALUES('Iphone 5s', '3310', '200', '200', 'Apple Smartphone, 2013')");
			st.execute("INSERT INTO Phones VALUES('Droid Razr Maxx', '3220', '500', '250', 'Motorola Smartphone, 2012')");
			st.execute("INSERT INTO Phones VALUES('Basic', '5110', '35', '149', 'LG Basic Phone, 2012')");
			st.execute("INSERT INTO Phones VALUES('Galaxy S4', '9400', '90', '300', 'Samsung Smartphone, 2013')");
			
			System.out.println("Phone table success");
		} catch (Exception e){
			e.printStackTrace();
		}		
	}
}