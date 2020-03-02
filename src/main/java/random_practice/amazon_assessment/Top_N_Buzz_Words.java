package random_practice.amazon_assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by udaythota on 3/1/20.
 * <p>
 * You work on a team whose job is to understand the most sought after toys for the holiday season. A teammate of yours has built a webcrawler that extracts a list of quotes about toys from different articles. You need to take these quotes and identify which toys are mentioned most frequently. Write an algorithm that identifies the top N toys out of a list of quotes and list of toys.
 * Your algorithm should output the top N toys mentioned most frequently in the quotes
 * </p>
 */
public class Top_N_Buzz_Words {
    private static List<String> returnTopToys(int numToys, int topToys, String[] toys, int numQuotes, String[] quotes) {
        List<String> result = new ArrayList<>();
        if (quotes == null || quotes.length == 0) {
            return null;
        }
        HashSet<String> toysSet = new HashSet<>();
        for (String toy : toys) {
            toysSet.add(toy.trim().toLowerCase());
        }
        Map<String, Integer> map = new HashMap<>();
        for (String quote : quotes) {
            HashSet<String> temp = new HashSet<>();
            for (String word : quote.replaceAll("[^A-Za-z]", " ").toLowerCase().split(" ")) {
                if (toysSet.contains(word) && !temp.contains(word)) {
                    temp.add(word);
                     map.putIfAbsent(word, 0);
                     map.put(word, map.get(word) + 1);
                  //  map.put(word, map.getOrDefault(word, 0) + 1);
                }
            }
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>((o1, o2) -> map.get(o1) == map.get(o2) ? o2.compareTo(o1) : map.get(o1) - map.get(o2));

        for (String toy : map.keySet()) {
            if (numToys < topToys) {    // when this condition is true, we only need to return the toys that are present in the quotes
                result.add(toy);
            } else {
                minHeap.add(toy);
                if (minHeap.size() > topToys) {
                    minHeap.poll();
                }
            }
        }

        while (minHeap.size() > 0) {
            result.add(minHeap.poll());
        }
        /*if (numToys > topToys) {
            Collections.reverse(result);
        }*/
        return result;
    }

    public static void main(String[] args) {
        int numToys = 6;
        int topToys = 2;
        String[] toys = {"elmo", "elsa", "legos", "drone", "tablet", "warcraft"};
        int numQuotes = 6;
        String[] quotes = {
                "Emo is the hottest of the season! Elmo Elmo Elmo Elmo Elmo Elmo will be on every kid's wishlist!",
                "The new Elmo dolls are super high quality",
                "Expect the Elsa dolls to be very popular this year",
                "Elsa and Elmo are the toys I'll be buying for my kids",
                "For parents of older kids, look into buying them a drone",
                "Warcraft is slowly rising in popularity ahead of the holiday season"};
        List<String> result = returnTopToys(numToys, topToys, toys, numQuotes, quotes);
        System.out.println(result.toString());
    }
}