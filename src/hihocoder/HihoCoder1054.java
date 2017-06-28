package hihocoder;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1054. 
 * (Google interview problem)
 * 
 * @version 2017-06-27
 * @author Zhang Yufei.
 */
public class HihoCoder1054 {
	/**
	 * The input.
	 */
	private static Scanner scan;

	/**
	 * The output.
	 */
	private static PrintWriter writer;

	/**
	 * Record all the conditions, for example, only if 2 is visited, 3 were
	 * accessible from 1, the key-value <"1-3", 2> records this rule.
	 */
	private static HashMap<String, Integer> condition;

	/**
	 * Mark if the number is visited.
	 */
	private static boolean[] visited;

	/**
	 * Record all the legal paths.
	 */
	private static List<Long> paths;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		initIO();

		init();

		visited = new boolean[10];
		for (int i = 1; i <= 9; i++) {
			visited[i] = false;
		}

		paths = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			search(i, 0, 0L);
		}

		// writer.println(paths.size());

		int T = scan.nextInt();
		for (int i = 0; i < T; i++) {
			int N = scan.nextInt();
			long number = 0;

			for (int j = 0; j < N; j++) {
				int x = scan.nextInt();
				int y = scan.nextInt();
				if (x > y) {
					int tmp = x;
					x = y;
					y = tmp;
				}
				int edge = (x - 1) * 8 - (x - 1) * (x - 2)
						/ 2 + y - x - 1;
				number |= 1L << edge;
			}

			int count = 0;
			for (long p : paths) {
				if ((p & number) == number) {
					count++;
				}
			}

			writer.println(count);
			writer.flush();
		}

		scan.close();
		writer.close();
	}

	/**
	 * Some initial operation.
	 */
	private static void init() {
		condition = new HashMap<>();
		condition.put("1-3", 2);
		condition.put("1-7", 4);
		condition.put("1-9", 5);
		condition.put("2-8", 5);
		condition.put("3-7", 5);
		condition.put("3-9", 6);
		condition.put("4-6", 5);
		condition.put("7-9", 8);
	}

	/**
	 * Find all the legal paths and store them into set <code> paths </code>.
	 * 
	 * @param index
	 *            The current node.
	 * @param count
	 *            The current number of nodes.
	 * @param number
	 *            The number of the current path.
	 */
	private static void search(int index, int count,
			long number) {
		visited[index] = true;
		count++;
		if (count >= 4) {
			paths.add(number);
		}

		for (int i = 1; i <= 9; i++) {
			if (visited[i]) {
				continue;
			}

			int a = index;
			int b = i;
			if (a > b) {
				int tmp = a;
				a = b;
				b = tmp;
			}

			String con = "" + a + "-" + b;
			if (condition.containsKey(con)
					&& !visited[condition.get(con)]) {
				continue;
			}

			int edge = (a - 1) * 8 - (a - 1) * (a - 2) / 2
					+ b - a - 1;
			search(i, count, number | (1L << edge));
		}

		visited[index] = false;
	}

	/**
	 * Initial the input and output.
	 */
	private static void initIO() {
		try {
			scan = new Scanner(System.in);
			// scan = new Scanner(new File(
			// "E:\\workspace\\ACM\\src\\data.txt"));
			// writer = new PrintWriter(new File(
			// "E:\\workspace\\ACM\\src\\test.txt"));
			writer = new PrintWriter(System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

