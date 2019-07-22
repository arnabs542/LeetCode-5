package med;

/**
 * Created by udaythota on 1/16/19.
 * <p>
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 * Example:
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * </p>
 */
public class _11_ContainerMostWater {

    // NOTE: time complexity: O(n2), space complexity: O(1)
    private static int getMaxContainerArea(int[] containerArray) {
        int maxArea = 0, currentArea = 0;
        if (containerArray == null || containerArray.length == 0) {
            return 0;
        }

        for (int i = 0; i < containerArray.length - 1; i++) {
            for (int j = i + 1; j < containerArray.length; j++) {
                // int length = Math.abs((i - j));
                int length = j - i;
                int breadth = Math.min(containerArray[i], containerArray[j]);
                currentArea = length * breadth;

                if (currentArea > maxArea) {
                    maxArea = currentArea;
                }
            }
        }
        return maxArea;
    }


    // NOTE: time complexity: O(n), space complexity: O(1)
    private static int getMaxContainerAreaOptimal(int[] containerArray) {
        int maxArea = 0, currentArea = 0;
        if (containerArray == null || containerArray.length == 0) {
            return 0;
        }

        int i = 0;
        int j = containerArray.length - 1;
        while (i < j) {
            int length = j - i;
            int breadth = Math.min(containerArray[i], containerArray[j]);
            currentArea = length * breadth;

            if (currentArea > maxArea) {
                maxArea = currentArea;
            }

            if (containerArray[i] < containerArray[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] containerArray = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int area = getMaxContainerArea(containerArray);
        System.out.println("Max container area is: " + area);


        int area1 = getMaxContainerAreaOptimal(containerArray);
        System.out.println("Max container area is: " + area1);
    }

}
