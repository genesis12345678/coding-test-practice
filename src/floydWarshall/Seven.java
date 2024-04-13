package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 정방향과 역방향으로 연결해서 각각 플로이드워셜을 통해 최단거리를 구한다.
 * 두 사건의 정방향과 역방향의 최단 거리를 비교한다.
 * 서로 같다면 유추할 수 없다. -> 0
 * 정방향이 더 작다면 앞에 있는 사건이 먼저 일어났다. -> -1
 * 역방향이 더 작다면 뒤에 있는 사건이 먼저 일어났다. -> 1
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1613">백준 1613번 - 플로이드워셜 : 역사</a>
 * @since 2024-04-12
 */
public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n + 1][n + 1];
        int[][] distR = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], 50000);
            Arrays.fill(distR[i], 50000);
            dist[i][i] = 0;
            distR[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dist[a][b] = 1;
            distR[b][a] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                    }

                    if (distR[s][e] > distR[s][k] + distR[k][e]) {
                        distR[s][e] = distR[s][k] + distR[k][e];
                    }
                }
            }
        }

        int s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (dist[a][b] == distR[a][b]) {
                System.out.println(0);

            } else if (dist[a][b] < distR[a][b]) {
                System.out.println(-1);

            } else if (dist[a][b] > distR[a][b]) {
                System.out.println(1);
            }
        }

    }
}
