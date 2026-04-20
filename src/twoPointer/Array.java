package twoPointer;

import java.util.*;

public class Array {

	public void rotate(int[] nums, int k) {
        if(nums.length < 2) return;
        
        rotateInRange(nums, 0, nums.length-1);
        
        if(nums.length < k) k = k % nums.length;
        rotateInRange(nums, 0, k-1);
        rotateInRange(nums, k, nums.length-1);
    }
    private void rotateInRange(int nums[], int i, int j) {
        while(i < j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++; j--;
        }
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        //print(nums);
        List<List<Integer>> res = new ArrayList();
        Set<List<Integer>> set = new HashSet();


        for(int i = 0; i < nums.length-2; i++) {

            List<List<Integer>> resFor2Sum = solve(nums,-nums[i],i+1,nums.length-1);
            if(resFor2Sum.size() > 0) {
                for(List<Integer> temp : resFor2Sum) {
                    temp.add(0, nums[i]);
                    if(!set.contains(temp)) {
                        res.add(new ArrayList(temp));
                        set.add(new ArrayList(temp));
                    }
                }
            }
        }

        return res;

    }
    private List<List<Integer>> solve(int nums[],int target, int s, int e) {
        List<List<Integer>> res = new ArrayList();
        int i = s, j = e;
        while(i < j) {
            if(nums[i]+nums[j] < target) i++;
            else if(nums[i]+nums[j] > target) j--;
            else {
                res.add(new ArrayList(List.of(nums[i],nums[j])));
                i++; j--;
            }
        }
        return res;
    }

}
