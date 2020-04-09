package hard;

import org.testng.Assert;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by udaythota on 4/8/20.
 * <p>
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 * </p>
 */
public class _358_Rearrange_String_K_Dist_Apart {
    // core logic: same logic as LC: 621 (task scheduler) and LC: 767 (reorganize string)
    // the only difference is that we added few optimizations here. eg: when the current time > string builder length, just return the empty string as we cannot reorganize the given string anyways
    private static String rearrangeString(String input, int k) {
        if (input == null || input.length() == 0) {
            return "";
        }
        if (k == 0) {  // no rearranging needed, so just return the same string
            return input;
        }

        HashMap<Character, Integer> charCountMap = new HashMap<>();
        for (char ch : input.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Character> queue = new PriorityQueue<>((o1, o2) -> charCountMap.get(o2) - charCountMap.get(o1));   // max heap
        for (char task : charCountMap.keySet()) {
            queue.offer(task);
        }

        StringBuilder stringBuilder = new StringBuilder();
        HashMap<Integer, Character> coolDownMap = new HashMap<>();
        int currentTime = 0;
        while (!queue.isEmpty() || coolDownMap.size() > 0) {   // 2nd condition ~ to !coolDownMap.isEmpty()
            int releaseTime = currentTime - k;
            if (coolDownMap.containsKey(releaseTime)) {   // check if there is any entry in the map which has completed the cool down period and can be released
                queue.offer(coolDownMap.remove(releaseTime));
            }

            if (!queue.isEmpty()) {
                char task = queue.poll();
                stringBuilder.append(task);
                int remainingTasks = charCountMap.get(task) - 1;

                if (remainingTasks > 0) {
                    charCountMap.put(task, remainingTasks);
                    coolDownMap.put(currentTime, task);
                }
            }
            currentTime++;   // increment the current time irrespective of if the queue or cool down map is empty. if either the queue is empty or cool down period has not completed (no valid key in the map), it means idle time (<k dist), so increment the current time

            if (currentTime > stringBuilder.length()) {   // optimization: this means we encountered one idle period where we didn't append anything into the sb. this means, we cannot reorganize the given string anyways, so we just return empty string
                return "";
            }
        }
        return currentTime == stringBuilder.length() ? stringBuilder.toString() : "";
    }

    public static void main(String[] args) {
        Assert.assertEquals(rearrangeString("aabbcc", 3), "acbacb");
        Assert.assertEquals(rearrangeString("aaabc", 3), "");
        Assert.assertEquals(rearrangeString("aaadbbcc", 2), "abcabcad");
    }
}
