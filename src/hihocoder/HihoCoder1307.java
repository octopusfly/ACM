package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1307.
 * 
 * @version 2017-04-26
 * @author Zhang Yufei.
 */
public class HihoCoder1307 {
	private static class Radar {
		int x;
		int y;
		int r;
		boolean visited;
	}

	/**
	 * Input data.
	 */
	@SuppressWarnings("unused")
	private static int T, W, H, N;

	/**
	 * Radar list.
	 */
	private static Radar[] radars;

	/**
	 * For input.
	 */
	private static Scanner scan;

	/**
	 * Record the range.
	 */
	private static int min, max;

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
		W = scan.nextInt();
		H = scan.nextInt();
		N = scan.nextInt();

		radars = new Radar[N];

		for (int i = 0; i < N; i++) {
			radars[i] = new Radar();
			radars[i].x = scan.nextInt();
			radars[i].y = scan.nextInt();
			radars[i].r = scan.nextInt();
			radars[i].visited = false;
		}

		for (int i = 0; i < N; i++) {
			if (!radars[i].visited) {
				max = Integer.MIN_VALUE;
				min = Integer.MAX_VALUE;
				dfs(i);
				if(min <= 0 && max >= H) {
					System.out.println("NO");
					return;
				}
			}
		}
		
		System.out.println("YES");
	}

	/**
	 * Use DFS to find the answer of this problem.
	 * 
	 * @param radar
	 *            The current radar to search;
	 */
	private static void dfs(int index) {
		Radar radar = radars[index];
		radar.visited = true;

		if (max < radar.y + radar.r) {
			max = radar.y + radar.r;
		}

		if (min > radar.y - radar.r) {
			min = radar.y - radar.r;
		}

		for (int j = 0; j < N; j++) {
			if (!radars[j].visited) {
				Radar rj = radars[j];
				long x1 = (long) radar.x;
				long x2 = (long) rj.x;
				long y1 = (long) radar.y;
				long y2 = (long) rj.y;
				long r1 = (long) radar.r;
				long r2 = (long) rj.r;
				if (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) <= 
						Math.pow(r1 + r2, 2)) {
					dfs(j);
				}
			}
		}
	}
}
