package random_practice.amazon_assessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by z002jsf on 3/2/20.
 * Amazon Assessment - Actual asked question
 */
class Top_N_Buzz_Words_Challenge {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    private static ArrayList<String> popularNToys(int numToys,
                                          int topToys,
                                          List<String> toys,
                                          int numQuotes,
                                          List<String> quotes) {
        ArrayList<String> result = new ArrayList<>();
        if (quotes == null || quotes.size() == 0) {
            return null;
        }

        HashSet<String> toysSet = new HashSet<>();
        for (String toy : toys) {
            toysSet.add(toy);
        }

        Map<String, Integer> map = new HashMap<>();
        for (String quote : quotes) {
            for (String word : quote.replaceAll("[^A-Za-z]", " ").toLowerCase().split(" ")) {
                if (toysSet.contains(word)) {
                    map.putIfAbsent(word, 1);
                    map.put(word, map.get(word) + 1);
                }
            }
        }

            PriorityQueue<String> minHeap = new PriorityQueue<>((o1, o2) -> map.get(o1) == map.get(o2) ? o1.compareTo(o2) : map.get(o1) - map.get(o2));
            for (String toy : map.keySet()) {
                if (topToys > numToys) {
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

        return result;
    }

    public static void main(String[] args) {
        int numToys = 6;
        int topToys = 2;
        List<String> toys = Arrays.asList("elmo", "elsa", "legos", "drone", "tablet", "warcraft");
        //String[] toys = {"elmo", "elsa", "legos", "drone", "tablet", "warcraft"};
        int numQuotes = 6;
        List<String> quotes = Arrays.asList("Emo is the hottest of the season! Elmo will be on every kid's wishlist!",
                "The new Elmo dolls are super high quality",
                "Expect the Elsa dolls to be very popular this year",
                "Elsa and Elmo are the toys I'll be buying for my kids",
                "For parents of older kids, look into buying them a drone",
                "Warcraft is slowly rising in popularity ahead of the holiday season");
        List<String> result = popularNToys(numToys, topToys, toys, numQuotes, quotes);
        System.out.println(result.toString());
    }
}