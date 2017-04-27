package hihocoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1310.
 * 
 * @version 2017-04-26
 * @author Zhang Yufei.
 */
public class HihoCoder1310 {
	/**
	 * Record the information of island.
	 */
	private static class Island implements
			Comparable<Island> {
		int area;
		String shape;

		@Override
		public int compareTo(Island o) {
			if (area < o.area) {
				return -1;
			} else if (area > o.area) {
				return 1;
			} else {
				return shape.compareTo(o.shape);
			}
		}
	}

	/**
	 * Input data.
	 */
	private static int N, M;

	/**
	 * The map of the islands.
	 */
	private static char[][] map;

	/**
	 * Mark if the island has been visited.
	 */
	private static int[][] tags;

	/**
	 * Record the range in x and y coordinates of a island.
	 */
	private static int maxX, minX, maxY, minY;

	/**
	 * The area of a island;
	 */
	private static int area;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command line parameters list.
	 */
	public static void main(String[] args) {
		// Input
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		M = scan.nextInt();

		map = new char[N][];
		tags = new int[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = scan.next().toCharArray();
			for (int j = 0; j < M; j++) {
				tags[i][j] = -1;
			}
		}

		scan.close();

		// DFS
		List<Island> islands = new ArrayList<>();

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '#' && tags[i][j] == -1) {
					cnt++;

					maxX = minX = maxY = minY = -1;
					area = 0;

					dfs(i, j, cnt);

					Island island = new Island();
					island.area = area;
					island.shape = getShape(cnt);

					islands.add(island);
				}
			}
		}

		// Compute.
		Object[] islandsArray = islands.toArray();

		Arrays.sort(islandsArray);

		int areaCnt, shapeCnt;

		areaCnt = shapeCnt = 1;
		for (int i = 1; i < islandsArray.length; i++) {
			Island cur = (Island) islandsArray[i];
			Island pre = (Island) islandsArray[i - 1];
			if (cur.area != pre.area) {
				areaCnt++;
			}
			if (!cur.shape.equals(pre.shape)) {
				shapeCnt++;
			}
//			System.out.println(cur.shape);
		}

		System.out.println(islandsArray.length + " "
				+ areaCnt + " " + shapeCnt);
	}

	/**
	 * Using DFS to find island.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @param tag
	 *            The tag for this island.
	 */
	private static void dfs(int x, int y, int tag) {
		tags[x][y] = tag;
		area++;

		if (maxX == -1 || maxX < x) {
			maxX = x;
		}

		if (minX == -1 || minX > x) {
			minX = x;
		}

		if (maxY == -1 || maxY < y) {
			maxY = y;
		}

		if (minY == -1 || minY > y) {
			minY = y;
		}

		if (x - 1 >= 0 && map[x - 1][y] == '#'
				&& tags[x - 1][y] == -1) {
			dfs(x - 1, y, tag);
		}

		if (x + 1 < N && map[x + 1][y] == '#'
				&& tags[x + 1][y] == -1) {
			dfs(x + 1, y, tag);
		}

		if (y - 1 >= 0 && map[x][y - 1] == '#'
				&& tags[x][y - 1] == -1) {
			dfs(x, y - 1, tag);
		}

		if (y + 1 < M && map[x][y + 1] == '#'
				&& tags[x][y + 1] == -1) {
			dfs(x, y + 1, tag);
		}
	}

	private static String getShape(int tag) {
		StringBuilder builder = new StringBuilder();
		builder.append(maxX - minX + 1);
		builder.append(",");
		builder.append(maxY - minY + 1);
		builder.append(":");
		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				if (tags[i][j] == tag) {
					builder.append('#');
				} else {
					builder.append('.');
				}
			}
		}

		return builder.toString();
	}
}
