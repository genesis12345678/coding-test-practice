package Greedy.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * A 행렬과 B 행렬 (0,0)에서부터 비교하면서 값이 다르다면 A 행렬의 3x3을 뒤집는다.
 * 더 이상 뒤집을 수 없을 때까지 반복하고 완성된 두 행렬을 비교한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1080">백준 1080번 : 그리디 알고리즘 - 행렬</a>
 * @since 2024-01-03
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        int[][] A = new int[N][M];
        int[][] B = new int[N][M];

        /**
         * A 행렬 저장
         * 값을 맞추기 위해 아스키 문자로 계산한다.
         */
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                A[i][j] = s.charAt(j) - 48;
            }
        }

        /**
         * B 행렬 저장
         * 값을 맞추기 위해 아스키 문자로 계산한다.
         */
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                B[i][j] = s.charAt(j) - 48;
            }
        }

        /**
         * (0, 0)부터 비교하여 뒤집는다.
         * 만약 N이나 M이 3보다 작다면 3x3을 뒤집을 수 없다.
         * 범위에 -3을 해줌으로 3x3을 뒤집을 수 없는 범위 직전까지 실행한다.
         */
        int count = 0;
        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= M - 3; j++) {
                if (A[i][j] != B[i][j]) {
                    count++;
                    for (int k = i; k < i + 3; k++) {
                        for (int l = j; l < j + 3; l++) {
                            A[k][l] = (A[k][l] == 0) ? 1 : 0;
                        }
                    }
                }
            }
        }

        /**
         * 완성된 두 행렬을 비교한다.
         * 중간에 하나라도 값이 다르다면 바로 -1을 출력하고 return한다.
         */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] != B[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(count);
    }
}
