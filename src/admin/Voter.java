package admin;

public class Voter {
    public String name;
    public String email;
    public String phoneNo;

    public Voter(String name, String email, String phoneNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public void castMyVote(String contender){
        int index=0;
        for(Contender c:ContenderList.contenderList){
            if(contender.equals(c.name)){
                break;
            }
            index++;
        }
        ContenderList.contenderList.get(index).castForMe();
    }
}