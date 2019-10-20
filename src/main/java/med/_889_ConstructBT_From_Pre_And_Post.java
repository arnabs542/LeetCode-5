package _5_Longest_Palindromic_Substring;

/**
 * Created by udaythota on 6/3/19.
 * <p>
 * Return any binary tree that matches the given preorder and postorder traversals.
 * Values in the traversals pre and post are distinct positive integers.
 * </p>
 */
public class _889_ConstructBT_From_Pre_And_Post {

    // FIXME: complete this
    private static TreeNode constructFromPrePost(int[] pre, int[] post) {
        return null;
    }

    public static void main(String[] args) {
        int[] pre = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] post = new int[]{4, 5, 2, 6, 7, 3, 1};
        TreeNode root = constructFromPrePost(pre, post);
        BinaryTreeUtils.printLevelOrder(root);
    }
}