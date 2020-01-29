package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/28/20.
 * <p>
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * </p>
 */
public class _254_Factor_Combinations {
    // simple dfs using recursion: start from '2'
    // TC: O(nlogn) - TODO: check back later
    // SC: O(logn) - stack space / height of the tree
    // TODO: check little optimized version later: https://leetcode.com/problems/factor-combinations/discuss/68041/My-short-Java-solution-which-Is-EASY-to-understand
    private static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 1) {
            return result;
        }
        dfs(n, 2, new ArrayList<>(), result);
        return result;
    }

    // dfs helper method
    private static void dfs(int n, int start, List<Integer> list, List<List<Integer>> result) {
        if (n <= 1) {
            if (list.size() > 1) {
                result.add(new ArrayList<>(list));
            }
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                dfs(n / i, i, list, result);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        assertEquals(getFactors(1), new ArrayList<List<Integer>>());
        assertEquals(getFactors(37), new ArrayList<List<Integer>>());

        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(2, 2, 3));
        input.add(Arrays.asList(2, 6));
        input.add(Arrays.asList(3, 4));
        assertEquals(getFactors(12), input);

        List<List<Integer>> input2 = new ArrayList<>();
        input2.add(Arrays.asList(2, 2, 2, 2, 2));
        input2.add(Arrays.asList(2, 2, 2, 4));
        input2.add(Arrays.asList(2, 2, 8));
        input2.add(Arrays.asList(2, 4, 4));
        input2.add(Arrays.asList(2, 16));
        input2.add(Arrays.asList(4, 8));
        assertEquals(getFactors(32), input2);
    }
}