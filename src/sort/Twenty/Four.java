package sort.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1374">백준 1374번 - 정렬 : 강의실</a>
 * <br>
 * <a href = "https://www.acmicpc.net/problem/19598">백준 19598번 - 정렬 : 최소 회의실 개수</a>
 * @since 2024-05-26
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
//            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[i][0] = start;
            arr[i][1] = end;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> qu = new PriorityQueue<>();

        int max = 0;

        for (int i = 0; i < n; i++) {

            while (!qu.isEmpty() && qu.peek() <= arr[i][0]) {
                qu.poll();
            }

            qu.offer(arr[i][1]);
            max = Math.max(max, qu.size());
        }

        System.out.println(max);

    }
}
