import java.io.*;
import java.util.HashMap;

public class Day3 {
    private static HashMap<Character, Integer> priorityMap;

    public static void main(String[] args) {
        initPriorityMap();

        int priortiyScore = 0;
        try {
            FileReader fileIn = new FileReader("input.txt");
            BufferedReader reader = new BufferedReader(fileIn);

            String line;
            while(true) {
                line = reader.readLine();
                if(line == null)
                    break;
                String firstPart = line.substring(0, line.length()/2);
                String secondPart = line.substring(line.length()/2);
                for(char c : firstPart.toCharArray()) {
                    if(secondPart.contains(String.valueOf(c))) {
                        priortiyScore += priorityMap.get(c);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sum of priorities: " + priortiyScore);
    }

    private static void initPriorityMap() {
        priorityMap = new HashMap<Character, Integer>();
        int i = 1;
        char c = 'a';
        while(c <= 'z') {
            priorityMap.put(c, i);
            c++;
            i++;
        }
        c = 'A';
        while(c <= 'Z') {
            priorityMap.put(c, i);
            c ++;
            i++;
        }
    }
}