package admin;

import voters.AlreadyVoted;
import voters.InvalidVoter;

import java.io.*;

public class Main {
    public static int totalParticipated = 0;
    static boolean isElectionsStarted = false;
    static boolean isNominationsDone = false;
    static boolean isRegistrationsDone = false;
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    //voter validation
    //cites
    //switch
    //bar graph
    public static File doneVoting = new File("src\\admin\\doneVoting.txt");
    public static FileWriter listOfAlreadyVoted;

    public static void main(String[] args) throws Exception {

        int option = 9999;
        displayOptions();
        while (option != 0) {
            System.out.println("Enter an option : ");
            option = Integer.parseInt(reader.readLine());
            switch (option) {
                case 0:
                    endOfElections();
                    break;
                case 1:
                    if (!isRegistrationsDone)
                        ManagingVoters.voterRegistration();
                    else
                        System.out.println("No more registrations\nRegistration closed");
                    break;
                case 2:
                    if (!isNominationsDone)
                        ManagingContenders.contenderNomination();
                    else
                        System.out.println("No more nominations\nNominations closed");
                    break;
                case 3:
                    if (!isNominationsDone) {
                        isNominationsDone = true;
                        ManagingContenders.loadContenders();
                        System.out.println("Nominations closed");
                    }
                    else
                        System.out.println("Already Nominations closed!");
                    break;
                case 4:
                    if (!isElectionsStarted) {
                        System.out.print("enter contender name to disqualify : ");
                        ManagingContenders.disqualify(reader.readLine());
                    } else {
                        System.out.println("Can't disqualify, Elections started");
                    }
                    break;
                case 5:
                    if (isNominationsDone) {
                        isElectionsStarted = true;
                        System.out.println("Elections start");
                    } else
                        System.out.println("Wait until nominations and registrations over");
                    break;
                case 6:
                    if (isNominationsDone&&isRegistrationsDone)
                        ManagingContenders.displayContenders();
                    else
                        System.out.println("Still nominations are going on\n" +
                                "After completion of nominations we will display Contenders");
                    break;
                case 7:
                    if (isElectionsStarted)
                        iWillVote();
                    else
                        System.out.println("Cant Vote, Elections yet to start");
                    break;
                case 8:
                    if (isElectionsStarted)
                        results();
                    else
                        System.out.println("Elections didn't start yet!");
                    break;
                case 9:
                    displayOptions();
                    break;
                case 10:
                    if (isRegistrationsDone)
                        ManagingVoters.listOfVoters();
                    else
                        System.out.println("Still Registations going on!");
                    break;
                case 11:
                    if(!isRegistrationsDone) {
                        isRegistrationsDone = true;
                        ManagingVoters.loadVoters();
                        System.out.println("Registrations done");
                    }else
                        System.out.println("Already registrations done");
                    break;
            }
        }
    }

    static void displayOptions() {
        System.out.println("0 - END OF ELECTIONS\n" +
                "1 - VOTER REGISTRATION\n" +
                "2 - CONTENDER NOMINATION\n" +
                "3 - CLOSE NOMINATIONS\n" +
                "4 - DISQUALIFY CONTENDER\n" +
                "5 - START ELECTIONS\n" +
                "6 - DISPLAY CONTENDERS\n" +
                "7 - LET ME VOTE\n" +
                "8 - RESULT ANNOUNCEMENT\n" +
                "9 - DISPLAY OPTIONS\n"+
                "10 - DISPLAY LIST OF VOTERS REGISTERED\n"+
                "11 - CLOSE REGISTRATIONS\n");
    }

    static void results() {
        System.out.println("Total participated : " + totalParticipated);
        for (Contender c : ManagingContenders.contenderList) {
            System.out.println(c.name + " has : " + c.myScore);
        }
    }

    static void endOfElections() throws Exception {
        listOfAlreadyVoted.close();
        System.out.println("Successfully Elections completed");
    }

    static void iWillVote() throws IOException {
        System.out.print("Enter name : ");
        String myName = reader.readLine();
        System.out.print("aadhar Number : ");
        String myAadharNo = reader.readLine();
        int index = isValidVoter(myName, myAadharNo);
        //System.out.println("index is : " + index);
        if (!isVoted(myAadharNo) && index != -1) {
            System.out.println("Enter name of the contender whom you want to vote : ");
            ManagingVoters.voterList.get(index).castMyVote(reader.readLine());
        }
    }

    static boolean isVoted(String myAadharNo) throws IOException {
        FileReader openListOfAlreadyVoted = new FileReader(Main.doneVoting);
        BufferedReader votedAadharNo = new BufferedReader(openListOfAlreadyVoted);
        String s;
        try {
            while (null != (s = votedAadharNo.readLine())) {
                if (myAadharNo.equals(s)) {
                    throw new AlreadyVoted();
                }
            }
        } catch (AlreadyVoted a) {
            System.out.println(a.toString());
            return true;
        }
        openListOfAlreadyVoted.close();
        return false;
    }

    static int isValidVoter(String myName, String myAadharNo) throws IOException {
        try {
            for (int i = 0; i < ManagingVoters.voterList.size(); i++) {
                if (ManagingVoters.voterList.get(i).name.equals(myName) &&
                        ManagingVoters.voterList.get(i).aadharNo.equals((myAadharNo))) {
                    return i;
                }
            }
            throw new InvalidVoter();
        } catch (InvalidVoter i) {
            System.out.println(i.toString());
            return -1;
        }
    }
}