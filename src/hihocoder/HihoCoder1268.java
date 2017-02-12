package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1268
 * 
 * @version 2017-01-29
 * @author Zhang Yufei
 */
public class HihoCoder1268 {
    /**
     * All possible results.
     */
    private static int[][] results = new int[][] {
            { 2, 7, 6, 9, 5, 1, 4, 3, 8 },
            { 2, 9, 4, 7, 5, 3, 6, 1, 8 },
            { 4, 3, 8, 9, 5, 1, 2, 7, 6 },
            { 4, 9, 2, 3, 5, 7, 8, 1, 6 },
            { 6, 1, 8, 7, 5, 3, 2, 9, 4 },
            { 6, 7, 2, 1, 5, 9, 8, 3, 4 },
            { 8, 3, 4, 1, 5, 9, 6, 7, 2 },
            { 8, 1, 6, 3, 5, 7, 4, 9, 2 } };

    /**
     * Used for find all possible results and the 
     * space for input data.
     */
    private static int[] matrix = new int[] { 1,
            2, 3, 4, 5, 6, 7, 8, 9 };

    /**
     * The main program.
     * 
     * @param args
     *            The command-line parameters list.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        for(int i = 0; i < 9; i++) {
            matrix[i] = scan.nextInt();
        }
        
        scan.close();
        
        int answerIndex = -1;
        int answerCount = 0;
        
        for(int i = 0; i < results.length; i++) {
            int j;
            for(j = 0; j < results[i].length; j++) {
                if(matrix[j] != 0 && matrix[j] != results[i][j]) {
                    break;
                }
            }
            if(j == results[i].length) {
                answerIndex = i;
                answerCount++;
            }
        }
        
        if(answerCount > 1) {
            System.out.println("Too Many");
        } else {
            for(int i = 0; i < 9; i++) {
                System.out.print(results[answerIndex][i] + " ");
                if(i % 3 == 2) {
                    System.out.println();
                }
            }
        }
    }

//    /**
//     * Find the all results.
//     * 
//     * @param index
//     *            The current index to search.
//     */
//    private static void search(int index) {
//        if (index == 8) {
//            if (matrix[0] + matrix[1]
//                    + matrix[2] == 15
//                    && matrix[3] + matrix[4]
//                            + matrix[5] == 15
//                    && matrix[6] + matrix[7]
//                            + matrix[8] == 15
//                    && matrix[0] + matrix[3]
//                            + matrix[6] == 15
//                    && matrix[1] + matrix[4]
//                            + matrix[7] == 15
//                    && matrix[2] + matrix[5]
//                            + matrix[8] == 15
//                    && matrix[0] + matrix[4]
//                            + matrix[8] == 15
//                    && matrix[2] + matrix[4]
//                            + matrix[6] == 15) {
//                System.out.print("{");
//                for (int x : matrix) {
//                    System.out.print(x + ", ");
//                }
//                System.out.println("},");
//            }
//
//            return;
//        }
//
//        for (int i = index; i < 9; i++) {
//            int swap = matrix[index];
//            matrix[index] = matrix[i];
//            matrix[i] = swap;
//            search(index + 1);
//            swap = matrix[index];
//            matrix[index] = matrix[i];
//            matrix[i] = swap;
//        }
//    }
}
