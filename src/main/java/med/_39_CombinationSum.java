package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by udaythota on 4/14/19.
 * <p>
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [[7],[2,2,3]]
 * </p>
 */
public class _39_CombinationSum {
    // TC: O(N^target), where N is the length of the candidate array -> Every time you have N choices (since you can use number repeatedly), and you can choose at most target times (actually it's smaller than target), so we can assume the worst-case time complexity is O(N^target).
    // SC: O(target)
    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        // Arrays.sort(candidates);  // optional as we don't need ordering in the results
        backtrack(resultList, new ArrayList<Integer>(), candidates, target, 0);
        return resultList;
    }

    // The core logic here is that as the array is already sorted, we try to evaluate each and every possible case that could add up to the given target.
    // If the sum matches (remaining target == 0), add that combination (temp list), to the result list. Break the existing loop (base case) when the current target > given target
    // flow of execution in DFS: 2222 -> 2223 -> 2226 -> 2227 -> 223 (add to the list) -> 224 -> 226 -> 227 -> 23 -> 233 -> 236 -> 237 -> 26 -> 27 -> 333 -> ....
    private static void backtrack(List<List<Integer>> resultList, List<Integer> tempList, int[] candidates, int remainingTarget, int start) {
        if (remainingTarget < 0) {
            return;
        } else if (remainingTarget == 0) {
            resultList.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                backtrack(resultList, tempList, candidates, remainingTarget - candidates[i], i);  // IMPORTANT: as we can use reuse the same elements (any number of times) to generate the target value, it should start from i and NOT i+1
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> actualResult = combinationSum(new int[]{6, 3, 7, 2}, 7);
        List<List<Integer>> expectedResult = new ArrayList<>();
        expectedResult.add(Arrays.asList(2, 2, 3));
        expectedResult.add(Arrays.asList(7));
        assertEquals(actualResult, expectedResult);

        List<List<Integer>> actualResult2 = combinationSum(new int[]{2, 3, 5}, 8);
        List<List<Integer>> expectedResult2 = new ArrayList<>();
        expectedResult2.add(Arrays.asList(2, 2, 2, 2));
        expectedResult2.add(Arrays.asList(2, 3, 3));
        expectedResult2.add(Arrays.asList(3, 5));
        assertEquals(actualResult2, expectedResult2);
    }
}