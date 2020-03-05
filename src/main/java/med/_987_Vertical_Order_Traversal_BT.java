package med;

import java.util.*;

/**
 * Created by udaythota on 2/2/20.
 * <p>
 * Given a binary tree, return the vertical order traversal of its nodes values.
 * </p>
 */
public class _987_Vertical_Order_Traversal_BT {
    // core logic: using BFS + hash map
    // TODO: though the logic is correct, somehow this doesn't work for few of the corner cases in LC. Investigate later
    // TODO: solve it using DFS as well: would be little shorter
    private static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;
        Queue<Integer> pos = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        pos.offer(0);
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        while (!queue.isEmpty()) {
            int position = pos.poll();   // FIXME: we need to operations based on the queue size ??
            min = Math.min(position, min);   // this is needed as in the end we need to know the min and max keys in the hash map to add them to the result list
            max = Math.max(position, max);   // this is needed as in the end we need to know the min and max keys in the hash map to add them to the result list
            TreeNode temp = queue.poll();

            map.putIfAbsent(position, new ArrayList<>());
            if (map.get(position) != null) {
                List<Integer> tempList = map.get(position);
                tempList.add(temp.val);
                map.put(position, tempList);
            }

            if (temp.left != null) {
                queue.offer(temp.left);
                pos.offer(position - 1);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
                pos.offer(position + 1);
            }
        }

        HashMap<Integer, List<Integer>> map1 = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int pos1 = entry.getKey();
            List<Integer> list2 = entry.getValue();
            Collections.sort(list2);
            map1.put(pos1, list2);
        }

        for (int i = min; i <= max; i++) {
            result.add(map1.get(i));
        }
        return result;
    }

    // copied from: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/discuss/439391/JAVA-Solution-with-BFS-%2B-HashMap
    // works for all the cases in LC. check it out later
    private static List<List<Integer>> verticalTraversal2(TreeNode root) {
        List<List<Integer>> rec = new ArrayList();
        if (root == null) return rec;
        Queue<TreeNode> queue = new LinkedList();
        Queue<Integer> pos = new LinkedList();
        queue.offer(root);
        pos.offer(0);
        Map<Integer, List<Integer>> map = new HashMap();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Map<Integer, List<Integer>> map1 = new HashMap();
            while (size-- > 0) {
                TreeNode curnode = queue.poll();
                int curindex = pos.poll();
                min = Math.min(min, curindex);
                max = Math.max(max, curindex);
                if (map1.containsKey(curindex)) {
                    map1.get(curindex).add(curnode.val);
                } else {
                    List<Integer> temp = new ArrayList();
                    temp.add(curnode.val);
                    map1.put(curindex, temp);
                }
                if (curnode.left != null) {
                    queue.offer(curnode.left);
                    pos.offer(curindex - 1);
                }
                if (curnode.right != null) {
                    queue.offer(curnode.right);
                    pos.offer(curindex + 1);
                }
            }
            for (Map.Entry<Integer, List<Integer>> entry : map1.entrySet()) {
                int key = entry.getKey();
                List<Integer> val = entry.getValue();
                Collections.sort(val);
                if (map.containsKey(key)) map.get(key).addAll(val);
                else map.put(key, val);
            }
        }
        for (int i = min; i <= max; i++) {
            if (map.containsKey(i)) {
                List<Integer> vals = map.get(i);
                rec.add(vals);
            }
        }
        return rec;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        root.left = node9;
        root.right = node20;
        node20.left = node15;
        node20.right = node7;

        List<Integer> list1 = Arrays.asList(9);
        List<Integer> list2 = Arrays.asList(3, 15);
        List<Integer> list3 = Arrays.asList(20);
        List<Integer> list4 = Arrays.asList(7);
        List<List<Integer>> result = new ArrayList<>();
        result.add(list1);
        result.add(list2);
        result.add(list3);
        result.add(list4);
        //assertEquals(verticalTraversal(root), result);
        System.out.println(verticalTraversal(root));
    }
}