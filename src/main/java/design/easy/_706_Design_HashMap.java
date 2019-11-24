package design.easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/21/19.
 * <p>
 * Design a HashMap without using any built-in hash table libraries.
 * To be specific, your design should include these functions:
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 * get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 * TODO: implement a solution taking load factor and rehashing into consideration
 * </p>
 */
public class _706_Design_HashMap {

    private ListNode[] nodes;

    class ListNode {
        int key, value;
        ListNode next;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private _706_Design_HashMap() {
        nodes = new ListNode[10000];
    }

    // calculate the hash, get the bucket and get the prev element (pointer to the given key). if the key already exists, just update the value, else (reached end of the list) create the new node and append to end of the list
    public void put(int key, int value) {
        int index = getIndex(key);
        ListNode prev = find(index, key);
        if (prev.next != null) {
            prev.next.value = value;
        } else {
            prev.next = new ListNode(key, value);
        }
    }

    // calculate the hash, get the bucket and get the prev element (pointer to the given key). return the value when its valid.
    public int get(int key) {
        int index = getIndex(key);
        ListNode prev = find(index, key);
        if (prev.next != null) {
            return prev.next.value;
        }
        return -1;
    }

    // calculate the hash, get the bucket and get the prev element (pointer to the given key). then remove the prev.next (which will be the desired key)
    public void remove(int key) {
        int index = getIndex(key);
        ListNode prev = find(index, key);
        if (prev.next != null) {
            prev.next = prev.next.next;
        }
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
            nodes[index] = new ListNode(-1, -1);
            return nodes[index];
        }

        ListNode prev = nodes[index];
        while (prev.next != null && prev.next.key != key) {
            prev = prev.next;
        }
        return prev;
    }

    public static void main(String[] args) {
        _706_Design_HashMap hashMap = new _706_Design_HashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        assertEquals(hashMap.get(1), 1);            // returns 1
        assertEquals(hashMap.get(3), -1);            // returns -1 (not found)
        hashMap.put(2, 1);          // update the existing value
        assertEquals(hashMap.get(2), 1);            // returns 1
        hashMap.remove(2);          // remove the mapping for 2
        assertEquals(hashMap.get(2), -1);            // returns -1 (not found)
    }
}