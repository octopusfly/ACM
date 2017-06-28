import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MultiGroupTesting {
	public static void main(String[] args) {
		int i = 0;
		long succ = 0;
		long fail = 0;
		for (long x = 1000000000000000000l; x >= 1000000000000000000l - 10000; x--) {
			System.out.println("\nTest Case #" + (i + 1));
			
			InputGen.setData("" + x);
			
			System.out.println("Input:");
			readInput();
			
			System.out.println("Testing");
			Main.main(null);
			System.out.println("Comparison");
			ComparisonGroup.main(null);
			
			System.out.println("Test Result:");
			if(TestingResult.testResult()) {
				succ++;
			} else {
				fail++;
			}
			i++;
		}
		
		System.out.println("Succ: " + succ + " Fail: " + fail);
	}

	private static void readInput() {
		try {
			Scanner scan = new Scanner(new File(
					"E:\\workspace\\ACM\\src\\data.txt"));
			while(scan.hasNext()) {
				System.out.println(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
