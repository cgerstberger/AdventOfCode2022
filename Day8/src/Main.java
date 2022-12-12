import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            while(true) {
                String line = reader.readLine();
                if(line == null)
                    break;
                strList.add(line);
            }
        } catch(IOException exc) {
            exc.printStackTrace();
        }

        if(strList.size() == 0 || strList.get(0).length() == 0) {
            System.out.println("Array empty!");
        }

        int[][] trees = convertStringListToIntArray(strList);
        int nrVisibleTrees = getNrOfVisibleTrees(trees);
        System.out.println("Visible Trees: " + nrVisibleTrees);
    }

    private static int[][] convertStringListToIntArray(List<String> strList) {
        int[][] trees = new int[strList.size()][strList.get(0).length()];
        for(int row = 0; row < strList.size(); row ++) {
            for(int col = 0; col < strList.get(0).length(); col ++) {
                trees[row][col] = Character.getNumericValue(strList.get(row).charAt(col));
            }
        }
        return trees;
    }

    private static int getNrOfVisibleTrees(int[][] trees) {
        int nrVisibleTrees = 0;
        for(int row = 1; row < trees.length-1; row ++) {
            for(int col = 1; col < trees[row].length-1; col ++) {
                boolean isVisible = isTreeVisible(trees[row][col], row, col, trees);
                if(isVisible)
                    nrVisibleTrees++;
            }
        }
        nrVisibleTrees += trees.length*2 + trees[0].length*2 - 4; // äußerste Spalten + äußerste Reihen - überscheidende Objekte
        return nrVisibleTrees;
    }

    private static boolean isTreeVisible(int treeHeight, int row, int col, int[][] trees) {
        // check top
        boolean visibleTop = true;
        for(int i = row-1; i >= 0; i --) {
            if(trees[i][col] >= treeHeight) {
                visibleTop = false;
                break;
            }
        }
        // check bottom
        boolean visibleBottom = true;
        for(int i = row+1; i < trees.length; i ++) {
            if(trees[i][col] >= treeHeight) {
                visibleBottom = false;
                break;
            }
        }
        // check left
        boolean visibleLeft = true;
        for(int i = col-1; i >= 0; i --) {
            if(trees[row][i] >= treeHeight) {
                visibleLeft = false;
                break;
            }
        }
        // check right
        boolean visibleRight = true;
        for(int i = col+1; i < trees[row].length; i ++) {
            if(trees[row][i] >= treeHeight) {
                visibleRight = false;
                break;
            }
        }
        return visibleTop || visibleBottom || visibleLeft || visibleRight;
    }
}