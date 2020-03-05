package med;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/29/20.
 * <p>
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * </p>
 */
public class _373_Find_K_Pairs_Smallest_Sums {
    // core logic: keep adding the sum of first and second elements in an increasing order and increment the indexes in heap accordingly
    // TC: O(klogk) - since the size of heap is always k
    // TODO: redo this later. check here again: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/128291/Java-Heap-with-Explanations
    private static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> kPairs = new ArrayList<>();
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 == 0 || len2 == 0) {
            return kPairs;
        }

        // Min heap of indices of u, v.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]]));
        minHeap.add(new int[]{0, 0});

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            kPairs.add(Arrays.asList(nums1[pair[0]], nums2[pair[1]]));
            if (pair[0] + 1 < len1)
                minHeap.add(new int[]{pair[0] + 1, pair[1]});
            if (pair[0] == 0 && pair[1] + 1 < len2)
                minHeap.add(new int[]{pair[0], pair[1] + 1});
        }
        return kPairs;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> in1 = Arrays.asList(1, 2);
        List<Integer> in2 = Arrays.asList(1, 4);
        List<Integer> in3 = Arrays.asList(1, 6);
        result.add(in1);
        result.add(in2);
        result.add(in3);
        assertEquals(kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3), result);
    }
}