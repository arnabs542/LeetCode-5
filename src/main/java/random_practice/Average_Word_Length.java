package random_practice;

/**
 * Created by udaythota on 10/9/19.
 * Find the average word length in a sentence
 */
public class Average_Word_Length {

    private static float averageWordLength(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }

        float average = 0f;
        input = input.trim();
        String[] tokens = input.split(" ");
        for (String token : tokens) {
            average += token.length();
        }

        if (average > 0) {
            average = average / tokens.length;
        }
        return average;
    }

    public static void main(String[] args) {
        float avg = averageWordLength("testing first version of coding");
        System.out.println(avg);
    }
}
