package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by udaythota on 4/16/19.
 * <p>
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set). Note: The solution set must not contain duplicate subsets.
 * Input: [1,2,2]
 * Output:[[2],[1],[1,2,2],[2,2],[1,2],[]]
 * </p>
 */
public class _90_Subsets_II {
    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);  // this is NEEDED to avoid processing the duplicates
        backTrack(resultList, new ArrayList<Integer>(), nums, 0);
        return resultList;
    }

    private static void backTrack(List<List<Integer>> resultList, List<Integer> tempList, int[] nums, int start) {
        resultList.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {  // skip processing duplicates
                continue;
            }
            tempList.add(nums[i]);
            backTrack(resultList, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 2}));
        System.out.println(subsets(new int[]{4, 4, 4, 1, 4}));
    }
}