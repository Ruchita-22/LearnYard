package subarray;

import java.util.*;

public class HashingWithPrefixSum {

	//560. Subarray Sum Equals K
	public int subarraySum(int[] nums, int k) {
		var map = new HashMap<Integer, Integer>();
		map.put(0,1);
		int sum = 0, count = 0;
		for(int num : nums) {
			sum += num;
			if(map.containsKey(sum - k)) {
				count += map.get(sum-k);
			}
			map.put(sum, map.getOrDefault(sum,0)+1);
		}
		return count;
	}

	//974. Subarray Sums Divisible by K
	public int subarraysDivByK(int[] nums, int k) {
		var map = new HashMap<Integer, Integer>();
		map.put(0,1);
		int sum = 0, count = 0;
		for(int num : nums) {
			sum += num;
			int sumMod = sum % k;
			if(sumMod < 0) sumMod += k;
			if(map.containsKey(sumMod)) {
				count += map.get(sumMod);
			}
			map.put(sumMod, map.getOrDefault(sumMod,0)+1);
		}
		return count;
	}

	//1590. Make Sum Divisible by P
	//https://www.youtube.com/watch?v=5jpCEfRI1sM&t=854s
	public int minSubarray(int[] nums, int p) {
		long sum = 0;
		for(int num : nums) {
			sum += num;
		}
		if(sum % p == 0) return 0;

		int k = (int)(sum % p + p) % p;

		// we need to remove the smallest subarray whose sum = k

		var map = new HashMap<Integer, Integer>();
		map.put(0,-1);
		sum = 0;
		int len = nums.length;
		for(int j = 0; j < nums.length; j++) {
			sum += nums[j];
			int currMod = (int)((sum % p + p) % p);
			int target = (currMod - k + p) % p;
			if(map.containsKey(target)) {
				len = Math.min(len, j - map.get(target) );
			}
			map.put(currMod, j);
		}
		return len == nums.length ? -1 : len;
	}
	
	//523. Continuous Subarray Sum
	public boolean checkSubarraySum(int[] nums, int k) {
		if(nums.length < 2 || k == 0) return false;
		var map = new HashMap<Integer, Integer>();
		map.put(0,-1);
		int sum = 0, len = Integer.MAX_VALUE;
		for(int j = 0; j < nums.length; j++) {
			sum += nums[j];
			int currSumMod = (sum % k + k) % k;
			if(map.containsKey(currSumMod)) {
				len = Math.min(len, j - map.get(currSumMod)+1);
				if(j - map.get(currSumMod) >= 2) return true;
			}
			map.putIfAbsent(currSumMod, j);
		}
		return false;
	}
	
	//2845. Count of Interesting Subarrays
	public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
		int arr[] = new int[nums.size()];

		for(int i = 0; i < nums.size(); i++) {
			arr[i] = nums.get(i) % modulo == k ? 1 : 0;
		}
		return solve(arr, modulo, k);
	}
	public long solve(int[] nums, int modulo, int k) {
		var map = new HashMap<Integer, Integer>();
		map.put(0,1);
		long sum = 0, count = 0;
		for(int num : nums) {
			sum += num;
			int currMod = (int)(sum % modulo);
			int key = (int)((currMod - k) % modulo + modulo) % modulo;
			if(map.containsKey(key)) {
				count += map.get(key);
			}
			map.put(currMod, map.getOrDefault(currMod,0)+1);
		}
		return count;
	}
	
	//1248. Count Number of Nice Subarrays
    public int numberOfSubarrays(int[] nums, int k) {
        int arr[] = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            arr[i] = nums[i] % 2 == 0 ? 0 : 1;
        }
        return solve(arr, k);
    }

    public int solve(int[] nums, int k) {
        var map = new HashMap<Integer, Integer>();
        map.put(0,1);

        int sum = 0, count = 0;
        for(int num : nums) {
            sum += num;
            if(map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        return count;
    }
    
    //1915. Number of Wonderful Substrings
    //https://www.youtube.com/watch?v=1DdmbJj4xLE
    public long wonderfulSubstrings(String word) {
        int currXor = 0;
        long count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        for(char ch : word.toCharArray()) {
            int charIdx = ch-'a';
            currXor ^= 1<<charIdx;
            count += map.getOrDefault(currXor,0);
            for(int i = 0; i < 10; i++) {
                count += map.getOrDefault(currXor ^ (1<<i),0);
            }
            map.put(currXor, map.getOrDefault(currXor, 0)+1);
        }
        return count;
    }
    
    //2260. Minimum Consecutive Cards to Pick Up
    public int minimumCardPickup(int[] nums) {
        var map = new HashMap<Integer, Integer>();
        int i = 0, ans = Integer.MAX_VALUE;

        for(int j = 0 ; j < nums.length; j++) {
            if(map.containsKey(nums[j])) {
                ans = Math.min(ans, j-map.get(nums[j])+1);
            } 
            map.put(nums[j], j); 
        }
        return ans == Integer.MAX_VALUE || ans == nums.length-1 ? -1 : ans;
    }
    
    /////////////////Flip Question//////////////////////////
    //1004. Max Consecutive Ones III
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
    



}
