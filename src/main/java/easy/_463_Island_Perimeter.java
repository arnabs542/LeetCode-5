package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/12/20.
 * <p>
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * </p>
 */
public class _463_Island_Perimeter {
    // core logic: based on if the land value is grid ('1'), calculate the perimeter accordingly. navigate the whole grid, and for an island, if the adjacent grid value value is water, it contributes to a perimeter, otherwise (if its an island), it doesn't add to the perimeter
    // TC: O(m * n) -> where m is number of rows and n is the number of columns
    private static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int perimeter = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i - 1][j] == 0) {
                        perimeter++;
                    }
                    if (i == rows - 1 || grid[i + 1][j] == 0) {
                        perimeter++;
                    }
                    if (j == 0 || grid[i][j - 1] == 0) {
                        perimeter++;
                    }
                    if (j == cols - 1 || grid[i][j + 1] == 0) {
                        perimeter++;
                    }
                }
            }
        }
        return perimeter;
    }

    public static void main(String[] args) {
        Assert.assertEquals(islandPerimeter(new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}}), 16);
    }
}
