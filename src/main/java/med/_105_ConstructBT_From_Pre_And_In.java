package med;

import java.util.HashMap;

/**
 * Created by udaythota on 6/2/19.
 * <p>
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * </p>
 */
public class _105_ConstructBT_From_Pre_And_In {

    // core logic of the problem lies in identifying the left and right subtree sizes in the pre order traversal
    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode helper(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int index = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == preOrder[preStart]) {
                index = i;
                break;
            }
        }
        TreeNode root = new TreeNode(inOrder[index]);
        root.left = helper(preOrder, preStart + 1, index - inStart + preStart, inOrder, inStart, index - 1);
        root.right = helper(preOrder, index - inStart + preStart + 1, preEnd, inOrder, index + 1, inEnd);
        return root;
    }

    // same as above solution, except that we pre calculate the inorder index look up and then use it later
    private static HashMap<Integer, Integer> indexLookUpMap = new HashMap<>();

    private static TreeNode buildTree2(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexLookUpMap.put(inorder[i], i);
        }
        return helper2(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode helper2(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[preStart]);
        int index = indexLookUpMap.get(preOrder[preStart]);

        root.left = helper2(preOrder, preStart + 1, index - inStart + preStart, inOrder, inStart, index - 1);
        root.right = helper2(preOrder, index - inStart + preStart + 1, preEnd, inOrder, index + 1, inEnd);
        return root;
    }

    public static void main(String[] args) {
        int[] pre = new int[]{3, 9, 20, 15, 7};
        int[] in = new int[]{9, 3, 15, 20, 7};
        TreeNode root = buildTree(pre, in);
        BinaryTreeUtils.printLevelOrder(root);

        System.out.println();
        TreeNode root1 = buildTree2(pre, in);
        BinaryTreeUtils.printLevelOrder(root1);
    }
}
