package easy;

/**
 * Created by udaythota on 4/12/20.
 * <p>
 * Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.
 * </p>
 */
public class _157_Read_N_Chars_Read_4 {
    // just make sure to take care of corner case conditions
    public int read(char[] buf, int n) {
        int loops = n / 4;
        int start = 0;
        for (int i = 0; i <= loops; i++) {
            char[] ch = new char[4];
            int count = read4(ch);
            for (int j = 0; j < count && start < n; j++) {   // read till end of the file or till the buffer is empty
                buf[start++] = ch[j];
            }
            if (count < 4) {   // reached end of the file and in the last read buffer there are <4 chars
                return start;
            }
        }
        return start;
    }

    // don't try to anticipate the method: only to be executed in LC
    private static int read4(char[] buf) {
        return 0;
    }
}
