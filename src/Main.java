import java.util.Scanner;

public class Main {

	private static int[] list = new int[105000];
	private static int[] prime = new int[1000];

	private static int T;
	private static int k;

	public static void getPrime() {
    	list[0] = list[1] = 1;
    	for(int i = 0; i < 105000; i++) {
    		list[i] = 0;
    	}
    	
    	list[0] = list[1] = 1;
    	
    	for(int i = 2; i < 105000; i++) {
    		if(list[i] == 0) {
    			for(int j = 2; i * j < 105000; j++) {
    				list[i * j] = 1;
    			}
    		}
    	}
    }

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		T = scan.nextInt();

		for (int i = 0; i < T; i++) {
			k = scan.nextInt();
			long result = prime[k - 1] * prime[k - 1] - k;
			System.out.println(result);
		}

		scan.close();
	}
}