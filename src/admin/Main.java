package admin;

public class Main {
    public static int successfulVotes=0;
    public static int totalParticipated=0;

    public static void main(String[] args) throws Exception {

        ContenderList.createContenderList();
        ContenderList.disqualify("CSK");
        VoterList.createVoterList();
        int noOfVoters = VoterList.voterList.size();
        for (int i = 0; i < noOfVoters; i++) {
            if (i == 1)
                VoterList.voterList.get(i).castMyVote("CSK");
            else
                VoterList.voterList.get(i).castMyVote("SRH");
        }
        analysis();
    }

    static void analysis(){
        System.out.println("Total participated : "+ totalParticipated);
        System.out.println("Successful Votes : "+ successfulVotes);
        for (Contender c : ContenderList.contenderList) {
            System.out.println(c.name + " has : " + c.myScore);
        }
    }
}