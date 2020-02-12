package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/11/20.
 * <p>
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 * </p>
 */
public class _272_Closest_BST_II {
    // core logic: maintain 2 stacks (one for storing elements lesser that target and other for greater for target), perform an inorder traversal (two times - once for filling one stack and other for filling one more stack), then consolidate 2 stacks to get the final K closest elements
    // TC: O(n) (for balanced its only O(logN)), SC: O(n)
    // TODO: may be we can do this better and much simpler. think about it later
    private static List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        if (root == null || k <= 0) {
            return result;
        }
        Stack<Integer> minTarget = new Stack<>();  // only contain elements lesser than target value
        Stack<Integer> maxTarget = new Stack<>();  // only contain elements greater than target value

        inorder(root, minTarget, false, target);
        inorder(root, maxTarget, true, target);

        // do a merge sort similar thing between 2 stacks and add k elements closest to target to the result
        while (k-- > 0) {
            if (maxTarget.isEmpty()) {
                result.add(minTarget.pop());
            } else if (minTarget.isEmpty()) {
                result.add(maxTarget.pop());
            } else {
                if (Math.abs(minTarget.peek() - target) < Math.abs(maxTarget.peek() - target)) {
                    result.add(minTarget.pop());
                } else {
                    result.add(maxTarget.pop());
                }
            }
        }

        return result;
    }

    // helper method to do the inorder traversal, but with the early termination (based on given conditions) without traversing the whole tree
    private static void inorder(TreeNode root, Stack<Integer> stack, boolean reverse, double target) {
        if (root == null) {
            return;
        }
        inorder(reverse ? root.right : root.left, stack, reverse, target);

        if ((reverse && target >= root.val) || (!reverse && target < root.val)) {  // early termination condition (as stack only need to contain elements either greater or lesser than target)
            return;
        }
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, stack, reverse, target);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);

        root.left = node2;
        root.right = node5;
        node2.left = node1;
        node2.right = node3;
        assertEquals(closestKValues(root, 3.714286, 2), Arrays.asList(4, 3));
    }
}