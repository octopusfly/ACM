import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder ???.
 * 
 * @version 2017-05-04
 * @author Zhang Yufei.
 */
public class Main {
	/**
	 * The input data.
	 */
	private static int TASKS, N, P, W, H;

	/**
	 * For input.
	 */
	private static Scanner scan;
	
	/**
	 * The character number of each paragraph.
	 */
	private static int[] paragraph;
	
	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		TASKS = scan.nextInt();
		
		for(int i = 0; i < TASKS; i++) {
			N = scan.nextInt();
			P = scan.nextInt();
			W = scan.nextInt();
			H = scan.nextInt();
			
			paragraph = new int[N];
			for(int j = 0; j < N; j++) {
				paragraph[j] = scan.nextInt();
			}
			
			int a = 1;
			int b = W < H ? W + 1 : H + 1;
			int result = -1;
			while(b >= a) {
				int S = (a + b) / 2;
				int charPerLine = (int) Math.floor((double) W / S);
				int linePerPage = (int) Math.floor((double) H / S);
				
				int lines = 0;
				for(int j = 0; j < N; j++) {
					lines += Math.ceil((double) paragraph[j] / charPerLine);
				}
				
				int pages = (int) Math.ceil((double) lines / linePerPage);
				
				if(pages <= P) {
					result = S;
					a = S + 1;
				} else {
					b = S - 1;
				}
				
			}
			
			System.out.println(result);
		}
		
		scan.close();
	}
}
