package hihocoder;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1350.
 * 
 * @version 2017-08-27
 * @author Zhang Yufei.
 */
public class HihoCoder1350 {
    /**
     * For input.
     */
    private static Scanner scan;

    /**
     * For output.
     */
    private static PrintWriter writer;

    /**
     * Define class to record the time.
     */
    private static class Time implements Comparable<Time> {
        int hour;
        int minute;

        @Override
        public int compareTo(Time o) {
            if (hour > o.hour) {
                return 1;
            } else if (hour < o.hour) {
                return -1;
            } else {
                if (minute > o.minute) {
                    return 1;
                } else if (minute < o.minute) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    /**
     * Input data.
     */
    private static int x;

    /**
     * The main program.
     * 
     * @param args
     *            The command-line parameters list.
     */
    public static void main(String[] args) {
        initIO();

        /*
         * Store all times in list, recording the number of '1' in each time
         * data.
         */
        @SuppressWarnings("unchecked")
        List<Time>[] result = new List[10];

        for (int i = 0; i < 10; i++) {
            result[i] = new ArrayList<Time>();
        }

        for (int i = 0; i < 1440; i++) {
            int hour = i / 60;
            int minute = i % 60;

            Time t = new Time();
            t.hour = hour;
            t.minute = minute;

            int count = 0;
            while (hour > 0) {
                if (hour % 2 == 1) {
                    count++;
                }
                hour /= 2;
            }

            while (minute > 0) {
                if (minute % 2 == 1) {
                    count++;
                }
                minute /= 2;
            }

            result[count].add(t);
        }

        /*
         * Deal with input.
         */
        x = scan.nextInt();
        Object[] times = result[x].toArray();
        Arrays.sort(times);

        for (Object o : times) {
            Time t = (Time) o;
            System.out.printf("%d%d:%d%d\n", t.hour / 10, t.hour % 10,
                    t.minute / 10, t.minute % 10);
        }
        
        closeIO();
    }

    /**
     * Initial the input and output.
     */
    private static void initIO() {
        try {
            scan = new Scanner(System.in);
            // scan = new Scanner(new
            // File("E:\\workspace\\ACM\\src\\data.txt"));
            // writer = new PrintWriter(
            // new File("E:\\workspace\\ACM\\src\\test.txt"));
            writer = new PrintWriter(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the input and output.
     */
    private static void closeIO() {
        scan.close();
        writer.close();
    }
}

