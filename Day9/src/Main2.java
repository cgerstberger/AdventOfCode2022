import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2 {
    private static Point head = new Point(0,0);
    private static Point tail = new Point(0,0);
    private static List<Point> visitedPoints = new ArrayList<Point>();
    public static void main(String[] args) {
        visitedPoints.add(new Point(tail.getX(), tail.getY()));
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            while(true) {
                String line = reader.readLine();
                if(line == null)
                    break;
                String[] arr = line.split(" ");
                char direction = arr[0].charAt(0);
                int steps = Integer.parseInt(arr[1]);

                moveSteps(direction, steps);
            }
            System.out.println("Number of tail points: " + visitedPoints.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void moveSteps(char direction, int steps) {
        for(int i = 0; i < steps; i ++) {
            switch(direction) {
                case 'R':
                    head.setX(head.getX()+1);
                    break;
                case 'L':
                    head.setX(head.getX()-1);
                    break;
                case 'U':
                    head.setY(head.getY()-1);
                    break;
                case 'D':
                    head.setY(head.getY()+1);
                    break;
            }
            moveTail();
        }
    }

    private static void moveTail() {
        int hX = head.getX();
        int hY = head.getY();
        int tX = tail.getX();
        int tY = tail.getY();
        int diffX = hX - tX;
        int diffY = hY - tY;
        if(diffX > 1 || diffY > 1 || diffX < -1 || diffY < -1) {
            if(diffX == 0 || diffY == 0) {
                if (diffX > 1) {
                    tail.setX(tail.getX() + 1);
                }
                if (diffX < -1)
                    tail.setX(tail.getX() - 1);
                if (diffY > 1)
                    tail.setY(tail.getY() + 1);
                if (diffY < -1)
                    tail.setY(tail.getY() - 1);
            } else {
                if (diffX > 1) {
                    tail.setX(tail.getX() + 1);
                    tail.setY(tail.getY() + diffY);
                }
                if (diffX < -1) {
                    tail.setX(tail.getX() - 1);
                    tail.setY(tail.getY() + diffY);
                }
                if (diffY > 1) {
                    tail.setY(tail.getY() + 1);
                    tail.setX(tail.getX() + diffX);
                }
                if (diffY < -1) {
                    tail.setY(tail.getY() - 1);
                    tail.setX(tail.getX() + diffX);
                }
            }

            if(!visitedPoints.contains(tail))
                visitedPoints.add(new Point(tail.getX(), tail.getY()));
        }
    }

    private static boolean isHeadAround() {
        int hX = head.getX();
        int hY = head.getY();
        int tX = tail.getX();
        int tY = tail.getY();
        int diffX = hX - tX;
        int diffY = hY - tY;
        if(diffX > 1 || diffY > 1) {
            if(diffX > 1)
                tail.setX(tail.getX()+1);
            if(diffX < -1)
                tail.setX(tail.getX()-1);
            if(diffY > 1)
                tail.setY(tail.getY()+1);
            if(diffY < -1)
                tail.setY(tail.getY()-1);
            return true;
        }
        if(hX == tX-1 && hY == tY-1
                || hX == tX && hY == tY-1
                || hX == tX+1 && hY == tY-1
                || hX == tX-1 && hY == tY
                || hX == tX+1 && hY == tY
                || hX == tX-1 && hY == tY+1
                || hX == tX && hY == tY+1
                || hX == tX+1 && hY == tY+1) {
            return true;
        }
        return false;
    }

//    private static class Point {
//        private int x;
//        private int y;
//
//        public Point() {
//        }
//
//        public Point(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        public int getX() {
//            return x;
//        }
//
//        public void setX(int x) {
//            this.x = x;
//        }
//
//        public int getY() {
//            return y;
//        }
//
//        public void setY(int y) {
//            this.y = y;
//        }
//    }
}
