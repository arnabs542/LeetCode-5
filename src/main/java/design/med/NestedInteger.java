package design.med;

import java.util.List;

/**
 * Created by udaythota on 2/15/20.
 * Sample Interface provided by LC. Implementation is LC specific. Don't try to speculate that. This is just to avoid compile time errors for the solutions which uses this interface
 */
public interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}
