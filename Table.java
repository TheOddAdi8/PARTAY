import java.util.ArrayList;

public class Table {

    public ArrayList<Attendees> tablePeople = new ArrayList<>();

    public int listSize() {
        return tablePeople.size();
    }
    public void addGuest(Attendees person) {
        tablePeople.add(person);
    }
    public boolean onlyCompany(int companyNum) {

        for (Attendees person : tablePeople) {

            if (person.getCompany() == companyNum) {

                return false;

            }

        }

        return true;

    }
    public void printStuff() {
        for (Attendees person : tablePeople) {
            System.out.println(person);
        }
    }

}
