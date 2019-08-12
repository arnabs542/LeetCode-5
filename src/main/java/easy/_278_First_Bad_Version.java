package easy;

/**
 * Created by udaythota on 8/11/19.
 * <p>
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * </p>
 */
public class _278_First_Bad_Version {

    // core logic: simple binary search - keep iterating till you encounter the first bad version
    // TC: O(logn)
    public int firstBadVersion(int n) {
        int start = 0;
        int end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (!isBadVersion(mid)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    // API signature given
    private static boolean isBadVersion(int version) {
        return version < 10;
    }
}