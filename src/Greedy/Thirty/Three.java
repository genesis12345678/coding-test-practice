package Greedy.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 강의시간을 시작 시간 오름차순 정렬하고 우선순위 큐를 이용해 끝나는 시간을 저장시키며
 * 같은 강의실에서 들을 수 있는 수업들을 정리해간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11000">백준 11000번 : 그리디 알고리즘 - 강의실 배정</a>
 * @since 2024-01-02
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] lecture = new int[N][2]; // 강의의 시작 시간과 끝나는 시간 정보
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()); // 시작 시간
            int T = Integer.parseInt(st.nextToken()); // 끝나는 시간
            lecture[i][0] = S;
            lecture[i][1] = T;
        }

        /**
         * 시작시간 기준 오름차순 정렬한다.
         * 시작시간이 같다면 끝나는 시간을 오름차순 정렬한다.
         * 정렬을 먼저 해야 앞에서부터 최대한 많은 강의들을 들을 수 있다.
         */
        Arrays.sort(lecture,((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }else
                return o1[0] - o2[0];
        }));

        Queue<Integer> qu = new PriorityQueue<>(); // 우선순위 큐(최소힙)
        qu.offer(lecture[0][1]); // 가장 먼저 끝나는 강의의 시간

        for (int i = 1; i < N; i++) {
            /**
             * 다음 강의의 시작 시간이 이전 강의의 끝나는 시간보다 크거나 같다면,
             * 같은 강의실에서 들을 수 있는 것이니 기존에 있던 강의는 빼준다.
             */
            if (!qu.isEmpty() && qu.peek() <= lecture[i][0]) {
                qu.poll();
            }
            qu.offer(lecture[i][1]);
        }
        System.out.println(qu.size()); // 마지막에 남은 우선순위 큐의 size(강의실 개수)가 답이 된다.

    }
}
