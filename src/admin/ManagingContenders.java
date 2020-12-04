package admin;

import contenders.InvalidContednerName;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ManagingContenders {
    public static List<Contender> contenderList = new ArrayList<>();
    public static BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
    public static void contenderNomination() throws Exception {
    //public static void main(String[] args) throws IOException{

        FileWriter fileWriter = new FileWriter("src\\contenders\\contenderList.txt", true);
        System.out.println("Enter Title, slogan");
        String s = reader.readLine()+"\n";
        char[] buffer = new char[s.length()];
        s.getChars(0, buffer.length, buffer, 0);
        for (char c : buffer) {
            fileWriter.write(c);
        }
        fileWriter.close();
    }
    public static void loadContenders() throws IOException{
        FileReader fileReader = new FileReader("src\\contenders\\contenderList.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer record = new StringTokenizer(line, ":");
            contenderList.add(new Contender(record.nextToken(), record.nextToken()));
        }
    }
    public static void displayContenders() {
        System.out.println("  Contender          Symbol\n");
        for (Contender contender : contenderList) {
            System.out.println("    " + contender.name + "             " + contender.slogan);
        }
    }
    public static void disqualify(String name){
        int index=0;
        try {
            for (Contender contender : contenderList) {
                if (contender.name.equals(name)) {
                    contenderList.remove(contenderList.get(index));
                    return;
                }
            }
            throw new InvalidContednerName();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
}