package hard;

import org.testng.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 5/16/20.
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)
 * Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.
 */
public class _1088_Confusing_Number_II {
    // simple BFS traversal. explore all the possible paths / leaves for every digit and keep continuing till we encounter a number > input num. keep counting the confusing nums in the process
    // TC: O(5^m) - where m is length of the input integer, SC: O(5^m) - as queue can grow this big
    // TODO: can also be done using DFS. pursue it later
    private static int confusingNumberII(int N) {
        int res = 0;
        int[] digits = new int[]{0, 1, 6, 8, 9};
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int num = queue.poll();
            for (int digit : digits) {
                int newNum = (num * 10) + digit;
                if (newNum > N) {   // new candidate number crossed the input num. so stop BFS. as we are performing BFS, it is guaranteed that the first time we encounter a greater number, we finished exploring all the valid possible paths
                    return res;
                }
                if (newNum != 0) {
                    queue.offer(newNum);
                }
                if (isConfusing(newNum)) {
                    res++;
                }
            }
        }
        return res;
    }

    // simple helper method to find if the given number is confusing
    private static boolean isConfusing(int num) {
        String numString = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        for (char ch : numString.toCharArray()) {
            if (ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '7') {
                return false;
            } else if (ch == '6') {
                sb.append('9');
            } else if (ch == '9') {
                sb.append('6');
            } else {
                sb.append(ch);
            }
        }
        return !sb.reverse().toString().equals(numString);
    }

    public static void main(String[] args) {
        Assert.assertEquals(confusingNumberII(20), 6);
        Assert.assertEquals(confusingNumberII(100), 19);
    }
}
