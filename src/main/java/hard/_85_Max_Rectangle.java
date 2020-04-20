package hard;

import org.testng.Assert;

import java.util.Stack;

/**
 * Created by udaythota on 4/20/20.
 * <p>
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * </p>
 */
public class _85_Max_Rectangle {
    // core logic: for every row, generate the histograms and keep calculating their areas. update the max area as needed
    // TC: O(m * n), SC: O(n) - where m is number of rows and n is number of columns,
    // for more explanation see here: https://www.youtube.com/watch?v=g8bSdXCG-lA
    private static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int currArea = 0, maxArea = 0;
        int[] cols = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {   // when the current grid value is '0', reset the cols[j] to 0, as if the same column in prev row is not 1, it cannot be contributed the the rectangle
                    cols[j] = 0;
                } else {
                    cols[j] += 1;
                }
            }
            currArea = largestRectangleArea(cols);
            maxArea = Math.max(maxArea, currArea);
        }
        return maxArea;
    }

    // LC: 84 - can be used as helper method here
    // TC: O(n)
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

    public static void main(String[] args) {
        Assert.assertEquals(maximalRectangle(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}), 6);
    }
}
