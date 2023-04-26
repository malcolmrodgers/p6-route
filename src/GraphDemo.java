import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Demonstrates the calculation of shortest paths in the US Highway
 * network, showing the functionality of GraphProcessor and using
 * Visualize
 * To do: Add your name(s) as authors
 */
public class GraphDemo {
    private static void main(String[] args) throws Exception {
        GraphProcessor gp = new GraphProcessor();
        File data = new File("data/usa.graph");
        FileInputStream stream = new FileInputStream(data);
        gp.initialize(stream);

        //user inputs start and end cities
        System.out.println("Choose starting coordinates. (lat,lon): ");
        Scanner input = new Scanner(System.in);
        String[] format = input.nextLine().split(",");
        Point start = new Point(Double.parseDouble(format[0]), Double.parseDouble(format[1]));

        System.out.println("Choose destination coordinates: ");
        Scanner input2 = new Scanner(System.in);
        String[] format2 = input2.nextLine().split(",");
        Point end = new Point(Double.parseDouble(format2[0]), Double.parseDouble(format2[1]));
        
        long a = System.nanoTime();

        //find closest points to given coords in the graph
        start = gp.nearestPoint(start);
        end = gp.nearestPoint(end);

        ArrayList<Point> route = new ArrayList<>(gp.route(start, end));
        double dist = gp.routeDistance(route);

        long b = System.nanoTime();
        long time = b - a;

        input.close();
        input2.close();
        System.out.println("Nearest point to (" + format[0] + "," + format[1] + "): " + start);
        System.out.println("Nearest point to (" + format2[0] + "," + format2[1] + "): " + end);
        System.out.println("Distance traveled: " + dist + "mi");
        System.out.println("Runtime: " + time + "ms");


        Visualize visualize = new Visualize("data/usa.vis", "images/usa.png");
        visualize.drawRoute(route);
        

    }
}