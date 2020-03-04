package med;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/28/19.
 * <p>
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
 * All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 * </p>
 */
public class _332_Reconstruct_Itinerary {
    // straight forward DFS: start from source ("JFK")
    // debug and understand if you find it tricky
    private static List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();
        HashMap<String, List<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets) {
            graph.putIfAbsent(ticket.get(0), new ArrayList<>());
            graph.get(ticket.get(0)).add(ticket.get(1));
        }

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {   // to make sure all values are in lexicographic order (in case there are both possible answers, return the result with smaller lexicographic order)
            Collections.sort(entry.getValue());
        }
        result.add("JFK");
        dfs(result, "JFK", graph, tickets.size());
        return result;
    }

    // simple dfs helper method
    // calculate Euler path. For each point, try to DFS its out-going point. There is chance that a DFS won't get a result. So, we do backtrack
    private static void dfs(List<String> result, String currentCity, HashMap<String, List<String>> graph, int totalTickets) {
        if (!graph.containsKey(currentCity)) {   // when the next city is not present as a key, just return so we can try other possibilities
            return;
        }

        // get the list of all the possible next candidates (cities)
        List<String> nextCities = graph.get(currentCity);
        for (int i = 0; i < nextCities.size(); i++) {
            String nextCity = nextCities.get(i);
            nextCities.remove(i);
            result.add(nextCity);
            dfs(result, nextCity, graph, totalTickets);

            if (result.size() == totalTickets + 1) {   // when result size is tickets + 1, it means we reached end of DFS (reached the last destination)
                return;
            }

            // these 2 steps are needed to handle the case where the next chosen city is not present (as a key) in the map and so put it back and try with next possible city (debug with eg: 3 and check it out. in the first 2 test cases, they still pass without these 2 steps)
            // in some cases where the dfs could solve the entire itinerary path (first 2 test cases), these below 2 steps are not even executed, but the cases where dfs couldn't solve (3rd test case), these 2 steps are needed
            nextCities.add(i, nextCity);   // put the next city in its origin position as it is not the right next city to chose
            result.remove(result.size() - 1);  // remove the last city from result (as the above next city is not the right choice)
        }
    }

    // Method 2: uses Eulerian path DFS Algorithm
    // investigate later
    private static Map<String, PriorityQueue<String>> graph;
    private static ArrayList<String> path;

    private static List<String> findItinerary2(List<List<String>> tickets) {
        graph = new HashMap<>();
        path = new ArrayList<>();
        for (List<String> ticket : tickets) {
            graph.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            graph.get(ticket.get(0)).add(ticket.get(1));
        }
        dfs2("JFK");
        return path;
    }

    private static void dfs2(String departure) {
        PriorityQueue<String> nextCities = graph.get(departure);
        while (nextCities != null && !nextCities.isEmpty()) {
            dfs2(nextCities.poll());
        }
        path.add(0, departure);  // add the city in the first index as its DFS
    }

    public static void main(String[] args) {
        // test case: 1
        List<List<String>> input = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("MUC");
        list1.add("LHR");

        List<String> list2 = new ArrayList<>();
        list2.add("JFK");
        list2.add("MUC");

        List<String> list3 = new ArrayList<>();
        list3.add("SFO");
        list3.add("SJC");

        List<String> list4 = new ArrayList<>();
        list4.add("LHR");
        list4.add("SFO");

        input.add(list1);
        input.add(list2);
        input.add(list3);
        input.add(list4);
        assertEquals(findItinerary(input), Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC"));
        assertEquals(findItinerary2(input), Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC"));

        // test case: 2
        List<List<String>> input2 = new ArrayList<>();
        List<String> list5 = new ArrayList<>();
        list5.add("JFK");
        list5.add("SFO");

        List<String> list6 = new ArrayList<>();
        list6.add("JFK");
        list6.add("ATL");

        List<String> list7 = new ArrayList<>();
        list7.add("SFO");
        list7.add("ATL");

        List<String> list8 = new ArrayList<>();
        list8.add("ATL");
        list8.add("JFK");

        List<String> list9 = new ArrayList<>();
        list9.add("ATL");
        list9.add("SFO");

        input2.add(list5);
        input2.add(list6);
        input2.add(list7);
        input2.add(list8);
        input2.add(list9);
        assertEquals(findItinerary(input2), Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"));
        assertEquals(findItinerary2(input2), Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"));

        // test case: 3
        List<List<String>> input3 = new ArrayList<>();
        List<String> list10 = new ArrayList<>();
        list10.add("JFK");
        list10.add("KUL");

        List<String> list11 = new ArrayList<>();
        list11.add("JFK");
        list11.add("NRT");

        List<String> list12 = new ArrayList<>();
        list12.add("NRT");
        list12.add("JFK");

        input3.add(list10);
        input3.add(list11);
        input3.add(list12);
        assertEquals(findItinerary(input3), Arrays.asList("JFK", "NRT", "JFK", "KUL"));
        assertEquals(findItinerary2(input3), Arrays.asList("JFK", "NRT", "JFK", "KUL"));
    }
}