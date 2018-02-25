/*
 * Please list your project group member information below
 *
 *                        Name            Section CRN
 * Group Member 1:        Ethan Kim         12721
 * Group Member 2:        Bowen Xu          12721
 * Group Member 3:        Jiamin Xie        12721
 */

import java.util.Scanner;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Project1_Prim {
    
    protected LinkedList<item> adjListArray[];
    
    @SuppressWarnings("unchecked")
    Project1_Prim(int V)    //constructor
    {
        adjListArray = new LinkedList[V+1];
        
        for (int i = 1; i < adjListArray.length; i++)
        {
            adjListArray[i] = new LinkedList<item>();
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        /*
         Insert your code here. Your DFS code should make use of a custom stack, which
         you will implement. It would be good to make a class in a separate file.
         */
        
        Scanner scan = new Scanner(System.in);
        
        int N = scan.nextInt();
        int M = scan.nextInt();
        
        Project1_Prim graph = new Project1_Prim(N);            //create a graph with N number of vertices
        
        for (int i = 0; i < M; i++)            //add edges to following vertices
        {
            int src = scan.nextInt();
            int dest = scan.nextInt();
            int weight = scan.nextInt();
            addEdge(graph, src, dest, weight);
        }
        
        
        //printGraph(graph);
        scan.close();
        Prim(graph, N);
    }
    
    static public void addEdge( Project1_Prim graph, int src, int dest, int weight)
    {
        item node = new item (dest, weight);
        item node1 = new item (src, weight);
        
        graph.adjListArray[src].add(node);        //add edges to both direction since it is undirected.
        graph.adjListArray[dest].add(node1);
    }
    
    /*
     static void printGraph(Project1_Prim graph)
     {
     for (int v = 1; v < graph.V+1; v++)
     {
     System.out.println("Adjacency list of vertex " + v);
     
     for(item item:graph.adjListArray[v])
     {
     System.out.println(item.key);
     }
     }
     }
     
     public static void readFile(String filename) throws FileNotFoundException, IOException
     {
     FileReader fileReader = new FileReader(filename);
     BufferedReader in = new BufferedReader(fileReader);
     
     String s = in.readLine();
     
     Scanner scan = new Scanner(s);
     
     int N = scan.nextInt();
     int M = scan.nextInt();
     
     Project_Prim1 graph = new Project_Prim1(N);            //create a graph with N number of vertices
     
     for (int i = 0; i < M; i++)            //add edges to following vertices
     {
     String lines = in.readLine();
     Scanner sc = new Scanner(lines);
     int src = sc.nextInt();
     int dest = sc.nextInt();
     int weight = sc.nextInt();
     addEdge(graph, src, dest, weight);
     }
     
     Prim(graph, N);
     //printGraph(graph);
     scan.close();
     
     }
     */
    
    public static void Prim(Project1_Prim graph, int N)
    {
        boolean visited[] = new boolean[N+1];            //save visited vertices
        Custom_Min_Heap  minHeap = new Custom_Min_Heap(N);
        int [] pred = new int[N+1];
        long sum = 0;
        
        for (int i = 1; i < N + 1 ; i++)            //first save all keys to infinity
        {
            item p = new item(i, Integer.MAX_VALUE);
            minHeap.add(p);
            visited[i] = false;
        }
        
        //initializing vertex1 as the source node to start
        minHeap.heap[0].key = 0;
        visited[1] = true;
        pred[1] = -1;
        
        while(!minHeap.isEmpty())
        {
            item min = minHeap.extract_min();
            int vertex = min.V;
            
            for(item node:graph.adjListArray[vertex]) //for every adjacent vertex of min (Visiting source vertex)
            {
                int k = minHeap.getVertexIndex(node.V); //Get the internal index of the adjacent vertex
                
                //if the adjacent vertext is not visited and its weight is less than previous weight in Heap
                if ((visited[node.V]==false) && (node.key < minHeap.heap[k].key))
                {
                    minHeap.decreaseKey(node.V, node.key); //set the weight in Heap as this new weight
                    pred[node.V] = vertex;        //the predecessor of this adjacent vertex becomes the visiting source vertex
                }
            }
            visited[vertex] = true;    //mark source vertex visited
        }
        
        for(int i = 2; i < N + 1; i++)
        {
            for(item shortest_Node:graph.adjListArray[i]) {
                if(shortest_Node.V == pred[i]) {
                    sum += shortest_Node.key;
                }
            }
        }
        System.out.println(sum);
    }
}

