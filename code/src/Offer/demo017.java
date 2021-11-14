package Offer;

import java.util.HashMap;

public class demo017 {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> charToCount = new HashMap<>();
        for (char ch : t.toCharArray()){
            charToCount.put(ch,charToCount.getOrDefault(ch,0)+1);
        }
        int count = charToCount.size();
        int start =0, end=0, minStart = 0, minEnd = 0;
        int minLength = Integer.MIN_VALUE;
        while (end <s.length() || (count == 0 && end == s.length())){
            if (count > 0 ){
                char endCh = s.charAt(end);
                if (charToCount.containsKey(endCh)){
                    charToCount.put(endCh,charToCount.get(endCh)-1);
                    if (charToCount.get(endCh)==0){
                        count--;
                    }
                }
            }
        }
        return s;
    }
}
