package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 플로이드워셜 알고리즘으로 최단 거리를 구한 후, 사이클이 발생한 경로에 대해서만 최댓값을 갱신한다.
 * V가 최대 400이고 플로이드워셜 알고리즘은 N^3이므로 400^3, 시간 내에 충분히 가능하다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1956">백준 1956번 - 플로이드워셜 : 운동</a>
 * @since 2024-04-10
 */
public class Three {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int max = 10000 * 400 + 1;

        int[][] dist = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            Arrays.fill(dist[i], max);
            dist[i][i] = 0;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            //단방향 연결
            dist[a][b] = c;
        }

        //플로이드워셜 수행
        for (int k = 1; k <= V; k++) {
            for (int s = 1; s <= V; s++) {
                for (int e = 1; e <= V; e++) {
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                    }
                }
            }
        }

        int min = max;

        for (int s = 1; s <= V; s++) {
            for (int e = 1; e <= V; e++) {
                if (s != e) { //출발과 시작이 같지 않을 때만

                    //다시 돌아오는 경로를 탐색한다.
                    int d = dist[s][e] + dist[e][s];

                    //경로가 최댓값보다 크다면 사이클이 발생하지 않는다는 뜻이다.
                    if (d < max) {
                        min = Math.min(min, d);
                    }
                }
            }
        }

        System.out.println(min == max ? -1 : min);
    }
}
