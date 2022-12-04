import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int nrIncludedRanges = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line;
            while(true) {
                line = reader.readLine();
                if(line == null)
                    break;
                String[] ranges = line.split(",");
                String[] arrRangeA = ranges[0].split("-");
                String[] arrRangeB = ranges[1].split("-");
                int lowerA = Integer.parseInt(arrRangeA[0]);
                int upperA = Integer.parseInt(arrRangeA[1]);
                int lowerB = Integer.parseInt(arrRangeB[0]);
                int upperB = Integer.parseInt(arrRangeB[1]);

                if((lowerA <= lowerB && upperA >= upperB) || (lowerB <= lowerA && upperB >= upperA)) {
                    nrIncludedRanges++;
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        System.out.println("Included ranges: " + nrIncludedRanges);
    }
}