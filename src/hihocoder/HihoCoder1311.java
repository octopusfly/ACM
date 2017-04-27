package hihocoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1311.
 * 
 * @version 2017-04-27
 * @author Zhang Yufei.
 */
public class HihoCoder1311 {
	/**
	 * Input data.
	 */
	private static int T;

	/**
	 * Used for input.
	 */
	private static Scanner scan;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command line parameters list.
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
		char[] num = scan.next().toCharArray();
		char[] r = Arrays.copyOfRange(num, 2, num.length);
		int count = 0;

		StringBuilder builder = new StringBuilder();
		while (true) {
			builder.delete(0, builder.length());
			int c = 0;
			boolean tag = false;
			for (int i = 0; i < r.length; i++) {
				int x = c * 10 + r[i] - '0';
				c = x % 5;
				if (x / 5 != 0) {
					tag = true;
				}

				if (tag) {
					builder.append(x / 5);
				}
			}

			if (c != 0) {
				break;
			}

			count++;
			
			if(builder.length() == 0) {
				break;
			}
			r = builder.toString().toCharArray();
		}

		if (count < num.length - 2) {
			System.out.println("NO");
			return;
		}
		
		builder.delete(0, builder.length());
		int c = 0;
		while(true) {
			if(check(num)) {
				break;
			}
			
			builder.append(num[0]);
			if(builder.length() == 1) {
				builder.append('.');
			}
			
			num[0] = '0';
			for(int i = num.length - 1; i >= 0; i--) {
				if(num[i] == '.') {
					continue;
				}
				int x = num[i] - '0';
				x *= 2;
				x += c;
				num[i] = (char) (x % 10 + '0');
				c = x / 10;
			}
		}
		builder.append(num[0]);
		System.out.println(builder.toString());
	}
	
	private static boolean check(char[] num) {
		for(int i = 2; i < num.length; i++) {
			if(num[i] != '0') {
				return false;
			}
		}
		
		return true;
	}
}
