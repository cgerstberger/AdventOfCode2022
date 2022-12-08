import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Character>[] stackArray = new Stack[0];
        boolean initPhase = true;
        boolean stackDone = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            while (true) {
                String line = reader.readLine();
                if(line == null)
                    break;
                if(line.isEmpty())
                    stackDone = true;
                if(!stackDone) {
                    // fill stack
                    if(initPhase) {
                        int arrLength = (line.length()+1)/4;
                        stackArray = new Stack[arrLength];
                        initPhase = false;
                    }
                    int idx = 0;
                    for(int i = 1; i < line.length(); i += 4) {
                        if(line.charAt(i) != ' ') {
                            if(stackArray[idx] == null)
                                stackArray[idx] = new Stack<>();
                            if(!Character.isDigit(line.charAt(i)))
                                stackArray[idx].add(0, line.charAt(i));
                        }
                        idx ++;
                    }
                } else {
                    if(line.isEmpty() || line.isBlank())
                        continue;
                    // move stack crates
                    int x = 0;
                    String[] moveParts = line.split(" ");
                    int amount = Integer.parseInt(moveParts[1]);
                    int from = Integer.parseInt(moveParts[3])-1;
                    int to = Integer.parseInt(moveParts[5])-1;
                    int stackToLength = stackArray[to].size();
                    while(amount > 0) {
                        Character cFrom = stackArray[from].pop();
                        stackArray[to].add(stackToLength, cFrom);
                        amount --;
                    }
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        String result = "";
        for(int i = 0; i < stackArray.length; i ++) {
            result += stackArray[i].peek();
        }
        System.out.println("Solution: " + result);
    }
}
