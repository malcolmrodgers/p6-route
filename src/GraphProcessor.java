import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private static HashMap<Point, HashSet<Point>> aList = new HashMap<>();
    private static Point[] points;

    //main method for testing
    public static void main(String[] args) throws Exception{
        GraphProcessor gp = new GraphProcessor();
        File graphData = new File("data/simple.graph");
        FileInputStream stream = new FileInputStream(graphData);

        gp.initialize(stream);
        
        System.out.println(aList);
        System.out.println(Arrays.toString(points));
    }

    /**
     * Creates and initializes a graph from a source data
     * file in the .graph format. Should be called
     * before any other methods work.
     * @param file a FileInputStream of the .graph file
     * @throws Exception if file not found or error reading
     */
    public void initialize(FileInputStream file) throws Exception{
        //Check for bad file, handle exception
        /* 
        if (file.read() == -1) {  //TODO: Fix this, for some reason this moves pointer over one byte?
            file.close();          //may be able to run without and just check for null with scanner
            throw new Exception("Error reading graph file");
        }
        */

        //create scanner to read from input stream
        //if first token is not an int, file format is incorrect
        Scanner readFile = new Scanner(file);
        if (!readFile.hasNextInt()) {
            readFile.close();
            file.close();
            throw new Exception("Graph file is incorrect format");
        }

        //Store sizes
        int v = readFile.nextInt();
        int e = readFile.nextInt();
        points = new Point[v];

        readFile.nextLine(); //blank line in file after sizes for some reason. this skips it

        //add each point to adj. list with empty neighbors set
        for (int i=0; i<v; i++) {
            String [] info = readFile.nextLine().split(" ");
            //System.out.println(Arrays.toString(info));
            Point p = new Point(Double.parseDouble(info[1]), Double.parseDouble(info[2]));
            points[i] = p; //stores each point in array inst. var
            aList.put(p, new HashSet<>()); 
        }

        //add neighbors to graph using edge data from file
        //If no next int, then no neighbors
        if (readFile.hasNextInt()) {
        while (readFile.hasNextLine()) {
            String s = readFile.nextLine();
            String[] info = s.split(" ");
            Point x = points[Integer.parseInt(info[0])];
            Point y = points[Integer.parseInt(info[1])];

            //undir. graph, so add endpoints to both sets
            aList.get(x).add(y);
            aList.get(y).add(x);
        }
    }
        file.close();
        readFile.close();
    }

    /**
     * Searches for the point in the graph that is closest in
     * straight-line distance to the parameter point p
     * @param p A point, not necessarily in the graph
     * @return The closest point in the graph to p
     */
    public Point nearestPoint(Point p) {
        //initialize dist to infinity and return point
        Double nearest = Double.POSITIVE_INFINITY;
        Point ret = p;

        //for every point call .distance(), replace if lower than current nearest val
        for (Point q : aList.keySet()) { 
            Double dist = q.distance(p);
            if (dist < nearest) {
                nearest = dist;
                ret = q;
            }
        }

        return ret;
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
        double sum = 0.0;
        for (int i=0; i<route.size()-1; i++) {
            sum += route.get(i).distance(route.get(i+1));
        }

        return sum;
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
