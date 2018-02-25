/*
 * Please list your project group member information below
 *
 *						Name			Section CRN
 * Group Member 1:		Ethan Kim		12721
 * Group Member 2:		Bowen Xu		12721
 * Group Member 3:		Jiamin Xie		12721
 */

import java.util.Scanner;

//You may use ArrayList and LinkedList only for building an Adjacency List.
//Any additional imports from java.util are forbidden
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/*
In general, you should not need any additional imports. If you want to import something that you find absolutely
necessary, ask about it on Piazza.
 */

public class Project1_BFS 
{	
	private LinkedList<Integer> adjListArray[];			//linked list to store the graph
	private int V;
	
	@SuppressWarnings("unchecked")
	Project1_BFS(int V)	//constructor
	{
		this.V = V;
		adjListArray = new LinkedList[V+1];
		
		for (int i = 1; i < adjListArray.length; i++)
		{
			adjListArray[i] = new LinkedList<Integer>();
		}
	}	
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
	    /*
	    Insert your code here. Your BFS code should make use of a custom queue, which
	    you will implement. It would be good to make a class for it in a separate file.
	     */


		Scanner scan = new Scanner(System.in);
			
		int N = scan.nextInt();
		int M = scan.nextInt();
		int S = scan.nextInt();
		
		Project1_BFS graph = new Project1_BFS(N);			//create a graph with N number of vertices
		
		for (int i = 0; i < M; i++)			//add edges to following vertices
		{
			int src = scan.nextInt();
			int dest = scan.nextInt();			
			addEdge(graph, src, dest);
		}
		
		//printGraph(graph);
		scan.close();
		
		BFS(graph, S, N);
	}
	
	static public void addEdge(Project1_BFS graph, int src, int dest)
	{
		graph.adjListArray[src].add(dest);		//add edges to both direction since it is undirected.
		graph.adjListArray[dest].add(src);
	}
	
	static void printGraph(Project1_BFS graph)
	{
		for (int v = 1; v < graph.V+1; v++)
		{
			System.out.println("Adjacency list of vertex " +v);
			System.out.print("head");
			for (Integer i: graph.adjListArray[v])
			{
				System.out.print(" -> " + i);
			}
			System.out.println("\n");
		}
	}
	
	static void BFS(Project1_BFS graph, int S, int N)
	{

		boolean visited[] = new boolean[N+1];			//save visited vertices	
		int [] distanceArray = new int[N+1];
		Custom_Queue queue = new Custom_Queue(N);		//custom queue object
		
		visited[S] = true;		//mark started vertices visited
		queue.add(S);			//enqueue the start vertex
		
		while(!queue.isEmpty())
		{
			int out = queue.remove();
			Iterator<Integer> it = graph.adjListArray[out].listIterator();
			
			while(it.hasNext())		//while the queue has adjacent vertices
			{
				int next = it.next();
				if(visited[next] == false)
				{
					visited[next] = true;		//only if the vertex has not been visited
					queue.add(next);
					distanceArray[next] = distanceArray[out]+1;
				}
			}
		}
		distanceArray[S] = 0;
		
		for (int i = 1; i < distanceArray.length; i++)
			System.out.println(distanceArray[i]);
	}
	
	
	/*
	// for self test
	public static void readFile(String filename) throws FileNotFoundException, IOException
	{
		FileReader fileReader = new FileReader(filename);
		BufferedReader in = new BufferedReader(fileReader);
		
		String s = in.readLine();

		Scanner scan = new Scanner(s);
			
		int N = scan.nextInt();
		int M = scan.nextInt();
		int S = scan.nextInt();
		
		Project1_BFS graph = new Project1_BFS(N);			//create a graph with N number of vertices
		
		for (int i = 0; i < M; i++)			//add edges to following vertices
		{
			String lines = in.readLine();
			Scanner sc = new Scanner(lines);
			int src = sc.nextInt();
			int dest = sc.nextInt();			
			addEdge(graph, src, dest);
		}
		
		//printGraph(graph);
		scan.close();
		
		BFS(graph, S, N);
	}
	*/
}

