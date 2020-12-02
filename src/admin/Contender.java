package admin;

import contenders.Disqualified;
public class Contender {
    public String name;
    public String symbol;
    public int myScore=0;
    boolean isEligible;
    public Contender(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        isEligible=true;
    }
    public void castForMe(){
        try {
            Main.totalParticipated++;
            if(!isEligible) throw new Disqualified();
            myScore++;
            Main.successfulVotes++;

        }catch(Disqualified disqualified){
            System.out.println("Can't vote for "+name);
        }
    }

}
