package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1319.
 * 
 * @version 2017-04-29
 * @author Zhang Yufei.
 */
public class HihoCoder1319 {
	/**
	 * Input data.
	 */
	private static int N, M, x, y;

	/**
	 * Record the matrix.
	 */
	private static int[][] matrix;
	
	/**
	 * Record if each grid has been visited.
	 */
	private static boolean[][] visited;

	/**
	 * Record the perimeter.
	 */
	private static int perimeter;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		M = scan.nextInt();
		x = scan.nextInt();
		y = scan.nextInt();

		matrix = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				matrix[i][j] = scan.nextInt();
				visited[i][j] = false;
			}
		}

		scan.close();

		perimeter = 0;

		dfs(x, y);
		
		System.out.println(perimeter);
	}

	/**
	 * Compute the parameter using DFS.
	 * 
	 * @param x
	 *            The current x coordinate.
	 * @param y
	 *            The current y coordinate.
	 */
	private static void dfs(int x, int y) {
		if(visited[x][y]) {
			return;
		}
		
		visited[x][y] = true;
		
		if (x - 1 >= 0 && matrix[x - 1][y] == matrix[x][y]) {
			dfs(x - 1, y);
		} else {
			perimeter++;
		}
		
		if(x + 1 < N && matrix[x + 1][y] == matrix[x][y]) {
			dfs(x + 1, y);
		} else {
			perimeter++;
		}
		
		if(y - 1 >= 0 && matrix[x][y - 1] == matrix[x][y]) {
			dfs(x, y - 1);
		} else {
			perimeter++;
		}
		
		if(y + 1 < M && matrix[x][y + 1] == matrix[x][y]) {
			dfs(x, y + 1);
		} else {
			perimeter++;
		}
	}
}
