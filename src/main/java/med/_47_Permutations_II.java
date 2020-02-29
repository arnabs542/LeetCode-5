package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by udaythota on 5/18/19.
 * <p>
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * Example:
 * Input: [1,1,2]
 * Output:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * </p>
 */
public class _47_Permutations_II {
    // DFS: use the visited array to keep the track of duplicates and not to visit the same node multiple times
    // TC: O(n!) -> For i position, there're (n-i) possibilities, so the total possibilities are: n(n-1)*(n-2)..1 = O(n!)
    private static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);  // this is needed to keep the track of duplicate numbers
        permuteBacktrack(resultList, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return resultList;
    }

    private static void permuteBacktrack(List<List<Integer>> resultList, ArrayList<Integer> tempList, int[] nums, boolean[] vistedNums) {
        if (tempList.size() == nums.length) {
            resultList.add(new ArrayList<>(tempList));   // make sure to add to new array list as we are in recursion
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (vistedNums[i] || i > 0 && nums[i] == nums[i - 1] && !vistedNums[i - 1]) {    // IMPORTANT: last condition means that, when current and prev numbers are same and if prev number is not used, use the current number, else just continue the loop
                    continue;
                }
                vistedNums[i] = true;  // set the visited to true once the number is scanned
                tempList.add(nums[i]);
                permuteBacktrack(resultList, tempList, nums, vistedNums);

                // set the visited to false and remove the last element so it could searching for new permutations
                vistedNums[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> resultList = permuteUnique(new int[]{1, 1, 0, 3,});
        System.out.println(resultList);

        List<List<Integer>> resultList1 = permuteUnique(new int[]{1, 1, 2, 2, 3});
        System.out.println(resultList1);

        List<List<Integer>> resultList2 = permuteUnique(new int[]{3, 3, 0, 3});  // NOTE: this test case should fail if Arrays.sort() is removed
        System.out.println(resultList2);
    }
}