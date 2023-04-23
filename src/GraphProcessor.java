import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Models a weighted graph of latitude-longitude points
 * and supports various distance and routing operations.
 * To do: Add your name(s) as additional authors
 * @author Brandon Fain
 *
 */
public class GraphProcessor {
    //Make instance variables
    private HashMap<Point, Set<Point>> aList = new HashMap<>();
    private Point[] points;

    /**
     * Creates and initializes a graph from a source data
     * file in the .graph format. Should be called
     * before any other methods work.
     * @param file a FileInputStream of the .graph file
     * @throws Exception if file not found or error reading
     */

    public void initialize(FileInputStream file) throws FileNotFoundException {
        //create scanner to read from input stream
        Scanner readFile = new Scanner(file);
        points = new Point[readFile.nextInt()];

        //number of verticies and edges
        int v = points.length;
        int e = readFile.nextInt();

        //add each point to adj. list to create graph
        for (int i=0; i<v; i++) {
            Point p = new Point(readFile.nextDouble(), readFile.nextDouble());
            points[i] = p; //stores each point in array inst. var
            aList.put(p, new HashSet<>()); 
        }

        //add neighbors to graph using edge data from file
        while (readFile.hasNextLine()) {
            String next = readFile.nextLine();
            String[] info = new String[2];
            info = next.split(" ");  //Read next line, split by whitespace, and store result in temp. string[]
    
            //store references to endpoints of given edge
            Point x = points[Integer.parseInt(info[0])]; 
            Point y = points[Integer.parseInt(info[1])];

            //undir. graph, so add endpoints to both sets
            aList.get(x).add(y);
            aList.get(y).add(x);
        }

        readFile.close();
        System.out.println(aList);
    }


    /**
     * Searches for the point in the graph that is closest in
     * straight-line distance to the parameter point p
     * @param p A point, not necessarily in the graph
     * @return The closest point in the graph to p
     */
    public Point nearestPoint(Point p) {
        // TODO: Implement nearestPoint
        return null;
    }


    /**
     * Calculates the total distance along the route, summing
     * the distance between the first and the second Points, 
     * the second and the third, ..., the second to last and
     * the last. Distance returned in miles.
     * @param start Beginning point. May or may not be in the graph.
     * @param end Destination point May or may not be in the graph.
     * @return The distance to get from start to end
     */
    public double routeDistance(List<Point> route) {
        // TODO Implement routeDistance
        return 0.0;
    }
    

    /**
     * Checks if input points are part of a connected component
     * in the graph, that is, can one get from one to the other
     * only traversing edges in the graph
     * @param p1 one point
     * @param p2 another point
     * @return true if p2 is reachable from p1 (and vice versa)
     */
    public boolean connected(Point p1, Point p2) {
        // TODO: Implement connected
        return false;
    }


    /**
     * Returns the shortest path, traversing the graph, that begins at start
     * and terminates at end, including start and end as the first and last
     * points in the returned list. If there is no such route, either because
     * start is not connected to end or because start equals end, throws an
     * exception.
     * @param start Beginning point.
     * @param end Destination point.
     * @return The shortest path [start, ..., end].
     * @throws InvalidAlgorithmParameterException if there is no such route, 
     * either because start is not connected to end or because start equals end.
     */
    public List<Point> route(Point start, Point end) throws InvalidAlgorithmParameterException {
        // TODO: Implement route
        return null;
    }

    
}
