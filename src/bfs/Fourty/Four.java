package bfs.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Four {
    static int N, M, K, X;
    static List<List<Integer>> graph = new ArrayList<>();
    static Queue<Integer> qu = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
        }

        bfs(X);

        if (qu.isEmpty()) {
            System.out.println(-1);
        } else {
            while (!qu.isEmpty()) {
                sb.append(qu.poll()).append("\n");
            }
            System.out.println(sb);
        }
    }

    static void bfs(int start) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start, 0});

        boolean[] visit = new boolean[1_000_001];
        visit[start] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int n = now[0];
            int cnt = now[1];

            if (cnt == K) {
                qu.offer(n);
            }

            for (int x : graph.get(n)) {
                if (!visit[x]) {
                    visit[x] = true;
                    q.offer(new int[]{x, cnt + 1});
                }
            }
        }
    }
}
