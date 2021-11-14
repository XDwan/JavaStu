package tools;

import java.util.Scanner;

public class Tool1 {

    static String Binary2Hex(String m){
        Integer num = Integer.valueOf(m,2);
        return Integer.toHexString(num);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true){
            StringBuilder M = new StringBuilder();
            for (int i=1;i<=24;i++){
                M.append(input.nextInt());
            }
            System.out.println(Binary2Hex(M.toString()));
        }
    }
}
