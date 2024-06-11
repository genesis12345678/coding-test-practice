import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            qu.offer(work);
        }

        for (int i = 0; i < n; i++) {
            if (qu.isEmpty() || qu.peek() == 0) {
                return 0;
            }

            qu.offer(qu.poll() - 1);
        }

        long total = 0;
        while (!qu.isEmpty()) {
            Integer time = qu.poll();
            total += (long) time * time;
        }
        return total;
    }
}