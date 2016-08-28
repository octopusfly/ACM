import java.util.HashMap;
import java.util.Map.Entry;
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

	/*
	 * Using the hash map to record the graph. The element is the edge of graph,
	 * the key is the destination vertex and value is the length of edge.
	 */
	private static HashMap<Integer, Integer>[] graph;

	/*
	 * Record the level and the number of vertexes in the sub-tree.
	 */
	private static long[] level;
	private static long[] node;

	// The THD value.
	private static long THD;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command line parameters.
	 *
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		M = scan.nextInt();

		graph = new HashMap[N];
		level = new long[N];
		node = new long[N];

		/*
		 * Store the graph using adjacent list.
		 */
		for (int i = 0; i < N; i++) {
			graph[i] = new HashMap<Integer, Integer>();
			level[i] = 0;
			node[i] = 0;
		}

		for (int i = 0; i < N - 1; i++) {
			int u, v, k;
			u = scan.nextInt();
			v = scan.nextInt();
			k = scan.nextInt();
			
			u--;
			v--;
			
			graph[u].put(v, k);
			graph[v].put(u, k);
		}

		/*
		 * Compute the weight of every edge and get the THD value.
		 */
		THD = 0;
		level[0] = 1;
		getWeight(0);

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
				
				int child = level[u] < level[v] ? v : u;
				THD -= node[child] * (N - node[child]) * graph[u].get(v);
				graph[u].remove(v);
				graph[v].remove(u);
				graph[u].put(v, k);
				graph[v].put(u, k);
				THD += node[child] * (N - node[child]) * graph[u].get(v);
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
	 *            The current index of vertex to operation.
	 * @return The number of vertexes in the next iterator.
	 */
	private static void getWeight(int cur) {
		node[cur] = 1;
		for (Entry<Integer, Integer> e : graph[cur].entrySet()) {
			int index = e.getKey();
			if (level[index] == 0) {
				level[index] = level[cur] + 1;
				getWeight(index);
				THD += e.getValue() * node[index] * (N - node[index]);
				node[cur] += node[index];
			}
		}
	}
}