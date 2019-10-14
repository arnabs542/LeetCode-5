package random_practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by udaythota on 10/12/19.
 * Random FB question for telephonic practice
 */
public class Find_Followers {
    private static HashMap<Character, Integer> getFollowers(char[][] input) {
        if (input == null || input.length == 0) {
            return null;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (char[] in : input) {
            if (in.length == 1) {
                map.put(in[0], map.getOrDefault(in[0], 0));
            } else {
                map.put(in[0], map.getOrDefault(in[0], 0) + 1);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        HashMap<Character, Integer> followers = getFollowers(new char[][]{{'D' }, {'A', 'B' }, {'A', 'C' }, {'C', 'A' }});
        System.out.println(followers.size());

        System.out.println("Printing through first method:");
        for (Map.Entry<Character, Integer> entry : followers.entrySet()) {  // simple and optimal
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("Printing through second method:");  // using keyset
        for (char person : followers.keySet()) {
            System.out.println(person + ":" + followers.get(person));
        }

        System.out.println("Printing through third method:");  // using lambdas
        followers.forEach((Character, Integer) -> System.out.println((Character) + ":" + Integer));

        System.out.println("Printing through fourth method:");  // avoid this
        Iterator<Map.Entry<Character, Integer>> entries = followers.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Character, Integer> entry = entries.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("Printing only keys:");
        for(Character character: followers.keySet()) {
            System.out.print(character + " ");
        }

        System.out.println("\nPrinting only values:");
        for(Integer num: followers.values()) {
            System.out.print(num + " ");
        }
    }
}

