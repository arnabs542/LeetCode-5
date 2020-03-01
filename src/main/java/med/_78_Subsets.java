package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by udaythota on 4/15/19.
 * <p>
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * Input: nums = [1,2,3]
 * Output:
 * [[3],[1],[2],[1,2,3],[1,3],[2,3],[1,2],[]]
 * </p>
 */
public class _78_Subsets {
    // TC: O(N*2^N), SC: O(2^N)
    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);  // this is OPTIONAL as the order of elements in the subset is not needed
        backTrack(resultList, new ArrayList<Integer>(), nums, 0);
        return resultList;
    }

    private static void backTrack(List<List<Integer>> resultList, List<Integer> tempList, int[] nums, int start) {
        resultList.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backTrack(resultList, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }
}