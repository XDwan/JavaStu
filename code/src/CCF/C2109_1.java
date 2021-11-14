package CCF;

import java.util.Scanner;

public class C2109_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] B = new int[n];
        for (int i = 0; i < n; i++) {
            B[i] = input.nextInt();
        }
        int A_max = 0;
        int A_min = 0;

        A_max += B[0];
        A_min += B[0];

        for (int i = 1; i < n; i++) {
            if (B[i] - B[i - 1] > 0) {
                A_max += B[i];
                A_min += B[i];
                continue;
            }
            A_max += B[i];
            A_min += 0;
        }
        System.out.println(A_max);
        System.out.println(A_min);
    }
}
