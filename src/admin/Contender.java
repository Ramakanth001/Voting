package admin;

public class Contender {
    public String name;
    public String slogan;
    public int myScore = 0;

    //boolean isEligible;
    public Contender(String name, String symbol) {
        this.name = name;
        this.slogan = symbol;
        //isEligible=true;
    }

    public void castForMe() {
        Main.totalParticipated++;
        //if(!isEligible) throw new Disqualified();
        myScore++;
    }
}
