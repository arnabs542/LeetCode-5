package med;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 3/13/20.
 * <p>
 * Create a timebased key-value store class TimeMap, that supports two operations.
 * </p>
 */
public class _981_Time_Based_Key_Value_Store {
    class Node {
        String value;
        int timestamp;

        Node(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    public Map<String, List<Node>> map;

    private _981_Time_Based_Key_Value_Store() {
        map = new HashMap<>();
    }

    // TC: O(1)
    // this was asked to me in tinder virtual onsite
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Node(value, timestamp));
    }

    // TC: O(logN) - binary search
    public String get(String key, int timestamp) {
        List<Node> nodes = map.get(key);
        if (nodes == null) {
            return "";
        }
        int left = 0, right = nodes.size() - 1;
        while (left + 1 < right) {   // left + 1 to handle the corner cases: eg when there are only 2 elements and if left = 0 and right = 1, if the condition was left < right, it would have calculated the mid value to be 0 and the right pointer would be set to -1 (mid - 1) and we would have got array out of bound exception in the end (run the 2nd example and see)
            int mid = left + (right - left) / 2;
            Node node = nodes.get(mid);
            if (node.timestamp == timestamp) {
                return node.value;
            } else if (node.timestamp < timestamp) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (nodes.get(right).timestamp <= timestamp) {
            return nodes.get(right).value;
        } else if (nodes.get(left).timestamp <= timestamp) {
            return nodes.get(left).value;
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        // example: 1
        _981_Time_Based_Key_Value_Store time_based_key_value_store = new _981_Time_Based_Key_Value_Store();
        time_based_key_value_store.set("foo", "bar", 1);
        assertEquals(time_based_key_value_store.get("foo", 1), "bar");
        assertEquals(time_based_key_value_store.get("foo", 3), "bar");
        time_based_key_value_store.set("foo", "bar2", 4);
        assertEquals(time_based_key_value_store.get("foo", 4), "bar2");
        assertEquals(time_based_key_value_store.get("foo", 5), "bar2");

        // example: 2
        _981_Time_Based_Key_Value_Store time_based_key_value_store2 = new _981_Time_Based_Key_Value_Store();
        time_based_key_value_store2.set("love", "high", 10);
        time_based_key_value_store2.set("love", "low", 20);
        assertEquals(time_based_key_value_store2.get("love", 5), "");
        assertEquals(time_based_key_value_store2.get("love", 10), "high");
        assertEquals(time_based_key_value_store2.get("love", 15), "high");
        assertEquals(time_based_key_value_store2.get("love", 20), "low");
        assertEquals(time_based_key_value_store2.get("love", 25), "low");
    }
}