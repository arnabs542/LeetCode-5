package random_practice.amazon_assessment;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by z002jsf on 3/2/20.
 * Actual asked question in the assessment
 * Similar to Zombie Matrix
 */
public class Min_Hours_Challenge {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    private int minimumHours(int rows, int columns, List<List<Integer>> grid) {
        if (grid == null || grid.size() == 0) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();
        int minimumHours = 0;
        int missingServers = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid.get(i).get(j) == 0) {
                    missingServers++;
                } else {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        while (!queue.isEmpty() && missingServers > 0) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] missingServer = queue.poll();
                for (int[] dir : directions) {
                    int newX = missingServer[0] + dir[0];
                    int newY = missingServer[1] + dir[1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < columns && grid.get(newX).get(newY) == 0) {
                        grid.get(newX).set(newY, 1);
                        queue.offer(new int[]{newX, newY});
                        missingServers--;
                    }
                }
            }
            minimumHours++;
        }
        return missingServers == 0 ? minimumHours : -1;
    }
}