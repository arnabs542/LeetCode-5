package med;

import java.util.*;

/**
 * Created by udaythota on 11/9/19.
 * <p>
 * Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * </p>
 */
public class _429_N_Ary_Level_Order_Traversal {
    // simple level order BFS: just use an array list instead of left and right links in the tree node structure
    private static List<List<Integer>> levelOrder(NAryNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<NAryNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < queueSize; i++) {
                NAryNode temp = queue.poll();
                level.add(temp.val);
                for (NAryNode children : temp.children) {
                    queue.offer(children);
                }
            }
            result.add(level);
        }
        return result;
    }

    public static void main(String[] args) {
        NAryNode node5 = new NAryNode(5, new ArrayList<>());
        NAryNode node6 = new NAryNode(6, new ArrayList<>());
        NAryNode node2 = new NAryNode(2, new ArrayList<>());
        NAryNode node4 = new NAryNode(4, new ArrayList<>());
        NAryNode node3 = new NAryNode(3, Arrays.asList(node5, node6));
        NAryNode root = new NAryNode(1, Arrays.asList(node3, node2, node4));

        List<List<Integer>> result = levelOrder(root);
        System.out.println(result);
    }
}