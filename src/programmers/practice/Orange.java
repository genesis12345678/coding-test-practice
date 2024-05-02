package programmers.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Orange {
    public static void main(String[] args) {
        int k = 6;
        int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};

        System.out.println(solution(k, tangerine));
    }

    static int solution(int k, int[] tangerine) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : tangerine) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Integer> numbers = new ArrayList<>(map.values());
        numbers.sort(Collections.reverseOrder());

        int sum = 0;
        int count = 0;

        for (int num : numbers) {
            if (sum + num >= k) {
                count++;
                break;
            } else {
                sum += num;
                count++;
            }
        }
        return count;
    }
}
