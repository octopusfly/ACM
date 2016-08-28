import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1238.
 * 
 * @version 2016-08-22
 * @author Zhang Yufei
 */
public class Main {

	// Input data.
	private static int N, M;

	// The graph.
	private static Vertex[] graph;
	private static Edge[][] matrix;

	// All the edges.
	private static Edge[] edges;

	// The THD value.
	private static int THD;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command line parameters.
	 *
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		M = scan.nextInt();

		graph = new Vertex[N];
		edges = new Edge[N - 1];
		matrix = new Edge[N][N];

		/*
		 * Store the graph using adjacent list.
		 */
		for (int i = 0; i < N; i++) {
			graph[i] = new Vertex(N - 1);
		}

		for (int i = 0; i < N - 1; i++) {
			edges[i] = new Edge();
			int u, v, k;
			u = scan.nextInt();
			v = scan.nextInt();
			k = scan.nextInt();

			u--;
			v--;

			edges[i].length = k;

			edges[i].dst1 = u;
			edges[i].dst2 = v;

			graph[u].add(edges[i]);
			graph[v].add(edges[i]);

			matrix[u][v] = matrix[v][u] = edges[i];
		}

		/*
		 * Compute the weight of every edge and get the THD value.
		 */
		getWeight(graph[0]);

		THD = 0;
		for (int i = 0; i < N - 1; i++) {
			THD += edges[i].length * edges[i].weight;
		}

		/*
		 * Get the operation and output the result.
		 */
		for (int i = 0; i < M; i++) {
			String operation = scan.next();

			if (operation.equals("EDIT")) {
				int u, v, k;
				u = scan.nextInt();
				v = scan.nextInt();
				k = scan.nextInt();

				u--;
				v--;

				THD -= matrix[u][v].length * matrix[u][v].weight;
				matrix[u][v].length = k;
				THD += matrix[u][v].length * matrix[u][v].weight;
			} else if (operation.equals("QUERY")) {
				System.out.println(THD);
			}
		}

		scan.close();
	}

	/**
	 * This function set the weight of the all edges using dfs.
	 * 
	 * @param cur
	 *            The current vertex to operation.
	 * @return The number of vertexes in the next iterator.
	 */
	private static int getWeight(Vertex cur) {
		cur.visited = true;
		int count = 1;
		for (int i = 0; i < cur.count; i++) {
			Vertex dst = null;
			if (cur != graph[cur.edges[i].dst1]) {
				dst = graph[cur.edges[i].dst1];
			} else {
				dst = graph[cur.edges[i].dst2];
			}

			if (!dst.visited) {
				int cnt = getWeight(dst);
				cur.edges[i].weight = cnt * (N - cnt);
				count += cnt;
			}
		}

		return count;
	}

}

/**
 * Define the class to record the edges in graph.
 * 
 * @author Zhang Yufei.
 */
class Edge {
	/**
	 * The destination of the edge.
	 */
	int dst1, dst2;

	/**
	 * The weight of this edge.
	 */
	int weight;

	/**
	 * The length of this edge.
	 */
	int length;

	Edge() {
		dst1 = dst2 = -1;
		weight = -1;
		length = -1;
	}
}

/**
 * Define class to record the vertexes in graph.
 * 
 * @author Zhang Yufei.
 *
 */
class Vertex {
	/**
	 * The edges whose destination is this vertex.
	 */
	Edge[] edges;
	int count;

	/**
	 * Mark if the vertex has been visited.
	 */
	boolean visited;

	Vertex(int size) {
		edges = new Edge[size];
		count = 0;
		visited = false;
	}
	
	void add(Edge e) {
		edges[count++] = e;
	}
}