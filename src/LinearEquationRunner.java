import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class LinearEquationRunner {
    public static void start() {
        parseCoordinatePair("(1, 2)"); //Testing
    }

    private static int[] parseCoordinatePair(String input) {
        int numOne = 0; int numTwo = 0;

        Pattern pattern = Pattern.compile("\\((\\d+),\\s*(\\d+)\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            numOne = stringToInt(matcher.group(1));
            numTwo = stringToInt(matcher.group(2));
        }

        return new int[] {numOne, numTwo};
    }

    private static String askQuestion(){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    private static int stringToInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            System.out.printf("[WARN] Invalid input \"%s\" to be converted to integer.%n", num);
        }
        return 0;
    }
}
