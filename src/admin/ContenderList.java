package admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ContenderList {
    public static List<Contender> contenderList = new ArrayList<>();

    public static void createContenderList() throws Exception {
        FileWriter fileWriter = new FileWriter("src\\contenders\\contenderList.txt");
        String s = "RCB::Play Bold\n" +
                "SRH::Orange Army\n"+
                "CSK::Wistle Podu\n";
        char[] buffer = new char[s.length()];
        s.getChars(0, buffer.length, buffer, 0);
        for (char c : buffer) {
            fileWriter.write(c);
        }
        fileWriter.flush();
        fileWriter.close();
        FileReader fileReader = new FileReader("src\\contenders\\contenderList.txt");
        BufferedReader br = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer record = new StringTokenizer(line, ":");
            contenderList.add(new Contender(record.nextToken(), record.nextToken()));
        }
        System.out.println("  Contender          Symbol\n");
        for (Contender contender : contenderList) {
            System.out.println("    " + contender.name + "             " + contender.symbol);
        }
    }

    public static void disqualify(String name){
        for (Contender contender : contenderList) {
            if (contender.name.equals(name)) {
                contender.isEligible = false;
                break;
            }
        }
    }
}