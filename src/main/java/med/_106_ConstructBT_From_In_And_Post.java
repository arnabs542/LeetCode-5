package _5_Longest_Palindromic_Substring;

/**
 * Created by udaythota on 6/2/19.
 * <p>
 * Construct Binary Tree from Inorder and Postorder Traversal
 * </p>
 */
public class _106_ConstructBT_From_In_And_Post {

    private static TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private static TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postOrder, int postStart, int postEnd) {
        if (postStart > postEnd) {
            return null;
        }
        int index = 0;
        for (int i = 0; i <= inorder.length; i++) {
            if (inorder[i] == postOrder[postEnd]) {
                index = i;
                break;
            }
        }

        TreeNode root = new TreeNode(postOrder[postEnd]);
        root.left = helper(inorder, inStart, index - 1, postOrder, postStart, postStart + index - 1 - inStart);
        root.right = helper(inorder, index + 1, inEnd, postOrder, postStart + index - inStart, postEnd - 1);
        return root;
    }

    public static void main(String[] args) {
        int[] in = new int[]{9, 3, 15, 20, 7};
        int[] post = new int[]{9, 15, 7, 20, 3};
        TreeNode root = buildTree(in, post);
        BinaryTreeUtils.printLevelOrder(root);
    }
}
