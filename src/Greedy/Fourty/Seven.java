package Greedy.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 최소힙에서 가장 작은 수 2개를 꺼내서 합을 두 번 집어넣고
 * m번 반복하면 된다.
 * 주의할 점은 최대 1,000,000 x 15,000의 수가 가능하기 때문에 long자료형을 사용해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/15903">백준 15903번 : 그리디 알고리즘 - 카드 합체 놀이</a>
 * @since 2024-01-05
 */
public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> qu = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            qu.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            Long a = qu.poll();
            Long b = qu.poll();

            qu.offer(a + b);
            qu.offer(a + b);
        }

        long sum = 0;
        for (Long num : qu) {
            sum += num;
        }
        System.out.println(sum);
    }
}
