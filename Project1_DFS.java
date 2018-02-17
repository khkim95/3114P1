package DFS;

/*
 * Please list your project group member information below
 *
 *						Name			Section CRN
 * Group Member 1:
 * Group Member 2:
 * Group Member 3:
 */

import java.util.Scanner;

import BFS.Custom_Queue;
import BFS.Project1_BFS;

//You may use ArrayList and LinkedList only for building an Adjacency List.
//Any additional imports from java.util are forbidden
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.StringBuilder;

/*
In general, you should not need any additional imports. If you want to import something that you find absolutely
necessary, ask about it on Piazza.
 */

public class Project1_DFS 
{
	protected LinkedList<Integer> adjListArray[];
	private int V;
	
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

		readFile("tests/dfs/test5.in");
		
		Scanner s = new Scanner(System.in);
		//Insert your code here
	
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
	
	public static void DFS(Project1_DFS graph, int S, int N)
	{

		boolean visited[] = new boolean[N+1];			//save visited vertices	
		Custom_Stack stack = new Custom_Stack(N);		//custom stack object
		
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
