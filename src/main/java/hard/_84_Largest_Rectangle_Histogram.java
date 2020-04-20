package hard;

import org.testng.Assert;

import java.util.Stack;

/**
 * Created by udaythota on 4/20/20.
 * <p>
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * </p>
 */
public class _84_Largest_Rectangle_Histogram {
    // core logic: maintain a 'increasing sequence stack'. whenever you see an element greater than the prev stack index element, push the current index to the stack. else keep removing values from stack until value of index at top of stack is less than curr value index. keep calculating the area while doing it.
    // simple to visualize it with an example. see more explanation here: https://www.youtube.com/watch?v=ZmnqCZp9bBs
    // TC: O(n), SC: O(n)
    private static int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int currArea = 0, maxArea = 0, i;
        for (i = 0; i < height.length; ) {   // remember we are not incrementing 'i' here. it is controlled from steps inside
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int top = stack.pop();
                if (stack.isEmpty()) {     // the reason we don't increment i here is that, we need to keep removing elements from stack till we only have increasing index elements in the stack
                    currArea = height[top] * i;
                } else {
                    currArea = height[top] * (i - stack.peek() - 1);  // height: height[top], width: right boundary - left boundary + 1 (+1 because of width of each slab): (i-1) - (stack.peek()+1) + 1 = i-1-stack.peek()
                }
                maxArea = Math.max(maxArea, currArea);
            }
        }
        while (!stack.isEmpty()) {   // take care of elements in the stack (if present) after the loop
            int top = stack.pop();
            if (stack.isEmpty()) {
                currArea = height[top] * i;
            } else {
                currArea = height[top] * (i - stack.peek() - 1);
            }
            maxArea = Math.max(maxArea, currArea);
        }
        return maxArea;
    }

    // similar to above logic and same complexity. may be pursue it later
    private static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) return 0;
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : height[i]);
            if (s.isEmpty() || h >= height[s.peek()]) {
                s.push(i);
            } else {
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Assert.assertEquals(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}), 10);
        Assert.assertEquals(largestRectangleArea2(new int[]{2, 1, 5, 6, 2, 3}), 10);
    }
}
