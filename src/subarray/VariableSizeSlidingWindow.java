package subarray;

import java.util.*;

public class VariableSizeSlidingWindow {
//	private int format (int[] nums, int k) {
//		int i = 0, ans = 0;
//
//		for(int j = 0; j < nums.length; j++) {
//
//			//include nums[j]
//			while(// window invalid) {
//
//				// remove nums[i]
//				i++;
//			}
//			// calculation ans
//		}
//		return ans;
//	}

	//209. Minimum Size Subarray Sum
	public int minSubArrayLen(int target, int[] nums) {
		int i = 0, sum = 0, ans = Integer.MAX_VALUE;

		for(int j = 0; j < nums.length; j++) {
			sum += nums[j];

			while(sum >= target) {
				ans = Math.min(ans,j-i+1);
				sum -= nums[i];
				i++;
			}

		}
		return ans == Integer.MAX_VALUE ? 0 : ans;
	}

	//3. Longest Substring Without Repeating Characters
	public int lengthOfLongestSubstring(String s) {
		var set = new HashSet<Character>();
		int i = 0, ans = Integer.MIN_VALUE;

		for(int j = 0; j < s.length(); j++) {
			char cj = s.charAt(j);

			while(set.contains(cj)) {
				char ci = s.charAt(i);
				set.remove(ci);
				i++;
			}

			set.add(cj);
			ans = Math.max(ans,set.size());
		}
		return ans == Integer.MIN_VALUE ? 0 : ans;
	}

	private int longestSubarrayWithKUniqueCharacter(String s, int k) {
		int i = 0, ans = 0;
		var map = new HashMap<Character, Integer>();
		for (int j = 0; j < s.length(); j++) {
			char cj = s.charAt(j);
			map.put(cj, map.getOrDefault(cj,0)+1);

			while(map.size() > k) {
				char ci = s.charAt(i);
				map.put(ci, map.get(ci)-1);
				if(map.get(ci) == 0)	map.remove(ci);
				i++;
			}
			ans = Math.max(ans,j-i+1);
		}
		return ans;
	}

	//904. Fruit Into Baskets or toy pick prob
	public int totalFruit(int[] fruits) {
		return longestSubarrayWithKUniqueCharacter(fruits, 2);
	}
	private int longestSubarrayWithKUniqueCharacter(int[] nums, int k) {
		int i = 0, ans = 0;
		var map = new HashMap<Integer, Integer>();
		for (int j = 0; j < nums.length; j++) {

			map.put(nums[j], map.getOrDefault(nums[j],0)+1);

			while(map.size() > k) {
				map.put(nums[i], map.get(nums[i])-1);
				if(map.get(nums[i]) == 0)	map.remove(nums[i]);
				i++;
			}
			ans = Math.max(ans,j-i+1);
		}
		return ans;
	}

	//1297. Maximum Number of Occurrences of a Substring
	public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
		var map = new HashMap<Character, Integer>();
		var ans = new HashMap<String, Integer>();

		int i = 0, count = 0;

		for(int j = 0; j < s.length(); j++) {
			char cj = s.charAt(j);
			map.put(cj, map.getOrDefault(cj,0)+1);

			while(j-i+1 > minSize) {
				char ci = s.charAt(i);
				map.put(ci, map.get(ci)-1);
				if(map.get(ci) == 0) map.remove(ci);
				i++;
			}
			if(j-i+1 == minSize && map.size() <= maxLetters) {
				String sub = s.substring(i, j + 1);
				ans.put(sub, ans.getOrDefault(sub, 0) + 1);
				count = Math.max(count, ans.get(sub));
			}
		}
		return count;
	}

	//76. Minimum Window Substring
	public String minWindow(String s, String p) {
		if(s.length() < p.length())     return "";

		//create freq map of p
		var map = new HashMap<Character,Integer>();
		for(char c : p.toCharArray()) {
			map.put(c, map.getOrDefault(c,0)+1);
		}

		int i = 0;
		int useful = 0, len = Integer.MAX_VALUE, idx = 0;

		for(int j = 0; j <s.length(); j++) {
			char cj = s.charAt(j);

			if(map.containsKey(cj)) {
				map.put(cj, map.get(cj)-1);
				if(map.get(cj) >= 0)    useful++;
			}

			while(useful == p.length()) {
				if(len >  j-i+1) {
					len = j-i+1;
					idx = i;
				}
				// remove effect of i
				char ci = s.charAt(i);
				if(map.containsKey(ci)) {
					map.put(ci, map.get(ci)+1);
					if(map.get(ci) > 0)    useful--;
				}
				i++;
			}
		}
		return len == Integer.MAX_VALUE ? "" : s.substring(idx, idx + len);

	}


	///////////////// category 2 find continuous and replacement/////////////////
	//424. Longest Repeating Character Replacement
	/*
	 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.
	 */
	//https://leetcode.com/problems/longest-repeating-character-replacement/

	//424. Longest Repeating Character Replacement
	public int characterReplacement(String s, int k) {

		int[] arr = new int[26];
		int largestCount = 0, i = 0, j = 0, maxlen = 0;

		while ( j < s.length()){
			char cj = s.charAt(j);
			arr[cj - 'A']++;
			largestCount = Math.max(largestCount, arr[cj - 'A']);

			if((j - i + 1) - largestCount > k){
				char ci = s.charAt(i);
				arr[ci - 'A']--;
				i++;
			}
			maxlen = Math.max(maxlen, j - i + 1);
			j++;
		}
		return maxlen;
	}

	//1004. Max Consecutive Ones III
	// here we can only flip 0
	public int longestOnes(int[] nums, int k) {

		int freq[] = new int[2];
		// freq[0] = freq of 0 and freq[1] = freq of 1

		int i = 0, ans  = 0;
		for(int j = 0; j < nums.length; j++) {
			if(nums[j] == 1)    freq[1]++;
			else    freq[0]++;
			while(freq[0] > k) {
				if(nums[i] == 0)    freq[0]--;
				else    freq[1]--;
				i++;
			}
			ans = Math.max(ans, j-i+1);
		}
		return ans;
	}

	//2024. Maximize the Confusion of an Exam
	//https://www.youtube.com/watch?v=vY06L8hZVGI&t=1536s
	public int maxConsecutiveAnswers1(String str, int k) {
		int res = 0;
		int i = 0, j = 0;
		int freq[] = new int[2];
		//0-T and 1 - F
		while ( j < str.length()) {
			if(str.charAt(j) == 'T') freq[0]++;
			else freq[1]++;
			while(Math.min(freq[0], freq[1]) > k) {
				if(str.charAt(i) == 'T') freq[0]--;
				else freq[1]--;
				i++;

			}
			res = Math.max(res,j-i+1);
			j++;
		}

		return res;
	}

	/////////////////category 3 - operation performed//////////
	//1658. Minimum Operations to Reduce X to Zero
	public int minOperations(int[] nums, int x) {
		// edge case
		long totalSum = 0;
		for(int num : nums) totalSum += num;
		if(x > totalSum)   return -1;

		long targetSum = totalSum - x;
		System.out.println("targetSum = "+ targetSum);
		if(targetSum == 0)  return nums.length;

		int ws = solve(nums, targetSum);
		return ws == 0 ? -1 : nums.length-ws;
	}
	private int solve(int nums[], long target) {
		int i = 0, ans = 0;
		long sum = 0;
		for(int j = 0; j < nums.length; j++) {
			sum += nums[j];

			while(sum > target) {
				sum -= nums[i];
				i++;
			}

			if(sum == target) ans = Math.max(ans, j-i+1);
		}
		return ans;
	}

	//2537. Count the Number of Good Subarrays
	public long countGood(int[] nums, int k) {
		var map = new HashMap<Integer, Integer>();
		int i = 0;
		long pairs = 0, ans = 0;

		for (int j = 0; j < nums.length; j++) {
			pairs += map.getOrDefault(nums[j], 0);
			map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

			while (pairs >= k) {
				map.put(nums[i], map.get(nums[i]) - 1);
				pairs -= map.get(nums[i]);
				ans += nums.length - j;
				i++;
			}
		}
		return ans;
	}
	
}
