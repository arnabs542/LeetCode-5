package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/13/19.
 * <p>
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * </p>
 */
public class _238_Product_Array_Except_Self {
    /*
    core logic: the value of ith index in the output array is can be computed as: product of all the values to the left of the current element * product of all the values to the right of the current element
    so in the first iteration, calculate the lefts: for each element, calculate the product of all the left elements to it. in the next iterations, calculate the rights: for each element, calculate the product of all the right elements to it
    Example:
    Given numbers [2, 3, 4, 5], regarding the third number 4, the product of array except 4 is 2*3*5 which consists of two parts: left 2*3 and right 5. The product is left*right. We can get lefts and rights:

       Numbers:     2    3    4     5
        Lefts:            2  2*3 2*3*4
        Rights:  3*4*5  4*5    5

        Assuming the missing elements (first and last) to be 1:

        Numbers:     2    3    4     5
        Lefts:       1    2  2*3 2*3*4
        Rights:  3*4*5  4*5    5     1
     */
    private static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                left = left * nums[i - 1];
            }
            result[i] = left;
        }

        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i < nums.length - 1) {
                right = right * nums[i + 1];
            }
            result[i] = result[i] * right;    //  value of this element would be right till the current element * value which is already present there (which corresponds to the left till that curremt element)
        }
        return result;
    }

    public static void main(String[] arg) {
        assertEquals(productExceptSelf(new int[]{2, 3, 4, 5}), new int[]{60, 40, 30, 24});
    }
}
