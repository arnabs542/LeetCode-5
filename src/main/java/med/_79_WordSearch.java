package _5_Longest_Palindromic_Substring;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by udaythota on 5/26/19.
 * <p>
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 * </p>
 */
public class _79_WordSearch {

    // Simple DFS on 2D array. Special character: '*' is used to mark the visited nodes (this makes it more space efficient by not using an other array to keep track of visited nodes)
    private static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (helperDFS(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean helperDFS(char[][] board, String word, int i, int j, int index) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || word.charAt(index) != board[i][j]) {
            return false;
        }
        board[i][j] = '*';     // mark the cell as visited
        if (index == word.length() - 1 || helperDFS(board, word, i, j + 1, index + 1) || helperDFS(board, word, i, j - 1, index + 1) || helperDFS(board, word, i + 1, j, index + 1) || helperDFS(board, word, i - 1, j, index + 1)) {
            return true;
        }
        board[i][j] = word.charAt(index);
        return false;
    }

    public static void main(String[] args) {
        // as the input array is being is modified, instantiate the array again for every call
        char[][] input1 = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(exist(input1, "ABCCED"));

        char[][] input2 = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(exist(input2, "SEE"));

        char[][] input3 = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertFalse(exist(input3, "ABCB"));
    }
}
