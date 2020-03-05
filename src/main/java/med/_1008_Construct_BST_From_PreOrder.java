package med;

/**
 * Created by udaythota on 6/3/19.
 * <p>
 * Return the root node of a binary search tree that matches the given preorder traversal.
 * </p>
 */
public class _1008_Construct_BST_From_PreOrder {
    // core logic: find the index in the pre order array where the index value of the element is greater than 1st element (root)
    // this means from 2nd element to before this index - its a left tree, from index to last element  - its a right tree
    // use recursion to generate the left and right sub trees
    // TC: O(n^2) as we have to find index for each and every function call
    private static TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1);
    }

    private static TreeNode helper(int[] preorder, int preStart, int preEnd) {
        if (preStart > preEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int i;
        for (i = preStart; i <= preEnd; i++) {
            if (preorder[i] > root.val) {
                break;
            }
        }

        root.left = helper(preorder, preStart + 1, i - 1);
        root.right = helper(preorder, i, preEnd);
        return root;
    }


    private static int nodeIndex = 0;

    // core logic: have the min and max elements bound for each and every element and traverse recursively
    // TC: O(n) as all the elements are visited exactly once
    private static TreeNode bstFromPreorder2(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return helper2(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static TreeNode helper2(int[] preorder, int min, int max) {
        if (nodeIndex == preorder.length || preorder[nodeIndex] < min || preorder[nodeIndex] > max) {
            return null;
        }

        int nodeValue = preorder[nodeIndex++];
        TreeNode root = new TreeNode(nodeValue);
        root.left = helper2(preorder, min, nodeValue);
        root.right = helper2(preorder, nodeValue, max);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{8, 5, 1, 7, 10, 12};
        // test 1st method
        TreeNode root = bstFromPreorder(preorder);
        BinaryTreeUtils.printLevelOrder(root);

        // test 2nd method
        System.out.println();
        TreeNode root2 = bstFromPreorder2(preorder);
        BinaryTreeUtils.printLevelOrder(root2);
    }
}
