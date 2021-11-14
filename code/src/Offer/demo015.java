package Offer;

import java.util.LinkedList;
import java.util.List;

public class demo015 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> indices = new LinkedList<>();
        if (s.length()<p.length()){
            return indices;
        }
        int [] counts = new int[26];
        int i=0;
        for (;i<p.length();i++){
            counts[p.charAt(i)-'a'] ++;
            counts[s.charAt(i)-'a'] --;
        }
        if (areAllZero(counts)){
            indices.add(0);
        }
        for (;i<s.length();i++){
            counts[s.charAt(i)-'a'] --;
            counts[s.charAt(i-p.length())-'a'] ++;
            if (areAllZero(counts)){
                indices.add(i-p.length() + 1);
            }
        }
        return indices;
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
