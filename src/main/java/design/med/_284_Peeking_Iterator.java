package design.med;

import java.util.Iterator;

/**
 * Created by udaythota on 2/15/20.
 * <p>
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
 * </p>
 */
public class _284_Peeking_Iterator implements Iterator<Integer> {
    private boolean noSuchElement = false;
    private Iterator<Integer> iter;
    private Integer nextElement = null;

    // core logic: as peek needs to return the next element, we need to maintain a cache element ('nextElement'), so we can just return this cached element when peek element is called
    // the meat of the problem lies in NOT calling it.next() in peek method, as calling peek method any number of times before calling next should return us the same element. only calling next() should really make the iterator forward to the next position
    // neatly explained here for more details: https://medium.com/@harycane/peeking-iterator-ef69ce9ef788
    public _284_Peeking_Iterator(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
        advanceIterator();
    }

    // Returns the next element in the iteration without advancing the iterator.
    // TC: O(1)
    public Integer peek() {
        return nextElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    // TC: O(1)
    @Override
    public Integer next() {
        Integer result = nextElement;
        advanceIterator();
        return result;
    }

    @Override
    // TC: O(1)
    public boolean hasNext() {
        return !noSuchElement;
    }

    // TC: O(1)
    // make sure to call this method only next() is invoked, NOT when peeking an element
    private void advanceIterator() {
        if (iter.hasNext()) {
            nextElement = iter.next();
        } else {
            noSuchElement = true;
        }
    }
}