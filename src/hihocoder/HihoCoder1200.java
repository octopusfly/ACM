package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder week 110.
 * 
 * @version 2016-08-13
 * @author Zhang Yufei.
 */
public class HihoCoder1200 {
	// Input data.
	private static int N, M;

	/**
	 * Define the matrix to record the graph.
	 * 
	 * @author Zhang Yufei
	 */
	private static class Graph {
		public int[][] matrix;

		public Graph(int N) {
			matrix = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = -1;
				}
			}
		}
	}

	/*
	 * Define the array to store the graph. dist[s].matrix[i][j] means the
	 * minimum distance between node i and node j, given that the path between
	 * those nodes contains s nodes.
	 */
	private static Graph[] dist;

	/**
	 * This is the main program.
	 * 
	 * @param args
	 *            command line parameter list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		M = scan.nextInt();

		int count = (int) (Math.log(M) / Math.log(2)) + 1;
		dist = new Graph[count];

		for (int i = 0; i < count; i++) {
			dist[i] = new Graph(N);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[0].matrix[i][j] = scan.nextInt();
				if(i == j) {
					dist[0].matrix[i][j] = -1;
				}
			}
		}

		scan.close();

		for (int i = 1; i < count; i++) {
			compute(dist[i - 1], dist[i - 1], dist[i]);
		}

		int ans = 0;
		Graph temp1 = new Graph(N);
		Graph temp2 = new Graph(N);
		int c;
		for (c = count - 1; c >= 0; c--) {
			if (check(dist[c])) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						temp1.matrix[i][j] = dist[c].matrix[i][j];
					}
				}
				ans += (int) Math.pow(2, c);
				break;
			}
		}

		for (c--; c >= 0; c--) {
			compute(temp1, dist[c], temp2);
			if (check(temp2)) {
				ans += (int) Math.pow(2, c);
				Graph t = temp1;
				temp1 = temp2;
				temp2 = t;
			}
		}

		System.out.println(ans);
	}

	/**
	 * This function computes the minimum distance between every pairs of node
	 * in graph from all paths which contains the (m + n) node.
	 * 
	 * @param g1
	 *            The matrix1 to compute. It records the minimum distance
	 *            between every pairs of node given that the path contains m
	 *            nodes.
	 * @param g2
	 *            The another matrix to compute.It records the minimum distance
	 *            between every pairs of node given that the path contains n
	 *            nodes.
	 * @param result
	 *            The store space of the result matrix, which contains the
	 *            minimum distance between every pairs of node in graph from all
	 *            paths which contains the (m + n) node.
	 */
	public static void compute(Graph g1, Graph g2, Graph result) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result.matrix[i][j] = -1;
				for (int k = 0; k < N; k++) {
					if (g1.matrix[i][k] != -1 && g2.matrix[k][j] != -1) {
						int t = g1.matrix[i][k] + g2.matrix[k][j];
						if (result.matrix[i][j] == -1 || result.matrix[i][j] > t) {
							result.matrix[i][j] = t;
						}
					}
				}
			}
		}
	}

	/**
	 * This function checks if the matrix is legal.
	 * 
	 * @param g
	 *            The matrix to check.
	 * @return If the matrix is legal, returns <code>true</code> or returns
	 *         <code>false</code>
	 */
	public static boolean check(Graph g) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (g.matrix[i][j] != -1 && g.matrix[i][j] <= M) {
					return true;
				}
			}
		}

		return false;
	}
}
