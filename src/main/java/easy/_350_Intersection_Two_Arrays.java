package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/15/19.
 * <p>
 * Given two arrays, write a function to compute their intersection.
 * </p>
 */
public class _350_Intersection_Two_Arrays {
    private static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                result.add(num);
                map.put(num, map.get(num) - 1);
            }
        }

        int[] res = new int[result.size()];
        int i = 0;
        for (int num : result) {
            res[i++] = num;
        }
        return res;
    }

    public static void main(String[] args) {
        assertEquals(intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2}), new int[]{2, 2});
        assertEquals(intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}), new int[]{9, 4});
    }
}