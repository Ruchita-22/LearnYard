package greedy;

import java.util.*;

public class Solution {

    public int maximum69Number (int num) {
        // convert number to string
        String s = Integer.toString(num);
        // as we can modify StringBuilder
        StringBuilder sb = new StringBuilder(s);

        int i = 0;
        while(i < sb.length()) {
            if(sb.charAt(i) == '6') {
                sb.setCharAt(i,'9');
                return Integer.valueOf(sb.toString());
            }
            i++;
        }
        return num;
    }

    //2279. Maximum Bags With Full Capacity of Rocks
    //https://www.youtube.com/watch?v=JpQaui_-a4c&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU&index=10
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {

        int diff[] = new int[rocks.length];
        for (int i = 0; i < rocks.length; i++) {
            diff[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(diff);

        int count = 0;

        for (int i = 0; i < diff.length; i++) {
            if(additionalRocks >= diff[i]) {
                additionalRocks -= diff[i];
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    //948. Bag of Tokens
    //https://www.youtube.com/watch?v=LCx1WzlYgvw&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU&index=2
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int i = 0, j = tokens.length-1;
        int score = 0, maxScore = 0;
        while(i <= j) {
            if(power >= tokens[i]) {
                power -= tokens[i++];
                score++;
                maxScore = Math.max(maxScore,score);
            } else if(score >= 1) {
                power += tokens[j--];
                score--;
            } else {
                return maxScore;
            }
        }
        return maxScore;
    }

    //881. Boats to Save People
    //https://www.youtube.com/watch?v=UsQzOL6r0HY&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU&index=3
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int i = 0, j = people.length-1;
        int count = 0;
        while(i <= j) {
            if(people[i] + people[j] <= limit) {
                count++;
                i++; j--;
            } else {
                count++; j--;
            }
        }
        return count;
    }

    //1328. Break a Palindrome
    /* odd leingth palindrome = aabaa = aabab
    even length palindrome = aabbaa == aaabaa
    single character palindrome aaaa = aaab
    single character a = ""
    * */
    //https://www.youtube.com/watch?v=Pbx0Pvyh7D4&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU&index=4
    public String breakPalindrome(String palindrome) {
        if(palindrome.length() == 1)    return "";
        char ch[] = palindrome.toCharArray();

        for (int i = 0; i < ch.length/2; i++) {
            if(ch[i] != 'a') {
                ch[i] = 'a';
                return new String(ch);
            }
        }
        ch[ch.length-1] = 'b';
        return new String(ch);
    }

    //991. Broken Calculator
    //https://www.youtube.com/watch?v=svM2wbyMT4g&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU&index=4
    public int brokenCalc(int startValue, int target) {
        int op = 0;
        while(target != startValue) {
            if(target > startValue && (target % 2 == 0)) {
                op++;
                target = target/2;
            } else {
                op++;
                target = target + 1;
            }
        }
        return op;
    }

    //1578. Minimum Time to Make Rope Colorful
    //https://www.youtube.com/watch?v=_xNrzKfORNA&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU&index=6
    public int minCost(String colors, int[] neededTime) {
        int i = 0, j = 1;
        int totalTime = 0;
        while(j < neededTime.length) {
            if(colors.charAt(i) == colors.charAt(j)) {
                if(neededTime[i] <= neededTime[j]) {
                    totalTime += neededTime[i];
                    i = j;
                    j++;
                } else {
                    totalTime += neededTime[j];
                    j++;
                }
            } else {
                i = j;
                j++;
            }

        }
        return totalTime;
    }

    //1827. Minimum Operations to Make the Array Increasing
    public int minOperations(int[] nums) {
        int j = 1, prev = nums[0], op = 0;
        while(j < nums.length) {
            if(prev >= nums[j]) {
                prev++;
                op += (prev - nums[j]);
            } else {
                prev = nums[j];
            }
            j++;
        }
        return op;
    }

    //2870. Minimum Number of Operations to Make Array Empty
    //https://www.youtube.com/watch?v=lkPNh2M1lUs&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU&index=24
    public int minOperations1(int[] nums) {
        var map = new HashMap<Integer, Integer>();

        for(int num : nums) {
            map.put(num, map.getOrDefault(num,0)+1);
        }
        System.out.println(map);
        int op = 0;
        for( int key : map.keySet()) {
            int freq = map.get(key);
            if(freq == 1) return -1;
            op += Math.ceil((double)freq/3);
        }
        return op;
    }

    //2654. Minimum Number of Operations to Make All Array Elements Equal to 1
    //https://www.youtube.com/watch?v=6VuCJCcpcZI&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU&index=50
    public int minOperations2(int[] nums) {
        int count1 = 0;
        for(int num : nums) {
            if(num == 1)    count1++;
        }
        if(count1 > 0)  return nums.length - count1;

        int minOp = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length-1; i++) {
            int currentGcd = nums[i];
            for (int j = i+1; j < nums.length ; j++) {
                currentGcd = gcd(currentGcd, nums[j]);
                if(currentGcd == 1) {
                    minOp = Math.min(minOp, j - i);
                    break;
                }
            }
        }
        return minOp == Integer.MAX_VALUE ? -1 : minOp + nums.length-1;

    }
    private int gcd(int a, int b) {
        if(b == 0 ) return a;

        return gcd(b,a%b);
    }

    //2244. Minimum Rounds to Complete All Tasks
    // same as 2870. Minimum Number of Operations to Make Array Empty
    //https://www.youtube.com/watch?v=IIalYnjpAJA&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU&index=10
    public int minimumRounds(int[] tasks) {
        var map = new HashMap<Integer, Integer>();

        for(int num : tasks) {
            map.put(num, map.getOrDefault(num,0)+1);
        }
        System.out.println(map);
        int op = 0;
        for( int key : map.keySet()) {
            int freq = map.get(key);
            if(freq == 1) return -1;
            op += Math.ceil((double)freq/3);
        }
        return op;
    }

    //1296. Divide Array in Sets of K Consecutive Numbers
    //846. Hand of Straights
    //https://www.youtube.com/watch?v=CnMwFyoD0Bk
    public boolean isPossibleDivide(int[] nums, int k) {
        var map = new TreeMap<Integer, Integer>();

        for(int num : nums) {
            map.put(num, map.getOrDefault(num,0)+1);
        }

        System.out.println(map);
        while(!map.isEmpty()) {

            int first = map.firstKey();
            // try to create consecutive group
            for(int i = first; i < first + k; i++) {

                if(!map.containsKey(i)) {
                    return false;
                }

                map.put(i, map.get(i) - 1);
                if(map.get(i) == 0) {
                    map.remove(i);
                }
            }
        }
        return true;
    }

    //763. Partition Labels
    //https://www.youtube.com/watch?v=c11UizbJYZU
    public List<Integer> partitionLabels(String s) {
        var map = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            char ci = s.charAt(i);
            map.put(ci,i);
        }
        System.out.println(map);

        List<Integer> list = new ArrayList<>();
        int i = 0;
        while(i < s.length()) {
            int e = map.get(s.charAt(i));
            for (int j = i+1; j < e; j++) {
                if(map.get(s.charAt(j)) > e) {
                    e = map.get(s.charAt(j));
                }
            }
            list.add(e-i+1);
            i = e+1;
        }
        return list;
    }

    //2405. Optimal Partition of String
    //https://www.youtube.com/watch?v=QonXo5Em5VA
    public int partitionString(String s) {
        var map = new HashMap<Character, Integer>();

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ci = s.charAt(i);

            if(map.containsKey(ci)) {
                count++;
                map.clear();
                map.put(ci, i);
            } else {
                map.put(ci, i);
            }
        }
        return map.size() > 0 ? count+1 : count;
    }

    //1400. Construct K Palindrome Strings
    //https://www.youtube.com/watch?v=Hh14hvVIHJc
    public boolean canConstruct(String s, int k) {
        if (s.length() < k) return false;
        if(s.length() == k) return true;

        var map = new HashMap<Character, Integer>();

        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c,0)+1);
        }

        int count = 0;

        for(int val : map.values()) {
            if(val % 2 == 1) count++;
        }

        return count > k ? false : true;
    }
    //767. Reorganize String
    //https://www.youtube.com/watch?v=E3lJDtIHwg4
    public String reorganizeString(String s) {
        var map = new HashMap<Character, Integer>();
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c,0)+1);
            if(map.get(c) > Math.ceil(s.length()*1.0/2)) {
                return "";
            }
        }

        var pq = new PriorityQueue<Character>((o1,o2) -> map.get(o2) - map.get(o1));
        for(char c : map.keySet()) {
            pq.add(c);
        }

        StringBuilder sb = new StringBuilder();

        while(pq.size() >= 2) {

            Character key1 = pq.poll();
            Character key2 = pq.poll();
            sb.append(key1);
            sb.append(key2);

            map.put(key1, map.get(key1)-1);
            map.put(key2, map.get(key2)-1);


            if(map.get(key1) == 0)   map.remove(key1);
            else pq.add(key1);
            if(map.get(key2) == 0)   map.remove(key2);
            else pq.add(key2);
        }
        if(pq.size() > 0) sb.append(pq.poll());
        return sb.toString();
    }
    //1433. Check If a String Can Break Another String
    //https://www.youtube.com/watch?v=tCIv14GDv10
    public boolean checkIfCanBreak(String s1, String s2) {
        char c1[] = s1.toCharArray();
        char c2[] = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);

        boolean flagA = true, flagB = true;

        for (int i = 0; i < c1.length; i++) {
            if(c1[i] < c2[i]) {
                flagA = false;
            }
            if(c2[i] < c1[i]) {
                flagB = false;
            }
        }
        return (flagA || flagB);
    }

    //316. Remove Duplicate Letters
    //1081. Smallest Subsequence of Distinct Characters
    //https://www.youtube.com/watch?v=rU5p0MRm5zU
    public String removeDuplicateLetters(String s) {
        int freq[] = new int[26];
        int taken[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ci = s.charAt(i);
            freq[ci-'a'] = i;
        }


        var stack = new Stack<Character>();
        for (int i = 0; i < s.length() ; i++) {
            char ci = s.charAt(i);
            if(taken[ci-'a'] == 1) continue;

            while(stack.size() > 0 && stack.peek() > ci && freq[stack.peek()-'a'] > i){
                taken[stack.peek()-'a'] = 0;
                stack.pop();
            }
            stack.push(ci);
            taken[ci-'a'] = 1;
        }
        StringBuilder sb = new StringBuilder();
        while(stack.size() > 0) {
            sb.insert(0,stack.pop());
        }
        return  sb.toString();
    }

}
