package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 3/14/20.
 * <p>
 * Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.
 * A group of duplicate files consists of at least two files that have exactly the same content.
 * </p>
 */
public class _609_Find_Duplicate_File {
    // core logic: simple string manipulation. split the string appropriately, get the content from each file, save that in the map. when the content is repeated again for a diff file path, add the new file to the map. in the end, iterate through the map and add all the values in the map which have size > 1 (which means there are duplicate files with same content)
    private static List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> result = new ArrayList<>();
        if (paths == null || paths.length == 0) {
            return result;
        }

        HashMap<String, List<String>> contentPathsMap = new HashMap<>();
        for (String dirPath : paths) {
            String[] splits = dirPath.split(" ");
            String dir = splits[0];
            for (int i = 1; i < splits.length; i++) {
                int index = splits[i].indexOf("(");
                String filePath = splits[i].substring(0, index);
                String content = splits[i].substring(index + 1, splits[i].length() - 1);   // -1 because we don't want the last ")" to be included as part of our file content
                contentPathsMap.putIfAbsent(content, new ArrayList<>());
                contentPathsMap.get(content).add(dir + "/" + filePath);
            }
        }

        for (String key : contentPathsMap.keySet()) {
            if (contentPathsMap.get(key).size() > 1) {
                result.add(contentPathsMap.get(key));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(findDuplicate(new String[]{"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"}), Arrays.asList(Arrays.asList("root/a/2.txt", "root/c/d/4.txt", "root/4.txt"), Arrays.asList("root/a/1.txt", "root/c/3.txt")));
    }
}