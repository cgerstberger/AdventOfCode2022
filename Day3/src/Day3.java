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
            fileLoop:
            while(true) {
                String[] arr = new String[3];
                for(int i = 0; i < arr.length; i ++) {
                    arr[i] = reader.readLine();
                    if(arr[i] == null)
                        break fileLoop;
                }
                for(char c : arr[0].toCharArray()) {
                    String valC = String.valueOf(c);
                    if(arr[1].contains(valC) && arr[2].contains(valC)) {
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