import java.util.Scanner;

public class Polygon {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int vertices = in.nextInt();
        int[] xCoordinates = new int[vertices];
        int[] yCoordinates = new int[vertices];

        for(int i = 0; i < vertices; i++) {
            xCoordinates[i] = in.nextInt();
            yCoordinates[i] = in.nextInt();
        }


        area(xCoordinates, yCoordinates, vertices);
        perimeter(xCoordinates, yCoordinates, vertices);

        /*
            Because I have seen so few geometry problems, I have neglected expanding this portion of my hackpack but
            there are many other geometry techniques that could and should be included in here. The most important of
            these topics is called Convex Hull. It finds the maximal convex polygon surrounding a set of points in space.
         */


    }

    //This perimeter method is very basic, it just uses the distance formula over and over.
    private static void perimeter(int[] x, int[] y, int vertices) {
        double perimeter = 0.0;
        for (int i = 0; i < vertices - 1; i++) {
            perimeter += Math.sqrt(sq(x[i+1]-x[i]) + sq(y[i+1]-y[i]));
        }
    }

    //The area method calculates the area of any non-self-intersecting polygon using the shoelace method.
    private static void area(int[] x, int[] y, int vertices) {
        double area = 0.0;

        int j = vertices - 1;
        for (int i = 0; i < vertices; i++)
        {
            area += (x[j] + x[i]) * (y[j] - y[i]);

            j = i;
        }

        area = Math.abs(area / 2.0);
    }

    public static double sq(int x) { return x * x; }
}
