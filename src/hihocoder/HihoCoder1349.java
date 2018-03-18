package hihocoder;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1349. 
 * Result: RA.
 * 
 * @version 2017-06-27
 * @author Zhang Yufei.
 */
public class HihoCoder1349 {
    /**
     * The input.
     */
    private static Scanner scan;

    /**
     * The output.
     */
    private static PrintWriter writer;

    /**
     * The main program.
     * 
     * @param args
     *            The command-line parameters list.
     */
    public static void main(String[] args) {
        initIO();

        long N = scan.nextLong();

        long x = 1;
        while (true) {
            long s = (long) ((9l * x - 1) / 9.0 * Math.pow(10, x) + 1 / 9.0 + 1);
            if (s >= N) {
                break;
            }
            x++;
        }

        x--;
        long s = (long) ((9l * x - 1) / 9.0 * Math.pow(10, x) + 1 / 9.0 + 1);

        N -= s;

        long num = (long) Math.pow(10, x) + N / (x + 1);
        if(num % 10 == 0) {
            long num2 = (num / 10 - 1) * 10;
            String str1 = "" + num;
            String str2 = "" + num2;
            if(str1.length() == str2.length()) {
                num = num2;
            }
        }
        
        int pos = (int) (N % (x + 1));

        writer.println(("" + num).charAt(pos));

        scan.close();
        writer.close();
    }

    /**
     * Initial the input and output.
     */
    private static void initIO() {
        try {
//           scan = new Scanner(System.in);
            scan = new Scanner(new File(
                    "E:\\workspace\\ACM\\src\\data.txt"));
            writer = new PrintWriter(new File(
                    "E:\\workspace\\ACM\\src\\test.txt"));
//           writer = new PrintWriter(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
