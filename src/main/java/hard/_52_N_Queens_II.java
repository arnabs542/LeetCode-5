package hard;

/**
 * Created by udaythota on 2/16/20.
 * <p>
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * </p>
 */
public class _52_N_Queens_II {
    private int result = 0;

    // exact same as n queens problem but instead of using a result array, just use a count variable to keep the count of possible solutions
    // TODO: there is a much simpler and efficient way to do this using memoization. deal with it later.
    private int totalNQueens(int n) {
        if (n <= 0) {
            return result;
        }
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = '.';
            }
        }
        nQueensDFSHelper(matrix, 0);
        return result;
    }

    // start the process by placing the queen in the first row [0, 0].
    // as we cannot place 2 queens in the same row, when we place the queen in the nth row and if its valid position, dfs again starting n+1 th row and try placing the next queen in all the columns of n+1th row and find a valid position.
    // if no valid queen column found in n+1th row, backtrack and go to nth row, and change the queen position to the next column and repeat the process
    private void nQueensDFSHelper(char[][] matrix, int row) {
        if (row == matrix.length) {
            result++;
            return;
        }
        for (int col = 0; col < matrix.length; col++) {
            matrix[row][col] = 'Q';   // mark the queen position to try out all the possibilities
            if (isValidPosition(matrix, row, col)) {
                nQueensDFSHelper(matrix, row + 1);
            }
            matrix[row][col] = '.';   // undo (un mark) the queen position
        }
    }

    // helper method to validate if the queen can be placed at a certain position (x and y coordinates) on current state of the board
    private static boolean isValidPosition(char[][] matrix, int x, int y) {
        for (int row = 0; row < x; row++) {   // check for the rows only before x as those are where the queen will be (as we are visiting the rows in sequential order)
            for (int col = 0; col < matrix.length; col++) {  // visit all the columns in these rows
                // when the matrix already has queen at [row, column] position, the new queen cannot be in this column or one of its diagonals (x-row and y-col -- better understood with an example: these conditions represents if the new queen is placed in one of the diagonals of an already existing queen. if yes, the new queen cannot be valid at the given position)
                if ((y == col || Math.abs(x - row) == Math.abs(y - col)) && matrix[row][col] == 'Q') {   // NOTE : important condition. don't forget to take care of it
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new _52_N_Queens_II().totalNQueens(1));
        System.out.println(new _52_N_Queens_II().totalNQueens(2));
        System.out.println(new _52_N_Queens_II().totalNQueens(3));
        System.out.println(new _52_N_Queens_II().totalNQueens(4));
    }
}