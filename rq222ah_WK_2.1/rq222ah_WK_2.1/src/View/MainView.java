package View;

import Controller.Controller;
import Model.Boats.Boat;
import Model.LanguageType;
import Model.Member;
import Model.MemberRegister;
import Model.Utilities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MainView {
    private LanguageView langView;
    private Controller controller;
    private LanguageType languageType;
    private HashMap<LangStringID, String> langStrings;

    public MainView(Controller controller) {
        this.controller = controller;
        showLanguageView();
    }

    public void showLanguageView() {
        langView = new LanguageView(controller);  //shows menu and loads strings
        languageType = langView.getLanguageType();
        langStrings = langView.getLanguageStrings();
    }

    public void displayMembers(MemberRegister register) {

        for (int i = 0; i < register.getMembers().size(); i++) {
            System.out.printf("'%s' '%20s' '%20s' %n", langStrings.get(LangStringID.NAME) + register.getMembers().get(i).getName(),
                    langStrings.get(LangStringID.PERSONAL_NUMBER) + register.getMembers().get(i).getPersonalNo()
                    , langStrings.get(LangStringID.UNIQUE_ID) + register.getMembers().get(i).getId());
        }
    }

    //this function confirms that member successfully removed from the list
    public void displayAMember(MemberRegister register) {
        //  getLanguageType();
        for (int i = register.getMembers().size() - 1; i < register.getMembers().size(); i++) {
            System.out.printf("'%s' '%20s' '%20s' '%10s' %n", langStrings.get(LangStringID.NAME) + register.getMembers().get(i).getName(),
                    langStrings.get(LangStringID.PERSONAL_NUMBER) + register.getMembers().get(i).getPersonalNo()
                    , langStrings.get(LangStringID.UNIQUE_ID) + register.getMembers().get(i).getId(),
                    langStrings.get(LangStringID.REMOVED));
        }

    }

    public void verboseList(MemberRegister register) {
        int i = register.getMembers().size();
        System.out.println(i);
        if (i == 0) {
            i++;
        }
//this if condition prevents to print the members twice. it works if there is only member
//there for i added this extra line of code
        for (i = 1; i < register.getMembers().size(); i++) {
            System.out.printf("%-5s  %-22s %-20s  \n", langStrings.get(LangStringID.UNIQUE_ID)
                    , langStrings.get(LangStringID.NAME), langStrings.get(LangStringID.PERSONAL_NUMBER));
            Iterator<Member> iterator = register.getMemberIterator();

            if (register.getMembers().size() == 0) {

            }
            while (iterator.hasNext()) {
                Member member = iterator.next();
                String name = member.getName();
                String pNo = member.getPersonalNo();
                Iterator<Boat> boatIterator = member.getBoatIterator();
                if (member.hasBoats()) {
                    Boat boat = boatIterator.next();
                    System.out.printf("%8s %15s %30s %50s\n", member.getId(), name, pNo,
                            boat.getBoatId() + ". " + boat.getBoatType() + ". " + boat.getLength() + "cm");
                    while (boatIterator.hasNext()) {
                        boat = boatIterator.next();
                        System.out.printf("%-5s %-22s %-20s %-10s\n", "", "", "",
                                boat.getBoatId() + ". " + boat.getBoatType() + ". " + boat.getLength() + "cm");
                    }
                } else {
                    System.out.printf("%8s %15s %30s\n", member.getId(), name, pNo);
                }
                /* Line separator between each member */
                System.out.printf("%-5s %-20s %-20s %-10s\n", "    -----", "----------------------", "--------------------",
                        "-------------------------");
            }
        }
    }

    public void displayCompactList(MemberRegister register) {
        System.out.printf("%-5s  %-22s %-15s  \n", langStrings.get(LangStringID.UNIQUE_ID)
                , langStrings.get(LangStringID.NAME), langStrings.get(LangStringID.NUMBER_OF_BOATS));
        Iterator<Member> iterator = register.getMemberIterator();
        while (iterator.hasNext()) {
            Member member = iterator.next();
            System.out.printf("%8s %15s %25s\n", member.getId(), member.getName(), member.countBoats());
            Iterator<Boat> b = member.getBoatIterator();
            while (b.hasNext()) {
                Boat boat = b.next();
                System.out.printf("%50s %15s %12s", boat.getBoatId(), boat.getBoatType(), boat.getLength() + " cm");
                System.out.println();
            }
            System.out.println();
            System.out.printf("%-5s %-20s %-20s %-10s\n", "    ----", "       ------------------", "             ---", "--------------------------------");
        }
    }

    public void displayBoats(String[] infoStrings) {
        for (int i = 0; i < infoStrings.length; i++)
            System.out.println(infoStrings[i]);

    }

    public String readMemberName() {

        Utilities.padChars('*', 40);
        System.out.println(langStrings.get(LangStringID.MEM_NAME));
        String str = Utilities.getString(languageType);
        return str;
    }

    public String readMemberPersonNr() {
        Utilities.padChars('*', 40);
        System.out.println(langStrings.get(LangStringID.MEM_PERSNO));
        String str = Utilities.getString(languageType);
        return str;
    }

    public String readMemberUnId() {
        Utilities.padChars('*', 30);
        System.out.println(langStrings.get(LangStringID.MEMBERUNIQID));
        String str = Utilities.getString(languageType);

        return str;
    }

    public int showMainMenuSe() {
        Utilities.padChars('+', 30);
        System.out.println("  " + langStrings.get(LangStringID.MEM_MAIN_MENU));
        System.out.println('a' + " " + langStrings.get(LangStringID.ADD_MEM)); //Add member");
        System.out.println('b' + " " + langStrings.get(LangStringID.RETRIEVE_MEM));
        System.out.println('c' + " " + langStrings.get(LangStringID.DISPLAY_MEMBERS));
        System.out.println('d' + " " + langStrings.get(LangStringID.UPDATE_MEM));
        System.out.println('e' + " " + langStrings.get(LangStringID.REMOVE_MEM));

        System.out.println('f' + " " + langStrings.get(LangStringID.CHANGE_CURRENT_LANGUAGE));

        System.out.println('g' + " " + langStrings.get(LangStringID.ADD_BOAT));
        System.out.println('h' + " " + langStrings.get(LangStringID.UPDATE_BOAT_INFO));
        System.out.println('i' + " " + langStrings.get(LangStringID.DELETE_BOAT));
        System.out.println('j' + " " + langStrings.get(LangStringID.VERBOSE_LIST));
        System.out.println('k' + " " + langStrings.get(LangStringID.COMPACT_LIST));
        System.out.println('l' + " " + langStrings.get(LangStringID.SAVE_AND_EXIT_));
        Scanner scanner = new Scanner(System.in);

        char userInputChar = scanner.next().toLowerCase().charAt(0);
        int choice = Character.getNumericValue(userInputChar) - 9;
        if (choice == 12) {
            choice = 0;
        }
        // int choice = Utilities.getInteger(0, 11, languageType);
        switch (choice) {
            case 0:

                break;
            case 6:
                showLanguageView();
                //   showMainMenu();
                break;
        }
        return choice;
    }


    public int showMainMenu() {
        Utilities.padChars('+', 30);
        System.out.println("  " + langStrings.get(LangStringID.MEM_MAIN_MENU));
        System.out.println("  1. " + langStrings.get(LangStringID.ADD_MEM)); //Add member");
        System.out.println("  2. " + langStrings.get(LangStringID.RETRIEVE_MEM));
        System.out.println("  3. " + langStrings.get(LangStringID.DISPLAY_MEMBERS));
        System.out.println("  4. " + langStrings.get(LangStringID.UPDATE_MEM));
        System.out.println("  5. " + langStrings.get(LangStringID.REMOVE_MEM));

        System.out.println("  6. " + langStrings.get(LangStringID.CHANGE_CURRENT_LANGUAGE));

        System.out.println("  7. " + langStrings.get(LangStringID.ADD_BOAT));
        System.out.println("  8. " + langStrings.get(LangStringID.UPDATE_BOAT_INFO));
        System.out.println("  9. " + langStrings.get(LangStringID.DELETE_BOAT));
        System.out.println("  10. " + langStrings.get(LangStringID.VERBOSE_LIST));
        System.out.println("  11. " + langStrings.get(LangStringID.COMPACT_LIST));
        System.out.println("  0. " + langStrings.get(LangStringID.SAVE_AND_EXIT_));
        int choice = Utilities.getInteger(0, 11, languageType);
        switch (choice) {
            case 0:
                break;
            case 6:
                showLanguageView();
//                showMainMenu();
                break;
        }
        return choice;
    }

    public String readBoatId() {
        System.out.print("Ange boat id: ");
        String str = Utilities.getString(languageType);

        return str;
    }

    public LanguageType getLanguageType() {
        return languageType;
//        switch (input()){
//            case 1:
//                return languageType.ENGLISH;
//            case 2:
//                return languageType.SVENSKA;
//            default:
//                System.out.println("Enter again");
//                getLanguageType();
//        }
    }

    public Integer input() {
        return new Scanner(System.in).nextInt();
    }


    public String printDetails(LanguageType lang, List<Boat> boats, Member member) {

        String str = "";
        if (boats.size() > 0) {
            for (Boat boat : boats)

                str += "\n" + boat.toString(lang) + ", ";

        }

        String strOut = String.format("%-15s  %-8s %-12s %-20s", member.getName(), member.getId(), member.getPersonalNo(), str);
        return strOut;
    }
}
