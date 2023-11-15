public class LinearEquation {
    private double xOne;
    private double yOne;
    private double xTwo;
    private double yTwo;

    public LinearEquation(double xOne, double yOne, double xTwo, double yTwo) {
        this.xOne = xOne;
        this.yOne = yOne;
        this.xTwo = xTwo;
        this.yTwo = yTwo;
    }

    public void setCoordinates(double xOne, double yOne, double xTwo, double yTwo) {
        this.xOne = xOne;
        this.yOne = yOne;
        this.xTwo = xTwo;
        this.yTwo = yTwo;
    }

    private double roundedToHundredth(double toRound) {
        return Math.round(toRound * 100.0) / 100.0;
    }

    public double distance() {
        double dist = Math.sqrt(Math.pow(xTwo - xOne, 2) + Math.pow(yTwo - yOne, 2));
        return roundedToHundredth(dist);
    }

    public double slope() {
        if (xOne == xTwo) {
            return Double.POSITIVE_INFINITY;
        }
        return roundedToHundredth(yTwo - yOne) / (xTwo - xOne);
    }

    public double yIntercept() {
        double m = slope();
        return roundedToHundredth(yOne - m * xOne);
    }

    private int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }

    public String equation() {
        double m = slope();
        double b = yIntercept();

        String slope = formatSlope(m);
        String intercept = formatIntercept(b);

        if (slope.contains("/1")) {
            slope = slope.substring(0, slope.length() - 2);
        }

        if (slope.equals("1")) {
            slope = "";
        }

        return String.format("y = %sx%s", slope, intercept);
    }

    private String formatSlope(double slope) {
        if (Double.isInfinite(slope)) {
            return "undefined";
        }

        if (slope == 0) {
            return "0";
        }

        int numerator = (int) ((yTwo - yOne) * 1000);
        int denominator = (int) ((xTwo - xOne) * 1000);

        int gcdValue = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcdValue;
        denominator /= gcdValue;

        if (denominator == 1) {
            return String.valueOf(numerator);
        }

        if (numerator < 0 && denominator < 0) {
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
        }

        if (denominator < 0) {
            denominator = Math.abs(denominator);
            numerator *= -1;
        }

        return numerator + "/" + denominator;
    }

    private String formatIntercept(double intercept) {
        if (intercept == 0) {
            return "";
        }
        if (intercept < 0) {
            return " - " + Math.abs(intercept);
        }

        return " + " + intercept;
    }

    public String coordinateForX(double x) {
        double m = slope();
        double b = yIntercept();

        double y = m * x + b;

        return String.format("(%f, %f)", roundedToHundredth(x), roundedToHundredth(y));
    }

    public String lineInfo() {
        return String.format("""
                The two points are (%.0f, %.0f) and (%.0f, %.0f).
                The equation of the line between these points is %s.
                The slope of this line is %.2f.
                The y-intercept of this line is %.2f.
                The distance between these points is %.2f.
                """, xOne, yOne, xTwo, yTwo, equation(), slope(), yIntercept(), distance());
    }
}