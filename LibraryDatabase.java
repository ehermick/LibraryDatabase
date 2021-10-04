import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import java.util.Scanner;

public class LibraryDatabase {
	
	static int authNum = 0;
	static int isbn = 0;
	static int libID = 0;
	static int memberID = 0;
	static int empID = 0;
	
	static String userType = "";
	
    final static String url = "jdbc:mysql:///C_Test_1";
    final static String user = "root";
    final static String password = "Diff3r3nt!";
	
	public static void readerLogin() throws Exception {
		Scanner scan = new Scanner(System.in);
		int a = 0;
		int v = 0;
		int d = 0;
		
		System.out.println(" ");
		System.out.println("MAIN MENU");
		System.out.println("------------------------------");
		System.out.println("(1) View");
		System.out.println("(2) Logout");
		System.out.println("(3) Exit");
		System.out.println("------------------------------");
		System.out.print(">  ");
		
		int i = scan.nextInt();
		
		//View
		if (i == 1) {
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("VIEW MENU");
			System.out.println("Select the category to view: ");
			System.out.println("------------------------------");
			System.out.println("(1) Authors");
			System.out.println("(2) Books");
			System.out.println("(3) Libraries");
			System.out.println("(4) My Books");
			System.out.println("------------------------------");
			System.out.print(">  ");
			v = scan.nextInt();
			
			if (v == 1) {
				view("AUTHORS");
			}
			else if (v == 2) {
				view("BOOKS");
			}
			else if (v == 3) {
				view("LIBRARIES");
			}
			else if (v == 4) {
				view("MYBOOKS");
			}
		}
		else if (i == 2) {
			login();
		}
		
		
		//Exit
		else if (i == 3) {
			System.out.println("Exiting....");
			System.exit(0);
		}
		
		scan.close();
	}
	
	public static void staffLogin() throws Exception {
		Scanner scan = new Scanner(System.in);
		int a = 0;
		int v = 0;
		int d = 0;
		
		System.out.println(" ");
		System.out.println("MAIN MENU");
		System.out.println("------------------------------");
		System.out.println("(1) View");
		System.out.println("(2) Add");
		System.out.println("(3) Delete");
		System.out.println("(4) Logout");
		System.out.println("(5) Exit");
		System.out.println("------------------------------");
		System.out.print(">  ");
		
		int i = scan.nextInt();
		
		//View
		if (i == 1) {
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("VIEW MENU");
			System.out.println("Select the category to view: ");
			System.out.println("------------------------------");
			System.out.println("(1) Authors");
			System.out.println("(2) Books");
			System.out.println("(3) Libraries");
			System.out.println("(4) Readers");
			System.out.println("(5) Staff");
			System.out.println("------------------------------");
			System.out.print(">  ");
			v = scan.nextInt();
			
			if (v == 1) {
				view("AUTHORS");
			}
			else if (v == 2) {
				view("BOOKS");
			}
			else if (v == 3) {
				view("LIBRARIES");
			}
			else if (v == 4) {
				view("READERS");
			}
			else if (v == 5) {
				view("STAFF");
			}
		}
		//Add
		else if (i == 2) {
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("ADD MENU");
			System.out.println("Select the category to add to: ");
			System.out.println("------------------------------");
			System.out.println("(1) Authors");
			System.out.println("(2) Books");
			System.out.println("(3) Libraries");
			System.out.println("(4) Readers");
			System.out.println("(5) Staff");
			System.out.println("(6) Return to Main Menu");
			System.out.println("(7) Exit");
			System.out.println("------------------------------");
			System.out.print(">  ");
			a = scan.nextInt();
			
			if (a == 1) {
				addAuthor();
			}
			else if (a == 2) {
				addBook();
			}
			else if (a == 3) { 
				addLibrary();
			}
			else if (a == 4) {
				addReader();
			}
			else if (a == 5) {
				addStaff();
			}
			else if (a == 6) {
				staffLogin();
			}
			else {
				System.out.println("Exiting....");
				System.exit(0);
			}
		}
		//Delete
		else if (i == 3) {
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("DELETE MENU");
			System.out.println("Select the category to delete from: ");
			System.out.println("------------------------------");
			System.out.println("(1) Authors");
			System.out.println("(2) Books");
			System.out.println("(3) Libraries");
			System.out.println("(4) Readers");
			System.out.println("(5) Staff");
			System.out.println("(6) Return to Main Menu");
			System.out.println("(7) Exit");
			System.out.println("------------------------------");
			System.out.print(">  ");
			d = scan.nextInt();
			
			if (d == 1) {
				delete("AUTHORS");
			}
			else if (d == 2) {
				delete("BOOKS");
			}
			else if (d == 3) {
				delete("LIBRARIES");
			}
			else if (d == 4) {
				delete("READERS");
			}
			else if (d == 5) {
				delete("STAFF");
			}
			else if (d == 6) {
				staffLogin();
			}
			else {
				System.out.println("Exiting....");
				System.exit(0);
			}
		}
		else if (i == 4) {
			login();
		}
		//Exit
		else {
			System.out.println("Exiting....");
			System.exit(0);
		}
		
		scan.close();
	}
	
	public static void view(String viewCategory) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("VIEW " + viewCategory);
		System.out.println("------------------------------");
	    // establish the connection
	    Connection con = DriverManager.getConnection(url, user, password);

	    // create JDBC statement object
	    Statement st = con.createStatement();
	    
	    if (viewCategory.equals("AUTHORS")) {
		    String qA = "SELECT authID, Name FROM Author";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Author ID \t      Name");
		    while (rsA.next()) {
		    	System.out.println("    " + rsA.getInt(1) + "\t\t" + rsA.getString(2));
		     }
	    }
	    
	    else if (viewCategory.equals("BOOKS")) {
		    String qA = "SELECT ISBN, Title, numPages FROM Book";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("ISBN \t Title \t\t Number of Pages");
		    while (rsA.next()) {
		    	System.out.println(" " + rsA.getInt(1) + "\t" + rsA.getString(2) + "\t  " + rsA.getInt(3));
		     }
	    }
	    
	    else if (viewCategory.equals("LIBRARIES")) {
		    String qA = "SELECT libID, Location, Name FROM Library";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Library ID \t Location");
		    while (rsA.next()) {
		    	System.out.println("     " + rsA.getInt(1) + "\t\t" + rsA.getString(2));
		     }
	    }

	    else if (viewCategory.equals("READERS")) {
		    String qA = "SELECT memberID, Sign_Up_Date, Name, Email, Pin, libID FROM Reader";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Member ID \t Sign Up Date \t Name \t\t Email \t\t Pin \t Library");
		    while (rsA.next()) {
		    	System.out.println("     " + rsA.getInt(1) + "\t\t  " + rsA.getDate(2) + "\t " + rsA.getString(3)+ "\t    " + rsA.getString(4)+ "\t" + rsA.getInt(5) + "\t  " + rsA.getInt(6));
		     }
	    }
	    
	    else if (viewCategory.equals("STAFF")) {
		    String qA = "SELECT empID, Name, Title, Password, libID FROM Staff";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Employee ID \t   Name \t Title \t\t Password \t Library");
		    while (rsA.next()) {
		    	System.out.println("     " + rsA.getInt(1) + "\t\t" + rsA.getString(2) + "\t" + rsA.getString(3)+ "\t  " + rsA.getString(4)+ "\t  " + rsA.getInt(5));
		     }
	    }
	    
	    if (userType.equals("Reader") && viewCategory.equals("AUTHORS")) {
			System.out.println("------------------------------");
			
			System.out.println("(1) View Author's Books");
			System.out.println("(2) Return to Main Menu");
			System.out.println("(3) Exit");
			System.out.println(">   ");
			int input = scan.nextInt();
			if (input == 1) {
				System.out.println();
				System.out.println();
				System.out.print("Enter Desired Author ID: > ");
				int chosenAuthID = scan.nextInt();
				
				System.out.println("ISBN \t\t Title");
				
			    Connection con2 = DriverManager.getConnection(url, user, password);

			    // create JDBC statement object
			    Statement st2 = con2.createStatement();
			    String qABooks = "SELECT ISBN, authID FROM Writes";
			    ResultSet rsABooks = st2.executeQuery(qABooks);
			    
			    while (rsABooks.next()) {
			    	if (rsABooks.getInt(2) == chosenAuthID) {
				    	int tempISBN = rsABooks.getInt(1);
				    	
				    	Connection con3 = DriverManager.getConnection(url, user, password);
					    Statement st3 = con3.createStatement();
					    String qABooks2 = "SELECT ISBN, Title, numPages FROM Book";
					    ResultSet rsABooks2 = st3.executeQuery(qABooks2);
					    
					    while (rsABooks2.next() ) {
					    	if (rsABooks2.getInt(1) == tempISBN) {
					    		System.out.println(" " + rsABooks2.getInt(1) + "\t" + rsABooks2.getString(2));
					    	}
					    }
			    	}
			    }
			    
			    System.out.println("------------------------------");
				System.out.println();
				System.out.println("(1) Return to Main Menu");
				System.out.println("(2) Exit");
				System.out.println(">   ");
				int in = scan.nextInt();
				
				if (in == 1) {
			    	if (userType.equals("Reader")) {
			    		readerLogin();
			    	}
			    	else if (userType.equals("Staff")) {
			    		staffLogin();
			    	}
				}
				else if (in == 2) {
					System.exit(0);
				}

				
			}
			else if (input == 2) {
		    	if (userType.equals("Reader")) {
		    		readerLogin();
		    	}
		    	else if (userType.equals("Staff")) {
		    		staffLogin();
		    	}
		    }
		    else if (input == 3) {
		    	System.exit(0);
		    }
	    }
	    
	    else if (userType.equals("Reader") && viewCategory.equals("BOOKS")) {
			System.out.println("------------------------------");
			System.out.println();
			System.out.println("(1) Checkout Book");
			System.out.println("(2) Return to Main Menu");
			System.out.println("(3) Exit");
			System.out.println(">   ");
			int input = scan.nextInt();
			if (input == 1) {
				System.out.println();
				System.out.println();
				System.out.print("Entered Desired Book ID: > ");
				int selectedBookID = scan.nextInt();
				//System.out.println("Book ID: " + selectedBookID);
				//System.out.println("Member ID: " + memberID);
				
			    String qCheckout = "INSERT INTO Borrows (Checkout_Date, memberID, ISBN)" + " VALUES (?, ?, ?)";
			    PreparedStatement pS = con.prepareStatement(qCheckout);
			    pS.setDate(1,  java.sql.Date.valueOf(java.time.LocalDate.now()));
			    pS.setInt(2, memberID);
			    pS.setInt(3, selectedBookID);
			    pS.execute();
			    
			    System.out.println();
			    System.out.println("Book Successfully Checked Out!");
			    
				System.out.println("------------------------------");
				System.out.println();
				System.out.println("(1) Return to Main Menu");
				System.out.println("(2) Exit");
				System.out.println(">   ");
				int in = scan.nextInt();
				
				if (in == 1) {
			    	if (userType.equals("Reader")) {
			    		readerLogin();
			    	}
			    	else if (userType.equals("Staff")) {
			    		staffLogin();
			    	}
				}
				else if (in == 2) {
					System.exit(0);
				}
				
			}
			else if (input == 2) {
		    	if (userType.equals("Reader")) {
		    		readerLogin();
		    	}
		    	else if (userType.equals("Staff")) {
		    		staffLogin();
		    	}
		    }
		    else if (input == 3) {
		    	System.exit(0);
		    }
	    }
	    
	    else if (userType.equals("Reader") && viewCategory.equals("MYBOOKS")) {
	    	Connection con4 = DriverManager.getConnection(url, user, password);
		    Statement st4 = con4.createStatement();
		    String qCheckout = "SELECT Checkout_Date, memberID, ISBN FROM Borrows";
		    ResultSet rsCheckout = st4.executeQuery(qCheckout);
		    
		    System.out.println("Checkout Date \t ISBN \t\t   Title");
		    
		    Boolean booksFound = false;
		    
		    while (rsCheckout.next() ) {
		    	if (rsCheckout.getInt(2) == memberID) {
		    		int tempISBN = rsCheckout.getInt(3);
		    		Date tempDate = rsCheckout.getDate(1);
		    		
			    	Connection con5 = DriverManager.getConnection(url, user, password);
				    Statement st5 = con5.createStatement();
				    String qCheckout2 = "SELECT ISBN, Title FROM Book";
				    ResultSet rsCheckout2 = st5.executeQuery(qCheckout2);
				    while (rsCheckout2.next() ) {
				    	if (rsCheckout2.getInt(1) == tempISBN) {
				    		System.out.println(" " + tempDate + "\t  " + tempISBN + "\t" + rsCheckout2.getString(2));
				    		booksFound = true;
				    	}
				    }
		    	}
		    }
		    
		    if (booksFound == false) {
		    	System.out.println("");
		    	System.out.println("");
		    	System.out.println("No Books Are Currently Checked Out");
		    }
		    
		    System.out.println("------------------------------");
			System.out.println();
			System.out.println("(1) Return to Main Menu");
			System.out.println("(2) Exit");
			System.out.println(">   ");
			int in = scan.nextInt();
			
			if (in == 1) {
		    	if (userType.equals("Reader")) {
		    		readerLogin();
		    	}
		    	else if (userType.equals("Staff")) {
		    		staffLogin();
		    	}
			}
			else if (in == 2) {
				System.exit(0);
			}
	    }
	    
	    else {
			System.out.println("------------------------------");
			
			System.out.println("(1) Return to Main Menu");
			System.out.println("(2) Exit");
			System.out.println(">   ");
			int input = scan.nextInt();
		    if (input == 1) {
		    	if (userType.equals("Reader")) {
		    		readerLogin();
		    	}
		    	else if (userType.equals("Staff")) {
		    		staffLogin();
		    	}
		    }
		    else {
		    	System.exit(0);
		    }
	    }
	    
	    
	    st.close();
	    con.close();
		scan.close();
	    
	}
	
	public static void delete(String deleteCategory) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("DELETE " + deleteCategory);
		System.out.println("Select the line number to delete");
		System.out.println("------------------------------");
	    // establish the connection
	    Connection con = DriverManager.getConnection(url, user, password);

	    // create JDBC statement object
	    Statement st = con.createStatement();
	    
	    int options = 1;
	    String deletedTitle = "";
	    
	    if (deleteCategory.equals("AUTHORS")) {
		    String qA = "SELECT authID, Name FROM Author";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Author ID \t      Name");
		    while (rsA.next()) {
		    	System.out.println("(" + options + ")  " + rsA.getInt(1) + "\t\t" + rsA.getString(2));
		    	options++;
		     }
	    }
	    
	    else if (deleteCategory.equals("BOOKS")) {
		    String qA = "SELECT ISBN, Title, numPages FROM Book";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("ISBN \t Title \t\t Number of Pages");
		    while (rsA.next()) {
		    	System.out.println("(" + options + ")" + rsA.getInt(1) + "\t" + rsA.getString(2) + "\t  " + rsA.getInt(3));
		    	options++;
		    }
	    }
	    
	    else if (deleteCategory.equals("LIBRARIES")) {
		    String qA = "SELECT libID, Location, Name FROM Library";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Library ID \t Location \t Name");
		    while (rsA.next()) {
		    	System.out.println("(" + options + ")   " + rsA.getInt(1) + "\t\t" + rsA.getString(2) + "\t\t  " + rsA.getString(3));
		    	options++;
		    }
	    }

	    else if (deleteCategory.equals("READERS")) {
		    String qA = "SELECT memberID, Sign_Up_Date, Name, Email, Pin, libID FROM Reader";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Member ID \t Sign Up Date \t Name \t\t Email \t\t Pin \t Library");
		    while (rsA.next()) {
		    	System.out.println("(" + options + ")  " + rsA.getInt(1) + "\t\t  " + rsA.getDate(2) + "\t " + rsA.getString(3)+ "\t    " + rsA.getString(4)+ "\t" + rsA.getInt(5) + "\t  " + rsA.getInt(6));
		    	options++;
		    }
	    }
	    
	    else if (deleteCategory.equals("STAFF")) {
		    String qA = "SELECT empID, Name, Title, Password, libID FROM Staff";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Employee ID \t   Name \t Title \t\t Password \t Library");
		    while (rsA.next()) {
		    	System.out.println("(" + options + ")   " + rsA.getInt(1) + "\t\t" + rsA.getString(2) + "\t" + rsA.getString(3)+ "\t  " + rsA.getString(4)+ "\t  " + rsA.getInt(5));
		    	options++;
		    }
	    }
		System.out.println("------------------------------");
		System.out.print(">   ");
		int deleteChoice = scan.nextInt();
		
		options = 1;
		
	    if (deleteCategory.equals("AUTHORS")) {
		    String qA = "SELECT authID, Name FROM Author";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Author ID \t      Name");
		    while (rsA.next()) {
		    	if (options == deleteChoice) {
		    		deletedTitle = rsA.getString(2);
		    		String qDelete = "DELETE FROM Author WHERE authID = ?";
		    		PreparedStatement preparedStatement = con.prepareStatement(qDelete);
		    		preparedStatement.setInt(1,  rsA.getInt(1));
		    		preparedStatement.execute();
		    	}
		    	options++;
		     }
	    }
	    
	    else if (deleteCategory.equals("BOOKS")) {
		    String qA = "SELECT ISBN, Title, numPages FROM Book";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("ISBN \t Title \t\t Number of Pages");
		    while (rsA.next()) {
		    	if (options == deleteChoice) {
		    		deletedTitle = rsA.getString(2);
		    		String qDelete = "DELETE FROM Book WHERE ISBN = ?";
		    		PreparedStatement preparedStatement = con.prepareStatement(qDelete);
		    		preparedStatement.setInt(1,  rsA.getInt(1));
		    		preparedStatement.execute();
		    	}
		    	options++;
		    }
	    }
	    
	    else if (deleteCategory.equals("LIBRARIES")) {
		    String qA = "SELECT libID, Location, Name FROM Library";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Library ID \t Location \t Name");
		    while (rsA.next()) {
		    	if (options == deleteChoice) {
		    		deletedTitle = rsA.getString(3);
		    		String qDelete = "DELETE FROM Library WHERE libID = ?";
		    		PreparedStatement preparedStatement = con.prepareStatement(qDelete);
		    		preparedStatement.setInt(1,  rsA.getInt(1));
		    		preparedStatement.execute();
		    	}
		    	options++;
		    }
	    }

	    else if (deleteCategory.equals("READERS")) {
		    String qA = "SELECT memberID, Sign_Up_Date, Name, Email, Pin, libID FROM Reader";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Member ID \t Sign Up Date \t Name \t\t Email \t\t Pin \t Library");
		    while (rsA.next()) {
		    	if (options == deleteChoice) {
		    		deletedTitle = rsA.getString(3);
		    		String qDelete = "DELETE FROM Reader WHERE memberID = ?";
		    		PreparedStatement preparedStatement = con.prepareStatement(qDelete);
		    		preparedStatement.setInt(1,  rsA.getInt(1));
		    		preparedStatement.execute();
		    	}
		    	options++;
		    }
	    }
	    
	    else if (deleteCategory.equals("STAFF")) {
		    String qA = "SELECT empID, Name, Title, Password, libID FROM Staff";
		    ResultSet rsA = st.executeQuery(qA);
		    System.out.println("Employee ID \t   Name \t Title \t\t Password \t Library");
		    while (rsA.next()) {
		    	if (options == deleteChoice) {
		    		deletedTitle = rsA.getString(2);
		    		String qDelete = "DELETE FROM Staff WHERE empID = ?";
		    		PreparedStatement preparedStatement = con.prepareStatement(qDelete);
		    		preparedStatement.setInt(1,  rsA.getInt(1));
		    		preparedStatement.execute();
		    	}
		    	options++;
		    }
	    }
		
		System.out.println(deletedTitle + " Successfully Deleted!");
		System.out.println("(1) Return to Main Menu");
		System.out.println("(2) Exit");
		System.out.println(">   ");
		int input = scan.nextInt();
	    if (input == 1) {
	    	if (userType.equals("Reader")) {
	    		readerLogin();
	    	}
	    	else if (userType.equals("Staff")) {
	    		staffLogin();
	    	}
	    }
	    else {
	    	System.exit(0);
	    }
	    
	    st.close();
	    con.close();
		scan.close();
	    
	}
	
	public static void addAuthor() throws Exception {
		Scanner scan = new Scanner(System.in);
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("ADD AUTHOR");
		System.out.println("------------------------------");
		System.out.print("Name:  ");
		String aName = scan.next();
		System.out.println("------------------------------");
		
	    // establish the connection
	    Connection con = DriverManager.getConnection(url, user, password);

	    // create JDBC statement object
	    Statement st = con.createStatement();
	      
	      
	    String q = "INSERT INTO Author (authID, Name)" + " VALUES (?, ?)";
	    PreparedStatement pS = con.prepareStatement(q);
	    pS.setInt(1,  authNum);
	    pS.setString(2, aName);
	    pS.execute();
	     
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Author Successfully Added!");
		System.out.println("(1) Return to Main Menu");
		System.out.println("(2) Exit");
		System.out.println(">   ");
		int input = scan.nextInt();
	    if (input == 1) {
	    	if (userType.equals("Reader")) {
	    		readerLogin();
	    	}
	    	else if (userType.equals("Staff")) {
	    		staffLogin();
	    	}
	    }
	    else {
	    	System.exit(0);
	    }

	    st.close();
	    con.close();
		scan.close();
	}
	
	public static void addBook() throws Exception {
		Scanner scan = new Scanner(System.in);
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("ADD BOOK");
		System.out.println("------------------------------");
		System.out.print("Title:  ");
		String bTitle = scan.next();
		System.out.print("Number of Pages:  ");
		int bNum = scan.nextInt();
		System.out.println("------------------------------");
		
	    // establish the connection
	    Connection con = DriverManager.getConnection(url, user, password);

	    // create JDBC statement object
	    Statement st = con.createStatement();
	      
	      
	    String q = "INSERT INTO Book (ISBN, Title, numPages)" + " VALUES (?, ?, ?)";
	    PreparedStatement pS = con.prepareStatement(q);
	    pS.setInt(1,  isbn);
	    pS.setString(2, bTitle);
	    pS.setInt(3, bNum);
	    pS.execute();
	     
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Book Successfully Added!");
		System.out.println("(1) Return to Main Menu");
		System.out.println("(2) Exit");
		System.out.println(">   ");
		int input = scan.nextInt();
	    if (input == 1) {
	    	if (userType.equals("Reader")) {
	    		readerLogin();
	    	}
	    	else if (userType.equals("Staff")) {
	    		staffLogin();
	    	}
	    }
	    else {
	    	System.exit(0);
	    }

	    st.close();
	    con.close();
		scan.close();
	}
	
	
	public static void addLibrary() throws Exception {
		Scanner scan = new Scanner(System.in);
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("ADD LIBRARY");
		System.out.println("------------------------------");
		System.out.print("Location:  ");
		String lLocation = scan.next();
		System.out.print("Name:  ");
		String lName = scan.next();
		System.out.println("------------------------------");
		
	    // establish the connection
	    Connection con = DriverManager.getConnection(url, user, password);

	    // create JDBC statement object
	    Statement st = con.createStatement();
	      
	      
	    String q = "INSERT INTO Library (libID, Location, Name)" + " VALUES (?, ?, ?)";
	    PreparedStatement pS = con.prepareStatement(q);
	    pS.setInt(1,  libID);
	    pS.setString(2, lLocation);
	    pS.setString(3, lName);
	    pS.execute();
	     
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Library Successfully Added!");
		System.out.println("(1) Return to Main Menu");
		System.out.println("(2) Exit");
		System.out.println(">   ");
		int input2 = scan.nextInt();
	    if (input2 == 1) {
	    	if (userType.equals("Reader")) {
	    		readerLogin();
	    	}
	    	else if (userType.equals("Staff")) {
	    		staffLogin();
	    	}
	    }
	    else if (input2 == 2) {
	    	System.exit(0);
	    }

	    st.close();
	    con.close();
		scan.close();
	}
	
	public static void addReader() throws Exception {
		Scanner scan = new Scanner(System.in);
		
	    // establish the connection
	    Connection con = DriverManager.getConnection(url, user, password);

	    // create JDBC statement object
	    Statement st = con.createStatement();
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("ADD READER");
		System.out.println("------------------------------");
		System.out.print("Name:  ");
		String rName = scan.next();
		System.out.print("Email:  ");
		String rEmail = scan.next();
		System.out.print("Pin:  ");
		int rPin = scan.nextInt();
		System.out.println("Enter the library ID of the Library location the new member belongs to: ");
		String qLocations = "SELECT libID, Location, Name FROM Library";
	    ResultSet rsLocations = st.executeQuery(qLocations);
	    System.out.println("Library ID \t Location \t Name");
	    while (rsLocations.next()) {
	    	  System.out.println(rsLocations.getInt(1) + "\t" + rsLocations.getString(2) + "\t" + rsLocations.getString(3));
	     }
	    System.out.print(">  ");
	    int inputLocation = scan.nextInt();
	      
		System.out.println("------------------------------");
		
	      
	      
	    String q = "INSERT INTO Reader (memberID, Sign_Up_Date, Name, Email, Pin, libID)" + " VALUES (?, ?, ?, ?, ?, ?)";
	    PreparedStatement pS = con.prepareStatement(q);
	    pS.setInt(1,  memberID);
	    pS.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
	    pS.setString(3, rName);
	    pS.setString(4, rEmail);
	    pS.setInt(5, rPin);
	    pS.setInt(6, inputLocation);
	    pS.execute();
	     
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Reader Successfully Added!");
		System.out.println("(1) Return to Main Menu");
		System.out.println("(2) Exit");
		System.out.println(">   ");
		int input = scan.nextInt();
	    if (input == 1) {
	    	if (userType.equals("Reader")) {
	    		readerLogin();
	    	}
	    	else if (userType.equals("Staff")) {
	    		staffLogin();
	    	}
	    }
	    else {
	    	System.exit(0);
	    }

	    st.close();
	    con.close();
		scan.close();
	}
	
	public static void addStaff() throws Exception {
		Scanner scan = new Scanner(System.in);
		
	    // establish the connection
	    Connection con = DriverManager.getConnection(url, user, password);

	    // create JDBC statement object
	    Statement st = con.createStatement();
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("ADD STAFF");
		System.out.println("------------------------------");
		System.out.print("Name:  ");
		String sName = scan.next();
		System.out.print("Title:  ");
		String sTitle = scan.next();
		System.out.print("Password:  ");
		String sPass = scan.next();
		System.out.println("Enter the library ID of the Library location the staff member belongs to: ");
		String qLocations = "SELECT libID, Location, Name FROM Library";
	    ResultSet rsLocations = st.executeQuery(qLocations);
	    System.out.println("Library ID \t Location \t Name");
	    while (rsLocations.next()) {
	    	  System.out.println(rsLocations.getInt(1) + "\t" + rsLocations.getString(2) + "\t" + rsLocations.getString(3));
	     }
	    System.out.print(">  ");
	    int inputLocation = scan.nextInt();
	      
		System.out.println("------------------------------");
		
	      
	      
	    String q = "INSERT INTO Staff (empID, Name, Title, Password, libID)" + " VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement pS = con.prepareStatement(q);
	    pS.setInt(1,  empID);
	    pS.setString(2, sName);
	    pS.setString(3, sTitle);
	    pS.setString(4, sPass);
	    pS.setInt(5, inputLocation);
	    pS.execute();
	     
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Staff Member Successfully Added!");
		System.out.println("(1) Return to Main Menu");
		System.out.println("(2) Exit");
		System.out.println(">   ");
		int input = scan.nextInt();
	    if (input == 1) {
	    	if (userType.equals("Reader")) {
	    		readerLogin();
	    	}
	    	else if (userType.equals("Staff")) {
	    		staffLogin();
	    	}
	    }
	    else {
	    	System.exit(0);
	    }

	    st.close();
	    con.close();
		scan.close();
	}
	
	public static void login() throws Exception {
		System.out.println("Welcome to the Library System!");
	      System.out.println("");
	      System.out.println("");
	      System.out.println("LOGIN");
	      System.out.println("------------------------------");
	      System.out.print("ID: >  ");
	      Scanner scan = new Scanner(System.in);
	      int enteredID = Integer.parseInt(scan.next());
	      memberID = enteredID;
	      
	      System.out.print("Password/Pin:  >  ");
	      String enteredPass = scan.next();
	      System.out.println("------------------------------");
	      System.out.println("");
	      System.out.println("Validating...");
	      System.out.println("");
	      
	      boolean readerCheck = false;
	      
	      try {
	    	  int enteredPassInt = Integer.parseInt(enteredPass);
	    	  readerCheck = true;
	      }
	      catch(NumberFormatException e) {
	    	  readerCheck = false;
	      }
	      

	      // establish the connection
	      Connection con = DriverManager.getConnection(url, user, password);
	      
	      
	      boolean userFound = false;
	      
	      //Reader Login
	      if (readerCheck == true) {
		      Statement stValR = con.createStatement();
		      String qValR = "SELECT memberID, Pin,  Name FROM Reader";
		      ResultSet rsValR = stValR.executeQuery(qValR);
		      while (rsValR.next()) {
		    	  if (rsValR.getInt(1) == enteredID) {
		    		  if (rsValR.getInt(2) == Integer.parseInt(enteredPass)) {
		    			  System.out.println("Login Validated!");
		    			  System.out.println("Welcome " + rsValR.getString(3));
		    			  userFound = true;
		    			  userType = "Reader";
		    			  readerLogin();
		    		  }
		    		  else {
		    			  System.out.println("Login Invalid");
		    			  System.out.println("Please Try Again");
		    			  System.out.println("");
		    			  System.out.println("");
		    			  login();
		    		  }
		    	  }
		      }
	      }
	      //Staff Login
	      else {
		      Statement stValS = con.createStatement();
		      String qValS = "SELECT empID, Password, Name FROM Staff";
		      ResultSet rsValS = stValS.executeQuery(qValS);
		      while (rsValS.next()) {
		    	  if (rsValS.getInt(1) == enteredID) {
		    		  if (rsValS.getString(2).equalsIgnoreCase(enteredPass)) {
		    			  System.out.println("Login Validated!");
		    			  System.out.println("Welcome " + rsValS.getString(3));
		    			  userFound = true;
		    			  userType = "Staff";
		    			  staffLogin();
		    		  }
		    		  else {
		    			  System.out.println("Login Invalid");
		    			  System.out.println("Please Try Again");
		    			  System.out.println("");
		    			  System.out.println("");
		    			  login();
		    		  }
		    	  }
		      }
	      }
	      
	      

	      
	      if (userFound == false) {
	    	  System.out.println("Invalid User ID");
	    	  System.out.println("Please Try Again");
	    	  System.out.println("");
	    	  System.out.println("");
	    	  login();
	      }
	}
	
   public static void main(String[] args) throws Exception {

      // variables
      final String url = "jdbc:mysql:///C_Test_1";
      final String user = "root";
      final String password = "Diff3r3nt!";
      


      Connection con = DriverManager.getConnection(url, user, password);
      Statement st = con.createStatement();
      
      //Incrementing Author ID
      String qA = "SELECT authID, Name FROM Author";
      ResultSet rsA = st.executeQuery(qA);
      while (rsA.next()) {
          authNum = rsA.getInt(1) + 1;
       }
      
      //Incrementing ISBN
      String qISBN = "SELECT ISBN, Title, numPages FROM Book";
      ResultSet rsISBN = st.executeQuery(qISBN);
      while (rsISBN.next()) {
          isbn = rsISBN.getInt(1) + 1;
       }
      
      //Incrementing Library ID
      String qL = "SELECT libID, Location, Name FROM Library";
      ResultSet rsL = st.executeQuery(qL);
      while (rsL.next()) {
          libID = rsL.getInt(1) + 1;
       }
      
      //Incrementing Member ID
      String qM = "SELECT memberID, Sign_Up_Date, Name, Email, Pin, libID FROM Reader";
      ResultSet rsM = st.executeQuery(qM);
      while (rsM.next()) {
          memberID = rsM.getInt(1) + 1;
       }
      
      //Incrementing Staff ID
      String qS = "SELECT empID, Name, Title, Password, libID FROM Staff";
      ResultSet rsS = st.executeQuery(qS);
      while (rsS.next()) {
          empID = rsS.getInt(1) + 1;
       }
      
      login();
      


      // close JDBC objects
      st.close();
      con.close();

   } 
}