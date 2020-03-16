package Model;

import Model.Boats.Boat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
    private int id;
    private String personalNo;
    private String name;

    private List<Boat> boats;

    public Member() {
        boats = new ArrayList<Boat>();  //boats that the member has
    }

    public Member(int id, String name, String personalNo, Boat boat) {
        this.name = name;
        this.personalNo = personalNo;
        this.id = id;
        if (boat != null)
            boats.add(boat);
        else
            boats.add(new Boat());
    }

    //Member constructor
    public Member(int id, String name, String personalNo) {
        this(id, name, personalNo, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Boat> getBoats() {
        return boats;
    }

    public void addBoat(Boat boat) {
        if (boat != null)
            this.boats.add(boat);
    }

    public Boat removeBoat(String boatId) {
        if (ifboatExist(boatId)) {
            for (int i = 0; i < boats.size(); i++) {
                if (boatId.equals(boats.get(i).getBoatId())) {
                    return boats.remove(i);
                }
            }
        }
        return null;
    }

    public Boat updateBoat(String boatId) {
        if (ifboatExist(boatId)) {
            for (int i = 0; i < boats.size(); i++) {
                if (boatId.equals(boats.get(i).getBoatId())) {
                    return boats.get(i);
                }
            }
        }
        return null;
    }

    public boolean ifboatExist(String boatId) {

        for (Boat b : boats) {
            if (boatId.equals(b.getBoatId())) {
                return true;
            }
        }
        return false;
    }

    public Iterator<Boat> getBoatIterator() {
        return this.boats.iterator();
    }

    public boolean hasBoats() {
        if (boats.isEmpty()) {
            return false;
        }
        return true;
    }

    public int countBoats() {
        return boats.size();
    }

    public String getPersonalNo() {
        return personalNo;
    }

    public void setPersonalNo(String personalNo) {
        this.personalNo = personalNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
