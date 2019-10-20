package _5_Longest_Palindromic_Substring;

import java.util.PriorityQueue;

/**
 * Created by udaythota on 7/20/19.
 * <p>
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * </p>
 */
public class _378_kthSmallestElement_SortedMatrix {

    // core logic: maintain a min heap and keep adding elements in the right order. when you see kth smallest element, return it
    // when you encounter the first row, add elements to the right (same row, next column) in addition to adding its bottom elements (next row, same column)
    // in all the cases just keep adding the bottom elements to the queue (next row, same column)
    // TC: O(klogn)
    // this can also be done using binary search approach: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/128601/2-Java-Solutions-Binary-Search-and-Min-Heap
    private static int kthSmallest(final int[][] matrix, int k) {
        int c = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, (o1, o2) -> matrix[o1[0]][o1[1]] - matrix[o2[0]][o2[1]]);
        queue.offer(new int[]{0, 0});
        while (true) {
            int[] pair = queue.poll();
            if (++c == k) {    // return when you see kth smallest
                return matrix[pair[0]][pair[1]];
            }

            if (pair[0] == 0 && pair[1] + 1 < matrix.length) {    // to handle the first row (add elements to its right)
                queue.offer(new int[]{0, pair[1] + 1});
            }
            if (pair[0] + 1 < matrix.length) {   // add element to its immediate down
                queue.offer(new int[]{pair[0] + 1, pair[1]});
            }
        }
    }

    // trivial: add all the element to priority queue and poll the queue till you get the kth smallest element
    // TC: O(n^2)
    private static int kthSmallest2(final int[][] matrix, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                queue.offer(matrix[i][j]);
            }
        }

        for (int i = 0; i < k - 1; i++) {
            queue.poll();
        }
        return queue.poll();
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        System.out.println(kthSmallest(matrix, 8));
        System.out.println(kthSmallest2(matrix, 8));

        int[][] matrix1 = new int[][]{{1, 6, 12}, {5, 8, 13}, {9, 13, 15}};
        System.out.println(kthSmallest(matrix1, 4));
        System.out.println(kthSmallest2(matrix1, 4));
    }
}
