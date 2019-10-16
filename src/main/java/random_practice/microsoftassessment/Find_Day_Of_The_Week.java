package random_practice.microsoftassessment;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/16/19.
 * <p>
 * Given a string s and an integer k, return the day of the week that is k days later
 * </p>
 */
public class Find_Day_Of_The_Week {
    // calculate the actual index (<7) from k and string (use map to retrieve the string index) and retrieve the corresponding day value for the index from the week array
    private static String findDayOfTheWeek(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) {
            return null;
        }

        String[] week = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < week.length; i++) {
            map.put(week[i], i);
        }

        /*int index = k % 7;
        int mapIndex = map.get(s);
        return week[(index + mapIndex) % 7];*/

        return week[(k + map.get(s)) % 7];  // simple way of writing the above 3 lines
    }

    public static void main(String[] args) {
        assertEquals(findDayOfTheWeek("Wed", 2), "Fri");
        assertEquals(findDayOfTheWeek("Sat", 23), "Mon");
    }
}
