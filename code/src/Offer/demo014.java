package Offer;

public class demo014 {
    public boolean checkInclusion(String s1, String s2) {
       if (s2.length() <s1.length()){
           return false;
       }
       int [] counts = new  int[26];
       for (int i=0;i<s1.length();i++){
           counts[s1.charAt(i)-'a'] ++;
           counts[s2.charAt(i)-'a'] --;
       }
       if (areAllZero(counts)){
           return true;
       }
       for (int i= s1.length();i<s2.length();i++){
           counts[s2.charAt(i)-'a'] --;
           counts[s2.charAt(i-s1.length())-'a'] ++;
           if (areAllZero(counts)){
               return true;
           }
       }
       return false;
    }

    private boolean areAllZero(int[] counts) {
        for (int count : counts){
            if (count != 0 ){
                return false;
            }
        }
        return true;
    }
}
