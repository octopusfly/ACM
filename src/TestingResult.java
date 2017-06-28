import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestingResult {
	public static boolean testResult() {
		boolean result = true;
		try {
			Scanner scan1 = new Scanner(
					new File(
							"E:\\workspace\\ACM\\src\\comparison.txt"));
			Scanner scan2 = new Scanner(new File(
					"E:\\workspace\\ACM\\src\\test.txt"));

			while (scan1.hasNext() && scan2.hasNext()) {
				String str1 = scan1.nextLine();
				String str2 = scan2.nextLine();

				if (!str1.equals(str2)) {
					result = false;
					break;
				}
			}
			if (result) {
				if (scan1.hasNext() || scan2.hasNext()) {
					result = false;
				} else {
					result = true;
				}
			}

			scan1.close();
			scan2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result) {
			System.out.println("Success");
		} else {
			System.out.println("Failed");
		}

		return result;
	}

	public static void main(String[] args) {
		testResult();
	}
}
