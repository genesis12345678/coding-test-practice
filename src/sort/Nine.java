package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * <a href = "https://www.acmicpc.net/problem/11652">백준 11652번 - 정렬 : 카드</a>
 * @since 2024-05-08
 */
public class Nine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        HashMap<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());

            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int max = 0;

        for (Integer value : map.values()) {
            if (max < value) {
                max = value;
            }
        }

        ArrayList<Long> list = new ArrayList<>();
        for (long num : map.keySet()) {
            if (map.get(num) == max) {
                list.add(num);
            }
        }

        Collections.sort(list);

        System.out.print(list.get(0));
    }
}
