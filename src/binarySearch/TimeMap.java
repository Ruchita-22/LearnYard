package binarySearch;

import java.util.*;

//981. Time Based Key-Value Store
public class TimeMap {
	class Pair {
	    String value;
	    int timestamp;
	    public Pair(String value, int timestamp) {
	        this.value = value;
	        this.timestamp = timestamp;
	    }
	}
	Map<String, List<Pair>> map;
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key,new ArrayList());
        map.get(key).add(new Pair(value,timestamp));
    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key)) return "";
        Pair p = binarySearch(map.get(key),timestamp);
        return p == null ? "" : p.value;
    }
    private Pair binarySearch(List<Pair> pairs, int timestamp) {
        int s = 0, e = pairs.size()-1;
        Pair ans = null;
        while(s <= e) {
            int m = s + (e-s)/2;
            Pair p = pairs.get(m);
            if(p.timestamp == timestamp) {
                return p;
            } else if(p.timestamp <= timestamp) {
                ans = p;
                s = m + 1;
            } else {
                e = m - 1;
            }
        }
        return ans;
    }

}
