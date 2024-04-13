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
 * 가장 멀리 있는 친구의 관계 점수가 가장 작은 사람을 뽑으라는 문제다.
 * 관계를 그래프로 표현하고 가중치를 1로 공통화하면 최단거리를 알 수 있다.
 * 각 사람의 거리 중 최댓값이 가장 작은 사람이 정답이 된다. 물론 여러명 일 수 있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2660">백준 2660번 - 플로이드워셜 : 회장뽑기</a>
 * @since 2024-04-11
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], 10000);
            dist[i][i] = 0;
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) {
                break;
            }

            //양방향 연결
            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        //플로이드워셜 수행
        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                    }
                }
            }
        }

        int[] arr = new int[n + 1]; //각 사람당 최댓값이 담길 배열
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            int max = 0;

            for (int j = 1; j <= n; j++) {
                max = Math.max(max, dist[i][j]);
            }

            arr[i] = max;
            min = Math.min(min, arr[i]); //최댓값 중에서 최솟값 갱신
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (arr[i] == min) { //가장 적은 사람만 추가
                result.add(i);
            }
        }

        System.out.println(min + " " + result.size());

        result.forEach(i -> System.out.print(i + " "));
    }

}
