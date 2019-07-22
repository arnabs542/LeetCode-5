package easy;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 7/21/19.
 * <p>
 * Design a HashSet without using any built-in hash table libraries.
 * </p>
 */
public class _705_Design_HashSet {

    private ListNode[] nodes;

    class ListNode {
        int key;
        ListNode next;

        ListNode(int key) {
            this.key = key;
        }
    }

    // the logic is almost similar to designing hash map, EXCEPT that here, we only need key and next in the ListNode (rather than key, value and next)
    private _705_Design_HashSet() {
        nodes = new ListNode[1000];
    }

    public void add(int key) {
        int index = getIndex(key);
        ListNode prev = find(index, key);
        if (prev.next == null) {
            prev.next = new ListNode(key);
        }
    }

    public void remove(int key) {
        int index = getIndex(key);
        ListNode prev = find(index, key);
        if (prev.next != null) {
            prev.next = prev.next.next;
        }
    }

    public boolean contains(int key) {
        int index = getIndex(key);
        ListNode prev = find(index, key);
        return prev.next != null;
    }

    // calculates the hash from the given key and returns the index (bucket)
    // the index corresponds to a bucket which is again a linked list of nodes
    private int getIndex(int key) {
        return Integer.hashCode(key) % nodes.length;
    }

    // find the PREVIOUS element in the linked list which points to the given key
    // if there are no elements in the linked list for the corresponding index / bucket, create a DUMMY node / head. this is to avoid null pointer exceptions (if index=null, index.next will always throw you a null pointer exception)
    private ListNode find(int index, int key) {
        if (nodes[index] == null) {
            nodes[index] = new ListNode(-1);
            return nodes[index];
        }

        ListNode prev = nodes[index];
        while (prev.next != null && prev.next.key != key) {
            prev = prev.next;
        }
        return prev;
    }

    public static void main(String[] args) {
        _705_Design_HashSet hashSet = new _705_Design_HashSet();
        hashSet.add(1);
        hashSet.add(2);
        assertTrue(hashSet.contains(1));    // returns true
        assertFalse(hashSet.contains(3));    // returns false (not found)
        hashSet.add(2);
        assertTrue(hashSet.contains(2));    // returns true
        hashSet.remove(2);
        assertFalse(hashSet.contains(2));    // returns false (already removed)
    }
}