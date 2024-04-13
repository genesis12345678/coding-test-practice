package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * n이 최대 200이고 플로이드워셜은 n^3의 시간 복잡도를 갖고 있으므로 200^3 = 8,000,000이다. 플로이드워셜 알고리즘으로 해결해도 충분할 것 같다.
 * 플로이드워셜로 최단 거리를 구하면서 동시에 거치는 경로들을 저장해준다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1719">백준 1719번 - 플로이드워셜 : 택배</a>
 * @since 2024-04-08
 */
public class Two {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n + 1][n + 1];
        int[][] result = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], 1000 * 10000);
            Arrays.fill(result[i], -1);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            //초깃값 저장
            dist[a][b] = c;
            dist[b][a] = c;

            result[a][b] = b;
            result[b][a] = a;
        }

        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                        result[s][e] = result[s][k];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (result[i][j] == -1) {
                    System.out.print(" -");
                } else {
                    System.out.print(" " + result[i][j]);
                }
            }
            System.out.println();
        }
    }
}
