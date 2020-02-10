package hard;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/9/20.
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * </p>
 */
public class _42_Trapping_Rain_Water {
    // core logic: for every element in the array, find the max level of water that can be trapped, which is equal to min of max height of bars on both (left and right) the sides minus its own height
    // TC: O(n^2)
    // TODO: there is an efficient two pointer approach which can be solved in O(n). deal with it later. reference: https://leetcode.com/problems/trapping-rain-water/discuss/17467/detailed-java-solution
    private static int trap(int[] height) {
        int maxWater = 0;
        for (int i = 1; i < height.length; i++) {
            int leftMax = 0, rightMax = 0;
            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);  // left max: max of all the bars left to current bar
            }
            for (int k = i; k < height.length; k++) {
                rightMax = Math.max(rightMax, height[k]);   // right max: max of all the bars right to current bar
            }
            maxWater += Math.min(leftMax, rightMax) - height[i];  // calculate the water that can be trapped
        }
        return maxWater;
    }

    public static void main(String[] args) {
        assertEquals(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}), 6);
    }
}