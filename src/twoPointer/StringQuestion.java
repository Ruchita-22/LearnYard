package twoPointer;

import java.util.*;

public class StringQuestion {
	
	public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length()-1;
        while(i >= 0) {
            // skip spaces
            while (i >= 0 && s.charAt(i) == ' ') i--;

            if (i < 0) break;

            int j = i;

            // move to start of word
            while (i >= 0 && s.charAt(i) != ' ') i--;

            sb.append(s.substring(i + 1, j + 1)).append(" ");

        }
        return sb.toString().trim();
        
    }
    
	private String reverseWordsUsingStack(String s) {
        s = s.trim();
        System.out.println(s);
        if(s.length() == 0) return "";

        String[] strs = s.split(" ");
        Stack<String> stack = new Stack();
        for(int i = 0 ; i < strs.length; i++) {
            if(strs[i].length() > 0) stack.push(strs[i]);
        }
        String res = "";
        while(stack.size() > 1)
            res = res + stack.pop() + " ";
        return res + stack.pop();
    }
    
	//2938. Separate Black and White Balls
    public long minimumSteps(String s) {
        char ch[] = s.toCharArray();
        long swap = 0;
        int i = 0, j =  s.length()-1;

        while(i < j) {
            if(ch[i] == '0') i++;
            else if(ch[j] == '1') j--;
            else {
                swap += (j-i);
                i++; j--;
            }
        }
         return swap; 
    }

}
