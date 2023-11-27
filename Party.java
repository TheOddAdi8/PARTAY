import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Party {

	final String GUESTS_FILE = "partyguests.txt";
	final String COMPANIES_FILE = "companies.txt";
	int numGuests = 0;
	int numCompanies = 0;

	// ArrayList with attendee object
	public ArrayList<Attendees> people = new ArrayList<Attendees>();

	public Party() {
		try {
			File guests = new File(GUESTS_FILE);
			File companies = new File(COMPANIES_FILE);

			Scanner guestFileBot = new Scanner(guests);

			while (guestFileBot.hasNextLine()) {
				String data = guestFileBot.nextLine();
				if (!data.equals("")) {
					people.add(new Attendees(data));
					numGuests++;
				}
			}

			guestFileBot.close();

			Scanner companyFileBot = new Scanner(companies);

			while (companyFileBot.hasNextLine()) {
				String data = companyFileBot.nextLine();
				if (!data.equals("")) {
					numCompanies++;
				}
			}
			companyFileBot.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Uh oh, there was an error :(");
			e.printStackTrace();
		}
	}

	public void addGuests() {
		Scanner guestBot = new Scanner(System.in);
		String newGuest = "";
		String company = "";
		String companyNum = "";
		boolean companyExists = false;
		for (int i = numGuests; i < 100; i++) {
			newGuest = "";
			company = "";
			companyNum = "";
			companyExists = false;

			newGuest = Integer.toString(i + 1) + ",";

			System.out.println("What is guest #" + (i + 1) + "'s last name?");
			newGuest += guestBot.nextLine() + ",";
			
			System.out.println("What is guest #" + (i + 1) + "'s first name?");
			newGuest += guestBot.nextLine() + ",";

			while (companyExists == false) {
				System.out.println("What company does guest #" + (i + 1) + " work for?");
				company = guestBot.nextLine().toLowerCase();
				
				try {
					File newGuestFile = new File(COMPANIES_FILE);
					Scanner fileBot = new Scanner(newGuestFile);
					while (fileBot.hasNextLine()) {
						String data = fileBot.nextLine();
						if (!data.equals("")) {
							if (data.substring(3).toLowerCase().equals(company)) {
								companyNum = data.substring(0, 2);
								companyExists = true;
								break;
							}
						}
					}
					fileBot.close();
					
				}
				catch (FileNotFoundException e) {
					System.out.println("Uh oh, there was an error :(");
					e.printStackTrace();
				}
			}

			if (companyNum.substring(0, 1).equals("0")) {
				companyNum = companyNum.substring(1, 2);
			}

			newGuest += companyNum;

			System.out.println("");

			people.add(new Attendees(newGuest));
			numGuests++;
		}
		guestBot.close();
	}

	ArrayList<Table> allTables = new ArrayList<>();

	public void tableSet() {

		for (int i = 0; i < 10; i++) {

			allTables.add(new Table());

		}

		for (Attendees guest : people) {
			
			for (Table eachTable : allTables) {

				if ((eachTable.listSize() < 10) && (eachTable.onlyCompany(guest.getCompany()))) {

					eachTable.addGuest(guest);
					break;

				}

			}

		}

		for (Table eachTable : allTables) {
			eachTable.printStuff();
			System.out.println("");
		}

	}
}
