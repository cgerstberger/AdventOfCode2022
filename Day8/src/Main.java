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
        int highestScenicScore = getHighestScenicScore(trees);
        System.out.println("Highest Scenic Score: " + highestScenicScore);
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

    private static int getHighestScenicScore(int[][] trees) {
        int highestScenicScore = 0;
        for(int row = 1; row < trees.length-1; row ++) {
            for(int col = 1; col < trees[row].length-1; col ++) {
                int scenicScoreOfTree = getScenicScoreOfTree(row, col, trees);
                if(scenicScoreOfTree > highestScenicScore)
                    highestScenicScore = scenicScoreOfTree;
            }
        }
        return highestScenicScore;
    }

    private static int getScenicScoreOfTree(int row, int col, int[][] trees) {
        int treeHeight = trees[row][col];
        // check top
        int topScore = 0;
        for(int i = row-1; i >= 0; i --) {
            topScore++;
            if(trees[i][col] >= treeHeight) {
                break;
            }
        }
        // check bottom
        int bottomScore = 0;
        for(int i = row+1; i < trees.length; i ++) {
            bottomScore++;
            if(trees[i][col] >= treeHeight) {
                break;
            }
        }
        // check left
        int leftScore = 0;
        for(int i = col-1; i >= 0; i --) {
            leftScore++;
            if(trees[row][i] >= treeHeight) {
                break;
            }
        }
        // check right
        int rightScore = 0;
        for(int i = col+1; i < trees[row].length; i ++) {
            rightScore++;
            if(trees[row][i] >= treeHeight) {
                break;
            }
        }
        return topScore * bottomScore * leftScore * rightScore;
    }
}