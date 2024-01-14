package bfs.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 1번부터 큐를 활용한 bfs 방식으로 계산한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2606">백준 2606번 : 너비우선탐색 - 바이러스</a>
 * @since 2024-01-11
 */
public class Three {

    static boolean[] visit;
    static List<List<Integer>> graph = new ArrayList<>(); // 인접리스트
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        visit = new boolean[c + 1];
        for (int i = 0; i <= c; i++) {
            graph.add(new ArrayList<>());
        }

        int s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        dfs();
        System.out.println(sum);
    }

    static void dfs() {
        // 1번부터 시작
        visit[1] = true;
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(1);

        while (!qu.isEmpty()) {
            int poll = qu.poll();
            List<Integer> nums = graph.get(poll);
            for (int num : nums) {
                if (!visit[num]) {
                    sum++;
                    visit[num] = true;
                    qu.offer(num);
                }
            }
        }
    }
}
