package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 2-친구 라는 말을 이해하는 데 한참 걸렸다.
 * 다른 친구와의 연결 횟수가 2단계 이상 가지 않는 각 사람의 친구의 수 중 최댓값이 정답이다.
 * 일단 Y면 가중치를 1로 하고 연결하고 플로이드워셜 알고리즘으로 각 노드 당 최단거리를 구한다.
 * 각 노드 당 2 이하의 개수를 구한다.
 * 2 이하의 개수를 가장 많이 갖고있는 2 이하의 개수가 정답이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1058">백준 1058번 - 플로이드워셜 : 친구</a>
 * @since 2024-04-11
 */
public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {
                dist[i][j] = arr[j] == 'Y' ? 1 : 10000;

            }
            dist[i][i] = 0;
        }

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int sum = 0;

            for (int j = 0; j < n; j++) {
                if (i != j && 2 - dist[i][j] >= 0) {
                    sum++;
                }
            }

            max = Math.max(max, sum);
        }


        System.out.print(max);
    }
}
