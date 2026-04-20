package subarray;

import java.util.*;

public class VariableSizeSlidingWindow {

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

	//424. Longest Repeating Character Replacement
	public int characterReplacement(String s, int k) {
		int i = 0, largestCount = 0, ans = 0;
		int freq[] = new int[26];

		for(int j = 0; j < s.length(); j++) {
			char cj = s.charAt(j);
			freq[cj-'A']++;
			largestCount = Math.max(largestCount, freq[cj-'A']);

			while((j-i+1)-largestCount > k) {
				char ci = s.charAt(i);
				freq[ci-'A']--;
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
	
	
}
