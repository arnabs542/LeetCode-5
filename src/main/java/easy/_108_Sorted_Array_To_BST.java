package easy;

/**
 * Created by udaythota on 7/24/19.
 * <p>
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * </p>
 */
public class _108_Sorted_Array_To_BST {

    // core logic: middle of the list would be the root and recurse left and right lists to get the left and right sub trees
    private static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return helper(nums, 0, nums.length - 1);
    }

    private static TreeNode helper(int[] nums, int start, int end) {
        if (start > end) {  // base case
            return null;
        }

        int mid = start + (end - start) / 2;  // just to avoid integer over flow: otherwise equivalent to start + end / 2
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, start, mid - 1);
        root.right = helper(nums, mid + 1, end);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        BinaryTreeUtils.printLevelOrder(root);
    }
}