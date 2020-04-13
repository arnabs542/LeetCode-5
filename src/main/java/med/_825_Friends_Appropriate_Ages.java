package med;

import org.testng.Assert;

import java.util.HashMap;

/**
 * Created by udaythota on 4/12/20.
 * <p>
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 * <p>
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * Otherwise, A will friend request B.
 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
 * How many total friend requests are made?
 * </p>
 */
public class _825_Friends_Appropriate_Ages {
    // core logic: the meat of the problem lies in the notes given in the problem (1 <= ages.length <= 20000 and 1 <= ages[i] <= 120.)
    // as ages size can be 20000 and ages are only between 1 and 120, we first create a map (age and count of ages). iterate through all the ages in the entry set with all the ages in the entry set and count the requests
    // TC: O(A^2 + N) - where A is the number of ages and N is the size of ages array (N is the time for building the map), SC: O(N) - size of the map
    private static int numFriendRequests(int[] ages) {
        int friendRequests = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int age : ages) {
            map.put(age, map.getOrDefault(age, 0) + 1);
        }
        for (int ageA : map.keySet()) {
            for (int ageB : map.keySet()) {
                if (isFriendReqValid(ageA, ageB)) {
                    //  friendRequests += map.get(ageA) * (map.get(ageB) - (ageA == ageB ? 1 : 0));     // equivalent to below if condition
                    if (ageA == ageB) {
                        friendRequests += map.get(ageA) * (map.get(ageB) - 1);   // as a friend can't make a friend request with himself
                    } else {
                        friendRequests += map.get(ageA) * map.get(ageB);
                    }
                }
            }
        }
        return friendRequests;
    }

    // if A can friend request B
    private static boolean isFriendReqValid(int A, int B) {
        return !(B <= 0.5 * A + 7 || B > A || (B > 100 && A < 100));  // if even any one of the given conditions is true, return false
    }

    public static void main(String[] args) {
        Assert.assertEquals(numFriendRequests(new int[]{16, 16}), 2);
        Assert.assertEquals(numFriendRequests(new int[]{16, 16, 16, 16}), 12);
        Assert.assertEquals(numFriendRequests(new int[]{16, 17, 18}), 2);
        Assert.assertEquals(numFriendRequests(new int[]{20, 30, 100, 110, 120}), 3);
    }
}
