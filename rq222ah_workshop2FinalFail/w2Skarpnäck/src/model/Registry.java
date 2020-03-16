package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* Class that represents a member registry*/
public class Registry {
	private ArrayList<Member> members = new ArrayList<>();
	private String filepath = ("MemberData.txt");

	/* Method for adding a member */
	public Member addMember(String name,String personalNumber) {
		Member m = new Member(name, personalNumber);
		members.add(m);
		m.setId(generateId(m));
		return m;
	}

	/* Method for returning all of the members */
	public Iterator<Member> getMemberIterator() {
		return this.members.iterator();
	}

	// *****
	// test toString
	@Override
	public String toString() {
		System.out.println("test");
		return "Registry [members=" + members + "]";
	}

	// ****

	/* Method for generating a random member ID */
	private int generateId(Member m) {
		int id = (int) (Math.random() * 9000) + 1000;
		for (int i = 0; i < members.size(); i++) {
			if (m.getId() == id) {
				id = (int) (Math.random() * 9000) + 1000;
				i = 0;
			}
		}
		return id;
	}

	/* Method for deleting a member from the registry */
	public void deleteMember(Member m) {
		members.remove(m);
	}

	/* Method to get a member */
	public Member getMember(int id) {
		for (Member m : members) {
			if (id == m.getId()) {
				return m;
			}
		}
		return null;
	}

	/* Boolean method that checks if member exists */
	public boolean memberExists(int id) {
		for (Member m : members) {
			if (id == m.getId())
				return true;
		}
		return false;
	}

	/* Method for saving a registry to a text file */
	public void saveRegistry() {
		StringBuilder printer = new StringBuilder();
		Iterator<Member> memberIterator = this.getMemberIterator();
		Iterator<Boat> boatIterator;

		try {
			PrintWriter outputFile = new PrintWriter(new FileOutputStream(this.filepath, false));
			/*
			 * Loops through members and writes a number of % symbols, which is later used
			 * when loading specific info
			 */
			while (memberIterator.hasNext()) {
				Member member = memberIterator.next();
				printer.append(member.getName()).append("%").append(member.getPersonalNumber()).append("%%")
						.append(member.getId()).append("%%%");
				/* If member has boats */
				if (member.hasBoats()) {
					boatIterator = member.getBoatIterator();
					while (boatIterator.hasNext()) {
						Boat boat = boatIterator.next();
						printer.append(boat.getType()).append("%%%%").append(boat.getLength()).append("%%%%%");
					}
				}
				outputFile.println(printer);
				printer = new StringBuilder();
			}
			outputFile.close();
		} catch (IOException e1) {
			// System.err.println("Error in file path!");
		}
	}

	/* Method for loading a registry from a text file */
	public void loadRegistry(String filepath) throws FileNotFoundException {

		File file = new File(this.filepath);

		/* If file exists and if it ends with .txt */
		if (file.exists()) {
			FileInputStream fstream = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fstream));

			this.members.clear();
			String id = "";
			String name = "";
			String pNumber = "";
			String boatType = "";
			String strLine;
			char symbol = '%';
			try {
				while ((strLine = bufferedReader.readLine()) != null) {
					String reader = "";
					int counter = 0;
//					Member member;
					/* Loop that goes through the file */
					for (int i = 0; i < strLine.length() + 1; i++) {
						if (counter == 1) { // If there is 1 % symbol, set value to name
							name = reader;
							reader = "";
							counter = 0;
						} else if (counter == 2) { // If there are 2 % symbols, set value to pNumber
							pNumber = reader;
							reader = "";
							counter = 0;
						} else if (counter == 3) { // If there are 3 % symbols, create member and set value to id
						//	member = new Member(pNumber, name);
							Member newMember = this.addMember(name,pNumber);
							newMember.setId(Integer.valueOf(reader));
							id = reader;
							reader = "";
							counter = 0;
						} else if (counter == 4) { // If there are 4 % symbols, set value to boatType
							boatType = reader;
							reader = "";
							counter = 0;
						} else if (counter == 5) { // If there are 5 % symbols, create boat
							Boat boat = new Boat(Boat.Type.valueOf(boatType.toLowerCase()), Integer.valueOf(reader));
							this.getMember(Integer.valueOf(id)).addBoat(boat);
							reader = "";
							counter = 0;
						}
						String start = strLine + "       ";
						if (start.charAt(i) == symbol) {
							counter = 1;
							if (start.charAt(i + 1) == symbol) {
								counter = 2;
								i++;
								if (start.charAt(i + 1) == symbol) {
									counter = 3;
									i++;
									if (start.charAt(i + 1) == symbol) {
										counter = 4;
										i++;
										if (start.charAt(i + 1) == symbol) {
											counter = 5;
											i++;
										}
									}
								}
							}
						} else {
							if (i != strLine.length()) {
								reader += strLine.charAt(i);
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}