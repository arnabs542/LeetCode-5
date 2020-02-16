package med;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udaythota on 4/24/19.
 * <p>
 * Given a collection of distinct integers, return all possible permutations.
 * Input: [1,2,3]
 * Output:
 * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 */
public class _46_Permutations {
    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        // Arrays.sort(nums);  /// redundant as order is not needed
        backtrack(resultList, new ArrayList<Integer>(), nums);
        return resultList;
    }

    private static void backtrack(List<List<Integer>> resultList, ArrayList<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {   // base condition
            resultList.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) {
                    continue;   // if the element is already present, don't add it
                } else {
                    tempList.add(nums[i]);
                }
                backtrack(resultList, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3};
        System.out.println(permute(input));
    }
}