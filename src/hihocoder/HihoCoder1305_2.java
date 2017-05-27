package hihocoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1305.
 * 
 * @version 2017-05-27
 * @author Zhang Yufei.
 */
public class HihoCoder1305_2 {
	/**
	 * Range A and range B.
	 */
	private static enum Range {
		RA, RB;
	}

	/**
	 * Start or end edge of the given value.
	 */
	private static enum Type {
		START, END;
	}

	/**
	 * Record the edge value of each range.
	 */
	private static class Edge implements Comparable<Edge> {
		Range range;
		Type type;
		int value;

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return value - o.value;
		}
	}

	/**
	 * Input data.
	 */
	private static int N, M;

	/**
	 * Range data list.
	 */
	private static Edge[] edges;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		M = scan.nextInt();

		edges = new Edge[2 * (N + M)];
		for (int i = 0; i < 2 * (N + M); i++) {
			edges[i] = new Edge();
			edges[i].value = scan.nextInt();

			if (i % 2 == 0) {
				edges[i].type = Type.START;
			} else {
				edges[i].type = Type.END;
			}

			if (i >= 2 * N) {
				edges[i].range = Range.RB;
			} else {
				edges[i].range = Range.RA;
			}
		}

		scan.close();

		Arrays.sort(edges);

		int cntA = 0;
		int cntB = 0;
		int result = 0;
		for (int i = 0; i < 2 * (N + M) - 1; i++) {
			if (edges[i].type == Type.START) {
				if (edges[i].range == Range.RA) {
					cntA++;
				} else if (edges[i].range == Range.RB) {
					cntB++;
				}
			} else if (edges[i].type == Type.END) {
				if (edges[i].range == Range.RA) {
					cntA--;
				} else if (edges[i].range == Range.RB) {
					cntB--;
				}
			}

			if (cntA != 0 && cntB == 0) {
				result += edges[i + 1].value
						- edges[i].value;
			}
		}

		System.out.println(result);
	}
}