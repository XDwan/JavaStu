package CCF;

import java.util.Scanner;

public class C2109_4 {
    static double[][] dp = new double[1 << 17][100];
    static boolean[][] dis = new boolean[1 << 17][100];
    static double[] p = new double[17];
    static int n;
    static int k;

    static double dfs(int status, int times, int cnt, int sum, double l) {
        //              状态     抽卡次数     积攒硬币   有效次数  当前概率
//        System.out.println("status:"+Integer.toBinaryString(status)+" times:"+times+" cnt:"+cnt+" sum:"+sum+" l:"+l);
        if (dis[status][times]) return dp[status][times];
        dis[status][times] = true;
        if (sum == n || (cnt >= (n - sum) * k)) {
            dp[status][times] = times;
            return times;
        }
        // 不重复地情况
        for (int i = 1; i <= n; i++) {
            if (((status >> i) & 1) == 0) {
                dp[status][times] += p[i] * dfs(status | (1 << i), times + 1, cnt, sum + 1, l + p[i]);
            }
        }
        // 重复的情况
        if (status > 0) {
            dp[status][times] += l * dfs(status, times + 1, cnt + 1, sum, l);
        }
        return dp[status][times];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        k = input.nextInt();
        for (int i = 1; i <= n; i++) {
            p[i] = input.nextDouble();
        }
        System.out.printf("%.10f",dfs(0, 0, 0, 0, 0));
    }
}
