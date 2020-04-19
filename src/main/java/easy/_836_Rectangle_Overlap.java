package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/18/20.
 * <p>
 * A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.
 * Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.
 * Given two (axis-aligned) rectangles, return whether they overlap.
 * </p>
 */
public class _836_Rectangle_Overlap {
    // exact same as LC: 223. just make sure to compare all the edges of first and second rectangle appropriately
    private static boolean isRectangleOverlap(int[] rect1, int[] rect2) {
        if (rect1[2] <= rect2[0] || rect1[0] >= rect2[2] || rect1[3] <= rect2[1] || rect1[1] >= rect2[3]) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Assert.assertTrue(isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));
        Assert.assertFalse(isRectangleOverlap(new int[]{0, 0, 1, 1}, new int[]{1, 0, 2, 1}));
    }
}
