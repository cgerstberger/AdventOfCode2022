import java.io.*;
import java.util.HashMap;

public class Day2 {
    private static final char opponentRock = 'A';
    private static final char opponentPaper = 'B';
    private static final char opponentScissors = 'C';
    private static final char stateLose = 'X';
    private static final char stateDraw = 'Y';
    private static final char stateWin = 'Z';

    private static final char ROCK = 'R';
    private static final char PAPER = 'P';
    private static final char SCISSORS = 'S';

    private static HashMap<Character, Character> loseMap = new HashMap<>();
    private static HashMap<Character, Character> winMap = new HashMap<>();

    public static void main(String[] args) {
        HashMap<Character, Character> mapping = new HashMap<>();
        mapping.put('A', ROCK);
        mapping.put('B', PAPER);
        mapping.put('C', SCISSORS);
        mapping.put('X', ROCK);
        mapping.put('Y', PAPER);
        mapping.put('Z', SCISSORS);

        loseMap = new HashMap<>();
        loseMap.put(ROCK, SCISSORS);
        loseMap.put(PAPER, ROCK);
        loseMap.put(SCISSORS, PAPER);
        winMap = new HashMap<>();
        winMap.put(ROCK, PAPER);
        winMap.put(PAPER, SCISSORS);
        winMap.put(SCISSORS, ROCK);

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
                char opponentSign = mapping.get(opponentMove);
                char myState = values[1].charAt(0); // X ... lose, Y ... draw, Z ... win

                score += play(opponentSign, myState);
            }
        } catch(IOException exc) {
            exc.printStackTrace();
        }
        System.out.println("Your score: " + score);
    }

    private static int play(char opponentSign, char myState) {
        int score = 0;
        switch (myState) {
            case stateLose: score = playLose(opponentSign);
                break;
            case stateDraw: score = playDraw(opponentSign);
                break;
            case stateWin: score = playWin(opponentSign);
                break;
        }
        return score;
    }

    private static int playLose(char opponentSign) {
        int score = 0;
        char losingSign = loseMap.get(opponentSign);
        score += getPointsForSign(losingSign);
        score += 0; // points for losing
        return score;
    }

    private static int playDraw(char opponentSign) {
        int score = 0;
        score += getPointsForSign(opponentSign);
        score += 3;
        return score;
    }

    private static int playWin(char opponentSign) {
        int score = 0;
        char winningSign = winMap.get(opponentSign);
        score += getPointsForSign(winningSign);
        score += 6;
        return score;
    }

    private static int getPointsForSign(char sign) {
        switch(sign) {
            case ROCK: return 1;
            case PAPER: return 2;
            case SCISSORS: return 3;
        }
        return 0;
    }
}
