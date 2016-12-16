import java.util.Scanner;

/**
 * This is the ACM problem solving program for Taige Algorithm contest problem
 * C.
 * 
 * @version 2016-12-16
 * @author Zhang Yufei
 */
public class Main {
    /**
     * Used to input.
     */
    private static Scanner scan;

    /**
     * The main program.
     * 
     * @param args
     *            The command-line parameters-list.
     */
    public static void main(String[] args) {
        scan = new Scanner(System.in);
        int T = scan.nextInt();

        for (int i = 0; i < T; i++) {
            function();
        }

        scan.close();
    }

    /**
     * Deal with one test case.
     */
    private static void function() {
        int N, K;
        N = scan.nextInt();
        K = scan.nextInt();

        if (N % K != 0) {
            System.out.println("NO");
            return;
        }

        Group[] groups = new Group[N / K];

        for (int i = 0; i < N / K; i++) {
            groups[i] = new Group(K);
        }

        boolean result = true;
        for (int i = 0; i < N; i++) {
            int x = scan.nextInt();
            if (result) {
                int j = 0;
                for (j = 0; j < N / K; j++) {
                    if (groups[j].addNum(x)) {
                        break;
                    }
                }
                if (j == N / K) {
                    result = false;
                }
            }
        }
        
        if(result) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        
        return;
    }
}

class Group {
    private int K;
    private int current;
    private int[] number;

    Group(int K) {
        this.K = K;
        number = new int[K];
        current = -1;
    }

    boolean addNum(int n) {
        if (current == K - 1) {
            return false;
        }
        if (current != -1 && number[current] + 1 != n) {
            return false;
        }

        current++;
        number[current] = n;

        return true;
    }
}