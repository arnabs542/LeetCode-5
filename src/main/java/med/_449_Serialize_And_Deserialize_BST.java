package _5_Longest_Palindromic_Substring;

/**
 * Created by udaythota on 6/4/19.
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * </p>
 */
public class _449_Serialize_And_Deserialize_BST {
    // Encodes a tree to a single string.
    // Return the pre order traversal of BST (as we can de-serialize back BST using pre-order elements). We can construct a BST only from pre order traversal.
    private String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.val).append(",");
        stringBuilder.append(serialize(root.left));
        stringBuilder.append(serialize(root.right));
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    // Convert the serialized string (pre order elements) back to BST. Similar approach as in LC: 1008, except that instead of a global variable there, we here use a nodeIndex array to keep a track of the number of visited elements
    private TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] preorder = data.split(",");
        int[] nodeIndex = {0};
        return helper(preorder, nodeIndex, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static TreeNode helper(String[] preorder, int nodeIndex[], int min, int max) {
        if (nodeIndex[0] == preorder.length || Integer.parseInt(preorder[nodeIndex[0]]) < min || Integer.parseInt(preorder[nodeIndex[0]]) > max) {
            return null;
        }

        int nodeValue = Integer.parseInt(preorder[nodeIndex[0]]);
        TreeNode root = new TreeNode(nodeValue);
        nodeIndex[0]++;
        root.left = helper(preorder, nodeIndex, min, nodeValue);
        root.right = helper(preorder, nodeIndex, nodeValue, max);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node4.left = node7;
        node4.right = node8;

        _449_Serialize_And_Deserialize_BST serialize_and_deserialize_bst = new _449_Serialize_And_Deserialize_BST();
        String serializedString = serialize_and_deserialize_bst.serialize(root);
        System.out.println(serializedString);

        TreeNode newRoot = serialize_and_deserialize_bst.deserialize(serializedString);
        BinaryTreeUtils.printLevelOrder(newRoot);
    }
}
