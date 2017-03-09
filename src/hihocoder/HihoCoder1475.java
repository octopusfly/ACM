package hihocoder;

import java.util.HashMap;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1475
 * 
 * @version 2017-03-09
 * @author Zhang Yufei
 */
public class HihoCoder1475 {
	
	/**
	 * The constant used for mod.
	 */
	public static final int MOD = 1000000007;
	
	/**
	 * The input data.
	 */
	public static int N;
	
	/**
	 * sum[i] = array[0] + array[1] + ... + array[i].
	 */
	public static int[] sum;
	
	/**
	 * i => sum{f[j] | 1 <= j < i, s[j] = i}
	 */
	public static HashMap<Integer, Long> g;
	
	/**
	 * Record the result;
	 */
	public static long[] f;
	
	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		sum = new int[N];
		g = new HashMap<>();
		f = new long[N];
		
		// fsum = f[0] + f[1] + ... + f[i].
		long fsum = 1;
		g.put(0, (long) 1);
		for(int i = 0; i < N; i++) {
			int x = scan.nextInt();
			sum[i] = x;
			if(i > 0) {
				sum[i] += sum[i - 1];
			}
			
			f[i] = fsum;
			Long value = g.get(sum[i]);
			if(value == null) {
				g.put(sum[i], new Long(f[i]));
			} else {
				f[i] -= value;
				if(f[i] < 0) {
					f[i] += MOD;
				}
				value += f[i];
				value %= MOD;
				g.put(sum[i], value);
			}
			fsum += f[i];
			fsum %= MOD;
		}
		
		System.out.println(f[N - 1]);
		
		scan.close();
	}
}

