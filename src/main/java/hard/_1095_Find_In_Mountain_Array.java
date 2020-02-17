package hard;

/**
 * Created by udaythota on 2/16/20.
 * <p>
 * You may recall that an array A is a mountain array if and only if:
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  If such an index doesn't exist, return -1.
 * </p>
 */
public class _1095_Find_In_Mountain_Array {
    // implemented by LC. don't try to speculate / modify the functionality
    interface MountainArray {
        int get(int index);

        int length();
    }

    // core logic: the fact that the input array always contains an increasing sequence followed by a decreasing sequence, first find the index of peak element in the array
    // search for target in the first half (as we need min index). if we couldn't find there, search for target in second half again using reverse binary search (remember 2nd half contains elements in decreasing order)
    private int findInMountainArray(int target, MountainArray mountainArr) {
        int peakIndex = findPeak(mountainArr, 0, mountainArr.length() - 1);
        int minIndex = binarySearch(mountainArr, 0, peakIndex, target);
        if (minIndex == -1) {
            minIndex = revBinarySearch(mountainArr, peakIndex + 1, mountainArr.length() - 1, target);
        }
        return minIndex;
    }

    // regular binary search to find the target element: for elements in ASCENDING order
    private int binarySearch(MountainArray mountainArray, int low, int high, int target) {
        while (low <= high) {
            int midIndex = low + (high - low) / 2;
            int midElement = mountainArray.get(midIndex);
            if (midElement == target) {
                return midIndex;
            } else if (midElement > target) {
                high = midIndex - 1;
            } else {
                low = midIndex + 1;
            }
        }
        return -1;
    }

    // reverse binary search to find the target element: for elements in DESCENDING order
    private int revBinarySearch(MountainArray mountainArray, int low, int high, int target) {
        while (low <= high) {
            int midIndex = low + (high - low) / 2;
            int midElement = mountainArray.get(midIndex);
            if (midElement == target) {
                return midIndex;
            } else if (midElement > target) {
                low = midIndex + 1;   // this is the only change needed compared to above regular binary search
            } else {
                high = midIndex - 1;  // this is the only change needed compared to above regular binary search
            }
        }
        return -1;
    }

    // find peak of the element also using the binary search: depending on the mid, mid-1 and mid+1 elements, search the appropriate half accordingly
    private int findPeak(MountainArray mountainArray, int low, int high) {
        while (low < high) {
            int mid = low + (high - low) / 2;
            int midElement = mountainArray.get(mid);
            int midLeft = mountainArray.get(mid - 1);
            int midRight = mountainArray.get(mid + 1);

            if (midElement > midLeft && midElement > midRight) {
                return mid;
            } else if (midElement < midRight) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return -1;
    }
}