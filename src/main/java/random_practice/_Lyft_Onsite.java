package random_practice;

import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by udaythota on 4/7/20.
 * <p>
 * Lyft onsite Template
 * </p>
 */
public class _Lyft_Onsite {

    public static void sampleProblem(String test) {

    }

    public static void main(String[] args) throws Exception {
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
