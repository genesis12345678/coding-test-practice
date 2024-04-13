package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 플로이드워셜로 최단 거리를 구하면서 최단 거리로 가기 위한 경로를 저장해야 한다.
 * path[i][j]를 i에서 j까지 최단 경로로 가기 위해 이 번호를 거쳐야 한다를 저장한다.
 * 예를 들어 path[1][5] 에 5가 저장되어 있으면 1번에서 5번까지는 한번에 바로 갈수 있는 것이고,
 * 3이 저장되어 있으면 path[3][5]를 확인해야 한다. 이렇게 해서 도착지 번호가 나올 때까지 확인한다.
 */

/**
 * <a href  = "https://www.acmicpc.net/problem/11780">백준 11780번 - 플로이드워셜 : 플로이드 2</a>
 * @since 2024-04-12
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int max = 1_000_001;

        int[][] dist = new int[n + 1][n + 1]; //최단 거리 배열
        int[][] path = new int[n + 1][n + 1]; //중간 경로 배열

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], max);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = Math.min(dist[a][b], c);
            path[a][b] = b; //초기값, a에서 b로 갈려면 b를 거친다.
        }

        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                        path[s][e] = path[s][k];
                        //최단 거리를 구하면서 경로 업데이트
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        //모든 최단 경로 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(dist[i][j] != max ? dist[i][j] : 0).append(" ");
            }
            sb.append("\n");
        }

        List<Integer> list = new ArrayList<>();

        //i에서 j로 가는 경로 출력
        for (int i = 1; i <= n; i++) { //출발지 i
            for (int j = 1; j <= n; j++) { //도착지 j

                if (i != j && dist[i][j] != max) {

                    int now = i; //출발

                    while (now != j) { //도착할 때까지 경로 저장
                        list.add(now);
                        now = path[now][j];
                    }

                    sb.append(1 + list.size()).append(" "); //도착지를 포함해 +1
                    for (int num : list) {
                        sb.append(num).append(" "); //쌓였던 경로 저장
                    }

                    sb.append(j).append("\n"); //마지막으로 도착지 저장

                } else { //출발=도착 이거나, 아예 경로가 없는 경우
                    sb.append(0).append("\n");
                }

                list.clear();
            }
        }

        System.out.print(sb);
    }
}
