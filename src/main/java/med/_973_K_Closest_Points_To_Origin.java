package _5_Longest_Palindromic_Substring;

import java.util.Arrays;
import java.util.PriorityQueue;

import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 8/17/19.
 * <p>
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * </p>
 */
public class _973_K_Closest_Points_To_Origin {
    // core logic: maintain a MAX HEAP with size K. for each point, we add it to the heap
    // once the size of the heap is greater than K, we are supposed to extract one from the max heap to ensure the size of the heap is always K
    // thus, the max heap is always maintain top K smallest elements from the first one to current one
    // TC: O(NlogK)
    private static int[][] kClosest(int[][] points, int K) {
        int[][] result = new int[K][0];
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> (o2[0] * o2[0] + o2[1] * o2[1]) - (o1[0] * o1[0] + o1[1] * o1[1]));  // max heap
        for (int[] point : points) {
            queue.add(point);
            if (queue.size() > K) {
                queue.poll();
            }
        }

        for (int i = 0; i < K; i++) {
            result[i] = queue.poll();
        }
        return result;
    }

    // core logic: similar to above except that this is using MIN HEAP
    // this is NOT an OPTIMAL solution as we insert all the elements to the heap and then process it (take the smallest elements out)
    // rather use the MAX HEAP and whenever the size exceeds K, remove the max element, so finally you are left with K min elements (see above solution) - advantage: you never exceed queue size > K
    private static int[][] kClosest3(int[][] points, int K) {
        int[][] result = new int[K][0];
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]));  // max heap
        for (int[] point : points) {
            queue.add(point);
        }

        for (int i = 0; i < K; i++) {
            result[i] = queue.poll();
        }
        return result;
    }

    // core logic: sort the array based on the euclidean distance and return the array with top K elements
    // TC: O(NlogN)
    private static int[][] kClosest2(int[][] points, int K) {
        Arrays.sort(points, (o1, o2) -> (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]));
        return Arrays.copyOfRange(points, 0, K);
    }

    public static void main(String[] args) {
        // test method: 1
        assertTrue(Arrays.deepEquals(kClosest(new int[][]{{1, 3}, {-2, 2}}, 1), new int[][]{{-2, 2}}));
        assertTrue(Arrays.deepEquals(kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2), new int[][]{{-2, 4}, {3, 3}}));

        // test method: 2
        assertTrue(Arrays.deepEquals(kClosest2(new int[][]{{1, 3}, {-2, 2}}, 1), new int[][]{{-2, 2}}));
        assertTrue(Arrays.deepEquals(kClosest2(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2), new int[][]{{3, 3}, {-2, 4}}));

        System.out.println(Arrays.deepToString(kClosest3(new int[][]{{1, 3}, {-2, 2}}, 1)));
        System.out.println(Arrays.deepToString(kClosest3(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2)));
    }
}