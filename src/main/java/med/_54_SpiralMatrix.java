package med;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udaythota on 5/15/19.
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 */
public class _54_SpiralMatrix {
    // iterate traversing in all the 4 directions and stop when the processing of rows and columns are completed
    // the only tricky part is to check if the row or column has already been processed when traversing left or up so as to avoid duplicates
    private static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> resultList = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return resultList;
        }

        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            // traverse right
            for (int i = colStart; i <= colEnd; i++) {
                resultList.add(matrix[rowStart][i]);
            }
            rowStart++;

            // traverse down
            for (int i = rowStart; i <= rowEnd; i++) {
                resultList.add(matrix[i][colEnd]);
            }
            colEnd--;

            // traverse left
            if (rowStart <= rowEnd) {   // don't process if this row has already been processed (it could have already been processed when traversing from left to right)
                for (int i = colEnd; i >= colStart; i--) {
                    resultList.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;

            // traverse up   // don't process if this column has already been processed (it could have already been processed when traversing from top to down)
            if (colStart <= colEnd) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    resultList.add(matrix[i][colStart]);
                }
            }
            colStart++;
        }
        return resultList;
    }

    // the core concept is still same as the first one (keep processing all the four possibilities), but 2 things to note here are:
    // 1) we check the while condition for every row / column processing, which eliminates the need for putting some additional checks to evaluate if the row / column has already processed
    // 2) we use a direction value (either 0 / 1 / 2 / 3) to control the flow of the logic (every direction value dictates processing of row /column in a certain direction)
    private static List<Integer> spiralOrderUsingDirections(int[][] matrix) {
        List<Integer> resultList = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return resultList;
        }

        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;
        int direction = 0;

        // this condition becomes false only when all the elements from the matrix are processed
        while (rowStart <= rowEnd && colStart <= colEnd) {
            if (direction == 0) {
                for (int i = colStart; i <= colEnd; i++) {
                    resultList.add(matrix[rowStart][i]);
                }
                rowStart++;
            } else if (direction == 1) {
                for (int i = rowStart; i <= rowEnd; i++) {
                    resultList.add(matrix[i][colEnd]);
                }
                colEnd--;
            } else if (direction == 2) {
                for (int i = colEnd; i >= colStart; i--) {
                    resultList.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            } else {
                for (int i = rowEnd; i >= rowStart; i--) {
                    resultList.add(matrix[i][colStart]);
                }
                colStart++;
            }
            direction = (direction + 1) % 4;
        }
        return resultList;
    }

    public static void main(String[] args) {
        // test the first method
        int[][] input = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };

        int[][] input1 = new int[][]{
                {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
        };

        System.out.println(spiralOrder(input));
        System.out.println(spiralOrder(input1));

        // test the 2nd method
        int[][] input3 = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };

        int[][] input4 = new int[][]{
                {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
        };

        System.out.println(spiralOrderUsingDirections(input3));
        System.out.println(spiralOrderUsingDirections(input4));
    }
}