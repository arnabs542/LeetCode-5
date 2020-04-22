package med;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by udaythota on 4/21/20.
 * <p>
 * Implement a SnapshotArray that supports the following interface:
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 * </p>
 */
public class _1146_Snapshot_Array {

    private List<TreeMap<Integer, Integer>> list;   // list(i) -> saves the index and map saves the snap_id and the corresponding value
    private int snapId;

    // O(N)
    // TODO: pursue this later
    public _1146_Snapshot_Array(int length) {
        snapId = 0;
        list = new ArrayList<>();
        for (int i = 0; i < length; i++) {   // as we don't have more than length number of indexes in any snap
            list.add(new TreeMap<>());
            list.get(i).put(snapId, 0);    // to take care of returning the default value ('0') when there is no element in the tree map
        }
    }

    // O(1)
    public void set(int index, int val) {
        list.get(index).put(snapId, val);
    }

    // O(1)
    public int snap() {
        return snapId++;  // first return the snap id and then increment the snap (as we need to return snap-1)
    }

    // O(N)
    public int get(int index, int snap_id) {
        return list.get(index).floorEntry(snap_id).getValue();
    }
}
