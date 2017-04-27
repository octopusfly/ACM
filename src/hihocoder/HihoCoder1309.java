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
	 * Record the task time.
	 */
	private static class TaskTime implements Comparable<TaskTime> {
		static final int START = 0;
		static final int END = 1;
		
		int time;
		int tag;

		@Override
		public int compareTo(TaskTime o) {
			if(time > o.time) {
				return 1;
			} else if(time < o.time) {
				return -1;
			} else {
				if(tag == START) {
					return 1;
				} else {
					return -1;
				}
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
	private static TaskTime[] times;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();

		times = new TaskTime[2 * N];

		for (int i = 0; i < 2 * N; i += 2) {
			times[i] = new TaskTime();
			times[i].time = scan.nextInt();
			times[i].tag = TaskTime.START;
			times[i + 1] = new TaskTime();
			times[i + 1].time = scan.nextInt();
			times[i + 1].tag = TaskTime.END;
		}

		scan.close();

		Arrays.sort(times);
		
		int result = 0;
		int ans = 0;
		for(int i = 0; i < 2 * N; i++) {
			if(times[i].tag == TaskTime.START) {
				ans++;
			} else {
				ans--;
			}
			
			if(result < ans) {
				result = ans;
			}
		}
		
		System.out.println(result);
	}
}
