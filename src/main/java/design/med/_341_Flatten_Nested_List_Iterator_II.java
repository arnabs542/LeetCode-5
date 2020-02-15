package design.med;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by udaythota on 2/15/20.
 * <p>
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * </p>
 */
public class _341_Flatten_Nested_List_Iterator_II implements Iterator<Integer> {
    private Queue<Integer> queue;

    // core logic: first flatten the whole list and insert the elements in to queue and keep polling them whenever next is called
    // this is much simpler to understand than using the stack approach, but this is little memory intensive when compared to stack as here we are flattening all the inner lists at once (when compared to flattening the inner lists only when next is called in the stack approach)
    public _341_Flatten_Nested_List_Iterator_II(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        flatten(nestedList);
    }

    @Override
    public Integer next() {
        return hasNext() ? queue.poll() : null;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    // helper method to flatten the whole list at once and insert the elements in to queue
    private void flatten(List<NestedInteger> list) {
        if (list == null) {
            return;
        }

        for (NestedInteger nestedInteger : list) {
            if (nestedInteger.isInteger()) {
                queue.offer(nestedInteger.getInteger());
            } else {
                flatten(nestedInteger.getList());
            }
        }
    }
}