package med;

import java.util.Stack;

import static org.testng.Assert.*;

/**
 * Created by udaythota on 6/1/19.
 * <p>
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * </p>
 */
public class _173_BSTIterator {
    private Stack<TreeNode> stack;
    private TreeNode current;

    // core logic: simple in-order traversal as the elements in the BST follows inorder traversal
    private _173_BSTIterator(TreeNode root) {
        stack = new Stack<>();
        current = root;
    }

    /**
     * @return the next smallest number
     */
    private int next() {
        int result = 0;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                result = current.val;
                current = current.right;
                break;   // break the loop once you get the next value
            }
        }
        return result;
    }

    /**
     * @return whether we have a next smallest number
     */
    private boolean hasNext() {
        return !stack.isEmpty() || current != null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(15);
        TreeNode node3 = new TreeNode(9);
        TreeNode node4 = new TreeNode(20);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        _173_BSTIterator iterator = new _173_BSTIterator(root);
        assertEquals(iterator.next(), 3);
        assertEquals(iterator.next(), 7);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), 9);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), 15);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), 20);
        assertFalse(iterator.hasNext());
    }
}
