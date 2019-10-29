package med;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udaythota on 10/28/19.
 * <p>
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * </p>
 */
public class _216_Combination_Set_III {
    // same approach as combinations I and II: just iterate from 1 to 9 as the combinations could be from any of these digits
    private static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (k > n) {
            return result;
        }
        helper(k, n, result, new ArrayList<>(), 1);
        return result;
    }

    // simple DFS: same approach as combination I an II
    private static void helper(int totalNumbers, int remainingSum, List<List<Integer>> result, List<Integer> tempList, int start) {
        if (remainingSum < 0) {
            return;
        } else if (remainingSum == 0 && tempList.size() == totalNumbers) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i <= 9; i++) {
                tempList.add(i);
                helper(totalNumbers, remainingSum - i, result, tempList, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 7));
        System.out.println(combinationSum3(3, 9));
    }
}