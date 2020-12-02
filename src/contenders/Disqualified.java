package contenders;

public class Disqualified extends Exception{
    public Disqualified(){
        //
    }

    @Override
    public String toString() {
        return "Disqualified please Cast your vote to another contender";
    }
}
