package voters;

public class NotEligibleToVote extends Exception{
    public NotEligibleToVote(){
        //
    }
    public String toString() {
        return "Your are not 18 years old";
    }
}