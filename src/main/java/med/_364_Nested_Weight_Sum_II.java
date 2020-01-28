package med;

import easy.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by udaythota on 1/28/20.
 * <p>
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * </p>
 */
public class _364_Nested_Weight_Sum_II {
    // simple BFS: traverse the list and keep a cumulative sum variable as needed
    // similar logic to LC: 339. The only trick is "NEVER RESET prev sum for each level". Say, for input with 3 levels, the weight of int on the first level will be added 3 times to total sum because prev sum contains its value and is never reset.
    private static int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }

        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        int prevSum = 0, totalSum = 0;

        while (queue.size() > 0) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                NestedInteger nestedInteger = queue.poll();
                if (nestedInteger.isInteger()) {
                    prevSum += nestedInteger.getInteger();
                } else {
                    queue.addAll(nestedInteger.getList());
                }
            }
            totalSum += prevSum;  // NOTE: DON'T RESET the prev sum variable, so it gets counted repeatedly at each level
        }
        return totalSum;
    }

    // simple DFS: compute the list depth first and use that depth to calculate the total sum for all the elements in the list. the first level elements will have the max depth and 2nd level elements will have depth = depth - 1, so on..
    private static int depthSum2(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }
        int depth = getListDepth(nestedList);
        return dfs(nestedList, depth);
    }

    // dfs helper method
    private static int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum += nestedInteger.getInteger() * depth;
            } else {
                sum += dfs(nestedInteger.getList(), depth - 1);
            }
        }
        return sum;
    }

    // helper method to compute the depth of the given nested list
    private static int getListDepth(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int max = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                max = Math.max(max, 1);
            } else {
                max = Math.max(max, getListDepth(nestedInteger.getList()) + 1);
            }
        }
        return max;
    }
}