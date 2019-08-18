package med;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/18/19.
 * <p>
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * </p>
 */
public class _314_Vertical_Order_Traversal_BT {
    // core logic: perform BFS, and in the process, along with the nodes, also store the node values in their corresponding columns (root = column0, roor.left = column -1, root.right = column +1)
    // also maintain 2 pointers (min and max) to keep track of minimum and max columns processed and update them at each and every step - this is useful in iterating the columns from min to max when saving them to result
    private static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> columns = new LinkedList<>();
        nodes.offer(root);
        columns.offer(0);
        HashMap<Integer, List<Integer>> columnNodesMap = new HashMap<>();
        int minCol = 0, maxCol = 0;

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            Integer column = columns.poll();

            if (!columnNodesMap.containsKey(column)) {
                columnNodesMap.put(column, new ArrayList<>());
            }
            columnNodesMap.get(column).add(node.val);

            if (node.left != null) {
                nodes.add(node.left);
                columns.add(column - 1);
                minCol = Math.min(minCol, column - 1);
            }
            if (node.right != null) {
                nodes.add(node.right);
                columns.add(column + 1);
                maxCol = Math.max(maxCol, column + 1);
            }
        }
        for (int i = minCol; i <= maxCol; i++) {
            result.add(columnNodesMap.get(i));
        }
        return result;
    }

    // core logic: same as above logic except the need of min and max pointers is removed by using a tree map instead (as tree map stores the keys in the ascending order - min and max columns are first and last keys in the map)
    private static List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> columns = new LinkedList<>();
        nodes.offer(root);
        columns.offer(0);
        TreeMap<Integer, List<Integer>> columnNodesMap = new TreeMap<>();

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            Integer column = columns.poll();

            if (!columnNodesMap.containsKey(column)) {
                columnNodesMap.put(column, new ArrayList<>());
            }
            columnNodesMap.get(column).add(node.val);

            if (node.left != null) {
                nodes.add(node.left);
                columns.add(column - 1);
            }
            if (node.right != null) {
                nodes.add(node.right);
                columns.add(column + 1);
            }
        }
        for (Integer key : columnNodesMap.keySet()) {
            result.add(columnNodesMap.get(key));
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node8 = new TreeNode(8);
        TreeNode node4 = new TreeNode(4);
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node7 = new TreeNode(7);
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);

        root.left = node9;
        root.right = node8;
        node9.left = node4;
        node9.right = node0;
        node8.left = node1;
        node8.right = node7;
        node0.right = node2;
        node1.left = node5;
        assertEquals(verticalOrder(root), Arrays.asList(Arrays.asList(4), Arrays.asList(9, 5), Arrays.asList(3, 0, 1), Arrays.asList(8, 2), Arrays.asList(7)));
        assertEquals(verticalOrder2(root), Arrays.asList(Arrays.asList(4), Arrays.asList(9, 5), Arrays.asList(3, 0, 1), Arrays.asList(8, 2), Arrays.asList(7)));
    }
}