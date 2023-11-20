public class Attendees {
	private int ID;
	private String strID;
	private String lastName;
	private String firstName;
	private int company;
	public String[] info = new String[4];
	
	public Attendees(String guestInfo) {
		
		info = guestInfo.split(",");
		
		ID = Integer.parseInt(info[0]);
		lastName = info[1];
		firstName = info[2];
		company = Integer.parseInt(info[3]);
	}
	public String toString() {
		return ID + lastName + firstName + company;
	}
}
