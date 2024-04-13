package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 그림을 그려서 파악해보니 정방향과 역방향으로 그렸을 때 도달할 수 없는 노드 개수를 찾는 문제였다.
 * 그래서 가중치는 안 나와 있으니 1로 하고, 정방향과 역방향의 최단 거리를 구한다.
 * 정뱡향과 역방향 중에 하나라도 연결된 곳이 있다면 연결 개수를 +1 해준다.
 * 각 번호당 N - 연결 개수가 정답이 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10159">백준 10159번 - 플로이드워셜 : 저울</a>
 * @since 2024-04-11
 */
public class Five {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] dist = new int[n + 1][n + 1]; //정방향
        int[][] distR = new int[n + 1][n + 1]; //역방향

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], 10000);
            Arrays.fill(distR[i], 10000);
            dist[i][i] = 0;
            distR[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dist[a][b] = 1; //정방향 연결
            distR[b][a] = 1; //역방향 연결
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

        for (int i = 1; i <= n; i++) {
            int sum = 1; //처음엔 자기 자신을 포함해서 1부터 시작한다.

            for (int j = 1; j <= n; j++) {
                if (i != j) { //시작과 끝이 같지 않으면서 정방향과 역방향 중 한 곳이라도 연결이 됐다면 연결 개수를 증가한다.
                    if (dist[i][j] != 10000 || distR[i][j] != 10000) {
                        sum++;
                    }
                }
            }

            System.out.println(n - sum); //총 개수 - 연결 개수
        }
    }
}
