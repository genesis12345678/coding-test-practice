import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
         PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (String operation : operations) {
            char op = operation.charAt(0);

            if (op == 'I') {
                int num = Integer.parseInt(operation.substring(2));
                minHeap.add(num);
                maxHeap.add(num);
            }
            else if (op == 'D') {
                String sub = operation.substring(2);

                if (sub.equals("1")) {
                    if (!maxHeap.isEmpty()) {
                        minHeap.remove(maxHeap.poll());
                    }

                } else if (sub.equals("-1")) {
                    if (!minHeap.isEmpty()) {
                        maxHeap.remove(minHeap.poll());
                    }
                }
            }

        }
        if (minHeap.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{maxHeap.poll(), minHeap.poll()};
        }
    }
}