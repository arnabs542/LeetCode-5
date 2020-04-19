package med;

import org.testng.Assert;

/**
 * Created by udaythota on 4/18/20.
 * <p>
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * </p>
 */
public class _223_Rectangle_Area {
    // core logic: area of rectangle 1 + area of rectangle 2 - overlapped are. just make sure to double check the valid overlap conditions and overlap coordinates if they exist
    // TC: O(1)
    private static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int overlap = 0;
        int area1 = (C - A) * (D - B);   // area of rect 1
        int area2 = (G - E) * (H - F);  // area of rect 2

        if (C <= E || A >= G || D <= F || B >= H) {   // no overlapped area
            overlap = 0;
        } else {                                    // calculate the desired overlapped area
            int length = Math.min(C, G) - Math.max(A, E);
            int width = Math.min(D, H) - Math.max(B, F);
            overlap = length * width;
        }
        return area1 + area2 - overlap;
    }

    public static void main(String[] args) {
        Assert.assertEquals(computeArea(-3, 0, 3, 4, 0, -1, 9, 2), 45);
    }
}
