package random_practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by udaythota on 10/9/19.
 * Find the average word length in a sentence
 */
public class Average_Word_Length {

    private static float averageWordLength(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }

        float average = 0f;
        input = input.trim();
        String[] tokens = input.split(" ");
        for (String token : tokens) {
            average += token.length();
        }

        if (average > 0) {
            average = average / tokens.length;
        }
        return average;
    }

    private static boolean isPossible(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (map.get(num) == 0) continue;
            int count = 0;
            while (map.containsKey(num) && map.get(num) > 0) {
                count++;
                if (count > 3 && map.get(num) <= map.get(num - 1)) {
                    break;
                }
                map.put(num, map.get(num) - 1);
                num++;
            }
            if (count < 3) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        float avg = averageWordLength("testing first version of coding");
        System.out.println(avg);

        System.out.println(isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}));
    }
}
