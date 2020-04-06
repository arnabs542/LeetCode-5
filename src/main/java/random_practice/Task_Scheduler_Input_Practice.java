package random_practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by udaythota on 4/2/20.
 * Task scheduler code practicing reading the inputs from std in. For Lyft onsite practice
 */
public class Task_Scheduler_Input_Practice {
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

    public static void main(String[] args) throws IOException {
        // assertEquals(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B',  2), 8);
        // method 1
        List<String> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        list.add(input);
        System.out.println("printing from the list: ");
        System.out.println(list);

        // method 2
        Scanner scanner = new Scanner(System.in);
        String input2 = scanner.nextLine();
        System.out.println("the line from the input is: " + input2);

        // method 3
        Scanner scanner3 = new Scanner(System.in);
        while (scanner3.hasNext()) {
            String input3 = scanner3.next();
            System.out.println("printing: " + input3);
        }

        // method 4: reading from the file
        FileReader fileReader = new FileReader("src/main/resources/test.txt");
        Scanner infile = new Scanner(fileReader);

        while (infile.hasNext()) {
            System.out.println(infile.nextLine());
        }
        fileReader.close();
    }
}
