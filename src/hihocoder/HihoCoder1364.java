package hihocoder;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1364.
 * 
 * @version 2018-03-24
 * @author Zhang Yufei.
 */
public class HihoCoder1364 {
    /**
     * Record the commodity information.
     */
    private static class Commodity {
        int price;
        int value;
    }
    
    /** For input. */
    private static Scanner scan;

    /** For output. */
    private static PrintWriter writer;

    /** Input data */
    private static int M, N;
    
    /** Commodity list */
    private static List<Commodity> commodities;
    
    /** 
     *  Used for DP, in i-th iterator, dp[j] means the maximum 
     *  prize given i commodities and j token.
     */
    private static int[] dp;
    
    /**
     * Used for optimization, matrix[i][j] records the amount
     * of commodities whose price is i and value is j.
     */
    private static int[][] matrix;

    /**
     * The main program.
     * 
     * @param args
     *            The command-line parameters list.
     */
    public static void main(String[] args) {
        initIO();
        
        input();
        
        dp = new int[M + 1];
        
        for(int j = 0; j <= M; j++) {
            dp[j] = 0;
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = M; j >= 0; j--) {
                int r1 = dp[j];
                int r2 = 0;
                if(j - commodities.get(i).price >= 0) {
                    r2 = dp[j - commodities.get(i).price] + commodities.get(i).value;
                }
                
                dp[j] = r1 > r2 ? r1 : r2;
            }
        }
        
        System.out.println(dp[M]);
        
        closeIO();
    }

    /**
     * Deal with input.
     */
    private static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        
        matrix = new int[11][11];
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                matrix[i][j] = 0;
            }
        }
        
        for(int i = 0; i < N; i++) {
            int W, P;
            W = scan.nextInt();
            P = scan.nextInt();
            
            matrix[W][P]++;
        }
        
        commodities = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            for(int j = 1; j <= 10; j++) {
                int count = matrix[i][j];
                if(count > 0) {
                    int s = 1;
                    while(count >= s) {
                        Commodity c = new Commodity();
                        c.price = s * i;
                        c.value = s * j;
                        
                        commodities.add(c);
                        
                        count -= s;
                        s *= 2;
                    }
                    
                    if(count > 0) {
                        Commodity c = new Commodity();
                        c.price = count * i;
                        c.value = count * j;
                        
                        commodities.add(c);
                    }
                }
            }
        }
        
        N = commodities.size();
    }
    
    /**
     * Initial the input and output.
     */
    private static void initIO() {
        try {
            scan = new Scanner(System.in);
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

