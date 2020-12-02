package admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class VoterList {
    public static List<Voter> voterList=new ArrayList<>();
    public static void createVoterList() throws Exception{
        FileWriter fileWriter=new FileWriter("src\\voters\\voterList.txt");
        String s="Venkatesh venkatesh@gmail.com 6300636693\n"+
                            "Ramakanth ramakanth@gmail.com 9876543210\n";
        char[] buffer=new char[s.length()];
        s.getChars(0, buffer.length, buffer, 0);
        for (char c : buffer) {
            fileWriter.write(c);
        }
        fileWriter.flush();
        fileWriter.close();
        FileReader fileReader=new FileReader("src\\voters\\voterList.txt");
        BufferedReader br=new BufferedReader(fileReader);
        StringBuilder sb=new StringBuilder();
        String line;
        while((line=br.readLine())!=null)
        {
            StringTokenizer record=new StringTokenizer(line);
            voterList.add(new Voter(record.nextToken(),record.nextToken(), record.nextToken()));
        }
    }

}