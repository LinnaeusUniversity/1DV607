package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The type Member.
 */
/* Class that represents a member object */
public class Member {

	/** Fields */
	private String pNumber;
	private String name;
	private int id;
	private ArrayList<Boat> boats = new ArrayList<>();

	/**
	 * Constructor for member with parameters number Specifies the personal number
	 * for the member mName Specifies the name of the member
	 *
	 * @param mName  the m name
	 * @param number the number
	 */
	public Member(String mName, String number) {
		pNumber = number;
		name = mName;
	}

	/**
	 * Instantiates a new Member.
	 */
	/* Empty constructor for member */
	public Member() {
	}

	/**
	 * Sets name.
	 *
	 * @param inputName the input name
	 */
	public void setName(String inputName) {
		name = inputName;
	}

	/**
	 * Sets personal number.
	 *
	 * @param inputPnumber the input pnumber
	 */
	public void setPersonalNumber(String inputPnumber) {
		pNumber = inputPnumber;
	}

	/**
	 * Add boat.
	 *
	 * @param boat the boat
	 */
	public void addBoat(Boat boat) {
		boats.add(boat);
		boat.setId(generateId(boat));
	}

	/**
	 * Delete boat.
	 *
	 * @param boat the boat
	 */
	public void deleteBoat(Boat boat) {
		boats.remove(boat);
	}

	/**
	 * Gets boat.
	 *
	 * @param id the id
	 * @return the boat
	 */
	public Boat getBoat(int id) {
		for (Boat boat : boats) {
			if (id == boat.getId()) {
				return boat;
			}
		}
		return null;
	}

	/* Method for generating boat id */
	private int generateId(Boat boat) {
		int id = (int) (Math.random() * 90) + 10;
		for (int i = 0; i < boats.size(); i++) {
			if (boat.getId() == id) {
				id = (int) (Math.random() * 90) + 10;
				i = 0;
			}
		}
		return id;
	}

	/**
	 * Method for getting a list of boats for a specific member  @return the boat iterator
	 */
	public Iterator<Boat> getBoatIterator() {
		return this.boats.iterator();
	}

	/**
	 * Boat exists boolean.
	 *
	 * @param id the id
	 * @return the boolean
	 */
	/*
	 * Method to check if a specific boat exists id Specifies the boat id Return
	 * true if boat exists, otherwise return false
	 */
	public boolean boatExists(int id) {
		for (Boat boat : boats) {
			if (id == boat.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Has boats boolean.
	 *
	 * @return the boolean
	 */
	public boolean hasBoats() {
		if (boats.isEmpty()) {
			return false;
		}
		return true;
	}

	/* Method for counting boats for a specific member */

	/**
	 * Count boats int.
	 *
	 * @return the int
	 */
	public int countBoats() {
		return boats.size();
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	/*
	 * Method for getting a specific members name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets personal number.
	 *
	 * @return the personal number
	 */
	/* Method for getting a specific members personal number */
	public String getPersonalNumber() {
		return pNumber;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	/* Method for getting a specific members id */
	public int getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	/* Method for setting a Specifies id for a member */
	void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Member [Personal Number=" + pNumber + ", name=" + name + ", id=" + id + ", boats=" + boats + "]";
	}
}