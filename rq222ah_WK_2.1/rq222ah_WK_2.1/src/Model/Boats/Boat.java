package Model.Boats;

import Model.LanguageType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Boat {
    private BoatType boatType;
    private double length;
    private String boatId;
    private int count;  //current no available
    public int getCount() {
        return count;
    }

    public void setCount(int boatsAvailable) {
        this.count = boatsAvailable;
    }
    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }


    public Boat() {
    }

    //Boat Constructor
    public Boat(String boatId, BoatType boatType, double length) {
        this.boatType = boatType;
        this.length = length;
        this.boatId = boatId;
    }

    public BoatType getBoatType() {
        return boatType;
    }

    public void setBoatType(BoatType boatType) {
        this.boatType = boatType;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getBoatId() {
        return boatId;
    }

    public void setBoatId(String boatId) {
        this.boatId = boatId;
    }


    public String toString(LanguageType lang) {
        String[] langStrings = BoatType.getNames(lang);
        int index = boatType.ordinal();

        return "Boat ID: " + boatId + " " + langStrings[index] + " Length = " + length;
    }

}
