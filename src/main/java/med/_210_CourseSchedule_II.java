package _5_Longest_Palindromic_Substring;

import java.util.*;

/**
 * Created by udaythota on 6/15/19.
 * <p>
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * </p>
 */
public class _210_CourseSchedule_II {

    // this approach is based on BFS (TC: O(n))
    private static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null) {
            return null;
        }

        int[] result = new int[numCourses], inDegree = new int[numCourses];
        int index = 0;

        for (int i = 0; i < prerequisites.length; i++) {   // build an in degree prerequisite map (number of prerequisites needed for each course)
            inDegree[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                result[index++] = i;
                queue.offer(i);    // push all the courses with ZERO prerequisites to queue
            }
        }

        /*
         NOTE: 1) queue is to maintain all the courses that can be processed (with no prerequisites)
               2) for each course (say '1') with no prerequisite, iterate through the input prerequisites array, reduce the in degree count for all the courses with '1' as prerequisite
               3) if the in degree count is zero (meaning there are no more prerequisites to this course), add it to the queue and repeat the same process
               4) once the entire queue is processed, check if the result index == num courses, meaning all the courses have been successfully processed
         */
        while (!queue.isEmpty()) {
            int courseWithNoPreReq = queue.poll();  // this course don't have any prerequisites
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == courseWithNoPreReq) {
                    inDegree[prerequisites[i][0]]--;
                    if (inDegree[prerequisites[i][0]] == 0) {   // when there are no in degree connections to this node, it cam be added to queue to process further
                        result[index++] = prerequisites[i][0];
                        queue.offer(prerequisites[i][0]);
                    }
                }
            }
        }
        return index == numCourses ? result : new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOrder(2, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
        System.out.println(Arrays.toString(findOrder(3, new int[][]{{1, 0}, {0, 1}, {1, 2}})));
    }
}
