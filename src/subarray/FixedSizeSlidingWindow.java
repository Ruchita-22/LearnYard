package subarray;

import java.util.*;

public class FixedSizeSlidingWindow {
    private int format (int[] nums, int k) {
        int i = 0, ans = 0;

        for(int j = 0; j < nums.length; j++) {

            //include nums[j]
            if((j-i+1) == k) {
                // calculation ans
                // remove nums[i]
                i++;
            }
        }
        return ans;
    }

    //2461. Maximum Sum of Distinct Subarrays With Length K
    public long maximumSubarraySum(int[] nums, int k) {
        int i = 0;
        long ans = Integer.MIN_VALUE, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for(int j = 0; j < nums.length; j++) {

            sum += nums[j];
            map.put(nums[j], map.getOrDefault(nums[j], 0)+1);

            if((j-i+1) == k) {
                if(map.size() == k) {
                    ans = Math.max(ans, sum);
                }
                sum -= nums[i];
                map.put(nums[i], map.get(nums[i])-1);
                if(map.get(nums[i]) == 0) map.remove(nums[i]);
                i++;
            }
        }
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }

    //1876. Substrings of Size Three with Distinct Characters
    public int countGoodSubstrings(String s) {
        return solve(s,3);
    }
    public int solve (String s, int k) {
        int i = 0, ans = 0;
        Map<Character, Integer> map = new HashMap<>();

        for(int j = 0; j < s.length(); j++) {
            // add j
            char cj = s.charAt(j);
            map.put(cj, map.getOrDefault(cj,0)+1);

            if((j-i+1) == k) {
                // calculation ans
                if(map.size() == k) ans++;

                // remove nums[i]
                char ci = s.charAt(i);
                map.put(ci, map.get(ci)-1);
                if(map.get(ci) == 0) map.remove(ci);

                i++;
            }
        }
        return ans;
    }

    //567. Permutation in String
    public boolean checkInclusion(String p, String s) {
        if(s.length() < p.length())   return false;

        int fs1[] = new int[26];
        int fs2[] = new int[26];

        for(char c : p.toCharArray()) {
            fs1[c-'a']++;
        }

        int i = 0, k = p.length();

        for(int j = 0; j < s.length(); j++) {
            //add nums[i]
            char cj = s.charAt(j);
            fs2[cj-'a']++;

            if((j-i+1) == k) {
                // calculation ans
                if(Arrays.equals(fs1,fs2))   return true;

                // remove nums[i]
                char ci = s.charAt(i);
                fs2[ci-'a']--;
                i++;
            }
        }
        return false;

    }

    //438. Find All Anagrams in a String
    public List<Integer> findAnagrams(String s, String p) {
        if(s.length() < p.length())   return new ArrayList();

        int fs1[] = new int[26];
        int fs2[] = new int[26];

        for(char c : p.toCharArray()) {
            fs1[c-'a']++;
        }

        int i = 0, k = p.length();
        List<Integer> list=  new ArrayList<>();

        for(int j = 0; j < s.length(); j++) {
            //add nums[i]
            char cj = s.charAt(j);
            fs2[cj-'a']++;

            if((j-i+1) == k) {
                // calculation ans
                if(Arrays.equals(fs1,fs2))   list.add(i);

                // remove nums[i]
                char ci = s.charAt(i);
                fs2[ci-'a']--;
                i++;
            }
        }
        return list;

    }

    //239. Sliding Window Maximum
    public int[] maxSlidingWindow(int[] nums, int k) {

        Deque<Integer> q = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        int i = 0;

        for(int j = 0; j < nums.length; j++) {

            //add nums[i]
            while(q.size() > 0 && q.peekLast() < nums[j]) {
                q.pollLast();
            }
            q.addLast(nums[j]);

            if((j-i+1) == k) {
                // calculation ans
                list.add(q.peekFirst());

                // remove nums[i]
                if(q.peekFirst() == nums[i])    q.pollFirst();
                i++;
            }
        }
        return list.stream().mapToInt(Integer :: intValue).toArray();
    }


}
