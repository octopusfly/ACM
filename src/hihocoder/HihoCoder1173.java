package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1173.
 * 
 * @version 2017-01-14
 * @author Zhang Yufei.
 */
public class HihoCoder1173 {
	/**
	 * The main program.
	 * @param args The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		
		int result = 0;
		int sg;
		for(int i = 0; i < N; i++) {
			int x = scan.nextInt();
			if(x % 4 == 1 || x % 4 == 2) {
				sg = x;
			} else if(x % 4 == 3) {
				sg = x + 1;
			} else {
				sg = x - 1;
			}
			result ^= sg;
		}
		
		scan.close();
		if(result == 0) {
			System.out.println("Bob");
		} else {
			System.out.println("Alice");
		}
	}
}
