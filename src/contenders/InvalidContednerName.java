package contenders;

public class InvalidContednerName extends Exception{
    public InvalidContednerName(){
        //
    }

    @Override
    public String toString() {
        return "did not nominate!";
    }
}
