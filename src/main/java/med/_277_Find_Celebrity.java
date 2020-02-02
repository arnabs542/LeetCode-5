package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/1/20.
 * <p>
 * You are given a helper function bool knows(a, b) which tells you whether A knows B.
 * Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 * </p>
 */
public class _277_Find_Celebrity {
    // core logic: brute force: compute the count of inward and outward connections for every node and in the end when the inward connections for a node = n-1 and outward connections = 0, return that node
    private static int findCelebrity(int n) {
        if (n <= 0) {
            return -1;
        }
        int[] inward = new int[n];
        int[] outward = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {   // when i =j, we don't care about the inward or outward connections
                    if (knows(i, j)) {
                        outward[i]++;
                        inward[j]++;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {   // verify the candidate
            if (inward[i] == n - 1 && outward[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    // core logic: first loop is to find the possible candidate and the second loop is to check if that possible candidate is the actual celebrity. TC: O(n)
    // proof of first pass: the fact that there is only one celebrity here makes it easy to find the possible candidate only in one loop. see the below references for more info
    // https://leetcode.com/problems/find-the-celebrity/discuss/341661/Java-two-pass-solution-with-detailed-explanation.
    // and https://leetcode.com/problems/find-the-celebrity/discuss/144815/Logical-Thinking-with-Clear-Java-Code
    private static int findCelebrity2(int n) {
        int candidate = 0;
        for (int i = 0; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) {
                return -1;
            }
        }
        return candidate;
    }

    // just a dummy method (with the given sample example), actual method impl is not provided (available runtime on LC)
    private static boolean knows(int a, int b) {
        int[][] graph = new int[][]{{1, 1, 0}, {0, 1, 0}, {1, 1, 1}};
        return graph[a][b] == 1;
    }

    public static void main(String[] args) {
        assertEquals(findCelebrity(3), 1);
        assertEquals(findCelebrity2(3), 1);
    }
}