package Greedy.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 아이디어
 * 최대힙을 사용해 최다투표수를 하나씩 뺏어온다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1417">백준 1417번 : 그리디 알고리즘 - 국회의원 선거</a>
 * @since 2024-01-08
 */
public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());

        // 기호 1번 다솜이
        int dasom = Integer.parseInt(br.readLine());
        // 다솜이를 제외한 나머지 후보들
        int vote = N - 1;
        while (vote-- > 0) {
            qu.offer(Integer.parseInt(br.readLine()));
        }
        // 후보가 없다면 매수해야 할 사람이 없다.
        if (vote == 0) {
            System.out.println(0);
            return;
        }

        int count = 0;
        while (true) {
            /**
             * 다솜이를 제외한 후보자들 중 최다 투표 후보자가 다솜이보다 투표수가 적을 때까지
             * 최다투표수가 다솜이보다 많다면 최다투표수는 1 줄이고, 다솜이는 1 더한다.
             * 한표를 뺀 후보자의 투표수를 다시 최대힙에 저장해 재정렬을 해준다.
             */
            if (!qu.isEmpty() && qu.peek() >= dasom) {
                Integer poll = qu.poll();
                qu.offer(--poll);
                dasom++;
                count++;
            }else break;
        }
        System.out.println(count);
    }
}
