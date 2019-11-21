package hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 8/12/19.
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * </p>
 */
public class _297_Serialize_And_Deserialize_BT {
    // Encodes a tree to a single string.
    // simple level order traversal
    private static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current != null) {
                stringBuilder.append(current.val + ",");
                queue.offer(current.left);
                queue.offer(current.right);
            } else {
                stringBuilder.append("null" + ",");
            }
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    // simple level order traversal
    private static TreeNode deserialize(String data) {
        if (data == null || data.equals("")) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        int i = 0;
        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(nodes[i++]));
        queue.offer(root);
        while (!queue.isEmpty()) {
        TreeNode current = queue.poll();
            if (!nodes[i].equals("null")) {
                current.left = new TreeNode(Integer.valueOf(nodes[i]));
                queue.offer(current.left);
            }
            i++;

            if (!nodes[i].equals("null")) {
                current.right = new TreeNode(Integer.valueOf(nodes[i]));
                queue.offer(current.right);
            }
            i++;
        }
        return root;
    }

    // simple level order traversal : same as above approach. just a different style of approach
    private static TreeNode deserialize2(String data) {
        if (data == null || data.equals("")) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        queue.offer(root);

        for (int i = 1; i < nodes.length; i++) {
            TreeNode current = queue.poll();
            if (!nodes[i].equals("null")) {
                current.left = new TreeNode(Integer.valueOf(nodes[i]));
                queue.add(current.left);
            }

            if (!nodes[++i].equals("null")) {     // or do i++ before this
                current.right = new TreeNode(Integer.valueOf(nodes[i]));
                queue.add(current.right);
            }
        }
        return root;
    }

    // recursive: using pre order traversal
    private static String serializeRecursive(TreeNode root) {
        if (root == null) {
            return "null";
        }
        return root.val + "," + serializeRecursive(root.left) + "," + serializeRecursive(root.right);
    }

    // recursive: using pre order traversal
    private static TreeNode deserializeRecursive(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return helper(queue);
    }

    // recursive: helper method
    private static TreeNode helper(Queue<String> queue) {
        String current = queue.poll();
        if (current.equals("null")) {  // base case
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(current));
        root.left = helper(queue);
        root.right = helper(queue);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        System.out.println(serialize(root));
        System.out.println(serializeRecursive(root));

        // TreeNode newRoot = deserialize("1,2,3,null,null,4,5");
        // printLevelOrder(newRoot);  // as this utility function doesn't print the nulls for final root leaves
    }
}