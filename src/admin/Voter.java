package admin;

import java.io.FileWriter;
import java.io.IOException;

public class Voter {
    public String name;
    public String email;
    public String phoneNo;
    public String aadharNo;
    public String age;

    public Voter(String name, String age, String email, String phoneNo, String addharNo) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNo = phoneNo;
        this.aadharNo = addharNo;
    }

    public void castMyVote(String contenderName) throws IOException {
        int index = 0;
        Main.listOfAlreadyVoted = new FileWriter(Main.doneVoting,true);
        for (Contender c : ManagingContenders.contenderList) {
            if (contenderName.equals(c.name)) {
                break;
            }
            index++;
        }
        char[] buffer = new char[this.aadharNo.length()];
        (this.aadharNo).getChars(0, this.aadharNo.length(), buffer, 0);
        for (char chars : buffer) {
            Main.listOfAlreadyVoted.write(chars);
        }
        Main.listOfAlreadyVoted.close();
        ManagingContenders.contenderList.get(index).castForMe();
    }
}