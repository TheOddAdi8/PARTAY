/**
 * Company.java
 * @author Adi Duggal
 * @since 11/27/2023
 * @version 1.0.0
 * 
 * This class contains info and ArrayLists about the companies.
 */


//allows the use of ArrayLists
import java.util.ArrayList;

public class Company {
    
    //ArrayList of Attendees objects
    public ArrayList<Attendees> companyPeople = new ArrayList<>();

    //method to add a person to the companyPeople arraylist
    public void addGuest(Attendees person) {
        companyPeople.add(person);
    }

    //method to print useful info
    public void printStuff() {

        //for each person in a company
        for (Attendees person : companyPeople) {

            //if the person has a table
            if (person.getTable() != 0) {
                //ex: Dominick Hopkins, Table 3, Seat 5
                System.out.println(person.name() + ", Table " + person.getTable() + ", Seat " + person.getSeat());
            }
            else {
                //ex: Armando Paul, *No Table Found*
                System.out.println(person.name() + ", *No Table Found*");
            }

        }

    }

    //method to get the name of the company
    public String getName() {
        //if there are people in the company
        if (companyPeople.size() > 0) {
            /*
            gets the first person in the companyPeople ArrayList and gets the company name from the company number
            company number is calculated by using the first person in the ArrayList and getting their company number
            ideally the company number should be the same for every person in the same company
            */
            return companyPeople.get(0).getCompanyName(companyPeople.get(0).getCompany());
        }
        else {
            //if the company doesn't have any workers then return empty quotes
            return "";
        }
    }

}
