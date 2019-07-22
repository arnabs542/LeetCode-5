package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/15/19.
 * <p>
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
 * If the town judge exists, then:
 * <p>
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 * </p>
 */
public class _997_TownJudge {

    // core logic: use two integer arrays to represent the inward and outward connections on the node
    // after storing all values into the two arrays just go through those two arrays and find the person with 0 outward connections and being N-1 inward connections
    private static int findJudge(int N, int[][] trust) {

        int[] in = new int[N + 1];   // to maintain inward connections to a node (N+1 because N is labelled from 1)
        int[] out = new int[N + 1];  // to maintain outward connections from a node (N+1 because N is labelled from 1)

        for (int i = 0; i < trust.length; i++) {
            in[trust[i][1]]++;   // [i, j] -> j represents the inward connection to that node
            out[trust[i][0]]++;  // [i, j] -> i represents the outward connection from that node
        }

        for (int i = 1; i <= N; i++) {
            if (in[i] == N - 1 && out[i] == 0) {   // trust person will have N-1 inward connections (as all the people except him trusts him) and 0 outward connections (trusts no one)
                return i;
            }
        }
        return -1;   // return -1 when no trust person exists
    }

    public static void main(String[] args) {
        assertEquals(findJudge(2, new int[][]{{1, 2}}), 2);
        assertEquals(findJudge(3, new int[][]{{1, 3}, {2, 3}}), 3);
        assertEquals(findJudge(3, new int[][]{{1, 3}, {2, 3}, {3, 1}}), -1);
        assertEquals(findJudge(1, new int[][]{}), 1);
    }
}
