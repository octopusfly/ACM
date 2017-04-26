package hihocoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1309.
 * 
 * @version 2017-04-26
 * @author Zhang Yufei.
 */
public class HihoCoder1309 {
	/**
	 * Record the task.
	 */
	private static class Task implements Comparable<Task> {
		int start;
		int end;
		boolean visited;

		@Override
		public int compareTo(Task o) {
			if (end > o.end) {
				return 1;
			} else if (end < o.end) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	/**
	 * Input data.
	 */
	private static int N;

	/**
	 * The task list.
	 */
	private static Task[] tasks;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();

		tasks = new Task[N];

		for (int i = 0; i < N; i++) {
			tasks[i] = new Task();
			tasks[i].start = scan.nextInt();
			tasks[i].end = scan.nextInt();
			tasks[i].visited = false;
		}

		scan.close();

		Arrays.sort(tasks);
		
//		for(Task t : tasks) {
//			System.out.print(t.end + " ");
//		}
//		System.out.println();
		
		int ans = 0;
		int count = 0;

		while (count < N) {
			Task cur = null;
			ans++;
			for (int i = 0; i < N; i++) {
				if (!tasks[i].visited) {
					if (cur == null
							|| tasks[i].start >= cur.end) {
						count++;
						tasks[i].visited = true;
						cur = tasks[i];
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}
