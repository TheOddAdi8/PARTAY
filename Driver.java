/**
 * Driver.java
 * @author Adi Duggal
 * @since 11/27/2023
 * @version 1.0.0
 * 
 * This class runs the whole program. It creates an instance of the Party class and has a main method. The program itself is meant to take 2 files (one with a list of people and the other with a list of companies) and sort them into groups.
 */


//Scanner is necessary to access user input
import java.util.Scanner;

public class Driver {
	//runs everything
	
	//creates an instance of the Scanner class
	//static because multiple instances of Scanner(System.in) caused a NoSuchElementException error
	public static Scanner scanBot = new Scanner(System.in);

	//This method clears the terminal screen
	//Source: Adi's Battleship project, Mr. Twyford, and Geeks for Geeks
	public static void clear() { 
        System.out.print("\033[H\033[2J");
    }

	//This method pauses the program for a certain period of time
	//Source: Adi's Battleship project and https://www.geeksforgeeks.org/wait-method-in-java-with-examples/
    public static void wait(int ms) { 
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

	//main method
	public static void main(String[] args) {
		//creates an instance of the party class
		Party p1 = new Party();

		clear();

		System.out.println("Add 10 guests to your roster\n");
		//method in the p1 object
		p1.addGuests();

		clear();
		//method in the p1 object
		p1.tableSet();

		while (true) {
			//This is a cool animation that displays a user typing "help" into a command terminal
			wait(1000);
			System.out.println("Command Terminal:");
			System.out.print("PS C:\\Users\\you\\Documents\\APCS\\PARTAY> ");
			wait(1000);
			System.out.print("h");
			wait(250);
			System.out.print("e");
			wait(250);
			System.out.print("l");
			wait(250);
			System.out.print("p");
			wait(500);
			//options that the user can choose from
			System.out.println("\n\n - \"table\": to print rosters by table");
			System.out.println(" - \"company\": to print rosters by company");
			System.out.println(" - \"search\": to find a person and their information");
			System.out.println(" - \"end\": to end the program");
			wait(3000);
			System.out.println("");
			System.out.print("PS C:\\Users\\you\\Documents\\APCS\\PARTAY\\typeHere> ");

			String choice = "";

			do {
				choice = scanBot.nextLine();
			}
			while (!choice.equalsIgnoreCase("table") && !choice.equalsIgnoreCase("company") && !choice.equalsIgnoreCase("search") && !choice.equalsIgnoreCase("end"));

			if (choice.equalsIgnoreCase("table")) {
				p1.printByTable();
			}
			else if (choice.equalsIgnoreCase("company")) {
				p1.printByCompany();
			}
			else if (choice.equalsIgnoreCase("search")) {
				p1.search();
			}
			else {
				break;
			}

			//scanner scans the next line but doesn't do anything with what was scanned
			System.out.println("(\nPress enter to continue)");
			scanBot.nextLine();
			clear();
		}
		
	}
}
