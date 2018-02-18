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
import java.util.Iterator;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
In general, you should not need any additional imports. If you want to import something that you find absolutely
necessary, ask about it on Piazza.
 */

public class Project1_DFS 
{
	protected LinkedList<Integer> adjListArray[];
	private int V;
	
	@SuppressWarnings("unchecked")
	Project1_DFS(int V)	//constructor
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
	    Insert your code here. Your DFS code should make use of a custom stack, which
	    you will implement. It would be good to make a class in a separate file.
	     */

		Scanner scan = new Scanner(System.in);
		
			
		int N = scan.nextInt();
		int M = scan.nextInt();
		int S = scan.nextInt();
		
		Project1_DFS graph = new Project1_DFS(N);			//create a graph with N number of vertices
		
		for (int i = 0; i < M; i++)			//add edges to following vertices
		{
			int src = scan.nextInt();
			int dest = scan.nextInt();			
			addEdge(graph, src, dest);
		}
		
		scan.close();
		DFS(graph, S, N, M);
	
    }
	
	static public void addEdge(Project1_DFS graph, int src, int dest)
	{
		graph.adjListArray[src].add(dest);		//add edges to both direction since it is undirected.
		graph.adjListArray[dest].add(src);
	}
	
	static void printGraph(Project1_DFS graph)
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
	
	/*
	public static void readFile(String filename) throws FileNotFoundException, IOException
	{
		FileReader fileReader = new FileReader(filename);
		BufferedReader in = new BufferedReader(fileReader);
		
		String s = in.readLine();

		Scanner scan = new Scanner(s);
			
		int N = scan.nextInt();
		int M = scan.nextInt();
		int S = scan.nextInt();
		
		Project1_DFS graph = new Project1_DFS(N);			//create a graph with N number of vertices
		
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
		
		DFS(graph, S, N);
	}
	*/
	
	public static void DFS(Project1_DFS graph, int S, int N, int M)
	{

		boolean visited[] = new boolean[N+1];			//save visited vertices	
		Custom_Stack stack = new Custom_Stack(M);		//custom stack object
		
		for (int i = 1; i < N + 1; i++)		//mark all vertices as not visited.
			visited[i] = false;
		
		stack.push(S);			//enqueue the start vertex
		
		while(!stack.isEmpty())
		{
			int out = stack.pop();
			
			if(visited[out] == false)
			{
				System.out.println(out);
				visited[out] = true;
			}
			
			Iterator<Integer> it = graph.adjListArray[out].listIterator();
			
			while(it.hasNext())
			{
				int next = it.next();
				if(visited[next] == false)
				{
					//visited[next] = true;
					stack.push(next);
				}
			}
		} 

	}
	
}
