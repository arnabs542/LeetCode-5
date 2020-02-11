package easy;

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
public class _339_Nested_List_Weight_Sum {
    // core logic: simple BFS. just make sure to add the nested lists to queue properly and increment the level variable as needed
    // DFS approach is much simpler to do this. see the below alternate DFS approach
    private static int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }

        int sum = 0, level = 1;
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);

        while (queue.size() > 0) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                NestedInteger nestedInteger = queue.poll();
                if (nestedInteger.isInteger()) {
                    sum += nestedInteger.getInteger() * level;
                } else {
                    queue.addAll(nestedInteger.getList());
                }
            }
            level++;
        }
        return sum;
    }

    // core logic: simple dfs: start from depth 1 and go deep (dfs), increment depth accordingly and calculate the total sum
    private int sum2 = 0;

    private int depthSum2(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }

        for (NestedInteger nestedInteger : nestedList) {
            dfsHelper(nestedInteger, 1);
        }
        return sum2;
    }

    // dfs helper to calculate the cumulative sum for all the levels
    private void dfsHelper(NestedInteger nestedInteger, int depth) {
        if (nestedInteger.isInteger()) {
            sum2 += nestedInteger.getInteger() * depth;
        } else {
            for (NestedInteger ni : nestedInteger.getList()) {
                dfsHelper(ni, depth + 1);
            }
        }
    }

    // core logic: simple dfs: exact same as above approach, just a diff way of writing the code (for loop inside the helper method itself)
    private int sum3 = 0;

    private int depthSum3(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }
        dfsHelper3(nestedList, 1);
        return sum3;
    }

    // dfs helper to calculate the cumulative sum for all the levels
    private int dfsHelper3(List<NestedInteger> nestedList, int depth) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum3 += nestedInteger.getInteger() * depth;
            } else {
                dfsHelper3(nestedInteger.getList(), depth + 1);
            }
        }
        return sum3;
    }
}