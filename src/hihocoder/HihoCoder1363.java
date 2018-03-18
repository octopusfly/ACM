package hihocoder;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1363.
 * 
 * @version 2018-03-18
 * @author Zhang Yufei.
 */
public class HihoCoder1363 {
    /** For input. */
    private static Scanner scan;

    /** For output. */
    private static PrintWriter writer;

    /** Input data */
    private static int H, W, D;

    /** Input data */
    private static int[][] A, B;

    /** The matrix used for Gauss elimination. */
    private static double[][] matrix;

    /** The result vector for Gauss elimination. */
    private static double[] result;

    /** Record the result */
    private static int[] op;

    /**
     * The main program.
     * 
     * @param args
     *            The command-line parameters list.
     */
    public static void main(String[] args) {
        initIO();

        input();
        diagonalization();
        compute();

        closeIO();
    }

    /**
     * Deal with the input data.
     */
    private static void input() {
        H = scan.nextInt();
        W = scan.nextInt();
        D = scan.nextInt();

        A = new int[H][W];
        B = new int[H - D + 1][W - D + 1];
        op = new int[D * D];
        matrix = new double[(H - D + 1) * (W - D + 1)][D * D];
        result = new double[(H - D + 1) * (W - D + 1)];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                A[i][j] = scan.nextInt();
            }
        }

        for (int i = 0; i < H - D + 1; i++) {
            for (int j = 0; j < W - D + 1; j++) {
                B[i][j] = scan.nextInt();
            }
        }

        for (int i = 0; i < H - D + 1; i++) {
            for (int j = 0; j < W - D + 1; j++) {
                makeRow(i, j);
            }
        }
        
//        for (int i = 0; i < (H - D + 1) * (W - D + 1); i++) {
//            for (int j = 0; j < D * D; j++) {
//                System.out.printf("%+03.2f ", matrix[i][j]);
//            }
//            System.out.printf("%+03.2f\n", result[i]);
//        }
//        System.out.println();
    }

    /**
     * Make one row in the matrix.
     * 
     * @param r
     *            The row of result.
     * @param c
     *            The column of result.
     */
    private static void makeRow(int r, int c) {
        int row = r * (W - D + 1) + c;
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < D; j++) {
                matrix[row][i * D + j] = A[r + i][c + j];
            }
        }

        result[row] = B[r][c];
    }

    /**
     * Compute the result, getting the answer of this problem.
     */
    private static void compute() {
        for(int i = D * D - 1; i >= 0; i--) {
            if(checkZero( matrix[i][i])) {
                continue;
            }
            for(int j = D * D - 1; j > i; j--) {
                result[i] -= matrix[i][j] * op[j];
            }
            op[i] = (int) Math.round(result[i] / matrix[i][i]);
        }
        
        for(int i = 0; i < D * D; i++) {
            System.out.print(op[i] + " ");

            if(i % D == D - 1) {
                System.out.println();
            }
            
        }
    }
    /**
     * Convert the matrix into diagonalization, which means that the value of
     * any element in position (i, j) is zero if the i is greater than j.
     */
    private static void diagonalization() {
        for (int i = 0; i < D * D; i++) {
            if (checkZero(matrix[i][i])) {
                for (int j = i + 1; j < (H - D + 1) * (W - D + 1); j++) {
                    if (!checkZero(matrix[j][i])) {
                        changeRow(i, j);
                        break;
                    }
                }
            }
            
            for(int j = i + 1; j < (H - D + 1) * (W - D + 1); j++) {
                if(!checkZero(matrix[j][i])) {
                    eliminate(j, i, -1.0 * matrix[j][i] / matrix[i][i]);
                }
            }
        }
        
//        for (int i = 0; i < (H - D + 1) * (W - D + 1); i++) {
//            for (int j = 0; j < D * D; j++) {
//                System.out.printf("%+03.2f ", matrix[i][j]);
//            }
//            System.out.printf("%+03.2f\n", result[i]);
//        }
    }

    /**
     * Check if the given double num is zero.
     * 
     * @param num
     *            The number to check.
     * @return Returns if the number is zero.
     */
    private static boolean checkZero(double num) {
        if (Math.abs(num) <= 1e-9) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Change two rows in matrix.
     * 
     * @param r1
     *            one row.
     * @param r2
     *            another row.
     */
    private static void changeRow(int r1, int r2) {
        for (int i = 0; i < D * D; i++) {
            double tmp = matrix[r1][i];
            matrix[r1][i] = matrix[r2][i];
            matrix[r2][i] = tmp;
        }
        
        double tmp = result[r1];
        result[r1] = result[r2];
        result[r2] = tmp;
    }

    /**
     * Eliminate one element in matrix, changing it into zero by computing r1 =
     * r1 + multiply * r2;
     * 
     * @param r1
     *            one row.
     * @param r2
     *            another row.
     * @param multiply
     *            Multiply parameter.
     */
    private static void eliminate(int r1, int r2, double multiply) {
        for (int i = 0; i < D * D; i++) {
            matrix[r1][i] = matrix[r1][i] + matrix[r2][i] * multiply;
        }
        result[r1] = result[r1] + result[r2] * multiply;
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

