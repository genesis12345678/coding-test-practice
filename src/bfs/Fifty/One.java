package bfs.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * B를 해킹하면 A 해킹이 가능하니까 B -> A로 인접리스트에 저장한다.
 * 시간초과가 계속 난다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1325">백준 1325번 : 너비우선탐색 - 효율적인 해킹</a>
 * @since 2024-02-03
 */
public class One {
    static int max = Integer.MIN_VALUE;
    static List<List<Integer>> graph = new ArrayList<>();
    static int N;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(B).add(A);
        }

        int[] temp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            bfs(i);
            temp[i] = count;
            max = Math.max(max, count);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (temp[i] == max) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }

    static void bfs(int x) {
        count = 0;
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(x);

        boolean[] visit = new boolean[N + 1];
        visit[x] = true;

        while (!qu.isEmpty()) {
            Integer now = qu.poll();
            for (int num : graph.get(now)) {
                if (!visit[num]) {
                    visit[num] = true;
                    qu.offer(num);
                    count++;
                }
            }
        }
    }
}
