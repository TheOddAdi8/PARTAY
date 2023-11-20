import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Party {
	// ArrayList with attendee object
	
	public ArrayList<Attendees> people = new ArrayList<Attendees>();
	public Party() {
		try {
			File guests = new File("partyguests.txt");
			Scanner fileBot = new Scanner(guests);
			while (fileBot.hasNextLine()) {
				String data = fileBot.nextLine();
				people.add(new Attendees(data));
			}
			fileBot.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Uh oh, there was an error :(");
			e.printStackTrace();
		}
	}
}
