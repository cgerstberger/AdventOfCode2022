import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class Day1 {
    public static void main(String[] args) {
        ArrayList<Integer> elfCaloriesList = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("input.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn));

            int idx = 0;
            while(true) {
                String line = reader.readLine();
                if(line == null)
                    break;
                if(!line.isEmpty() && !line.isBlank()) {
                    int val = Integer.parseInt(line);
                    if(elfCaloriesList.size() == idx)
                        elfCaloriesList.add(val);
                    else
                        elfCaloriesList.set(idx, elfCaloriesList.get(idx) + val);
                } else {
                    idx++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int maxCalIdx = -1;
        int maxCal = Integer.MIN_VALUE;
        for(int i = 0; i < elfCaloriesList.size(); i ++) {
            if(elfCaloriesList.get(i) > maxCal) {
                maxCal = elfCaloriesList.get(i);
                maxCalIdx = i;
            }
        }

        System.out.println("The elf on index " + maxCalIdx + " has the most calories with " + maxCal + " calories.");
    }
}
