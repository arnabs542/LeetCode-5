package _5_Longest_Palindromic_Substring;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 6/15/19.
 * <p>
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * </p>
 */
public class _207_CourseSchedule {

    private static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null) {
            return true;
        }

        List<List<Integer>> courses = new ArrayList<>();  // to represent a dependency graph
        for (int i = 0; i < numCourses; i++) {
            courses.add(new ArrayList<Integer>());
        }

        // create a dependency graph
        for (int i = 0; i < prerequisites.length; i++) {
            courses.get(prerequisites[i][1]).add(prerequisites[i][0]);   // prerequisites[i][j] -> represents i is dependent on j. so create a parent -> dependent child graph
        }

        int[] visited = new int[numCourses];  // to track the visited nodes. 0->not visited , 1-> being visited, 2-> visited

        for (int i = 0; i < numCourses; i++) {  // iterate through all the courses and whenever there is a cycle in the dependent child's, return false
            if (!dfs(i, courses, visited)) {
                return false;
            }
        }
        return true;   // means we successfully iterated through all the nodes and no cyclic loop is seen between the underlying courses, so course schedule is possible
    }

    // dfs helper: topological sort (which is equivalent to a pre order traversal)
    private static boolean dfs(int course, List<List<Integer>> courses, int[] visited) {
        visited[course] = 1;   // represents the node being visited
        List<Integer> eligibleCourses = courses.get(course);   // get all the children for the current node

        for (Integer eligibleCourse : eligibleCourses) {
            if (visited[eligibleCourse] == 1) {  // has been re-visited while visiting its children - CYCLE !!!! (eg: 0->1 and 1->0)
                return false;
            }
            if (visited[eligibleCourse] == 0) {  // node not visited: so preform dfs
                if (!dfs(eligibleCourse, courses, visited)) {
                    return false;
                }
            }
        }
        visited[course] = 2;  // mark the visited node
        return true;
    }

    // this approach is based on BFS
    // LC: 210: Course Schedule II - based on the exact same approach. so try to follow this
    // TC: O(n)
    private static boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null) {
            return false;
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
        return index == numCourses;
    }

    public static void main(String[] args) {
        // test method: 1
        assertTrue(canFinish(2, new int[][]{{1, 0}}));
        assertTrue(canFinish(3, new int[][]{{0, 1}, {1, 2}}));
        assertFalse(canFinish(2, new int[][]{{1, 0}, {0, 1}}));

        // test method: 2
        assertTrue(canFinishBFS(2, new int[][]{{1, 0}}));
        assertTrue(canFinishBFS(3, new int[][]{{0, 1}, {1, 2}}));
        assertFalse(canFinishBFS(2, new int[][]{{1, 0}, {0, 1}}));
    }
}
