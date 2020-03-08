package design.med;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/21/19.
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * The cache is initialized with a positive capacity.
 */
public class _146_LRU_Cache {
    private ListNode head, tail;
    private int capacity, count;
    private HashMap<Integer, ListNode> map;

    class ListNode {
        int key;
        int value;
        ListNode pre;
        ListNode next;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private _146_LRU_Cache(int capacity) {
        this.capacity = capacity;
        count = 0;
        map = new HashMap<>(capacity);
        head = new ListNode(0, 0); // dummy head
        tail = new ListNode(0, 0); // dummy tail
        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
    }

    // when value (node) exists for the key, just return the value and move (delete and insert at head) the current node to the first node after head (as it is recently accessed)
    // TC: O(1) -> map lookup
    public int get(int key) {
        if (map.get(key) != null) {
            ListNode node = map.get(key);
            delete(node);
            addToHead(node);
            return node.value;
        }
        return -1;
    }

    // when key exists, update the value and move the node to head
    // when key doesn't exist: if count < capacity, add the node and move it to head, else (count > capacity), delete the last before node from tail (as this is least recently used) and add the current node to head
    // TC: O(1) -> due to double linked list add / delete operations
    public void put(int key, int value) {
        if (map.get(key) != null) {
            ListNode node = map.get(key);
            node.value = value;
            delete(node);
            addToHead(node);
        } else {
            ListNode node = new ListNode(key, value);
            map.put(key, node);
            if (count < capacity) {
                count++;
                addToHead(node);
            } else {
                map.remove(tail.pre.key);
                delete(tail.pre);  // as count >= capacity (remember count starts from 0), remove the least recently used node (the one before dummy tail)
                addToHead(node);
            }
        }
    }

    private void addToHead(ListNode node) {
        node.next = head.next;
        node.next.pre = node;
        node.pre = head;
        node.pre.next = node;
    }

    private void delete(ListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public static void main(String[] args) {
        _146_LRU_Cache cache = new _146_LRU_Cache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(cache.get(1), 1);       // returns 1
        cache.put(3, 3);    // evicts key 2, as we called get 1 recently and hence 2 is the least frequently used
        assertEquals(cache.get(2), -1);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        assertEquals(cache.get(1), -1);       // returns -1 (not found)
        assertEquals(cache.get(3), 3);       // returns 3
        assertEquals(cache.get(4), 4);       // returns 4
    }
}