package hihocoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1322.
 * 
 * @version 2017-05-05
 * @author Zhang Yufei.
 */
public class HihoCoder1322 {
	/**
	 * The node in graph.
	 */
	private static class Node {
		/**
		 * Used in dfs mark if the node has been visited.
		 */
		boolean visited;

		/**
		 * The nodes list of all nodes which are adjcent to this node.
		 */
		List<Node> nodes;

		public Node() {
			nodes = new ArrayList<>();
			visited = false;
		}
	}

	/**
	 * The input data.
	 */
	private static int T, N, M;

	/**
	 * Node list in graph.
	 */
	private static Node[] nodes;

	/**
	 * For input.
	 */
	private static Scanner scan;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		scan = new Scanner(System.in);

		T = scan.nextInt();

		for (int i = 0; i < T; i++) {
			function();
		}

		scan.close();
	}

	/**
	 * Deals with one test case.
	 */
	private static void function() {
		N = scan.nextInt();
		M = scan.nextInt();

		nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			nodes[i] = new Node();
		}

		for (int i = 0; i < M; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			a--;
			b--;
			nodes[a].nodes.add(nodes[b]);
			nodes[b].nodes.add(nodes[a]);
		}

		if(N != M + 1) {
			System.out.println("NO");
			return;
		}
		
		dfs(nodes[0]);
		
		boolean result = true;
		for(int i = 0; i < N; i++) {
			if(!nodes[i].visited) {
				result = false;
				break;
			}
		}
		
		if(result) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	/**
	 * Using DFS to visit the graph.
	 * 
	 * @param cur
	 *            The current node.
	 */
	private static void dfs(Node cur) {
		cur.visited = true;
		
		for(Node n : cur.nodes) {
			if(!n.visited) {
				dfs(n);
			}
		}
	}
}
