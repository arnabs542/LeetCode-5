package med;

import java.util.HashMap;
import java.util.PriorityQueue;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 4/2/20.
 * <p>
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * </p>
 */
public class _621_Task_Scheduler {
    // core logic: we need to make sure we always process the tasks with highest counts first, so we maintain the max heap to do this. cool down map is to make sure we process the current processed task only after the cool down time
    // TC: O(n), SC: O(n)
    private static int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        HashMap<Character, Integer> taskCountMap = new HashMap<>();
        for (char task : tasks) {
            taskCountMap.put(task, taskCountMap.getOrDefault(task, 0) + 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);   // max heap
        for (char task : taskCountMap.keySet()) {
            queue.offer(taskCountMap.get(task));
        }

        HashMap<Integer, Integer> coolDownMap = new HashMap<>();
        int currentTime = 0;
        while (!queue.isEmpty() || !coolDownMap.isEmpty()) {
            int releaseTime = currentTime - n - 1;
            if (coolDownMap.containsKey(releaseTime)) {   // check if there is any entry in the map which has completed the cool down period and can be released
                queue.offer(coolDownMap.remove(releaseTime));   // get the value associated with the current time and remove the corresponding entry from the map. add the value back to the queue as the cool down has been completed and can be processed further
            }

            if (!queue.isEmpty()) {
                int tasksLeft = queue.poll() - 1;
                if (tasksLeft > 0) {
                    coolDownMap.put(currentTime, tasksLeft);
                }
            }
            currentTime++;   // increment the current time irrespective of if the queue or cool down map is empty. if either the queue is empty or cool down period has not completed (no valid key in the map), it means idle time, so increment the current time
        }
        return currentTime;
    }

    // same as above logic except that we here maintain the queue with valid tasks instead of its counts and the cool down map with the task and its time processed
    // TC: O(n), SC: O(n)
    private static int leastInterval2(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        HashMap<Character, Integer> taskCountMap = new HashMap<>();
        for (char task : tasks) {
            taskCountMap.put(task, taskCountMap.getOrDefault(task, 0) + 1);
        }

        PriorityQueue<Character> queue = new PriorityQueue<>((o1, o2) -> taskCountMap.get(o2) - taskCountMap.get(o1));   // max heap
        for (char task : taskCountMap.keySet()) {
            queue.offer(task);
        }

        HashMap<Integer, Character> coolDownMap = new HashMap<>();
        int currentTime = 0;
        while (!queue.isEmpty() || !coolDownMap.isEmpty()) {
            int releaseTime = currentTime - n - 1;
            if (coolDownMap.containsKey(releaseTime)) {   // check if there is any entry in the map which has completed the cool down period and can be released
                queue.offer(coolDownMap.remove(releaseTime));
            }

            if (!queue.isEmpty()) {
                char task = queue.poll();
                int remainingTasks = taskCountMap.get(task) - 1;
                taskCountMap.put(task, remainingTasks);

                if (remainingTasks > 0) {
                    coolDownMap.put(currentTime, task);
                }
            }
            currentTime++;   // increment the current time irrespective of if the queue or cool down map is empty. if either the queue is empty or cool down period has not completed (no valid key in the map), it means idle time, so increment the current time
        }
        return currentTime;
    }

    public static void main(String[] args) {
        assertEquals(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2), 8);
        assertEquals(leastInterval2(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2), 8);
    }
}
