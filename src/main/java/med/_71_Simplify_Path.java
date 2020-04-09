package med;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by udaythota on 4/9/20.
 * <p>
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
 * In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.
 * </p>
 */
public class _71_Simplify_Path {
    // core logic: use stack: split using "/". there are only 3 special cases to handle the split tokens: '.', '..' and ''. based on the token, either push / pop from the stack appropriately
    // TC: O(n), SC: O(n)
    private static String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        Stack<String> stack = new Stack<>();
        String[] tokens = path.split("/");
        for (String token : tokens) {
            if (token.length() == 0 || token.equals(".")) {
                continue;
            }
            if (token.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(token);
            }
        }

        /*List<String> list = new ArrayList<String>(stack);
        return "/" + String.join("/", list);*/

        // equivalent to the above commented code
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : stack) {   // as we need the elements from the stack in the sequential order and NOT last in first out order
            stringBuilder.append("/").append(string);
        }
        return stringBuilder.length() == 0 ? "/" : stringBuilder.toString();
    }

    public static void main(String[] args) {
        Assert.assertEquals(simplifyPath("/home/"), "/home");
        Assert.assertEquals(simplifyPath("/../"), "/");
        Assert.assertEquals(simplifyPath("/home//foo/"), "/home/foo");
        Assert.assertEquals(simplifyPath("/a/./b/../../c/"), "/c");
        Assert.assertEquals(simplifyPath("/a/../../b/../c//.//"), "/c");
        Assert.assertEquals(simplifyPath("/a//b////c/d//././/.."), "/a/b/c");
    }
}
