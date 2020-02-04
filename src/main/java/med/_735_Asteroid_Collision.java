package med;

import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/4/20.
 * <p>
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * </p>
 */
public class _735_Asteroid_Collision {
    // core logic: push to stack only when asteroid is positive, else do the peek and pop operations as needed
    private static int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            if (asteroid > 0) {
                stack.push(asteroid);   // if positive push to stack
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && Math.abs(asteroid) > stack.peek()) {  // this means that the negative asteroid weight> positive asteroid, so pop the prev asteroid (which means prev asteroid bursted)
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {  // push the current asteroid as it burst'ed all the prev smaller asteroids
                    stack.push(asteroid);
                } else if (asteroid + stack.peek() == 0) {  // when the positive and negative asteroid weights are equal, remove the stack top (as the last one gets burst'ed)
                    stack.pop();
                }
            }
        }
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {  // fill in the result array from the final and left over asteroids
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(asteroidCollision(new int[]{5, 10, -5}), new int[]{5, 10});
        assertEquals(asteroidCollision(new int[]{8, -8}), new int[]{});
        assertEquals(asteroidCollision(new int[]{10, 2, -5}), new int[]{10});
        assertEquals(asteroidCollision(new int[]{-2, -1, 1, 2}), new int[]{-2, -1, 1, 2});
    }
}