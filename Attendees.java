public class Attendees {
	private int ID;
	private String lastName;
	private String firstName;
	private int company;
	private int table = -1;
	public String[] info = new String[4];
	
	public Attendees(String guestInfo) {
		
		info = guestInfo.split(",");
		
		ID = Integer.parseInt(info[0]);
		lastName = info[1];
		firstName = info[2];
		company = Integer.parseInt(info[3]);
	}

	public int getCompany() {
		return company;
	}

	public void setTable(int tableNum) {
		table = tableNum;
	}

	public int getTable() {
		return table;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public String toString() {
		return ID + lastName + firstName + company;
	}
}
