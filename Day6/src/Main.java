import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Deque<Character> queue = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            int i = 1;
            while(true) {
                int val = reader.read();
                if(val == -1)
                    break;
                char c = (char) val;
                if(queue.size() == 14) {
                    queue.poll();
                }
                queue.add(c);
                boolean markerFound = isDifferent(queue);
                if(markerFound) {
                    System.out.println("Marker after character " + i);
                    return;
                }
                i++;
            }
        } catch(IOException exc) {
            exc.printStackTrace();
        }
    }

    private static boolean isDifferent(Queue<Character> queue) {
        String s = queue.stream().map(Object::toString).reduce("", (acc, val) -> acc + val);
        if(s.length() < 14)
            return false;
        char[] cArr = s.toCharArray();
        for(int to = 1; to < cArr.length; to ++) {
            for(int from = 0; from < to; from ++) {
                if(cArr[from] == cArr[to]) {
                    return false;
                }
            }
        }
        return true;
    }
}