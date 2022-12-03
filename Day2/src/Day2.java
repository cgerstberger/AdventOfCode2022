import java.io.*;
import java.util.HashMap;

public class Day2 {
    private static final char opponentRock = 'A';
    private static final char opponentPaper = 'B';
    private static final char opponentScissors = 'C';
    private static final char myRock = 'X';
    private static final char myPaper = 'Y';
    private static final char myScissors = 'Z';

    public static void main(String[] args) {
        HashMap<Character, Character> mapping = new HashMap<>();
        mapping.put('A', 'R');
        mapping.put('B', 'P');
        mapping.put('C', 'S');
        mapping.put('X', 'R');
        mapping.put('Y', 'P');
        mapping.put('Z', 'S');

        int score = 0;
        try {
            FileInputStream fileIn = new FileInputStream("input.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn));

            while(true) {
                String line = reader.readLine();
                if(line == null)
                    break;
                String[] values = line.split(" ");
                char opponentMove = values[0].charAt(0);
                char myMove = values[1].charAt(0);

                switch (myMove) {
                    case myRock: score += 1;
                        break;
                    case myPaper: score += 2;
                        break;
                    case myScissors: score += 3;
                        break;
                }

                if(mapping.get(myMove) == mapping.get(opponentMove)) {
                    score += 3;
                } else if (myMove == myRock && opponentMove == opponentScissors
                        || myMove == myPaper && opponentMove == opponentRock
                        || myMove == myScissors && opponentMove == opponentPaper) {
                    score += 6;
                }
            }
        } catch(IOException exc) {
            exc.printStackTrace();
        }
        System.out.println("Your score: " + score);
    }
}
