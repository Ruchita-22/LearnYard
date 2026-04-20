package seperate;

import java.util.*;

public class MergeInterval {
    //56. Merge Intervals
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) return new int[0][0];

        // sort
        Arrays.sort(intervals, (o1,o2)-> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        ArrayList<int[]> res = new ArrayList();
        res.add(intervals[0]);

        for(int i = 1; i < intervals.length; i++) {

            int[] oldInterval = res.get(res.size()-1);
            if(oldInterval[1] < intervals[i][0]) {
                res.add(intervals[i]);
            } else if(oldInterval[1] >= intervals[i][0]) {
                oldInterval[0] = Math.min(oldInterval[0], intervals[i][0]);
                oldInterval[1] = Math.max(oldInterval[1], intervals[i][1]);
            }

        }
        return res.toArray(new int[res.size()][]);
    }
    //57. Insert Interval
    public int[][] insert(int[][] intervals, int[] newInterval) {

        if(intervals.length == 0)    return new int[][]{newInterval};

        ArrayList<int[]> list = new ArrayList();
        int i = 0;

        for(i = 0; i < intervals.length; i++) {

            if(intervals[i][1] <  newInterval[0]) {
                list.add(intervals[i]);
            } else if(intervals[i][0] > newInterval[1] ) {
                break;
            } else if(intervals[i][1] >=  newInterval[0]) {
                newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);

            }
        }
        list.add(newInterval);
        while(i < intervals.length) {
            list.add(intervals[i++]);
        }
        return list.toArray(new int[list.size()][]);
    }
}
