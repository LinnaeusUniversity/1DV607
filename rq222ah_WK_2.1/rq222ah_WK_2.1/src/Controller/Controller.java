package Controller;

import Model.AllMembers;
import Model.Boats.Boat;
import Model.Boats.BoatRegister;
import Model.LanguageType;
import Model.Member;
import Model.Utilities;
import View.LangStringID;
import View.LanguageView;
import View.MainView;

import java.io.IOException;
import java.util.HashMap;
public class Controller {
    private Model.MemberRegister model;
    private MainView view;
    private BoatRegister boatModel;
    private Model.LanguageType languageType;
//    private Member member;
    private AllMembers allMembers;
//    private Utilities utilities;
//    private LanguageView languageView;
//    private HashMap<LangStringID, String> langStrings;

    public Controller() throws IOException {
        model = new Model.MemberRegister();
        boatModel = new BoatRegister();
        view = new MainView(this);  //shows language
        languageType = view.getLanguageType();
    }



    public void start() {
        boolean done = false;




        while (!done) {
            int mainChoice=0;
            if(view.getLanguageType()== LanguageType.SVENSKA) {
                int ch=view.showMainMenuSe();
                mainChoice=ch;
            }else if(view.getLanguageType()==LanguageType.ENGLISH) {
                int ch=view.showMainMenu();
                mainChoice=ch;
            }else {
                //Other languages
            }
            switch (mainChoice) {
                case 1:
                    createMember();
                    break;
                case 2:
                    retrieveMember();
                    break;
                case 3:
                    displayMembers();
                    break;
                case 4:
                    updateMember();
                    break;
                case 5:
                    removeMember();
                    break;
                case 7:
                    addBoat();
                    break;
                case 8:
                    updateBoat();
                    break;
                case 9:
                    deleteBoat();
                    break;
                case 10:
                    displayVerboseList();
                    break;
                case 11:
                    displayCompactList();
                    break;
                case 0:
                    this.model.saveInFile();
                    done = true;

                    break;
//                default:
            }

        }
    }

    public void displayMembers() {
        view.displayMembers(model);
    }

    public void displayCompactList() {
        view.displayCompactList(model);
    }

    public void displayVerboseList() {

        view.verboseList(model);
    }

    public void createMember() {
                        
        String name = view.readMemberName();
        if (name.isEmpty() || name.matches(".*\\d+.*")) { // If name is empty or contains digits
            System.err.println("The name can't be empty or contain digits, please try again.\n");
            return;
        }
        String personalNumber = view.readMemberPersonNr();
        String formatPnumber = personalNumber.replaceAll("-", "");
        if (!formatPnumber.matches("\\d+")) {
            System.err.println("The personal number can only contain digits. Please try again."); // If personal number contains letters or symbols
            return;
        } else {
            model.addNewMember(name,personalNumber);
        }
    }

    public void retrieveMember() {
        String memberId = view.readMemberUnId();
        String formatPnumber = memberId.replaceAll("-", "");
        if (!formatPnumber.matches("\\d+") || memberId.length() > 4) {
            System.err.println("Member id can only contain 4-digits. Please try again."); // If personal number contains letters or symbols
            return;
        }
        Integer id = Integer.valueOf(memberId);
        if (model.memberExist(id)) {
            //here i need call a funtion to iterate Member details
            System.out.println(this.view.printDetails(languageType, model.retrieveMember(id).getBoats(), model.retrieveMember(id)));
        } else {
            System.err.println("not found");
            return;
        }
    }

    public boolean updateMember() {
        String memberId = view.readMemberUnId();
        int id = Integer.valueOf(memberId);
        if (model.memberExist(id)) {
            model.updateMember(id).setName(view.readMemberName());
            model.updateMember(id).setPersonalNo(view.readMemberPersonNr());
            return true;
        }
        System.err.println("Member does not exist");
        return false;
    }

    public void removeMember() {
        String memberId = view.readMemberUnId();
        Integer id = Integer.valueOf(memberId);
        if (model.memberExist(id)) {
            view.displayAMember(model);
            System.out.println(this.view.printDetails(languageType, model.retrieveMember(id).getBoats(), model.retrieveMember(id)));
            model.removeMember(id);
        } else {
            System.out.println("member not found");
        }
    }

    public void addBoat() {
        String memberId = view.readMemberUnId();
        Integer id = Integer.valueOf(memberId);
        if (model.memberExist(id)) {
            displayBoats();
            //run a loop for the adding boats to the same (above) member
            String str = view.readBoatId();
            Boat boat = boatModel.getBoat(str);  //select from the registry
            if (boat != null) {
                this.model.retrieveMember(id).getBoats().add(boat);
            }
        } else {
            System.err.println("Member not found");
        }
    }

    public void deleteBoat() {
        String memberId = view.readMemberUnId();
        Integer id = Integer.valueOf(memberId);
        if (model.memberExist(id)) {
            System.out.println(this.view.printDetails(languageType, model.retrieveMember(id).getBoats(), model.retrieveMember(id)));
            String boat = view.readBoatId();
            this.model.retrieveMember(id).removeBoat(boat);
        }
    }

    public void updateBoat() {
        String memberId = view.readMemberUnId();
        Integer id = Integer.valueOf(memberId);
        if (model.memberExist(id)) {
            System.out.println(this.view.printDetails(languageType, model.retrieveMember(id).getBoats(), model.retrieveMember(id)));
            String boat = view.readBoatId();
            displayBoats();
            this.model.retrieveMember(id).updateBoat(boat).setBoatId(view.readBoatId());
        }
    }

    public void displayBoats() {
        String[] infoStrings = boatModel.getBoatInfo(languageType);
        view.displayBoats(infoStrings);
    }
}
