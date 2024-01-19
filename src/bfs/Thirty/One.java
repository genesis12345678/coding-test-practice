package bfs.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Comparator.*;

/**
 * 아이디어
 * 숨바꼭질 문제와 거의 똑같다. x * 2가 0초 걸리는게 다르다.
 * x2 시간을 먼저 처리하기 위해 우선순위 큐를 이용해 bfs로 탐색한다.
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 시작부터 위치가 같다.
        if (N == K) {
            System.out.println(0);
            return;
        }
        // 입력 최대 수로 방문 배열 초기화
        boolean[] visit = new boolean[100_001];

        // {현재 위치, 이동 거리} 배열에 이동 거리 오름차순 정렬
        Queue<int[]> qu = new PriorityQueue<>(comparingInt(o -> o[1]));
        // 시작 위치, 시간 입력
        qu.offer(new int[]{N, 0});

        while (true) {
            /**
             * 주의!
             * 큐의 사이즈가 계속 변하여 원하는 결과를 못 얻을 수 있음
             * 먼저 선언해둬야 함.
             */
            int size = qu.size();
            for (int i = 0; i < size; i++) {
                int[] now = qu.poll();

                int x = now[0];
                visit[x] = true;

                if (x == K) {
                    System.out.println(now[1]);
                    return;
                }

                // -1 1초 증가
                if (x - 1 >= 0 && !visit[x - 1]) {
                    qu.offer(new int[]{x - 1, now[1] + 1});
                }
                // +1 1초 증가
                if (x + 1 <= 100000 && !visit[x + 1]) {
                    qu.offer(new int[]{x + 1, now[1] + 1});
                }
                // x2 0초 증가
                if (x * 2 <= 100000 && !visit[x * 2]) {
                    qu.offer(new int[]{x * 2, now[1]});
                }
            }
        }
    }
}
