package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1163.
 * 
 * @version 2017-01-11
 * @author Zhang Yufei.
 */
public class HihoCoder1163 {
	/**
	 * The main program.
	 * @param args The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		
		int result = 0;
		for(int i = 0; i < N; i++) {
			int x = scan.nextInt();
			result ^= x;
		}
		
		if(result == 0) {
			System.out.println("Bob");
		} else {
			System.out.println("Alice");
		}
		
		scan.close();
	}
}
