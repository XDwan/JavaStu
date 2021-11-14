package CCF;

import java.util.ArrayList;
import java.util.Scanner;

public class C2109_2 {

    static int[] a = new int[(int) 1e6];
    static int[] diff = new int[(int) 1e6];

    static void add(int l, int r) {
        diff[l]++;
        diff[r + 1]--;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = input.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            if (a[i - 1] < a[i]) {
                add(a[i - 1] + 1, a[i]);
            }
        }
        int max = 0, pre = 0;
        for (int p = 1; p < 1e4; p++) {
            pre += diff[p];
            max = Math.max(max, pre);
        }
        System.out.println(max);
    }
}
