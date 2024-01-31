package bfs.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 숨바꼭질 문제에 최단경로 경우의 수까지 계산해야 한다.
 * 경우의 수는 여러개 나올 수 있으니 중복 방문을 허용해야 한다. 하지만 중복 방문 시 확인해야 할 게 있다.
 * 1. 방문 시간이 일치하면 -> 가능성 있는 경로라고 보고 허용한다.
 * 2. 이전 방문 시간이 더 빠르다면 -> 가능성 없는 경로라고 보고 허용하지 않는다.
 * 이렇게 bfs 탐색을 하다가 목표(K)에 도달했을 때 시간과 경우의 수를 갱신한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/12851">백준 12851번 : 너비우선탐색 - 숨바꼭질 2</a>
 * @since 2024-01-31
 */
public class Six {
    static int min = Integer.MAX_VALUE;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            System.out.println(1);
        } else {
            bfs(N, K);
            System.out.println(min);
            System.out.println(count);
        }
    }

    static void bfs(int n, int k) {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(n);

        int[] time = new int[100_001];
        time[n] = 1; //시작 위치

        while (!qu.isEmpty()) {
            Integer now = qu.poll();
            // 현재 탐색 시간이 목표를 찾았을 때 시간보다 크다면 더 이상 탐색할 필요가 없다.
            if (time[now] > min) {
                return;
            }

            for (int i = 0; i < 3; i++) {
                int next;
                if (i == 0) {
                    next = now + 1;
                } else if (i == 1) {
                    next = now - 1;
                } else {
                    next = now * 2;
                }
                // 범위 안에 있을때
                if (next >= 0 && next <= 100_000) {
                    //다음 위치가 목표라면
                    //경우의 수 증가
                    //최소시간 갱신
                    if (next == k) {
                        count++;
                        min = time[now];
                    }

                    //처음 방문이거나 방문 시간이 똑같은 경우 가능성이 있는 경로이므로 다음 탐색에 포함해준다.
                    if (time[next] == 0 || time[next] == time[now] + 1) {
                        qu.offer(next);
                        time[next] = time[now] + 1;
                    }
                }
            }
        }
    }

}
