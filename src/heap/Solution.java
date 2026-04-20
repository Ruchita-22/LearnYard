package heap;

import java.util.*;

public class Solution {
	
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap();

        for(String word : words) {
            map.put(word, map.getOrDefault(word,0)+1);
        }
        System.out.println(map);

        PriorityQueue<String> pq = new PriorityQueue<>(
            (a, b) -> {
                int freq = Integer.compare(map.get(a), map.get(b)); // min-heap by freq
                if (freq == 0) return b.compareTo(a);               // reverse lexicographic
                return freq;
            }
        );

        for(String word : map.keySet()) {
            pq.add(word);
            if(pq.size() > k) pq.poll();
        }
        System.out.println(pq);
        List<String> res = new ArrayList(pq);
        while(pq.size() > 0) res.add(0,pq.poll());
        
        return res;
        
    }
    //347. Top K Frequent Elements
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();

        for(int num : nums) {
            map.put(num, map.getOrDefault(num,0)+1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a,b) -> {
                int freq = Integer.compare(map.get(a),map.get(b));
                if(freq == 0)   return Integer.compare(a,b);
                return freq;
            }
        );

        for(Integer key : map.keySet()) {
            pq.add(key);
            if(pq.size() > k)   pq.poll();
        }
        return pq.stream().mapToInt(Integer :: intValue).toArray();
    }

}
