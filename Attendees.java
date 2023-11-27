/**
 * Attendees.java
 * @author Adi Duggal
 * @since 11/27/2023
 * @version 1.0.0
 * 
 * This class contains info about individual people. This info includes ID, last name, first name, company, table number, and seat number. This class acts as the base for this project because it contains the most vital info.
 */


//allows file compatibility
import java.io.File;
//handles FileNotFoundException errors
import java.io.FileNotFoundException;
//grants the ability to read .txt files
import java.util.Scanner;

public class Attendees {
	//vital info for each person
	private int ID;
	private String lastName;
	private String firstName;
	private int company;
	private int table;
	private int seat;
	//array for split method
	public String[] info = new String[4];
	
	//constructor that runs when an instance of the Attendees class is created
	//param guestInfo comes in a String in format: "ID,LastName,FirstName,CompanyNumber"
	public Attendees(String guestInfo) {
		
		//splits the String at the commas and puts the individual Strings in array info
		info = guestInfo.split(",");
		
		//var ID is set to the first value of info
		ID = Integer.parseInt(info[0]);
		//var lastName is set to the second value of info
		lastName = info[1];
		//and so forth
		firstName = info[2];
		//and so forth again
		company = Integer.parseInt(info[3]);
	}

	//method to get company number
	public int getCompany() {
		return company;
	}

	//method to set the table number to param tableNum
	public void setTable(int tableNum) {
		table = tableNum;
	}

	//method to get table number
	public int getTable() {
		return table;
	}

	//method to set the seat number to param seatNum
	public void setSeat(int seatNum) {
		seat = seatNum;
	}

	//method to get seat number
	public int getSeat() {
		return seat;
	}

	//method to get the company name from a company number
	public String getCompanyName(int companyNum) {
		try {
			//creating File object for "companies.txt"
			File newGuestFile = new File("companies.txt");
			//instance of Scanner class to read "companies.txt"
			Scanner fileBot = new Scanner(newGuestFile);
			//while there is a next line
			while (fileBot.hasNextLine()) {
				String data = fileBot.nextLine();
				//if line is not empty
				if (!data.equals("")) {
					//if any of the company numbers is equivalent to the param companyNum
					if (Integer.parseInt(data.substring(0, 2)) == companyNum) {
						fileBot.close();
						//returns name of company
						return data.substring(3);
					}
				}
			}
			fileBot.close();
			//error msg just in case :)
			return "ERROR lol imagine getting an error :)";
		}
		catch (FileNotFoundException e) {
			//more error msgs just in case :)
			System.out.println("Uh oh, there was an error :(");
			e.printStackTrace();
		}
		//even more error msgs just in case the program stops working properly :)
		return "You should not have gotten this error. If you got this error that's a big problem :(";
	}

	//method to return a String if the program tries to System.out.println() an Attendees object
	public String toString() {
		return firstName + " " + lastName + ", " + getCompanyName(company);
	}

	// method to get the full name of the person
	public String name() {
		return firstName + " " + lastName;
	}
}
