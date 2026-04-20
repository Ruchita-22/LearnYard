package binarySearch;

//528. Random Pick with Weight
public class RandomPick {
	int pf[];

    public RandomPick(int[] w) {
        pf = new int[w.length];
        pf[0] = w[0];
        for(int i = 1; i < w.length; i++) {
            pf[i] = pf[i-1] + w[i];
        }
    }
    
    public int pickIndex() {
        int sum = pf[pf.length-1];
        int r = (int)(Math.random() * sum) + 1;
        return binarySearch(pf,r);
    }
    private int binarySearch(int nums[], int target) {
        int s = 0, e  = nums.length-1;
        int ans = -1;
        while(s <= e) {
            int m = s + (e-s)/2;

            if(nums[m] == target) {
                return m;
            } else if(nums[m] < target) {
                s = m+1;
            } else {
                ans = m;
                e = m - 1;
            }
        }
        return ans;
    }

}
