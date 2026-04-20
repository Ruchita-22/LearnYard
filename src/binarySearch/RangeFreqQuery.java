package binarySearch;

import java.util.*;

//2080. Range Frequency Queries
public class RangeFreqQuery {
	Map<Integer, List<Integer>> map;

    public RangeFreqQuery(int[] arr) {
        map = new HashMap();
        for(int i = 0; i <arr.length; i++) {
            map.putIfAbsent(arr[i],new ArrayList());
            map.get(arr[i]).add(i);
        }
        System.out.println(map);
        
    }
    
    public int query(int left, int right, int value) {
        if(!map.containsKey(value)) return 0;
        List<Integer> list = map.get(value);
        int s = binarySearchLowerBound(list,left);
        int e = binarySearchUpperBound(list, right);
        if (s == list.size() || e < 0 || s > e) return 0;
        return (e-s+1);
    }
    private int binarySearchLowerBound(List<Integer> list,int left) {
        int s = 0, e = list.size()-1;
        int ans = list.size();
        while(s <= e) {
            int m = s + (e-s)/2;

            if(list.get(m) == left) {
                ans = m;
                e = m-1;
            } else if (list.get(m) < left) {
                s = m+1;
            } else {
                ans = m;
                e = m-1;
            }
        }
        return ans;

    }
    private int binarySearchUpperBound(List<Integer> list,int right) {
        int s = 0, e = list.size()-1;
        int ans = -1;
        while(s <= e) {
            int m = s + (e-s)/2;

            if(list.get(m) == right) {
                ans = m;
                s = m+1;
            } else if (list.get(m) < right) {
                ans = m;
                s = m+1;
            } else {
                e = m-1;
            }
        }
        return ans;

    }
}
