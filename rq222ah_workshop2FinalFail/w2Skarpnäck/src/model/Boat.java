package model;

import java.util.ArrayList;

/**
 * Class that represents a boat object
 */
public class Boat {

	@Override
	public String toString() {
		return "Boat [boatType=" + boatType + ", length=" + length + ", id=" + id + "]";
	}

	/** Fields */
	private Type boatType;
	private int length;
	private int id;

	/* Enum with all the different boat types */
	public enum Type {
		sailboat, motorsailer, canoe, other
	}

	/*
	 * Constructor for boat with parameters boatType Specifies the type of the boat
	 * length Specifies the length of the boat
	 */

	public Boat(Type boatType, int length) {
		this.boatType = boatType;
		this.length = length;
	}

	public Boat() {
	}

	/*
	 * Method for getting the type Returns the boat type
	 */
	public Type getType() {
		return boatType;
	}

	/* Method for setting a Specifies the ID */
	public void setId(int id) {
		this.id = id;
	}

	/* Method for getting the boat ID */
	public int getId() {
		return id;
	}

	/*
	 * Method for setting a Specific boat the type
	 */
	public void setType(Type bt) {
		this.boatType = bt;
	}

	/* Method for getting the boat length */
	public int getLength() {
		return length;
	}

	/* Method for setting a Specifies the boat length */
	public void setLength(int length) {
		this.length = length;
	}
}