package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1358.
 * 
 * @version 2016-08-21
 * @author Zhang Yufei
 */
public class HihoCoder1361 {
	private static int[] tags = new int[26];
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String s = scan.next();
		
		char[] s_array = s.toCharArray();
		char[][] passwd = new char[5][5];
		int r = 0, c = 0;
		
		for(int i = 0; i < 26; i++) {
			tags[i] = 0;
		}
		
		for(int i = 0; i < s_array.length; i++) {
			if(s_array[i] == 'J') {
				s_array[i] = 'I';
			}
			
			if(tags[s_array[i] - 'A'] == 0) {
				tags[s_array[i] - 'A'] = 1;
				passwd[r][c++] = s_array[i];
				if(c == 5) {
					r++;
					c = 0;
				}
			}
		}
		
		tags['J' - 'A'] = 1;
		for(int i = 0; i < 26; i++) {
			if(tags[i] == 0) {
				tags[i] = 1;
				passwd[r][c++] = (char) ('A' + i);
				if(c == 5) {
					r++;
					c = 0;
				}
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(passwd[i][j]);
			}
			System.out.println();
		}
		
		scan.close();
	}
}