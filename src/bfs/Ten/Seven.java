package bfs.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 구간마다 x-1, x+1, x*2를 탐색하여 K에 도달할 때까지 수를 센다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1697">백준 1697번 : 너비우선탐색</a>
 * @since 2024-01-12
 */
public class Seven {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            return;
        }

        boolean[] visit = new boolean[100001];
        
        int count = 0;
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(N);
        visit[N] = true;

        while (true) {
            count++;
            /**
             * 주의!
             * 큐의 사이즈가 계속 변하여 원하는 결과를 못 얻을 수 있음
             * 먼저 선언해둬야 함.
             */
            int size = qu.size();
            for (int i = 0; i < size; i++) {
                int x = qu.poll();
                visit[x] = true;

                if (x + 1 == K || x - 1 == K || x * 2 == K) {
                    System.out.println(count);
                    return;
                }
                if (x - 1 >= 0 && !visit[x - 1]) {
                    qu.offer(x - 1);
                    visit[x - 1] = true;
                }
                if (x + 1 <= 100000 && !visit[x + 1]) {
                    qu.offer(x + 1);
                    visit[x + 1] = true;
                }
                if (x * 2 <= 100000 && !visit[x * 2]) {
                    qu.offer(x * 2);
                    visit[x*2] = true;
                }
            }
        }

    }
    
}


