/**
 * Party.java
 * @author Adi Duggal
 * @since 11/27/2023
 * @version 1.0.0
 * 
 * This class runs most of the methods that appear on the screen. The Party class contains most of the essential aspects to this project.
 */


//allows file compatibility
import java.io.File;
//handles FileNotFoundException errors
import java.io.FileNotFoundException;
//allows the use of ArrayLists
import java.util.ArrayList;
//allows both user input and the ability to read .txt files
import java.util.Scanner;

public class Party {
	//creating final variables for easy changes if necessary
	//"partyguests.txt" written in format: ID,LastName,FirstName,CompanyNumber
	final String GUESTS_FILE = "partyguests.txt";
	//"companies.txt" written in format: CompanyNumber,CompanyName
	final String COMPANIES_FILE = "companies.txt";
	//these will be changed later but this is for initialization
	int numGuests = 0;
	int numCompanies = 0;

	// ArrayList with Attendees object that will contain every person in "partyguests.txt" and 10 more from user input
	public ArrayList<Attendees> people = new ArrayList<Attendees>();

	//constructor that runs when an instance of Party is created
	public Party() {
		try {
			//creating File objects for "partyguests.txt" and "companies.txt"
			File guests = new File(GUESTS_FILE);
			File companies = new File(COMPANIES_FILE);

			//instance of Scanner class that reads "GUESTS_FILE"
			Scanner guestFileBot = new Scanner(guests);

			while (guestFileBot.hasNextLine()) {
				String data = guestFileBot.nextLine();
				//if line is not empty
				if (!data.equals("")) {
					//adding Attendees to the people ArrayList
					people.add(new Attendees(data));
					//counts number of guests (ideally should be 90 by the end of this loop)
					numGuests++;
				}
			}

			guestFileBot.close();

			//instance of Scanner class that reads "COMPANIES_FILE"
			Scanner companyFileBot = new Scanner(companies);

			while (companyFileBot.hasNextLine()) {
				String data = companyFileBot.nextLine();
				//if line is not empty
				if (!data.equals("")) {
					//counts number of companies (ideally should be 16 by the end of this loop)
					numCompanies++;
				}
			}
			companyFileBot.close();
		}
		//in case there is an error, this catch will let the user know what happened
		catch (FileNotFoundException e) {
			System.out.println("Uh oh, there was an error. The file could not be found :(");
			e.printStackTrace();
		}
	}

	//This method clears the terminal screen
	//Source: Driver.java, Adi's Battleship project, Mr. Twyford, and Geeks for Geeks
	public static void clear() {
        System.out.print("\033[H\033[2J");
    }

	//This method allows the user to add 10 guests as well (or up to 100 total guests)
	public void addGuests() {
		String newGuest = "";
		String company = "";
		String companyNum = "";
		boolean companyExists = false;
		//ideally should only loop 10 times
		//also limits the number of guests to 100
		for (int i = numGuests; i < 100; i++) {
			newGuest = "";
			company = "";
			companyNum = "";
			companyExists = false;

			//automatically creates ID (it's in numerical order (ex: 1, 2, 3, etc.))
			//ideally should begin at 91
			newGuest = Integer.toString(i + 1) + ",";

			//asks for user input
			System.out.println("What is guest #" + (i + 1) + "'s last name?");
			newGuest += Driver.scanBot.nextLine() + ",";
			
			//asks for user input
			System.out.println("What is guest #" + (i + 1) + "'s first name?");
			newGuest += Driver.scanBot.nextLine() + ",";

			//asks for user input with a twist
			//thinks that the company doesn't exist until it is proven that it exists
			while (companyExists == false) {
				System.out.println("What company does guest #" + (i + 1) + " work for?");
				//Driver.scanBot b/c of error mentioned in Driver class
				//turned into lowercase for comparison
				company = Driver.scanBot.nextLine().toLowerCase();
				
				try {
					//creates File object for "COMPANIES_FILE" ("companies.txt")
					File newGuestFile = new File(COMPANIES_FILE);
					//instance of Scanner class to read "COMPANIES_FILE"
					Scanner fileBot = new Scanner(newGuestFile);
					//while the next line exists
					while (fileBot.hasNextLine()) {
						String data = fileBot.nextLine();
						//line isn't blank
						if (!data.equals("")) {
							//company name begins at index 3
							//checks if var company (lowercase) is equal to any of the companies' names (lowercase)
							if (data.substring(3).toLowerCase().equals(company)) {
								//if it is then it takes the company number and saves it to a variable
								companyNum = data.substring(0, 2);
								//sets companyExists to true to break out of the while loop
								companyExists = true;
								//break to stop checking the rest of the company names
								break;
							}
						}
					}
					fileBot.close();
					
				}
				//in case there's an error
				catch (FileNotFoundException e) {
					System.out.println("Uh oh, there was an error :(");
					e.printStackTrace();
				}
			}
			
			//if the number starts with a 0 then it removes the zero (04 --> 4)
			if (companyNum.substring(0, 1).equals("0")) {
				companyNum = companyNum.substring(1, 2);
			}

			newGuest += companyNum;

			/*
			newGuest is now in format: ID,LastName,FirstName,CompanyNumber
			this is the same as the format for "partyguests.txt"
			*/

			System.out.println("");

			//add to people ArrayList
			people.add(new Attendees(newGuest));
			//counts more guests
			numGuests++;

			clear();
		}
	}
	//ArrayList of the Table class to have multiple tables
	ArrayList<Table> allTables = new ArrayList<>();
	//ArrayList of the Company class to have multiple companies
	ArrayList<Company> allCompanies = new ArrayList<>();

	public void tableSet() {

		//creates 10 tables
		for (int i = 0; i < 10; i++) {

			allTables.add(new Table());

		}

		int tableCounter = 1;

		//for each person in everyone invited
		for (Attendees guest : people) {
			
			tableCounter = 1;
			//for each table in all the tables
			for (Table eachTable : allTables) {

				//if the amount of people at the table is less than 10 and this person would be the only one from their own company
				if ((eachTable.listSize() < 10) && (eachTable.onlyCompany(guest.getCompany()))) {

					//adds the guest
					eachTable.addGuest(guest);
					//sets the company of guest to the company number (calculated by tableCounter)
					eachTable.setComp(guest, tableCounter);
					//break to stop checking other tables otherwise it would place people at multiple tables
					break;

				}
				//creates seat numbers for everyone at that table
				eachTable.setSeat();
				tableCounter++;
			}

		}

		//creates multiple companies (ideally 16)
		for (int i = 0; i < numCompanies; i++) {

			allCompanies.add(new Company());

		}

		int companyCounter = 1;

		//for each person in everyone invided
		for (Attendees guest : people) {

			companyCounter = 1;
			//for each company in all the companies
			for (Company eachCompany : allCompanies) {

				// if the person's company is the same as the companyCounter value
				if (guest.getCompany() == companyCounter) {
					//the person gets added to that particular company in the Arraylist
					eachCompany.addGuest(guest);
				}
				//calculates the company (not total but what company it is (ex: when companyCounter == 1, it's referring to "Wal-Mart"))
				companyCounter++;
			}

		}
	}

	//this method prints the rosters by table
	public void printByTable() {

		int count = 1;

		//for each table in all the tables
		for (Table eachTable : allTables) {
			//ex: Table 4
			System.out.println("Table " + count);
			//prints info such as seat, name, and company
			eachTable.printStuff();
			count++;
			System.out.println("");
		}

	}

	//this method prints the rosters by company
	public void printByCompany() {
		//for eath company in all the companies
		for (Company eachCompany : allCompanies) {
			//ex: Heinz
			System.out.println(eachCompany.getName());
			//prints info such as name, table, and seat
			eachCompany.printStuff();
			System.out.println("");
		}

	}

	//this method allows the user to find a person and find what table and seat they're at
	public void search() {
		
		System.out.println("\nWho do you want to find?");
		//takes user input
		String nameSearch = Driver.scanBot.nextLine();

		int counter = 0;

		//for each person in all the guests
		for (Attendees person : people) {

			//if the person's name (lowercase) contains the user input (lowercase)
			if (person.name().toLowerCase().contains(nameSearch.toLowerCase())) {

				//+1 search result
				counter++;

			}

		}

		Driver.wait(1000);

		System.out.println("\n");
		//displays search results (ex: 4 results)
		System.out.println(counter + " results\n");

		//if there are search results (not 0)
		if (counter != 0) {
			//for each person in all the guests
			for (Attendees person : people) {

				//repeat if the person's name (lowercase) contains the user input (lowercase)
				if (person.name().toLowerCase().contains(nameSearch.toLowerCase())) {

					//if they have a table
					if (person.getTable() != 0) {
						//ex: Morris Jimenez, Kroger, Table 1, Seat 2
						System.out.println(person.name() + ", " + person.getCompanyName(person.getCompany()) + ", Table " + person.getTable() + ", Seat " + person.getSeat());
					}
					else {
						//ex: Billy Gutierrez, *No Table Found*
						System.out.println(person.name() + ", *No Table Found*");
					}

				}
			}

		}

	}
	
}
