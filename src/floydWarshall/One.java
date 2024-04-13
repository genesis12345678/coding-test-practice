package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 특정 출발 노드가 주어지지 않았기 때문에 모든 노드에서 최단 거리를 구해야 한다.
 * 다익스트라로 구할 수도 있지만, N이 최대 100으로 매우 작으므로 좀 더 간편한 플로이드-워셜로 구해본다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/14938">백준 14938번 - 플로이드워셜 : 서강그라운드</a>
 * @since 2024-04-05
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //지역(노드) 개수
        int m = Integer.parseInt(st.nextToken()); //수색범위
        int r = Integer.parseInt(st.nextToken()); //길(간선) 개수

        st = new StringTokenizer(br.readLine());

        int[][] dist = new int[n + 1][n + 1]; //최단 거리 인접 행렬
        int[] item = new int[n + 1]; //각 노드에서 얻을 수 있는 아이템 개수

        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    dist[i][j] = 30 * 100 * 100 + 1; //초기 최댓값 저장
                }
            }
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            //양방향으로 길이 저장
            dist[a][b] = l;
            dist[b][a] = l;
        }

        //플로이드-워셜
        for (int k = 1; k <= n; k++) { //경유지 k
            for (int s = 1; s <= n; s++) { //출발지 s
                for (int e = 1; e <= n; e++) { //도착지 e
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;

        //1번~n번 노드를 시작 노드로 정했을 때, 최단 거리가 m 이하면 아이템을 얻으면서 최댓값 갱신
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] <= m) {
                    sum += item[j];
                }
            }
            max = Math.max(max, sum);
        }

        System.out.println(max);

    }
}
