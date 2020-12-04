package admin;

import voters.NotEligibleToVote;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ManagingVoters {
    public static List<Voter> voterList=new ArrayList<>();
    public static BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
    public static void voterRegistration() throws IOException {
    //public static void main(String... args) throws IOException{
        System.out.println("Enter name, age, email id, phone number, aadhar number");
        String s=reader.readLine()+"\n";
        try{
            if(isValidVoter(s)){
                FileWriter fileWriter=new FileWriter("src\\voters\\voterList.txt", true);
                char[] buffer=new char[s.length()];
                s.getChars(0, buffer.length, buffer, 0);
                for (char c : buffer) {
                    fileWriter.write(c);
                }
                fileWriter.close();
            }else{
                throw new NotEligibleToVote();
            }
        }catch (NotEligibleToVote e){
            System.out.println(e.toString());
        }
    }
    public static boolean isValidVoter(String record){
        StringTokenizer details=new StringTokenizer(record);
        String name=details.nextToken();
        int age=Integer.parseInt(details.nextToken());
        return age >= 18;
    }
    public static void loadVoters() throws IOException{
        FileReader fileReader = new FileReader("src\\voters\\voterList.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer record = new StringTokenizer(line);
            voterList.add(new Voter(record.nextToken(), record.nextToken(), record.nextToken(), record.nextToken(), record.nextToken()));
        }
    }
    public static void listOfVoters() {
        for(Voter v:voterList){
            System.out.println(v.name+v.aadharNo);
        }
    }
}