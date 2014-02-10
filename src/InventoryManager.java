import java.util.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("unused")
/*
 * This Program creates a GUI that manages an inventory of phones in a database. 
 * There are different Class files that manage the different aspects of the code. 
 * @CreateDatabase.java - Creates a new database with some default/ example values
 * @AddToDatabase.java - Adds new items into the existing database
 * @DataBaseCodeToBeUsed.java - It performs operations on a database and is the main "call" function for many methods
 * @DeleteFromDataBase.java - This deletes entries from the database
 * @EditFromDataBase.java - Edits items from within the database. Edit name/ price/ quantity/ etc
 * @InventoryManager.java - This controls the GUI that the user runs to make changes to the database
 */
class CellPhone extends Product implements Comparable
{
	private String productDescription;
	public CellPhone()
	{
		super(); //call the constructor in Product
		productDescription = ""; //add the additional attribute
	}

	public CellPhone(String name, int number, long stockQuantity, double price, String productDescription)
	{
		super(name, number, stockQuantity, price); //call the constructor in Product
		this.productDescription = productDescription; //add the additional attribute
	}

	public void setproductDescription(String productDescription)
	{
		this.productDescription = productDescription;
	}

	public String getproductDescription()
	{
		return productDescription;
	}

	public double calculateInventoryValue()
	{
		return getItemPrice() * getStockQuantity();
	}


	public int compareTo (Object o)
	{
		Product p = (Product)o;
		return getItemName().compareTo(p.getItemName());
	}

	public String toString()
	{
		return "\nName: "+getItemName() + "\nNumber: "+getItemNumber()+"\nPrice: $"+getItemPrice()+"\nQuantity: "+getStockQuantity() +"\nproductDescription: "+getproductDescription()+"\nValue: $"+calculateInventoryValue();
	}
}

class Product implements Comparable
{
	String name; // class variable that stores the item name
	double number; // class variable that stores the item number
	long stockQuantity; // class variable that stores the quantity in stock
	double price; // class variable that stores the item price
	public Product()
	{
		name = "";
		number = 0.0;
		stockQuantity = 0L;
		price = 0.0;
	}

	public Product(String name, int number, long stockQuantity, double price)
	{
		this.name = name;
		this.number = number;
		this.stockQuantity = stockQuantity;
		this.price = price;
	}
	
	public void setItemName(String name)
	{
		this.name = name;
	}

	public String getItemName()
	{
		return name;
	}

	public void setItemNumber(double number)
	{
		this.number = number;
	}

	public double getItemNumber()
	{
		return number;
	}

	public void setStockQuantity(long quantity)
	{
		stockQuantity = quantity;
	}

	public long getStockQuantity()
	{
		return stockQuantity;
	}

	public void setItemPrice(double price)
	{
		this.price = price;
	}

	public double getItemPrice()
	{
		return price;
	}

	public double calculateInventoryValue()
	{
		return getItemPrice() * getStockQuantity();
	}

	public int compareTo (Object o)
	{
		Product p = (Product)o;
		return name.compareTo(p.getItemName());
	}

	public String toString()
	{
		return "\nName: "+getItemName() + "\nNumber: "+number+"\nPrice: $"+price+"\nQuantity: "+stockQuantity + "\nValue: $"+calculateInventoryValue();
	}
}


public class InventoryManager extends JFrame implements ActionListener
{
	//utility class for displaying the picture
	private class MyPanel extends JPanel
	{
		ImageIcon image = new ImageIcon("x.jpg");
		int width = image.getIconWidth();
		int height = image.getIconHeight();
		long angle = 30;
		
		public MyPanel()
		{
			super();
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			g2d.rotate (Math.toRadians(angle), 60+width/2, 60+height/2);
			g2d.drawImage(image.getImage(), 100, 100, this);
			g2d.dispose();
		}
	}

	int currentIndex; //currently displayed Item
	Product[] supplies = new Product[4];
	JLabel name ;
	JLabel number;
	JLabel productDescription;
	JLabel quantity;
	JLabel price;
	JLabel totalValue;
	JLabel nameToEdit1;
	JTextField nameField = new JTextField(20);
	JTextField numberField = new JTextField(20);
	JTextField productDescriptionField = new JTextField(30);
	JTextField nameToEdit = new JTextField(20);
	JTextField quantityField = new JTextField(20);
	JTextField priceField = new JTextField(20);
	
	JPanel display;
	JPanel displayHolder;
	JPanel panel;

	public InventoryManager()
	{
		makeTheDataItems();
		setSize(800, 500);
		setTitle("Verizon Inventory: Branch Location");
		
		//make the panels
		display = new JPanel();
		JPanel other = new JPanel();
		JPanel picture = new MyPanel();
		JPanel buttons = new JPanel();
		JPanel centerPanel = new JPanel();
		displayHolder = new JPanel();
		display.setLayout(new GridLayout(3, 3));
		other.setLayout(new GridLayout(2, 1));
		
		//make the labels
		name = new JLabel("Item Name :");
		productDescription = new JLabel("Product Description :");
		number = new JLabel("Item ID Number :");
		quantity = new JLabel("Quantity in Stock :");
		price = new JLabel("Item Price :");
		totalValue = new JLabel("Total Value :");
		nameToEdit1 = new JLabel("Name to Edit : ");
		
		
		//make the buttons
		JButton first = makeButton("First");
		JButton next = makeButton("Next");
		JButton previous = makeButton("Previous");
		JButton end = makeButton("End");
		JButton exit = makeButton("Exit");

		
		//other buttons
		JButton add = makeButton("Add");
		JButton edit = makeButton("Edit");
		JButton delete = makeButton("Delete");
		
		//add the labels to the display panel
		display.add(name);
		display.add(number);
		display.add(price);
		display.add(quantity);
		display.add(productDescription);
		display.add(nameToEdit1);
		
		//add the buttons to the buttonPanel
		buttons.add(first);
		buttons.add(previous);
		buttons.add(next);
		buttons.add(end);
		buttons.add(exit);
		
		//add the picture panel and display to the centerPanel
		displayHolder.add(display);
		centerPanel.setLayout(new GridLayout(2, 1));
		centerPanel.add(picture);
		centerPanel.add(displayHolder);
		other.add(buttons);
		
		//add the other buttons to this panel
		JPanel forAdd = new JPanel(); 
		forAdd.add(add);
		forAdd.add(edit);
		forAdd.add(delete);
		other.add(forAdd);
		
		//add the panels to the frame
		getContentPane().add(centerPanel, "Center");
		getContentPane().add(other, "South");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setVisible(true);
		
	}

	private void makeTheDataItems ()
	{
		supplies[0] = new CellPhone("Iphone 5s", 3310, 200, 200, "Apple Smartphone, 2013");
		supplies[1] = new CellPhone("Droid Razr Maxx", 3220, 500, 250, "Motorola Smartphone, 2012");
		supplies[2] = new CellPhone("Basic", 5110, 35, 149, "LG Basic Phone, 2012");
		supplies[3] = new CellPhone("Galaxy S4", 9400, 90, 300, "Samsung, Smartphone, 2013");
	}

	//method for creating and dressing buttons
	private JButton makeButton(String label)
	{
		JButton button = new JButton(label);
		button.setActionCommand(label);
		button.addActionListener(this);
		return button;
	}

	//Adds an item
	private void addItem()
	{
		System.out.println("Add an Item");
		panel = new JPanel();
		JPanel add = new JPanel();
		add.setLayout(new GridLayout(2, 1));
		add.setLayout(new GridLayout(4, 4));
		
		JButton addIt = makeButton("Add Item");
		
		JLabel name = new JLabel("Name :");
		JLabel productDescription = new JLabel("Product Description :");
		JLabel quantity = new JLabel("Quantity :");
		JLabel price = new JLabel("Price :");
		
		add.add(name); add.add(nameField);
		add.add(productDescription); add.add(productDescriptionField);
		add.add(quantity); add.add(quantityField);
		add.add(price); add.add(priceField);
		
		panel.add(add);
		JPanel forAddIt = new JPanel();
		forAddIt.add(addIt);
		panel.add(forAddIt);
		displayHolder.remove(display);
		displayHolder.add(panel);

		this.setVisible(true);
	}
	
	//Edits an item
	private void editItem()
	{
		System.out.println("Edit an Item");
		panel = new JPanel();
		JPanel add = new JPanel();
		add.setLayout(new GridLayout(2, 1));
		add.setLayout(new GridLayout(5, 5));
		
		JButton editIt = makeButton("Edit Item");
		
		JLabel editingName = new JLabel("Phone Name to be Edited: ");
		JLabel name = new JLabel("Name :");
		JLabel productDescription = new JLabel("Product Description :");
		JLabel quantity = new JLabel("Quantity :");
		JLabel price = new JLabel("Price :");
		
		add.add(editingName); add.add(nameToEdit);
		add.add(name); add.add(nameField);
		add.add(productDescription); add.add(productDescriptionField);
		add.add(quantity); add.add(quantityField);
		add.add(price); add.add(priceField);
		
		panel.add(add);
		JPanel forAddIt = new JPanel();
		forAddIt.add(editIt);
		panel.add(forAddIt);
		displayHolder.remove(display);
		displayHolder.add(panel);

		this.setVisible(true);	
	}
	
	//Deletes an item 
	private void deleteItem()
	{
		System.out.println("Delete an Item");
		panel = new JPanel();
		JPanel delete = new JPanel();
		delete.setLayout(new GridLayout(2, 1));
		delete.setLayout(new GridLayout(4, 4));
		
		JButton addIt = makeButton("Delete Item");
		
		JLabel name = new JLabel("Name of Phone Item to be Deleted :");
		
		delete.add(name); delete.add(nameField);
		
		panel.add(delete);
		JPanel forAddIt = new JPanel();
		forAddIt.add(addIt);
		panel.add(forAddIt);
		displayHolder.remove(display);
		displayHolder.add(panel);

		//Creates an object that will allow deletion from the database
		DeleteFromDataBase del1 = new DeleteFromDataBase();
		del1.eraseDatabaseEvent(nameField.getText());
		
		this.setVisible(true);
	}
	
	//Main Method
	public static void main( String args[])
	{
		InventoryManager object = new InventoryManager();
	} 

	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand(); //retrieves command set for the button
		
		if(command.equals("First"))
		{
			displayFirst();
		}
		else if(command.equals("Next"))
		{
			displayNext();
		}
		else if(command.equals("Previous"))
		{
			displayPrevious();
		}
		else if(command.equals("End"))
		{
			displayLast();
		}
		else if(command.equals("Exit"))
		{
			this.dispose();
			System.exit(0);
		}
		else if(command.equals("Add"))
		{
			addItem();
		}
		else if(command.equals("Add Item"))
		{
			addItemToArray();
		}
		else if(command.equals("Delete")) 
		{
			deleteItem();
		}
		else if(command.equals("Delete Item")) 
		{
			deleteItemFromArray();
		}
		else if(command.equals("Edit"))
		{
			editItem();
		}
		else if(command.equals("Edit Item"))
		{
			editItemFromArray();
		}		
	}

	private void addItemToArray()
	{
		Product p = new CellPhone(nameField.getText(), (1000 + (int)(Math.random() * ((9999 - 1000) + 1))), Long.parseLong(quantityField.getText()), Double.parseDouble(priceField.getText()), productDescriptionField.getText());
		
		//extend size of array by one first
		Product[] productArray = new Product[supplies.length + 1];
		for(int i = 0; i < productArray.length-1; i++)
		{
			productArray[i] = supplies[i];
		}
		productArray[supplies.length] = p;
		supplies = productArray;
		displayHolder.remove(panel);
		displayHolder.add(display);
		displayLast();
		this.setVisible(false);
		this.setVisible(true);
		
		//Creates an object that will allow addition to the database
		AddToDatabase add1 = new AddToDatabase();
		add1.addItem(nameField.getText(), (1000 + (int)(Math.random() * ((9999 - 1000) + 1))), Long.parseLong(quantityField.getText()), Double.parseDouble(priceField.getText()), productDescriptionField.getText());	
				
	}
	
	private void deleteItemFromArray(){
		
		displayHolder.remove(panel);
		displayHolder.add(display);
		displayLast();
		this.setVisible(false);
		this.setVisible(true);
		
		//Creates an object that will allow deletion from the database
		DeleteFromDataBase del1 = new DeleteFromDataBase();
		del1.eraseDatabaseEvent(nameField.getText());
	}

	
	private void editItemFromArray(){
			
		displayHolder.remove(panel);
		displayHolder.add(display);
		displayLast();
		this.setVisible(false);
		this.setVisible(true);
		
		//Creates an object that will allow editing from the database
		EditFromDataBase edit1 = new EditFromDataBase();
		edit1.editDataBaseEvent(nameToEdit.getText(), nameField.getText(), (1000 + (int)(Math.random() * ((9999 - 1000) + 1))), Long.parseLong(quantityField.getText()), Double.parseDouble(priceField.getText()), productDescriptionField.getText());
				
	}

	//utility method
	private void displayItemAt(int index)
	{
		CellPhone product = (CellPhone)supplies[index];
		name.setText("Item Name: "+ product.getItemName());
		
		totalValue.setText("Total: " + product.calculateInventoryValue());
		
		number.setText("Item ID Number: "+ product.getItemNumber());
		
		quantity.setText("Quantity In Stock: "+ product.getStockQuantity());
		
		price.setText("Item Price: "+ product.getItemPrice());
		
		productDescription.setText("Product Description: "+ product.getproductDescription());
		
		this.setVisible(true);
	}

	public void displayFirst()
	{
		displayItemAt(0);
		currentIndex = 0;
	}

	public void displayNext()
	{
		if(currentIndex == supplies.length-1)
		{
			displayFirst();
			currentIndex = 0;
		}
		else
		{
			displayItemAt(currentIndex + 1);
			currentIndex++;
		}
	}

	public void displayPrevious()
	{
		if(currentIndex == 0)
		{
			displayLast();
			currentIndex = supplies.length-1;
		}
		else
		{
			displayItemAt(currentIndex - 1);
			currentIndex--;
		}
	}

	public void displayLast()
	{
		displayItemAt(supplies.length-1);
		currentIndex = supplies.length-1;
	}
}

