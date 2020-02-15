package design.med;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by udaythota on 2/15/20.
 * <p>
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * </p>
 */
public class _341_Flatten_Nested_List_Iterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    // core logic: first flatten the given list and push elements from last to first (as first element need to come first in the result). when next element is called, it could either be integer or a list
    // if its an integer, just return it, else flatten the list again and push it back to stack
    // better understood with an example
    // NOTE: see the much simpler method using queue. the only advantage here is that this solution uses little less space then queue (as we are flattening the inner lists only when next is called)
    public _341_Flatten_Nested_List_Iterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        flattenList(nestedList);
    }

    // always call hasNext first to confirm the stack pop always outputs an integer
    @Override
    public Integer next() {
        return hasNext() ? stack.pop().getInteger() : null;
    }

    // if the stack peek element is an integer, return true (which means it can be popped when next() is called), if not flatten the list back till the stack peek element is an integer
    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger nestedInteger = stack.peek();
            if (nestedInteger.isInteger()) {
                return true;
            } else {
                flattenList(stack.pop().getList());
            }
        }
        return false;
    }

    // helper method to flatten the list. inserting from end to begin as it is stack and we need the first element in the list to go in to the result first
    private void flattenList(List<NestedInteger> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            stack.push(list.get(i));
        }
    }
}