package view;

import model.Boat;
import model.Member;
import model.Registry;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

/*
 * Regarding to the class and sequence diagrams, We have fixed according to the feedback.
 *Also we fixed the dependencies between classes specially in Console and in the View.
 *TO see what improvements we have done scroll all the way down to the page.

 * */
/**
 * Class that represents the console display
 */
public class Console {
	private Registry reg;
	private IView print;
	private Scanner scanner = new Scanner(System.in);
	Scanner scan = new Scanner(System.in);

	public void start(Registry registry, IView view) throws IOException {
		reg = registry;
		print = view;
		reg.loadRegistry("MemberData.txt");// loads pre-stored data
		startPage();
	}

	private int inputInteger() {
		return scanner.nextInt();
	}

	private String inputString() {
		return scanner.nextLine();
	}

	private Options readOption() {
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			return Options.ADD_MEMBER;
		case 2:
			return Options.RETRIEVE_MEMBER;
		case 3:
			return Options.SHOW_VERBOSE_LIST;
		case 4:
			return Options.SHOW_COMPACT_LIST;
		case 5:
			return Options.EDIT_MEMBER;
		case 6:
			return Options.DELETE_MEMBER;
		case 7:
			return Options.ADD_BOAT;
		case 8:
			return Options.EDIT_BOAT;
		case 9:
			return Options.DELETE_BOAT;
		case 10:
			return Options.EXIT;
		default:
			System.err.print("Invalid choice");
			return readOption();
		}
	}

//start page 
	private void startPage() throws IOException {
		boolean running = true;
		while (running) {
			print.startPageHeader();// prints Home page
			switch (readOption()) {
			case ADD_MEMBER:
				addMember();
				break;
			case RETRIEVE_MEMBER:
				retrieveMember();
				break;
			case SHOW_VERBOSE_LIST:
				displayVerboseList();
				break;
			case SHOW_COMPACT_LIST:
				displayCompactList();
				break;
			case EDIT_MEMBER:
				editMember();
				break;
			case DELETE_MEMBER:
				deleteMember();
				break;
			case DELETE_BOAT:
				deleteBoat();
				break;
			case ADD_BOAT:
				addBoat();
				break;
			case EDIT_BOAT:
				editBoat();
				break;
			case EXIT:
				running = false;
				break;
			default:
				//empty
			}
		}
		exit();
	}

	private Boat.Type readBoatType() {
		System.out.println("\n1=>Sailboat\n2=>Motorsailer\n3=>Canoe\n4=>Other ");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			return Boat.Type.sailboat;
		case 2:
			return Boat.Type.motorsailer;
		case 3:
			return Boat.Type.canoe;
		case 4:
			return Boat.Type.other;

		default:
			System.out.print("Invalid boat type");
			return readBoatType();
		}
	}

	// adding member method
	private void addMember() {
		System.out.println("|Add Member\n----------\nEnter name: ");
		String name = scan.nextLine();
		if (name.isEmpty() || name.matches(".*\\d+.*")) { // If name is empty or contains digits
			print.errorMessage("The name can't be empty or contain digits, please try again.\n");
			return;
		}
		System.out.print("Enter personal number: ");
		String pNumber = scan.nextLine();
		String formatPnumber = pNumber.replaceAll("-", "");
		if (!formatPnumber.matches("\\d+")) {
			print.errorMessage("The personal number can only contain digits and \"-\", please try again."); // If personal number contains letters or symbols
//			addMember();
			return;
		} else {

				Member newMember = reg.addMember(name, pNumber);// AddMember belongs to the registry class
				System.out.println("\n"+newMember.getName() + " (Id= " + newMember.getId() + " added to registry\n");
		}
	}

	// Method that displays the information of a specific member to the user

	private void retrieveMember() {
		System.out.print("\n|Member info|\n-------------\n|Enter Member ID: ");
		int id = inputInteger();
		if (reg.memberExists(id)) {
			printMember(reg.getMember(id));
		} else { // Prints error message if that member doesn't exist.
			print.errorMessage("A member with that ID doesn't exist, please try again.\n");
			retrieveMember();
		}
	}

	// Method for displaying the verbose list to the user
	private void displayVerboseList() throws IOException {
		System.out.println("\n|Show verbose list|\n-------------------");
		/* Table header */
		System.out.printf("%-5s %-22s %-20s %-10s\n", "ID", "Personal Number","Name",  "Boat information");
		/* Loops through the list of members and prints them */
		Iterator<Member> iterator = reg.getMemberIterator();
		while (iterator.hasNext()) {
			Member member = iterator.next();

			String name = member.getName();
			String pNumber = member.getPersonalNumber();
			/* If member has boats it prints them, otherwise skips printing them */
			Iterator<Boat> boatIterator = member.getBoatIterator();

			if (member.hasBoats()) {
				Boat boat = boatIterator.next();
				System.out.printf("%-5s %-22s %-20s %-10s\n", member.getId(), pNumber,name,
						boat.getId() + ". " + boat.getType() + ". " + boat.getLength() + "cm");

				while (boatIterator.hasNext()) {
					boat = boatIterator.next();
					System.out.printf("%-5s %-22s %-20s %-10s\n", "", "", "",
							boat.getId() + ". " + boat.getType() + ". " + boat.getLength() + "cm");

				}
			} else {
				System.out.printf("%-5s %-22s %-20s\n", member.getId(),name, pNumber );
			}
			/* Line separator between each member */
			System.out.printf("%-5s %-20s %-20s %-10s\n", "-----", "----------------------", "--------------------",
					"-------------------------");
		}
	}

	// Method for displaying the compact list to the user
	private void displayCompactList() throws IOException {

		System.out.println("\n|Show compact list|\n-------------------");
		System.out.printf("\n%-5s %-18s %-18s %-10s %-15s %10s \n", "ID", "Name", "Number of Boats,", "Boat ID",
				"Boat Type", "Boat Length\n");
		/* Loops through the list of members and prints them */
		Iterator<Member> iterator = reg.getMemberIterator();
		
		while (iterator.hasNext()) {
			Member member = iterator.next();
			System.out.printf("%-5s %-22s %-10s\n", member.getId(), member.getName(), member.countBoats());
			Iterator<Boat> b = member.getBoatIterator();
			while (b.hasNext()) {
				Boat boat = b.next();
				System.out.printf("%50s %15s %12s", boat.getId(), boat.getType(), boat.getLength()+" cm");
				System.out.println();
			}
			System.out.println();
			System.out.printf("%-5s %-20s %-20s %-10s\n", "----","------------------", " ---","--------------------------------");
		}
	}

	private void exit() throws IOException {
		reg.saveRegistry();
		print.outputMessage("Program safely Terminated....");
		System.exit(0);
	}

	/**
	 * Method that displays the options for editing a specific member to the user
	 */
	private void editMember() {
		System.out.println("|==== Edit member: ====|\n Enter Member Unique id: ");
		int id = inputInteger();
		if (!reg.memberExists(id)) {
			// ToDo: show an error
			print.errorMessage("A member with that ID doesn't exist, please try again.\n");
			return;
		}

		System.err.println("Enter member name: ");
		String name = inputString();
		while (name.isEmpty() || name.matches("\\d+")) {
			print.errorMessage("The name can't be empty or contain digits, please try again.");
			name = inputString();
		}
		reg.getMember(id).setName(name);

		System.err.println("Enter member personal number: ");
		String pNumber = inputString();
		while (!pNumber.matches("\\d+")) {
			print.errorMessage("The personal number can only contain digits and \"-\", please try again.");
			pNumber = inputString();
		}
		reg.getMember(id).setPersonalNumber(pNumber);
	}

	private void deleteMember() {
		print.deleteBoatHeader();

		int id = inputInteger();
		if (!reg.memberExists(id)) {
			print.errorMessage("A member with that ID doesn't exist, please try again.\n");
			return;
		}

		System.out.println(reg.getMember(id) + " is deleted");
		reg.deleteMember(reg.getMember(id));
	}

	private void addBoat() throws IOException {
		print.addBoatHeader();
		int id = inputInteger();
		if (!reg.memberExists(id)) {
			print.errorMessage("A member with that ID doesn't exist, please try again.\n");
			return;
		}

		Member member = reg.getMember(id);

		System.out.print("Enter boat type: ");
		Boat.Type boatType = readBoatType();
		System.out.print("\nEnter boat length'cm': ");
		int boatLength = inputInteger();
		Boat boat = new Boat(boatType, boatLength);
		member.addBoat(boat);

	}

	private void editBoat() {
		System.out.println("Enter member unique Id: ");
		int memberId = inputInteger();
		if (!reg.memberExists(memberId)) {
			print.errorMessage("A member with that ID doesn't exist, please try again.\n");
			return;
		}
		System.out.println("Enter Boat Id");
		int boatId = inputInteger();
		if (!reg.getMember(memberId).boatExists(boatId)) {
			print.errorMessage("A boat with that ID doesn't exist, please try again.\n");
			return;
		}
		System.out.print("Enter boat length: ");
		int boatLength = inputInteger();

		reg.getMember(memberId).getBoat(boatId).setLength(boatLength);
		System.out.print("Insert boat type: ");
		Boat.Type boatType = readBoatType();
		reg.getMember(memberId).getBoat(boatId).setType(boatType);

	}

	private void deleteBoat() {
		System.out.println("Enter member unique Id: ");
		int memberId = inputInteger();
		if (!reg.memberExists(memberId)) {
			print.errorMessage("A member with that ID doesn't exist, please try again.\n");
			return;
		}

		System.out.println("Enter Boat Id");
		int boatId = inputInteger();
		if (!reg.getMember(memberId).boatExists(boatId)) {
			print.errorMessage("A boat with that ID doesn't exist, please try again.\n");
			return;
		}
		System.out.println(reg.getMember(memberId).getBoat(boatId) + " has been deleted");
		reg.getMember(memberId).deleteBoat(reg.getMember(memberId).getBoat(boatId));

	}

	/**
	 * Method for printing a specific member info
	 */
	public void printMember(Member m) {
		int ID = m.getId();
		String name = m.getName();
		String pNumber = m.getPersonalNumber();
		int boatLength;
		Object boatType;
		String boatInfo;
		Iterator<Boat> boatIterator = m.getBoatIterator();
		/* Table header */
		System.out.printf("%-5s %-22s %-20s %-10s\n", "ID", "Name", "Personal Number", "Boat information");
		/* If member has boats it prints them, otherwise skips printing them */
		if (m.hasBoats()) {
			Boat boat = boatIterator.next();
			System.out.printf("%-5s %-22s %-20s %-10s\n", ID, name, pNumber,
					boat.getId() + ". " + boat.getType() + ". " + boat.getLength() + "cm");

			while (boatIterator.hasNext()) {
				boat = boatIterator.next();
				boatType = boat.getType();
				boatLength = boat.getLength();
				boatInfo = boat.getId() + ". " + boatType + ". " + boatLength + "cm";
				System.out.printf("%-5s %-22s %-20s %-10s\n", "", "", "", boatInfo);

			}
		} else {
			System.out.printf("%-5s %-22s %-20s\n", ID, name, pNumber);
		}
	}

}
//previews code below


//public class Console {
//	/** Fields */
//	public String name;
//	private String pNumber;
//	private int id = 0;
//	private String input;
//	private int boatId = 0;
//	private Registry reg;
//	private IView print;
//	private String confirm;
//	//private String boatType = "";
//	private String boatLength;
//	private String edit_Name="10";
//
//
//	public void start(Registry registry, IView view) throws IOException {
//
//		reg = registry;
//		print = view;
//		reg.loadRegistry("MemberData.txt");// loading Members' stored data
//		startPage();
//
//	}
//
//	/**
//	 * Method that displays the start page alternatives to the user
//	 */
//
//	@SuppressWarnings("resource")
//	public String inputString() {
//		return new Scanner(System.in).nextLine();
//	}
//
//	public void startPage() throws IOException {
//
//		print.startPageHeader();
//		//		public  Option2 op() {
//		//			switch (inputString()) {
//		//			case Option2.ADD_MEMBER.getVal():
//		//			ADD_MEMBER(1);
//		//			break;
//		//			}
//		//		
//		//		}
//		switch (menu()) {
//		case ADD_MEMBER:
//			// add Member Process
//			addingMember();
//			break;
//		case SELECT_MEMBER:
//			// read member process..
//			selcetingMember();
//			break;
//		case SHOW_VERBOSE_LIST:
//			verboseList();
//			break;
//		case SHOW_COMPACT_LIST:
//			compactList();
//			break;
//		case EXIT:
//			exit();
//			break;
//		default:
//			print.errorMessage(1);
//			startPage();
//		}
//	}
//
//	public Options editMemberOptions(String input2, String editName)
//	{
//		switch (input2) {
//		case "1":
//			return Options.EDIT_NAME;
//		case "2":
//			return Options.EDIT_PERSONALNUMBER;
//		default:
//			return Options.WRONG_KEY; 
//		}
//	}
//	public Options menu() {
//		switch (inputString()) {
//		case "1":
//			return Options.ADD_MEMBER;
//		case "2":
//			return Options.SELECT_MEMBER;
//		case "3":
//			return Options.SHOW_VERBOSE_LIST;
//		case "4":
//			return Options.SHOW_COMPACT_LIST;
//		case "5": 
//			return Options.WRONG_KEY;
//		case "6": 
//			return Options.WRONG_KEY;
//		case "7":
//			return Options.EXIT;
//		case "8":
//			return Options.SPECIFIC_MEMBER;
//		case "9":
//			return Options.EDIT_MEMBER;
//		case "10":
//			return Options.ADD_BOAT;
//		case "11":
//			return Options.EDIT_BOAT;
//		case "12":
//			return Options.DELETE_BOAT;
//		case "14":
//			return Options.DISPLAY_MEMBER_INFO;
//		default:
//			return Options.WRONG_KEY;
//		}
//	}
//
//	/**
//	 * Method that displays the add member function to the user
//	 */
//
//	public boolean checkRetrun(String userInput) {
//		if(userInput.equals("0")) {
//			return false;
//		}
//		return true;
//	}
//	private void addingMember() throws IOException {
//		print.addMemHeader();
//		if (checkRetrun(inputString() )) { // If scanner.nextLine() isn't 0
//			print.outputMessage(1);
//			name = inputString();
//			if (name.isEmpty() || name.matches(".*\\d+.*")) { // If name is empty or contains digits
//				print.errorMessage(2);
//				addingMember();
//			}
//			print.outputMessage(2);
//			pNumber = inputString();
//			String formatPnumber = pNumber.replaceAll("-", "");
//			if (!formatPnumber.matches("\\d+")) {
//				print.errorMessage(3); // If personal number contains letters or symbols
//				addingMember();
//			} else {
//				print.outputMessage(3);
//				confirm = inputString();
//				if (confirm.toLowerCase().equals("yes")) { // If input equals yes
//					Member m = new Member(pNumber, name);
//					reg.addMember(m);
//					String memberinfo = m.getName() + "(Id=" + m.getId() + ") ";
//					print.addConfirmation(memberinfo);
//					startPage();
//				} else if (confirm.toLowerCase().equals("no")) { // If input equals no
//					print.outputMessage(4);
//					addingMember();
//				} else { // Prints error message if something other than yes or no is inputted
//					print.errorMessage(4);
//					addingMember();
//				}
//			}
//		} else { // If 0, rerun start
//			startPage();
//		}
//	}
//
//
//	/**
//	 * Method that displays the select member function to the user
//	 */
//	private void selcetingMember() throws IOException {
//		print.selectMemHeader();
//		if (checkRetrun(inputString() )) { // If scanner.nextLine() isn't 0
//			print.outputMessage(5);
//			String inputID = inputString();
//			if (inputID.matches("\\d+")) { // If the input contains digits
//				id = Integer.valueOf(inputID);
//				if (reg.memberExists(id)) { // If member exists
//					specificMember();
//				} else { // If member doesn't exist, print error message and return to page 2
//					print.errorMessage(5);
//					selcetingMember();
//				}
//			} else { // Prints error message if id contains anything other than digits
//				print.errorMessage(7);
//				selcetingMember();
//			}
//		} else { // Rerun start method
//			startPage();
//		}
//	}
//
//	/**
//	 * Method for displaying the verbose list to the user
//	 */
//	private void verboseList() throws IOException {
//		this.printVerboseList(reg.getMemberIterator());
//		startPage();
//	}
//
//	/**
//	 * Method for displaying the compact list to the user
//	 */
//	private void compactList() throws IOException {
//		print.compactListHeader();
//		if (checkRetrun(inputString() )) { /* User didnt press return. Showing compact list */
//			this.printCompactList(reg.getMemberIterator());
//			print.outputMessage(6);
//			inputString();
//			startPage();
//		}
//	}
//
//	/**
//	 * Method displaying the function to save a registry
//	 */
//	private void savingRegistry() throws IOException {
//
//		String filepath = ("MemberData.txt");
//		reg.saveRegistry(filepath);
//	}
//
//	/**
//	 * Method that displays the option to exit the software to the user
//	 */
//	private void exit() throws IOException {
//		print.outputMessage(9);
//		confirm = inputString();
//		if (confirm.toLowerCase().equals("yes")) { /* User wants to exit program */
//			savingRegistry();
//			print.outputMessage(10);
//			System.exit(0);
//		} else if (confirm.toLowerCase().equals("no")) { /* User didn't want to exit program. return to start page */
//			startPage();
//		} else { /* User typed wrong, yes/no */
//			print.errorMessage(4);
//			exit();
//		}
//	}
//
//	/**
//	 * Method that displays the options for a specific member to the user
//	 */
//	public void specificMember() throws IOException {/* Select member */
//		if (reg.memberExists(id)) {/* Checking if user exist */
//			print.memberMenuHeader(id);
//			input = inputString();
//			while (!input.equals("0")) { /* User didn't press return */
//				if (input.equals("13")) { /* User wants to delete member */
//					String info = reg.getMember(id).getName() + "(" + reg.getMember(id).getId() + ") ";
//					print.deleteConfirmation(info);/* Asking user if he wants to delete member */
//					confirm = inputString();
//					if (confirm.toLowerCase().equals("yes")) { /* User types yes, delete member */
//						reg.deleteMember(reg.getMember(id));
//						print.outputMessage(11);
//						startPage();
//					} else if (confirm.toLowerCase().equals("no")) { /* User types no, return to page 8 */
//						print.outputMessage(12);
//						specificMember();
//					} else { /* User didnt write yes or no, go back to delete member and ask again */
//						print.errorMessage(4);
//						input = "13";
//					}
//				} else if (input.matches("\\d+") && (Integer.valueOf(input) <= 14
//						&& Integer.valueOf(input) >= 9)) {/* User wants to go to another page */
//					/* change page */
//					BoatConsole boat = new BoatConsole();
//					switch (input) {
//					case "9":
//						editSpecificMember();
//						break;
//					case "10":
//						boat.addingBoat(reg.getMember(id).getId(), reg, print, this, boatLength, boatLength, boatLength);
//						break;
//					case "11":
//						boat.editSpecificBoat(reg.getMember(id).getId(), reg, print, this, boatLength, boatLength, boatLength);
//						break;
//					case "12":
//						deleteSpecificBoat();
//						break;
//					case "14":
//						infoOfSpecificMember();
//						break;
//					}
//
//				} else {
//					print.errorMessage(
//							1);/*
//							 * User pressed wrong button or a page that dosent exist, return to page 8 again
//							 */
//					specificMember();
//				}
//			}
//			startPage();
//		} else {/* User dosent exist, return to page 2 and ask user to write a new id */
//			print.errorMessage(5);
//			selcetingMember();
//		}
//	}
//
//	/**
//	 * Method that displays the options for editing a specific member to the user
//	 */
//	private void editSpecificMember() throws IOException {/* Edit member */
//		print.editMemberHeader(id);
//		input = inputString();
//		if (!input.equals("0")) {/* User didnt press return */
//			switch (editMemberOptions(input, edit_Name)) {
//			case EDIT_NAME: /* User pressed edit name */
//				print.outputMessage(1);
//				name = inputString();
//				if (name.isEmpty() || name.matches("\\d+")) { /*
//				 * Checking if name is incorrect, empty or digits.
//				 * Return to edit member and ask again.
//				 */
//					print.errorMessage(2);
//					editSpecificMember();
//				} else { /* If name was correct, change name */
//					reg.getMember(id).setName(name);
//					editSpecificMember();
//				}
//				break;
//			case EDIT_PERSONALNUMBER: /* User pressed edit personal number */
//				print.outputMessage(2);
//				pNumber = inputString();
//				if (!pNumber.matches("\\d+")) { /*
//				 * Checking if pNumber is incorrect, not digits. Return to edit member
//				 * and ask again.
//				 */
//					print.errorMessage(3);
//					editSpecificMember();
//				} else { /* If pNumber was correct, change pNumber */
//					reg.getMember(id).setPersonalNumber(pNumber);
//					editSpecificMember();
//				}
//				break;
//			case WRONG_KEY:/*
//			 * If user didnt press any of the keys listed in the menu, return to edit member
//			 * again and ask for right input
//			 */
//				print.errorMessage(1);
//				editSpecificMember();
//				break;
//				
//			case ADD_BOAT:
//				break;
//			case ADD_MEMBER:
//				break;
//			case DELETE_BOAT:
//				break;
//			case DISPLAY_MEMBER_INFO:
//				break;
//			case EDIT_BOAT:
//				break;
//			case EDIT_MEMBER:
//				break;
//			case EXIT:
//				break;
//			case RETURN:
//				break;
//			case SELECT_MEMBER:
//				break;
//			case SHOW_COMPACT_LIST:
//				break;
//			case SHOW_VERBOSE_LIST:
//				break;
//			case SPECIFIC_MEMBER:
//				break;
//			default:
//				break;
//			}
//		} else { /* User pressed return, return to page 8(select member) */
//			specificMember();
//		}
//	}
//	/**
//	 * Method that displays the function for deleting a specific boat
//	 */
//	private void deleteSpecificBoat() throws IOException {
//		print.deleteBoatHeader();
//		if (checkRetrun(inputString() )) { /* User didnt press return */
//			print.outputMessage(5);
//			String tempBoatID = inputString();
//			if (tempBoatID.matches("\\d+")) { /* Checks if boatid was correct, only digits */
//				boatId = Integer.valueOf(tempBoatID);
//				if (reg.getMember(id).boatExists(boatId)) { /* Checking if boat with that id exist */
//					String info = +boatId + ". " + reg.getMember(id).getBoat(boatId).getType() + " "
//							+ reg.getMember(id).getBoat(boatId).getLength();
//					print.deleteBoatConfiramtion(info);
//					confirm = inputString();
//					if (confirm.toLowerCase()
//							.equals("yes")) { /* User wants to remove boat. removing boat and return to page 8 */
//						reg.getMember(id).deleteBoat(reg.getMember(id).getBoat(boatId));
//						specificMember();
//					} else if (confirm.toLowerCase()
//							.equals("no")) { /* User didnt't want to remove boat. return to page 8 */
//						print.outputMessage(18);
//						specificMember();
//					} else { /* User typed wrong, yes/no */
//						print.errorMessage(4);
//						deleteSpecificBoat();
//					}
//				} else { /* Wrong id, no boat with that id */
//					print.errorMessage(6);
//					deleteSpecificBoat();
//				}
//
//			} else { /* Boatid typed incorrect, not digits */
//				print.errorMessage(7);
//				deleteSpecificBoat();
//			}
//		} else { /* User pressed return. return to page 8 */
//			specificMember();
//		}
//	}
//
//	/**
//	 * Method that displays the information of a specific member to the user
//	 */
//	private void infoOfSpecificMember() throws IOException {
//		print.memberInfoHeader(id);
//		if (checkRetrun(inputString() )) { /* User didnt press return. showing information */
//			this.printMember(reg.getMember(id));
//			print.outputMessage(19);
//			inputString();
//			specificMember();
//		}
//	}
//
//	/**
//	 * Method for printing a verbose list
//	 */
//	public void printVerboseList(Iterator<Member> memberIterator) {
//		int ID;
//		String name;
//		String pNumber;
//		int boatLength;
//		Object boatType;
//		String boatInfo;
//		/* Table header */
//		System.out.printf("%-5s %-22s %-20s %-10s\n", "ID", "Name", "Personal Number", "Boat information");
//		/* Loops through the list of members and prints them */
//		while (memberIterator.hasNext()) {
//			Member member = memberIterator.next();
//			Iterator<Boat> boatIterator = member.getBoatIterator();
//			ID = member.getId();
//			name = member.getName();
//			pNumber = member.getPersonalNumber();
//			/* If member has boats it prints them, otherwise skips printing them */
//			if (member.hasBoats()) {
//				Boat boat = boatIterator.next();
//				System.out.printf("%-5s %-22s %-20s %-10s\n", ID, name, pNumber,
//						boat.getId() + ". " + boat.getType() + ". " + boat.getLength() + "cm");
//
//				while (boatIterator.hasNext()) {
//					boat = boatIterator.next();
//					boatType = boat.getType();
//					boatLength = boat.getLength();
//					boatInfo = boat.getId() + ". " + boatType + ". " + boatLength + "cm";
//					System.out.printf("%-5s %-22s %-20s %-10s\n", "", "", "", boatInfo);
//
//				}
//			} else {
//				System.out.printf("%-5s %-22s %-20s\n", ID, name, pNumber);
//			}
//			/* Line separator between each member */
//			System.out.printf("%-5s %-20s %-20s %-10s\n", "-----", "----------------------", "--------------------",
//					"-------------------------");
//		}
//	}
//
//	/**
//	 * Method for printing a compact list
//	 */
//	public void printCompactList(Iterator<Member> memberIterator) {
//		int ID;
//		String name;
//		int numberOfBoats;
//		boolean checkForOne = false;
//		/* Table header */
//		System.out.printf("%-5s %-20s %-10s %-10s %-15s %10s \n", "ID", "Name"
//				,"Number of Boats,","Boat ID","Boat Type", "Boat Length");
//		/* Loops through the list of members and prints them */
//		while (memberIterator.hasNext()) {
//			Member member = memberIterator.next();
//
//			ID = member.getId();
//			name = member.getName();
//			numberOfBoats = member.countBoats();
//			System.out.printf("%-5s %-20s %-10s"
//					, ID, name, numberOfBoats
//					);
//			Iterator<Boat> b= member.getBoatIterator();
//			if(numberOfBoats > 1)
//				checkForOne = true;
//			while(b.hasNext())
//			{
//				Boat boat = b.next();
//				if(checkForOne) {
//					System.out.printf("%13s %15s %10s"
//							, boat.getId(), boat.getType(), boat.getLength());
//					System.out.println();
//					checkForOne = false;
//					continue;
//				}
//				System.out.printf("%50s %15s %10s"
//						, boat.getId(), boat.getType(), boat.getLength());
//				System.out.println();
//			}
//			System.out.println();
//		}
//	}
//
//	/**
//	 * Method for printing a specific member info
//	 */
//	public void printMember(Member m) {
//		int ID = m.getId();
//		String name = m.getName();
//		String pNumber = m.getPersonalNumber();
//		int boatLength;
//		Object boatType;
//		String boatInfo;
//		Iterator<Boat> boatIterator = m.getBoatIterator();
//		/* Table header */
//		System.out.printf("%-5s %-22s %-20s %-10s\n", "ID", "Name", "Personal Number", "Boat information");
//		/* If member has boats it prints them, otherwise skips printing them */
//		if (m.hasBoats()) {
//			Boat boat = boatIterator.next();
//			System.out.printf("%-5s %-22s %-20s %-10s\n", ID, name, pNumber,
//					boat.getId() + ". " + boat.getType() + ". " + boat.getLength() + "cm");
//
//			while (boatIterator.hasNext()) {
//				boat = boatIterator.next();
//				boatType = boat.getType();
//				boatLength = boat.getLength();
//				boatInfo = boat.getId() + ". " + boatType + ". " + boatLength + "cm";
//				System.out.printf("%-5s %-22s %-20s %-10s\n", "", "", "", boatInfo);
//
//			}
//		} else {
//			System.out.printf("%-5s %-22s %-20s\n", ID, name, pNumber);
//		}
//	}
//}
//
//
//


