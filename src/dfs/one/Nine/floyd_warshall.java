package dfs.one.Nine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 플로이드-워셜 알고리즘을 이해해볼 수 있는 문제다.
 * 모든 노드를 탐색해서 노드 간에 최단 경로를 탐색할 수 있다.
 * 시간 복잡도가 O(V^3)으로 범위가 작은 문제에 적용해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11404">백준 11404번 : 플로이드-워셜 - 플로이드</a>
 * @since 2024-02-11
 */
public class floyd_warshall {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] cost = new int[n][n];

        //시작과 끝이 겹치는 칸(1,1 or 2,2)만 0으로 하고 나머지는 큰 수로 초기화한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    cost[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        //입력으로 주어지는 정보를 저장한다.
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            cost[a][b] = Math.min(cost[a][b], c);
        }

        //출발에서 도착으로 다이렉트 경로가 더 빠를 수도 있고
        //연결된 노드들을 거쳐서 가는 경로가 더 빠를 수도 있다.
        for (int k = 0; k < n; k++) { //경유지
            for (int i = 0; i < n; i++) { //출발
                for (int j = 0; j < n; j++) { //도착
                    if (cost[i][k] != Integer.MAX_VALUE && cost[k][j] != Integer.MAX_VALUE) {
                        cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] ints : cost) {
            for (int num : ints) {
                sb.append(num == Integer.MAX_VALUE ? 0 : num).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
