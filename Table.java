/**
 * Table.java
 * @author Adi Duggal
 * @since 11/27/2023
 * @version 1.0.0
 * 
 * This class contains info and ArrayLists about the tables.
 */


//allows the use of ArrayLists
import java.util.ArrayList;

public class Table {

    //ArrayList of Attendees objects
    public ArrayList<Attendees> tablePeople = new ArrayList<>();

    //method to return the number of people at a table
    public int listSize() {
        return tablePeople.size();
    }

    //method to add a person to the ArrayList
    public void addGuest(Attendees person) {
        tablePeople.add(person);
    }

    //method to check if a person is the only one from their company at a table
    public boolean onlyCompany(int companyNum) {

        //for each person at the table
        for (Attendees person : tablePeople) {

            //if anybody works at the same company as the person being evaluated then return false
            if (person.getCompany() == companyNum) {

                return false;

            }

        }

        //otherwise they are they only one of their company at the table
        return true;

    }

    //method to set an individual's company
    public void setComp(Attendees person, int tableCounter) {

        //index was set at -1 to help me debug if necessary
        int index = -1;
        int count = 0;

        //for each individual at a table
        for (Attendees individual : tablePeople) {
            //if their name matches with the parameter person's name
            if (individual.name().equals(person.name())) {
                //then the index is set to the count
                index = count;
                //break to stop evaluating all the other options
                break;
            }
            //counting
            count++;
        }

        //setting the Attendee's var "table" to the table number from parameter tableCounter
        tablePeople.get(index).setTable(tableCounter);
    }

    //method to set the seat number of an Attendee
    public void setSeat() {
        int counter = 1;
        //for each person at a table
        for (Attendees person : tablePeople) {
            //sets the seat of an Attendee according to order
            //ex: first person has seat 1, second person has seat 2
            person.setSeat(counter);
            counter++;
        }
    }
    
    //method to print useful info
    public void printStuff() {
        int counter = 1;
        //for each person at a table
        for (Attendees person : tablePeople) {
            //ex: Seat 3: Sharon Carson, Gucci
            System.out.println("Seat " + counter + ": " + person);
            counter++;
        }
    }

}
