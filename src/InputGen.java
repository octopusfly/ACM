import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

public class InputGen {
	private static PrintWriter writer;
	private static Random random;
	
	public static void setData(String data) {
		init();
		writer.println(data);
		writer.close();
	}
	
	public static void main(String[] args) {
		init();
		
		random = new Random();
		long N = (long) (Math.abs(random.nextLong()) % (long) (Math.pow(10, 18) + 1));
		writer.println(N);
		
		writer.close();
	}

	private static void init() {
		try {
			writer = new PrintWriter(new File(
					"E:\\workspace\\ACM\\src\\data.txt"));
			// writer = new PrintWriter(System.out);
			random = new Random();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
