package CCF;

import java.util.Scanner;

public class C2109_2_my {


    private static final int N = (int) 1e6;
    static int[] a = new int[N];
    static int[] diff = new int[N];

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = input.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            if (a[i-1] < a[i]){
                diff[a[i-1]+1] ++;
                diff[a[i]+1] --;
            }
        }

        int max = 0;
        int pre = 0;

        for (int i=0;i<1e4;i++){
            pre+= diff[i];
            max = Math.max(max,pre);
        }
        System.out.println(max);
    }

}
