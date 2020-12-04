package voters;

public class AlreadyVoted extends Exception{
    public AlreadyVoted(){
        //
    }

    @Override
    public String toString() {
        return "Your vote has been recorded already!";
    }
}
