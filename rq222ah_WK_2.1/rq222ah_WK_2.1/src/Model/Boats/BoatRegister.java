package Model.Boats;

import Model.LanguageType;

import java.util.ArrayList;

//List of boats owned by the shop  (inventory)
/*
 * Here i use few test boats
 * 4 kind of boat types, hard code attributes
 * The user must use these 4 hard coded boat types to register new boats*/
//boat automatically will fill as hard coded!
public class BoatRegister  //like a coke-automate
{
    private ArrayList<Boat> boats = new ArrayList<Boat>();

    public BoatRegister() {
        registerBoats();
    }

    //Boat Storage
    private void registerBoats() {
        Boat boat = new Boat("1", BoatType.MOTORSAILER, 25.0);
        boat.setCount(10);
        boats.add(boat);

        boat = new Boat("2", BoatType.KAJAK, 12.0);
        boat.setCount(6);
        boats.add(boat);

        boat = new Boat("3", BoatType.SAILER, 6);
        boat.setCount(5);
        boats.add(boat);

        boat = new Boat("4", BoatType.OTHER, 20);
        boat.setCount(15);
        boats.add(boat);
    }

    public String[] getBoatInfo(LanguageType lang) {
        int size = boats.size();
        String[] infoStrings = new String[size];

        for (int i = 0; i < size; i++) {
            infoStrings[i] = boats.get(i).toString(lang);
        }

        return infoStrings;
    }

    public Boat getBoat(String id) {
        boolean found = false;

        for (Boat b : boats)
            if (b.getBoatId().equals(id)) {
                return b;
            }

        return null;
    }


}
