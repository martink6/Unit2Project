import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class  LinearEquationLogic {
    Scanner scan = new Scanner(System.in);
    public void start() {
        System.out.println("Welcome to the Linear Equation Calculator!");
        System.out.println("enter the coordinates of two points on a line, ex: (1, 2) or (3, 4)");

        boolean cont = true;

        LinearEquation equation = new LinearEquation(0, 0, 0, 0);

        while (cont) {
            try {
                int[] coords1 = parseCoordinatePair(askQuestion("Enter the first coordinate pair: "));
                int[] coords2 = parseCoordinatePair(askQuestion("Enter the second coordinate pair: "));

                if (coords1[0] == coords2[0] && coords1[1] == coords2[1]) {
                    throw new IllegalArgumentException("The two points must be different.");
                }
                if (coords1[0] == coords2[0]) {
                    throw new IllegalArgumentException("The two points must not have the same x-coordinate.");
                }

                equation.setCoordinates(coords1[0], coords1[1], coords2[0], coords2[1]);
                System.out.println(equation.lineInfo());

                String cordX = askQuestion("Enter a value for x: ");
                System.out.println("Coordinate for x: " + equation.coordinateForX(stringToDouble(cordX)));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            String contStr = askQuestion("Would you like to continue? (y/n) ");
            if (contStr.equals("n")) cont = false;
        }
    }

    private int[] parseCoordinatePair(String input) {
        int numOne = 0; int numTwo = 0;
        Pattern pattern = Pattern.compile("\\((-?\\d+),\\s*(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            numOne = stringToInt(matcher.group(1));
            numTwo = stringToInt(matcher.group(2));
        }

        return new int[] {numOne, numTwo};
    }

    private String askQuestion(String question) {
        System.out.print(question);
        return scan.nextLine();
    }

    private int stringToInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            System.out.printf("[WARN] Invalid input \"%s\" to be converted to integer.%n", num);
        }
        return 0;
    }

    private double stringToDouble(String num) {
        try {
            return Double.parseDouble(num);
        } catch (Exception e) {
            System.out.printf("[WARN] Invalid input \"%s\" to be converted to double.%n", num);
        }
        return 0;
    }
}
