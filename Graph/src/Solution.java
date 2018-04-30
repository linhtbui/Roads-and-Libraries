import java.util.*;
import java.util.List;
import java.util.ArrayList;


public class Solution {
    
    /**
     * Depth First Search Helper Function
     * @param v an int
     * @param visited an array of booleans
     * @param adj an ArrayList of an ArrayList of Integers
     */
    static void DFSUtil(int v,boolean[] visited, List<List<Integer>> adj)
    {
        // Mark the current node as visited and print it
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj.get(v).listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
            	//recurse for all neightbors
                DFSUtil(n, visited, adj);
        }
    }

    /**
     * Executing depth first search on the graph and return the number of components
     * @param n, an int of the first vertex
     * @param cities, a 2D array storing the edges
     * @return component, a long 
     */
    static long dfs(int n, int[][] cities) {
        long component = 0;
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            edges.add(new ArrayList<Integer>());
        }
        boolean[] visited = new boolean[n + 1];
        //storing edges in the slots for both vertices
        for (int i = 0; i < cities.length; i++) {
            edges.get(cities[i][0]).add(cities[i][1]);
            edges.get(cities[i][1]).add(cities[i][0]);
        }
        
        //check if there are still vertices not visited after each search, if there is 
        // increase number of components
        for (int i = 1; i <= n; i++) {
            if (visited[i] == false) {
                DFSUtil(i, visited, edges);
                component++;
            }
        }
        return component;
    }
        
    
    
    /**
     * Function to return the lowest cost of building roads/library
     * @param n, an int, number of vertices
     * @param m, an int, number of edges
     * @param c_lib, a long, the cost of a library
     * @param c_road, a long, the cost of a road
     * @param cities, a 2D array of edges
     * @return cost, a long
     */
   static long roadsAndLibraries(int n, int m, long c_lib, long c_road, int[][] cities) {
	   // if there is no fixable road or if cost of road is greater than cost of library
       if (c_road >= c_lib || m == 0) {
           return c_lib * n;
       } else {
           long component = dfs(n, cities);
           return component * c_lib + (n - component) * c_road;
       }
    }

    public static void main(String[] args) {
    	//getting input
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            int m = in.nextInt();
            long c_lib = in.nextLong();
            long c_road = in.nextLong();
            int[][] cities = new int[m][2];
            for(int cities_i = 0; cities_i < m; cities_i++){
                for(int cities_j = 0; cities_j < 2; cities_j++){
                    cities[cities_i][cities_j] = in.nextInt();
                }
            }
            //getting cost
         long result = roadsAndLibraries(n, m, c_lib, c_road, cities);
         System.out.println(result);
       
        }
        
        in.close();
    }
}
