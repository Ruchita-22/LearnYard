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
    //2337. Move Pieces to Obtain a String
    //https://www.youtube.com/watch?v=OlfIyTpCzvM
    public boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0, j = 0;
        while(i < n || j < n) {
            while(i < n && start.charAt(i) == '_') i++;
            while(j < n && target.charAt(j) == '_') j++;
            if(i == n || j == n){
                return i == n && j == n;
            } else if(start.charAt(i) != target.charAt(j)) return false;
            else if (start.charAt(i) =='L' &&  i < j)  return false;
            else if (start.charAt(i) == 'R' && i > j)  return false;
            i++; j++;

        }
        return true;
    }
    //1813. Sentence Similarity III
    //https://www.youtube.com/watch?v=J9KwcuukMZE&t=748s
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String s1[] = sentence1.split(" ");
        String s2[] = sentence2.split(" ");


        if(s1.length < s2.length) {
            String s[] = s1;
            s1 = s2;
            s2 = s;
        }
        int i = 0, j = s1.length-1, k = 0, l = s2.length-1;
        while(k <= l) {
            if(s2[k].equals(s1[i])) {
                i++; k++;
            } else if(s2[l].equals(s1[j])) {
                j--; l--;
            } else {
                return false;
            }
        }

        return true;

    }
    void print(String s[]) {
        for(String st : s) {
            System.out.print(st+" ");
        }
        System.out.println();
    }

}
