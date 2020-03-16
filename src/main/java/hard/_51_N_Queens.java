package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udaythota
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * </p>n 2/16/20.
 */
public class _51_N_Queens {
    // classic backtracking: initialize the n * n array and starting first position, perform DFS and fill the queens in the boards in the valid positions
    // TC: O(n!). There are N possibilities to put the first queen, not more than N (N - 2) to put the second one, not more than N(N - 2)(N - 4) for the third one etc. In total that results in O(N!) time complexity.
    private static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = '.';
            }
        }
        nQueensDFSHelper(matrix, 0, result);
        return result;
    }

    // start the process by placing the queen in the first row [0, 0].
    // as we cannot place 2 queens in the same row, when we place the queen in the nth row and if its valid position, dfs again starting n+1 th row and try placing the next queen in all the columns of n+1th row and find a valid position.
    // if no valid queen column found in n+1th row, backtrack and go to nth row, and change the queen position to the next column and repeat the process
    private static void nQueensDFSHelper(char[][] matrix, int row, List<List<String>> result) {
        if (row == matrix.length) {
            List<String> possibleResult = resultBuilder(matrix);
            result.add(possibleResult);
            return;
        }
        for (int col = 0; col < matrix.length; col++) {
            matrix[row][col] = 'Q';   // mark the queen position to try out all the possibilities
            if (isValidPosition(matrix, row, col)) {
                nQueensDFSHelper(matrix, row + 1, result);
            }
            matrix[row][col] = '.';   // undo (un mark) the queen position
        }
    }

    // helper method to validate if the queen can be placed at a certain position (x and y coordinates) on current state of the board
    private static boolean isValidPosition(char[][] matrix, int x, int y) {
        for (int row = 0; row < x; row++) {   // check for the rows only before x as those are where the queen will be (as we are visiting the rows in sequential order)
            for (int col = 0; col < matrix.length; col++) {  // visit all the columns in these rows
                // when the matrix already has queen at [row, column] position, the new queen cannot be in this column or one of its diagonals (x-row and y-col -- better understood with an example: these conditions represents if the new queen is placed in one of the diagonals of an already existing queen. if yes, the new queen cannot be valid at the given position)
                if (matrix[row][col] == 'Q' && (y == col || Math.abs(x - row) == Math.abs(y - col))) {   // IMPORTANT: check if this grid value is already a queen and if our new queen position is still valid w.r.t to that queen position: the first condition just means we are checking if there is already a queen at that position (we are not placing any new queens again)
                    return false;
                }
            }
        }
        return true;
    }

    // create the list of strings from queen positions
    private static List<String> resultBuilder(char[][] matrix) {
        int length = matrix.length;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(new String(matrix[i]));
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }
}