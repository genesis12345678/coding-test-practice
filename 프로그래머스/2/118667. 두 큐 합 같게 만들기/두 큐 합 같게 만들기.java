import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> qu1 = new LinkedList<>();
        Queue<Integer> qu2 = new LinkedList<>();

        long sum = 0;
        long qu1_sum = 0;
        long qu2_sum = 0;

        for (int i = 0; i < queue1.length; i++) {
            qu1.offer(queue1[i]);
            qu2.offer(queue2[i]);

            qu1_sum += queue1[i];
            qu2_sum += queue2[i];

            sum += queue1[i] + queue2[i];
        }

        if (sum % 2 != 0) {
            return -1;
        }

        long target = sum / 2;

        int result = 0;
        while (qu1_sum != target) {
            if (result > (qu1.size() + qu2.size()) * 2) {
                return -1;
            }
            if (qu1_sum > qu2_sum) {
                qu1_sum -= qu1.peek();
                qu2_sum += qu1.peek();
                qu2.offer(qu1.poll());
            } else {
                qu2_sum -= qu2.peek();
                qu1_sum += qu2.peek();
                qu1.offer(qu2.poll());
            }
            result++;
        }

        return result;
    }
}