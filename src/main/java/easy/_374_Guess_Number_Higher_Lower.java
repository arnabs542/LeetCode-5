package easy;

/**
 * Created by udaythota on 2/14/20.
 * <p>
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 * </p>
 */
public class _374_Guess_Number_Higher_Lower {
    public int guessNumber(int n) {
        int low = 1, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == -1) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // this is just a dummy implementation: this is a pre defined function given by LC.
    private static int guess(int num) {
        return 0;
    }
}