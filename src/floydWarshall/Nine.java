package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 이미 최단 거리는 구해져 있고, 이것을 통해 플로이드워셜을 수행하기 전의 상태를 알 수 있어야 하는 문제다.
 * 플로이드워셜에 핵심 아이디어는 s에서 e로 갈 때 바로 가는 것보다 중간 경로를 통해서 가는 것이 최단 경로일 수 있다는 것이다.
 * 그래서 똑같이 플로이드워셜 로직을 수행하면서 [s][e] = [s][k] + [k][e]이면 [s][e]를 0으로 초기화한다.
 * 이후 남은 경로의 합에 2로 나눈 값이 정답이다. 2로 나누는 이유는 모두 다 더하면 왔다갔다 2번씩 더해지기 때문이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1507">백준 1507번 - 플로이드워셜 : 궁금한 민호</a>
 * @since 2024-04-13
 */
public class Nine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dist = new int[n][n];
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = dist[i][j];
            }
        }

        for (int k = 0; k < n; k++) { //경유지
            for (int s = 0; s < n; s++) { //출발지
                for (int e = 0; e < n; e++) { //도착지

                    //출발, 경유, 도착이 겹치는 경우를 제외한다.
                    //제외하지 않으면 모든 경로를 지워버린다.
                    if (s != e && s != k && k != e) {

                        if (dist[s][e] == dist[s][k] + dist[k][e]) {
                            arr[s][e] = 0;
                        }

                        //탐색 도중 더 짧은 최단 거리를 찾았다면 모순이므로, 불가능한 경우다.
                        if (dist[s][e] > dist[s][k] + dist[k][e]) {
                            System.out.println(-1);
                            return;
                        }

                    }
                }
            }
        }

        int sum = 0;
        for (int[] ints : arr) {
            for (int num : ints) {
                sum += num;
            }
        }

        System.out.println(sum / 2);
    }
}
