package easy;

import java.util.LinkedList;
import java.util.Queue;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 3/14/20.
 * <p>
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
 * </p>
 */
public class _733_Flood_Fill {
    private static int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    // classic BFS: first add the src pixel to the queue and then navigate to all its valid directions, change its colors if needed and keep adding to the queue
    // TC: O(n)
    private static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return null;
        }
        Queue<int[]> queue = new LinkedList<>();
        int maxRows = image.length - 1;
        int maxCols = image[0].length - 1;
        int oldColor = image[sr][sc];

        queue.offer(new int[]{sr, sc});

        while (!queue.isEmpty()) {
            int[] currPos = queue.poll();
            image[currPos[0]][currPos[1]] = newColor;

            for (int[] dir : dirs) {
                int newPosX = dir[0] + currPos[0];
                int newPosY = dir[1] + currPos[1];
                if (newPosX < 0 || newPosX > maxRows || newPosY < 0 || newPosY > maxCols || image[newPosX][newPosY] != oldColor || image[newPosX][newPosY] == newColor) {
                    continue;
                }
                image[newPosX][newPosY] = newColor;
                queue.offer(new int[]{newPosX, newPosY});
            }
        }
        return image;
    }

    // classic DFS
    // TC: O(m * n) - where m is number of rows and n is number of columns, SC: O(n)
    private static int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {  // if the source already has new color, just return the current image
            return image;
        }
        dfsHelper(image, sr, sc, image[sr][sc], newColor);  // perform dfs for all other cases
        return image;
    }

    private static void dfsHelper(int[][] image, int i, int j, int oldColor, int newColor) {
        if (i < 0 || i > image.length - 1 || j < 0 || j > image[0].length - 1 || image[i][j] != oldColor) {
            return;
        }
        image[i][j] = newColor;
        dfsHelper(image, i, j - 1, oldColor, newColor);
        dfsHelper(image, i, j + 1, oldColor, newColor);
        dfsHelper(image, i - 1, j, oldColor, newColor);
        dfsHelper(image, i + 1, j, oldColor, newColor);
    }

    public static void main(String[] args) {
        assertEquals(floodFill(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2), new int[][]{{2, 2, 2}, {2, 2, 0}, {2, 0, 1}});
        assertEquals(floodFill(new int[][]{{0, 0, 0}, {0, 1, 0}}, 1, 1, 2), new int[][]{{0, 0, 0}, {0, 2, 0}});
        assertEquals(floodFill(new int[][]{{0, 0, 0}, {0, 1, 1}}, 1, 1, 1), new int[][]{{0, 0, 0}, {0, 1, 1}});

        assertEquals(floodFill2(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2), new int[][]{{2, 2, 2}, {2, 2, 0}, {2, 0, 1}});
        assertEquals(floodFill2(new int[][]{{0, 0, 0}, {0, 1, 0}}, 1, 1, 2), new int[][]{{0, 0, 0}, {0, 2, 0}});
        assertEquals(floodFill2(new int[][]{{0, 0, 0}, {0, 1, 1}}, 1, 1, 1), new int[][]{{0, 0, 0}, {0, 1, 1}});
    }
}