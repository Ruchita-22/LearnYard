package binarySearch;

public class BinarySearchOnAnswer {
	/*
	 * decide answer space correctly
	 * try to take long
	 * if any not found what to return 
	 * if need to take mode before returning ans 
	 * */
	
	// 69. Sqrt(x)
	public int mySqrt(int x) {
		// create Answer space
		int minSqrt = 1, maxSqrt = x;

		// Binary Search on Answer
		int s = minSqrt, e = maxSqrt;
		int ans = 0;
		while (s <= e) {
			int m = s + (e - s) / 2;
			if (m * 1L * m <= x) {
				ans = m;
				s = m + 1;
			} else {
				e = m - 1;
			}
		}
		return ans;
	}
	
	//2226. Maximum Candies Allocated to K Children
	//https://www.youtube.com/watch?v=9UxjDEBt8Vg
	public int maximumCandies(int[] candies, long k) {
        
        long minCandies = 1, maxCandies = 0, sum = 0;
        for(int candie : candies) {
            maxCandies = Math.max(maxCandies, candie);
            sum += candie;
        }
        if(sum < k)  return 0;

        long s = minCandies, e = maxCandies;
        long ans = 0;
        while(s <= e) {
            long m = s + (e-s)/2;
            if(isPossible(candies, m, k)) {
                ans = m;
                s = m+1;
            } else {
                e = m-1;
            }
        }
        return (int)ans;
    }
    private boolean isPossible(int nums[], long m, long k) {
        long totalChildren = 0;
        for(int num : nums){
           totalChildren += num/m;
        }
        return totalChildren >= k ? true : false;
    }
    
    //1870. Minimum Speed to Arrive on Time
    //https://www.youtube.com/watch?v=6VHDJMYtn3Q
    public int minSpeedOnTime(int[] dist, double hour) {
        if (hour <= dist.length - 1) return -1;
        //d= s*t;
        //s= d/t;
        int minSpeed = 1, maxSpeed = 10000000;

        int s = minSpeed, e = maxSpeed;
        int ans = 0;
        while(s <= e) {
            int m = s+(e-s)/2;
            
            if(isPossible(dist, m, hour )) {
                ans = m;
                e = m-1;
            } else {
                s = m+1;
            }
        }
        return ans;
    }
    private boolean isPossible(int[] dist, int m, double hour) {
        double totalTime = 0;
        for(int i = 0; i < dist.length-1; i++) {
            totalTime += Math.ceil((double)dist[i]/m);
        }
        totalTime += ((double)dist[dist.length-1]/m);
        return totalTime <= hour ? true : false;
    }
    
    //875. Koko Eating Bananas
    //https://www.youtube.com/watch?v=QQcEIxK-snE
    public int minEatingSpeed(int[] piles, int h) {
        
        long minBanana = 1, maxBanana = 0;
        for(int pile : piles) {
            maxBanana += pile;
        }
        
        long s =  minBanana, e = maxBanana;
        long ans = 0;
        while(s <= e) {
            long m = s + (e-s)/2;
            if(isPossible(piles, m, h)) {
                ans = m;
                e = m-1;
            } else {
                s = m+1;
            }
        }
        
        return (int)ans;
    }
    private boolean isPossible(int[] piles, long m, int h) {
        long totalHour = 0;
        for(int pile : piles) {
            totalHour += Math.ceil((double)pile/m);
        }
        return totalHour <= h ? true : false;
    }
    
    //2594. Minimum Time to Repair Cars
    //https://www.youtube.com/watch?v=tOo5RX2pt0g&list=PLpIkg8OmuX-LkgtrEF7eyyYWJM3m5tVQY&index=40
    public long repairCars(int[] ranks, int cars) {
        long minTime = 1, maxTime = Integer.MAX_VALUE;
        int maxRank = 0;
        for(int rank : ranks) {
            maxRank = Math.max(maxRank,rank);
        }
        maxTime = maxRank *1L * cars * cars;

        long s =  minTime, e = maxTime;
        long ans = 0;
        while(s <= e) {
            long m = s + (e-s)/2;
            if(isPossible1(ranks, m, cars)) {
                ans = m;
                e = m-1;
            } else {
                s = m+1;
            }
        }
        
        return ans;
    }

    // time = r * n*n
    // time/r = n^2
    private boolean isPossible1(int[] nums, long m, int k) {
        long totalCar = 0;
        for(int num : nums) {
            totalCar += Math.sqrt((double)m/num );
        }
        return totalCar >= k ? true : false;
    }
    
}
