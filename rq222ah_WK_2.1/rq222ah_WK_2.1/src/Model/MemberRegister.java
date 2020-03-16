package Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class MemberRegister {

    private Member member;
    private ObjectMapper saveJson;
    private File file;
    private AllMembers allMembers;

//    public boolean addNewMember(Member newMember) {
//
//    }

    public void saveInFile() {
        try {
            saveJson.writerWithDefaultPrettyPrinter().writeValue(file, allMembers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberRegister() throws IOException {
        file = new File("allMembers.json");
        saveJson = new ObjectMapper();
        allMembers = new AllMembers();
        if (file.length() != 0)
            allMembers = saveJson.readerFor(AllMembers.class).readValue(new File("allMembers.json"));
    }

    public List<Member> getMembers() {
        return allMembers.getAllMembers();
    }

    /* Method for returning all of the members */
    public Iterator<Member> getMemberIterator() {
        return this.allMembers.getAllMembers().iterator();
    }

    //generating member  id
    private int generateId() {
        int generateId = (int) (Math.random() * 9000) + 1000;
        if (allMembers.getAllMembers().contains(generateId)) {
            generateId = (int) (Math.random() * 9000) + 1000;
        }
        return generateId;
    }

    public boolean memberExist(int memberUniqId) {

        for (Member m : allMembers.getAllMembers()) {
            if (memberUniqId == m.getId()) {
                return true;
            }

        }
        return false;

    }

    //retrieve a member from the list
    public Member retrieveMember(int id) {
        if (memberExist(id)) {
            for (int i = 0; i < allMembers.getAllMembers().size(); i++) {
                if (id == allMembers.getAllMembers().get(i).getId()) {
                    return allMembers.getAllMembers().get(i);
                }
            }
        }
        return null;
    }

    public Member updateMember(int id) {

        if (memberExist(id)) {

            for (int i = 0; i < allMembers.getAllMembers().size(); i++) {
                if (id == allMembers.getAllMembers().get(i).getId()) {
                    return allMembers.getAllMembers().get(i);
                }
            }
        }

        return null;
    }

    public Member removeMember(int id) {

        if (memberExist(id)) {

            for (int i = 0; i < allMembers.getAllMembers().size(); i++) {
                if (id == allMembers.getAllMembers().get(i).getId()) {
                    return allMembers.getAllMembers().remove(i);
                }
            }
        }

        return null;
    }

    public void addNewMember(String name, String personalNumber) {
        Member newMember = new Member();
        newMember.setName(name);
        newMember.setId(generateId());
        newMember.setPersonalNo(personalNumber);
        allMembers.getAllMembers().add(newMember);
    }
}
