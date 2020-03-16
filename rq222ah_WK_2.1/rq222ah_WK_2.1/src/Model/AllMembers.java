package Model;

import java.util.ArrayList;
import java.util.List;

public class AllMembers {
    private List<Member> allMembers;

    AllMembers() {
        allMembers = new ArrayList<Member>();
    }

    public List<Member> getAllMembers() {
        return allMembers;
    }
}
