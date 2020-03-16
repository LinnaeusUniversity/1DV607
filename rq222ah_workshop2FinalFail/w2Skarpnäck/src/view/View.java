package view;

public class View implements IView {
	public int edit_name = 19;

	public void startPageHeader() {

		System.out.println("|=============== Home page ================|");
		System.out.println("| Select a number to get to the            |");
		System.out.println("| corresponding page.                      |");
		System.out.println("| 1. Add member(s)                         |");
		System.out.println("| 2. Retrieve member                       |");
		System.out.println("| 3. Show verbose list                     |");
		System.out.println("| 4. Show compact list                     |");
		System.out.println("| 5. Edit member                           |");
		System.out.println("| 6. Delete member                         |");
		System.out.println("| 7. Add boat                              |");
		System.out.println("| 8. Edit boat                             |");
		System.out.println("| 9. Delete boat                           |");
		System.out.println("|10. Save and Exit                         |");
		System.out.println("|==========================================|");
		System.out.print("Make a choice:");
	}

	public void addBoatHeader() {
		System.out.println("|========== Add boat to a member:  =========|");
		System.out.println("|             Enter member id               |");
		System.out.println("|===========================================|");

	}

	public void editBoatHeader() {
		System.out.println("|================ Edit boat ================|");
		System.out.println("|               Enter boat ID               |");
		System.out.println("|===========================================|");
	}

	public void deleteBoatHeader() {
		System.out.println("|========== Delete member:  =========|");
		System.out.println("|       Enter member Unique id       |");
		System.out.println("|====================================|");

	}

	public void memberInfoHeader(int id) {
		System.out.println("|========== Member info =========|");
		System.out.println("| Press ENTER to show member information,   |");
		System.out.println("|===========================================|");
	}

	public void addConfirmation(String in) {
		System.out.println(in + "added to registry");// **********
	}

	public void deleteConfirmation(String in) {
		System.out.println("Are you sure that you want to delete " + in + " Yes/No");
	}

	public void addBoatConfirmation(String in) {
		System.out.println(in + " cm) was added");
	}

	public void editBoatConfirmation(String in) {
		System.out.println("Are you sure you want to edit " + in + "cm)" + "? Yes/No");
	}

	public void editBoatMessage(String old, String changed) {
		System.out.println(old + " was changed to " + changed + "cm)");
	}

	public void deleteBoatConfiramtion(String in) {
		System.out.println("Are you sure you want to remove: " + in + "cm ? Yes/No");
	}

	public void errorMessage(String errorMesage) {
		System.err.println(errorMesage);
	}

	public void outputMessage(String str) {
		System.out.println(str);
			
	
	}
}