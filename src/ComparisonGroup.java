import java.util.*;
import java.io.*;

public class ComparisonGroup {
	/**
	 * The input.
	 */
	private static Scanner scan;

	/**
	 * The output.
	 */
	private static PrintWriter writer;
//    public static char check(int n, int id) {
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i <= n; i++) {
//            sb.append(i);
//        }
//        return sb.charAt(id);
//    }

    public static void main(String[] args){
    	initIO();
       // Scanner input = new Scanner(new File("input.txt"));
        long n = scan.nextLong();
        if(n < 10) {
            writer.print(n);
            writer.flush();
            return ;
        }
        long length = 0, start = 0, len = 1, power = 10;
        while(length + (power - start) * len <= n) {
            length += (power - start) * len;
//            System.out.println("length = " + length + " start = " + start + " power = " + power + " len = " + len);
            start = power;
            power *= 10;
            len++;
        }
//        System.out.println("after : " + "length = " + length + " start = " + start + " power = " + power + " len = " + len);
        long remain = n - length;
//        System.out.println("remain = " + remain);
        long pre = (remain + 1) / len, last = (remain) % len;
//        System.out.println("pre = " + pre + " last = " + last);
        long ret = (long)Math.pow(10, len - 1);
        if(pre != 0)
            ret += pre - 1;
//        System.out.println(ret);
        String val = String.valueOf(ret);
        writer.print(val.charAt((int)last));
//        System.out.println("right = " + check(3000, (int)n));
        
        scan.close();
        writer.flush();
    }
    
    /**
	 * Initial the input and output.
	 */
	private static void initIO() {
		try {
//			 scan = new Scanner(System.in);
			scan = new Scanner(new File(
					"E:\\workspace\\ACM\\src\\data.txt"));
			writer = new PrintWriter(new File(
					"E:\\workspace\\ACM\\src\\comparison.txt"));
//			 writer = new PrintWriter(System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
