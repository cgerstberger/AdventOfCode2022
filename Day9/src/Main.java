import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static int nrRows = 100;
    private static int nrColumns = 100;
    private static int headX = nrRows/2 - 1;
    private static int headY = nrColumns/2 - 1;
    private static int tailX = headX;
    private static int tailY = headY;
    private static char[][] actualArr = new char[nrRows][nrColumns];
    private static char[][] visitedArr = new char[nrRows][nrColumns];
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("testinput.txt"));
            while(true) {
                String line = reader.readLine();
                if(line == null)
                    break;
                String[] arr = line.split(" ");
                char direction = arr[0].charAt(0);
                int steps = Integer.parseInt(arr[1]);

                moveSteps(direction, steps);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void moveSteps(char direction, int steps) {
        
    }
}