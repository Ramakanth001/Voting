package voters;

public class InvalidVoter extends Exception{
    public InvalidVoter(){
        //
    }

    @Override
    public String toString() {
        return "Your details didn't match";
    }
}
