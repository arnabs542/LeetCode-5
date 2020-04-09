package random_practice;

import java.util.*;

/**
 * Created by udaythota on 4/8/20.
 * <p>
 * Lyft onsite
 * </p>
 */
public class _Lyft_Onsite {

    private static int version;

    class Node {
        int value;
        int timestamp;

        Node(int value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    public Map<String, List<Node>> map;

    private _Lyft_Onsite() {
        map = new HashMap<>();
        version = 0;
    }

    // TC: O(1)
    public void put(String key, int value) {
        StringBuilder stringBuilder = new StringBuilder();
        version = version + 1;
        stringBuilder.append("PUT").append("(#").append(version).append(") ").append(key).append(" = ").append(value);
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Node(value, version));
        System.out.println(stringBuilder.toString());
    }

    // TC: O(1)
    public void get(String key) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GET").append(" ").append(key).append(" = ");
        List<Node> nodes = map.get(key);
        if (nodes == null) {
            stringBuilder.append("<NULL>");
        } else {
            stringBuilder.append(nodes.get(nodes.size() - 1).value);
        }
        System.out.println(stringBuilder.toString());
    }

    // TC: O(logN) - binary search
    public void get(String key, int timestamp) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GET").append(" ").append(key).append(" = ");
        List<Node> nodes = map.get(key);
        if (nodes == null) {
            stringBuilder.append("<NULL>");
            System.out.println(stringBuilder.toString());
            System.exit(0);
        }
        int left = 0, right = nodes.size() - 1;
        while (left + 1 < right) {   // left + 1 to handle the corner cases: eg when there are only 2 elements and if left = 0 and right = 1, if the condition was left < right, it would have calculated the mid value to -1 and we would have got array out of bound exception in the end
            int mid = left + (right - left) / 2;
            Node node = nodes.get(mid);
            if (node.timestamp == timestamp) {
                stringBuilder.append("<NULL>");
                System.out.println(stringBuilder.toString());
                System.exit(0);
            } else if (node.timestamp < timestamp) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (nodes.get(right).timestamp <= timestamp) {
            stringBuilder.append(nodes.get(right).value);
        } else if (nodes.get(left).timestamp <= timestamp) {
            stringBuilder.append(nodes.get(left).value);
        } else {
            stringBuilder.append("<NULL>");
        }
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) throws Exception {
        _Lyft_Onsite lyft_onsite = new _Lyft_Onsite();
        Scanner scanner = new Scanner(System.in);
        // NOTE: ADDED the stdin part
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");
            if (tokens[0].startsWith("PUT")) {
                lyft_onsite.put(tokens[1], Integer.valueOf(tokens[2]));
            } else {
                if (tokens.length == 3) {
                    lyft_onsite.get(tokens[1], Integer.valueOf(tokens[2]));
                } else {
                    lyft_onsite.get(tokens[1]);
                }
            }
        }
    }
}