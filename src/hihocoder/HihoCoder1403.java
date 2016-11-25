package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1403.
 * 
 * @version 2016-11-22
 * @author Zhang Yufei
 */
public class HihoCoder1403 {
    /**
     * The input data.
     */
    private static int N, K;

    /**
     * The node data list.
     */
    private static int[] node;

    /**
     * The suffix array list, sorted on dictionary.
     */
    private static int[] sa;
    
    /**
     * The rank[i] means the order of the suffix[i]
     * by dictionary sort.
     */
    private static int[] rank;
    
    /**
     * Record the longest common prefix of the 
     * suffix[sa[i]] and suffix[sa[i-1]].
     */
    private static int[] height;

    /**
     * The main program.
     * 
     * @param args
     *            The command line parameters list.
     */
    public static void main(String[] args) {
        // Input data.
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        K = scan.nextInt();
        K--;

        node = new int[N];
        sa = new int[N];
        height = new int[N];
        rank = new int[N];

        for (int i = 0; i < N; i++) {
            node[i] = scan.nextInt();
            node[i]--;
        }

        scan.close();

        // Sorted the suffix array.
        sort();

        // Compute the result.
        getHeight();        
        compute();
    }

    /**
     * Sort the suffix arrays according to dictionary.
     */
    private static void sort() {
        int rankCnt = N >= 101 ? N + 1 : 101;
        int[] count = new int[rankCnt];
        // Init
        for (int i = 0; i < rankCnt; i++) {
            count[i] = 0;
        }

        for (int i = 0; i < N; i++) {
            count[node[i]]++;
        }

        for (int i = 1; i < rankCnt; i++) {
            count[i] += count[i - 1];
        }

        for (int i = N - 1; i >= 0; i--) {
            sa[count[node[i]] - 1] = i;
            count[node[i]]--;
        }

        for (int i = 0; i < rankCnt; i++) {
            count[i] = 0;
        }
        
        rank[sa[0]] = 1;
        rankCnt = 2;

        for (int i = 1; i < N; i++) {
            rank[sa[i]] = rank[sa[i - 1]];
            if (node[sa[i]] != node[sa[i - 1]]) {
                rank[sa[i]]++;
                rankCnt++;
            }
        }


        // Sort the len subsequences according to len/2 subsequences.
        int[] tsa = new int[N];
        for (int l = 1; rank[sa[N - 1]] < N; l *= 2) {
            int[] A = new int[N];
            int[] B = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = rank[i];
                if (i + l < N) {
                    B[i] = rank[i + l];
                } else {
                    B[i] = 0;
                }
            }

            // Sort according to low key.
            for (int i = 0; i < N; i++) {
                count[B[i]]++;
            }

            for (int i = 1; i < rankCnt; i++) {
                count[i] += count[i - 1];
            }

            for (int i = N - 1; i >= 0; i--) {
                tsa[count[B[i]] - 1] = i;
                count[B[i]]--;
            }

            for (int i = 0; i < rankCnt; i++) {
                count[i] = 0;
            }

            // Sort according to high key.
            for (int i = 0; i < N; i++) {
                count[A[i]]++;
            }

            for (int i = 1; i < rankCnt; i++) {
                count[i] += count[i - 1];
            }

            for (int i = N - 1; i >= 0; i--) {
                sa[count[A[tsa[i]]] - 1] = tsa[i];
                count[A[tsa[i]]]--;
            }

            for (int i = 0; i < rankCnt; i++) {
                count[i] = 0;
            }

            // Update rank array value.
            rank[sa[0]] = 1;
            rankCnt = 2;
            for (int i = 1; i < N; i++) {
                rank[sa[i]] = rank[sa[i - 1]];
                if (A[sa[i]] != A[sa[i - 1]] || B[sa[i]] != B[sa[i - 1]]) {
                    rank[sa[i]]++;
                    rankCnt++;
                }
            }
        }
    }
    
    /**
     * Compute the height array.
     */
    private static void getHeight() {
        for(int i = 0; i < N; i++) {
            rank[i]--;
        }        
        
        if(rank[0] == 0) {
            height[rank[0]] = 0;
        } else {
            int j = 0;
            int k = sa[rank[0] - 1];
            int h = 0;
            while(j < N && k < N) {
                if(node[j] != node[k]) {
                    break;
                }
                j++;
                k++;
                h++;
            }
            height[rank[0]] = h;
        }
        
        for(int i = 1; i < N; i++) {
            if(rank[i] == 0) {
                height[rank[i]] = 0;
                continue;
            }
            int h = height[rank[i - 1]] - 1;
            if(h < 0) h = 0;
            int j = i + h;
            int k = sa[rank[i] - 1] + h;
            while(j < N && k < N) {
                if(node[j] != node[k]) {
                    break;
                }
                j++;
                k++;
                h++;
            }
            height[rank[i]] = h;
        }
    }
    
    /**
     * This function computes the result of this problem.
     */
    private static void compute() {
        if(K == 0) {
            System.out.println(N);
            return;
        } 
        int max = -1;
        for(int i = 0; i <= N - K; i++) {
            int min = -1;
            for(int j = 0; j < K; j++) {
               if(min == -1 || min > height[i + j]) {
                   min = height[i + j];
               }
            }
            
            if(max == -1 || max < min) {
                max = min;
            }
        }
        
        System.out.println(max);
    }
}
