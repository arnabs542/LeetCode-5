package med;

import org.testng.Assert;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by udaythota on 4/8/20.
 * <p>
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * If possible, output any possible result.  If not possible, return the empty string.
 * </p>
 */
public class _767_Reorganize_String {
    // core logic: similar to task scheduler (LC: 621), except that we here maintain the queue with int[] to keep track of char and it count
    // TC: O(nlogk) -> where n is the length of the string and k is the distinct chars in the input. logK because of the priority queue operations. CHECK AGAIN on this.
    private static String reorganizeString(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character ch : input.toCharArray()) {
            int count = map.getOrDefault(ch, 0) + 1;
            if (count > (input.length() + 1) / 2) {   // if the count of any character encountered is greater than more than half the string length, there is no way we could reorganize the string
                return "";
            }
            map.put(ch, count);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]); // as we are storing the asci value of character, make sure we convert that to character when we add it to the string builder
        for (char ch : map.keySet()) {
            queue.offer(new int[]{ch, map.get(ch)});
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            int[] currHighestChar = queue.poll();
            if (stringBuilder.length() == 0 || (char) currHighestChar[0] != stringBuilder.charAt(stringBuilder.length() - 1)) {
                stringBuilder.append((char) currHighestChar[0]);
                currHighestChar[1]--;
                if (currHighestChar[1] > 0) {
                    queue.offer(currHighestChar);
                }
            } else {   // the fact that we eliminated the invalid strings in the beginning and this only contains the strings that can be reorganized, if the first high char is not valid, the second high char should be the valid one
                int[] secondHighestChar = queue.poll();
                stringBuilder.append((char) secondHighestChar[0]);
                secondHighestChar[1]--;
                if (secondHighestChar[1] > 0) {
                    queue.offer(secondHighestChar);
                }
                queue.offer(currHighestChar);
            }
        }
        return stringBuilder.toString();
    }

    // exact same as above approach, except that we here use the pair class to make it much more object oriented and more readable
    // the other difference is in the way to find when the reorganized string is not possible. in the prev solution, we used it when creating the map itself, but here we use the queue empty condition (see the else condition)
    private static String reorganizeString2(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character ch : input.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Pair> queue = new PriorityQueue<>((Pair p1, Pair p2) -> p2.count - p1.count); // as we are storing the asci value of character, make sure we convert that to character when we add it to the string builder
        for (char ch : map.keySet()) {
            queue.offer(new Pair(ch, map.get(ch)));
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            Pair currHighestChar = queue.poll();
            if (stringBuilder.length() == 0 || currHighestChar.ch != stringBuilder.charAt(stringBuilder.length() - 1)) {
                stringBuilder.append(currHighestChar.ch);
                currHighestChar.count--;
                if (currHighestChar.count > 0) {
                    queue.offer(currHighestChar);
                }
            } else {   // the fact that we eliminated the invalid strings in the beginning and this only contains the strings that can be reorganized, if the first high char is not valid, the second high char should be the valid one
                if (queue.size() == 0) {  // the fact that the queue is empty here means there is no different char in the queue and hence we cannot form the reorganized string. remember we already polled from queue in the beginning!!
                    return "";
                }
                Pair secondHighestChar = queue.poll();
                stringBuilder.append(secondHighestChar.ch);
                secondHighestChar.count--;
                if (secondHighestChar.count > 0) {
                    queue.offer(secondHighestChar);
                }
                queue.offer(currHighestChar);
            }
        }
        return stringBuilder.toString();
    }

    static class Pair {
        int count;
        char ch;

        Pair(char ch, int count) {
            this.count = count;
            this.ch = ch;
        }
    }

    public static void main(String[] args) {
        Assert.assertEquals(reorganizeString("aab"), "aba");
        Assert.assertEquals(reorganizeString("aaab"), "");

        Assert.assertEquals(reorganizeString2("aab"), "aba");
        Assert.assertEquals(reorganizeString2("aaab"), "");
    }
}
