package binarySearch;

import java.util.*;

public class Solution {
	// 704. Binary Search
	public int search(int[] nums, int target) {
		int s = 0, e = nums.length - 1;

		while (s <= e) {
			int m = s + (e - s) / 2;
			if (nums[m] == target) {
				return m;
			} else if (nums[m] < target) {
				s = m + 1;
			} else {
				e = m - 1;
			}
		}
		return -1;
	}

	// 34. Find First and Last Position of Element in Sorted Array
	public int[] searchRange(int[] nums, int target) {
		int s = 0, e = nums.length - 1;
		// first find first
		int first = -1;
		while (s <= e) {
			int m = s + (e - s) / 2;
			if (nums[m] == target) {
				first = m;
				e = m - 1;
			} else if (nums[m] < target) {
				s = m + 1;
			} else {
				e = m - 1;
			}
		}

		// now find last
		int last = -1;
		s = 0;
		e = nums.length - 1;
		while (s <= e) {
			int m = s + (e - s) / 2;
			if (nums[m] == target) {
				last = m;
				s = m + 1;
			} else if (nums[m] < target) {
				s = m + 1;
			} else {
				e = m - 1;
			}
		}
		return new int[] { first, last };
	}

	// 744. Find Smallest Letter Greater Than Target
	public char nextGreatestLetter(char[] letters, char target) {
		int s = 0, e = letters.length - 1;
		char ans = letters[0];
		while (s <= e) {
			int m = s + (e - s) / 2;
			if (letters[m] == target) {
				s = m + 1;
			} else if (letters[m] < target) {
				s = m + 1;
			} else {
				ans = letters[m];
				e = m - 1;
			}
		}
		return ans;
	}
	
	//2389. Longest Subsequence With Limited Sum
	public int[] answerQueries(int[] nums, int[] queries) {
        
        Arrays.sort(nums);

		// create prefix sum
        int pf[] = new int[nums.length];
        pf[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            pf[i] = pf[i-1] + nums[i];
        }  

        int res[] = new int[queries.length];
        Arrays.fill(res, -1);


        for(int i = 0; i < queries.length; i++) {
            int target = queries[i];
			// Binaray search
            int s = 0, e = pf.length - 1;

            while (s <= e) {
                int m = s + (e - s) / 2;
                if (pf[m] == target) {
                    res[i] = m;
                    break;
                } else if (pf[m] < target) {
                    res[i] = m;
                    s = m + 1;
                } else {
                    e = m - 1;
                }
            }
        }
        // to make it 1 based 
        for(int i = 0; i < queries.length; i++) {
            res[i] = res[i]+1;
        }
        return res;
        
    }
	
	////////////////////Semi Sorted Array/////////////////////////
	//162. Find Peak Element
	//852. Peak Index in a Mountain Array
	//these are local peek or any peek
	public int findPeakElement(int[] nums) {
        int n = nums.length;

        if(nums.length == 1)    return 0;
        if(nums[0] > nums[1])   return 0;
        if(nums[n-1]> nums[n-2])    return n-1;

        int s = 1, e = nums.length - 2;

		while (s <= e) {
			int m = s + (e - s) / 2;
			if (nums[m] > nums[m-1] && nums[m] > nums[m+1]) {
				return m;
			} else if (nums[m] > nums[m-1] && nums[m] < nums[m+1]) {
				s = m + 1;
			} else {
				e = m - 1;
			}
		}
		return -1;
    }
	
	//153. Find Minimum in Rotated Sorted Array
	public int findMin(int[] nums) {
        int pov = findPointOfRotation(nums);
        return nums[pov];
    }
    public int findPointOfRotation(int[] nums) {
        int s = 0, e = nums.length - 1;

        // If array not rotated
        if (nums[s] <= nums[e]) return 0;

        while (s < e) {
            int m = s + (e - s) / 2;

            if (nums[m] > nums[e]) {
                s = m + 1;      // pivot in right half
            } else {
                e = m;          // pivot in left half incl mid
            }
        }
        return s;   // index of minimum
    }
    
    //33. Search in Rotated Sorted Array
    // distinct element
    public int search1(int[] nums, int target) {
        int m = findPointOfRotation(nums);
        if (m == 0)  // not rotated
            return binarySearch(nums, target, 0, nums.length - 1);
        if(target >= nums[0]) {
            return binarySearch(nums, target, 0, m-1);
        }
        else {
            return binarySearch(nums, target, m, nums.length-1);
        }
    }
    public int binarySearch(int[] nums, int target, int l, int r) {
		int s = l, e = r;

		while (s <= e) {
			int m = s + (e - s) / 2;
			if (nums[m] == target) {
				return m;
			} else if (nums[m] < target) {
				s = m + 1;
			} else {
				e = m - 1;
			}
		}
		return -1;
	}
    
    //81. Search in Rotated Sorted Array II
    //duplicate element
    //This almost work only 2 test cases are not passed
    public boolean search2(int[] nums, int target) {
        int m = findPointOfRotation(nums);
        if (m == 0)  // not rotated
            return binarySearch2(nums, target, 0, nums.length - 1);
        if(target >= nums[0]) {
            return binarySearch2(nums, target, 0, m-1);
        }
        else {
            return binarySearch2(nums, target, m, nums.length-1);
        }
    }

    public boolean binarySearch2(int[] nums, int target, int l, int r) {
		int s = l, e = r;

		while (s <= e) {
			int m = s + (e - s) / 2;
			if (nums[m] == target) {
				return true;
			} else if (nums[m] < target) {
				s = m + 1;
			} else {
				e = m - 1;
			}
		}
		return false;
	}
    // this is another approach and work for all test cases
    public boolean search3(int[] nums, int target) {
        int s = 0, e = nums.length - 1;

        while (s <= e) {
            int m = s + (e - s) / 2;

            if (nums[m] == target) return true;

            // duplicates → shrink
            if (nums[s] == nums[m] && nums[m] == nums[e]) {
                s++; e--;
            }
            // left sorted
            else if (nums[s] <= nums[m]) {
                if (nums[s] <= target && target < nums[m])
                    e = m - 1;
                else
                    s = m + 1;
            }
            // right sorted
            else {
                if (nums[m] < target && target <= nums[e])
                    s = m + 1;
                else
                    e = m - 1;
            }
        }
        return false;
    }
}
