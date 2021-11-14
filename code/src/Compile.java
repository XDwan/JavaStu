public class Compile {
    static String Binary2H(String s){
        if (s.length()%4 !=0){
            return "error";
        }
        int count = 1;
        int num = 0;
        String result = new String("");
        for (int i=s.length()-1;i>=0;i--){
            num += (s.charAt(i)-'0') * count;
            if (count == 16){
                count = 1;
                num = 0;
                result = result+Integer.toHexString(num);
            }
            count*=2;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Binary2H("1001"));

    }
}
