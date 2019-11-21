package med;

/**
 * Created by udaythota on 6/4/19.
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * </p>
 */
public class _449_Serialize_And_Deserialize_BST {
    // Encodes a tree to a single string.
    // Return the pre order traversal of BST (as we can de-serialize back BST using pre-order elements). We can construct a BST ONLY from pre order traversal.
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
    // Convert the serialized string (pre order elements) back to BST. Same as LC 1008: construct BST from pre order traversal
    private TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        data = data.substring(0, data.length() - 1);
        String[] preorder = data.split(",");
        return helper(preorder, 0, preorder.length - 1);
    }

    private static TreeNode helper(String[] preorder, int preStart, int preEnd) {
        if (preStart > preEnd) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(preorder[preStart]));
        int i;
        for (i = preStart; i <= preEnd; i++) {
            if (Integer.valueOf(preorder[i]) > root.val) {
                break;
            }
        }

        root.left = helper(preorder, preStart + 1, i - 1);
        root.right = helper(preorder, i, preEnd);
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
