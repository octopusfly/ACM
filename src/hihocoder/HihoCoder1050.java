package hihocoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1050
 * 
 * @version 2017-03-09
 * @author Zhang Yufei
 */
public class HihoCoder1050 {
	/**
	 * Input data.
	 */
	private static int N;
	
	/**
	 * Record the graph.
	 */
	private static Vertex[] graph;
	
	/**
	 * Record the result.
	 */
	private static int max;
	
	/**
	 * The main program.
	 * @param args The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);	
		N = scan.nextInt();
		
		graph = new Vertex[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new Vertex();
		}
		
		for(int i = 0; i < N - 1; i++) {
			int A, B;
			A = scan.nextInt();
			B = scan.nextInt();
			
			A--;
			B--;
			
			graph[A].edges.add(graph[B]);
			graph[B].edges.add(graph[A]);
		}
		
		scan.close();
		
		max = -1;
		search(graph[0]);
		
		System.out.println(max);
	}
	
	/**
	 * Search the answer for this problem using DFS.
	 * @param cur The current vertex to search.
	 * @return The maximum of the depth in the current tree.
	 */
	private static int search(Vertex cur) {
		cur.visited = true;
		int max1 = 0;
		int max2 = 0;
		int depth = 0;
		
		for(Vertex v : cur.edges) {
			if(v.visited) {
				continue;
			}
			int r = search(v);
			if(depth < r) {
				depth = r;
			}
			
			if(max2 < r) {
				max2 = r;
				if(max1 < max2) {
					int tmp = max1;
					max1 = max2;
					max2 = tmp;
				}
			}
		}
		
		depth++;
		if(max < max1 + max2) {
			max = max1 + max2;
		}
		
		return depth;
	}
}

/**
 * Record the node in graph.
 */
class Vertex {
	ArrayList<Vertex> edges;
	boolean visited;
	
	public Vertex() {
		edges = new ArrayList<>();
		visited = false;
	}
}
