package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by udaythota on 4/14/19.
 * <p>
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is: [[1, 7], [1, 2, 5], [2, 6], [1, 1, 6]]
 * <p/>
 */
public class _40_CombinationSum2 {
    // TC: O(2^n) - TODO
    // little better time complexity explanation: https://leetcode.com/problems/combination-sum-ii/discuss/16871/time-complexity-analysis-of-recursive-approach
    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(resultList, new ArrayList<Integer>(), candidates, target, 0);
        return resultList;
    }

    // NOTE: the only differences from the other combination sum solution is that: a) the start pointer should be i+1   b) skip processing the same elements
    private static void backtrack(List<List<Integer>> resultList, List<Integer> tempList, int[] candidates, int remainingTarget, int start) {
        if (remainingTarget < 0) {
            return;
        } else if (remainingTarget == 0) {
            resultList.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;  // to skip the same elements as they CANNOT be reused
                }
                tempList.add(candidates[i]);
                backtrack(resultList, tempList, candidates, remainingTarget - candidates[i], i + 1);  // IMPORTANT: as we CANNOT use reuse the same elements (any number of times) to generate the target value, start pointer should be i+1 and NOT i
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> actualResult = combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        List<List<Integer>> expectedResult = new ArrayList<>();
        expectedResult.add(Arrays.asList(1, 1, 6));
        expectedResult.add(Arrays.asList(1, 2, 5));
        expectedResult.add(Arrays.asList(1, 7));
        expectedResult.add(Arrays.asList(2, 6));
        assertEquals(actualResult, expectedResult);

        List<List<Integer>> actualResult2 = combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        List<List<Integer>> expectedResult2 = new ArrayList<>();
        expectedResult2.add(Arrays.asList(1, 2, 2));
        expectedResult2.add(Arrays.asList(5));
        assertEquals(actualResult2, expectedResult2);
    }
}