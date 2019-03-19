public class BasicMath {

    /**
     * These next 2 methods are just definitions, you don't
     * need to understand exactly how they work just know what they do.
     * When initially calling these methods {@code a} and {@code b} are
     * reflexive.
     */
    public static int GreatestCommonDivisor(int a, int b) {
        return b == 0 ? a : GreatestCommonDivisor(b, a % b);
    }

    public static int LeastCommonMultiple(int a, int b) {
        return (a * b) / GreatestCommonDivisor(a, b);
    }


}
