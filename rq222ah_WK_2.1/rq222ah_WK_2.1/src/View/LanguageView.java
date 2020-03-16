package View;

import Controller.Controller;
import Model.LanguageType;
import Model.Utilities;

import java.util.HashMap;

public class LanguageView {
    private Controller controller;
    private LanguageType languageType = LanguageType.ENGLISH;

    private HashMap<LangStringID, String> langStrings = new HashMap<LangStringID, String>();  //id, value

    //Constructor
    public LanguageView(Controller controller) {
        this.controller = controller;
        setLanguageChoice();  //show menu
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public HashMap getLanguageStrings() {
        return langStrings;
    }

    private void setLanguageChoice() {
        Utilities.padChars('+', 40);
        System.out.println("Please select language");
        System.out.println("1 " + LanguageType.ENGLISH + "\n" + "2 " + LanguageType.SVENSKA + "\nOther language Not implemented yet");

        Utilities.padChars('+', 40);
        int choice = Utilities.getInteger(1, 2, languageType.ENGLISH);
        languageType = LanguageType.values()[choice - 1];
        loadLanguageString();
    }

    public HashMap<LangStringID, String> loadLanguageString() {
        switch (languageType) {
            case SVENSKA:
                loadSwedishStrings();
                break;
            case ENGLISH:
            default:
                loadEnglishStrings();
                break;
        }
        return langStrings;
    }

    private void loadSwedishStrings() {
        langStrings.put(LangStringID.MEM_MAIN_MENU, "Huvudmeny ");
        langStrings.put(LangStringID.MEM_NAME, "Ange ditt namn ");
        langStrings.put(LangStringID.MEM_PERSNO, "Ange ditt personnummer ");
        langStrings.put(LangStringID.MEMBERUNIQID, "Ange kund nummber 4-siffror");
        langStrings.put(LangStringID.ADD_MEM, "Lägg till medlem ");
        langStrings.put(LangStringID.RETRIEVE_MEM, "Hämta medlem ");
        langStrings.put(LangStringID.UPDATE_MEM, "Uppdatera medlem ");
        langStrings.put(LangStringID.REMOVE_MEM, "Ta bort medlemmen ");
        langStrings.put(LangStringID.ADD_BOAT, "Lägg till båt ");
        langStrings.put(LangStringID.CHANGE_CURRENT_LANGUAGE, "Ändra språket");
        langStrings.put(LangStringID.UPDATE_BOAT_INFO, "Uppdatera båten ");
        langStrings.put(LangStringID.DELETE_BOAT, "Radera båt ");
        langStrings.put(LangStringID.VERBOSE_LIST, "Ordlista ");
        langStrings.put(LangStringID.COMPACT_LIST, "Kompackt lista ");
        langStrings.put(LangStringID.NAME, "Namn: ");
        langStrings.put(LangStringID.PERSONAL_NUMBER, "Person nummer: ");
        langStrings.put(LangStringID.UNIQUE_ID, "Kund nummer: ");
        langStrings.put(LangStringID.SAVE_AND_EXIT_, "Spara och Exit ");
        langStrings.put(LangStringID.REMOVED, " Tog bort ");
        langStrings.put(LangStringID.DISPLAY_MEMBERS, "Visa all kunder ");
        langStrings.put(LangStringID.NUMBER_OF_BOATS, "Antal båtar ");


    }

    private void loadEnglishStrings() {
        langStrings.put(LangStringID.MEM_MAIN_MENU, "Main Menu ");
        langStrings.put(LangStringID.MEM_NAME, "Enter your name ");
        langStrings.put(LangStringID.MEM_PERSNO, "Enter personal number ");
        langStrings.put(LangStringID.MEMBERUNIQID, "Enter member Unique id ");
        langStrings.put(LangStringID.ADD_MEM, "Add a member ");
        langStrings.put(LangStringID.RETRIEVE_MEM, "Retrieve member ");
        langStrings.put(LangStringID.UPDATE_MEM, "Update member details ");
        langStrings.put(LangStringID.REMOVE_MEM, "Remove member ");
        langStrings.put(LangStringID.CHANGE_CURRENT_LANGUAGE, "Change Language");
        langStrings.put(LangStringID.ADD_BOAT, "Add boat :");
        langStrings.put(LangStringID.UPDATE_BOAT_INFO, "Update boat details ");
        langStrings.put(LangStringID.DELETE_BOAT, "Delete boat ");
        langStrings.put(LangStringID.VERBOSE_LIST, "Verbose list ");
        langStrings.put(LangStringID.COMPACT_LIST, "Compact list ");
        langStrings.put(LangStringID.SAVE_AND_EXIT_, "Save and exit ");
        langStrings.put(LangStringID.REMOVED, " Removed ");
        langStrings.put(LangStringID.NUMBER_OF_BOATS, "Number of boats");
        langStrings.put(LangStringID.DISPLAY_MEMBERS, "Display members ");
        langStrings.put(LangStringID.NAME, "Name: ");
        langStrings.put(LangStringID.PERSONAL_NUMBER, "Personal number: ");
        langStrings.put(LangStringID.UNIQUE_ID, "Member Unique id: ");
    }
}
