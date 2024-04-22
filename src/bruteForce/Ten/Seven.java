package bruteForce.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 재귀호출을 통해 두 팀으로 나눌 수 있는 모든 경우의 수에 대해 최솟값을 갱신한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/14889">백준 14889번 - 브루트포스 : 스타트와 링크</a>
 * @since 2024-04-22
 */
public class Seven {

    static int[][] map;
    static boolean[] visit;
    static int n;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0);
        System.out.print(min);
    }

    static void divide(int depth, int start) {
        if (depth == n / 2) { //2 구역으로 나눠졌으면 능력치를 구해서 최솟값을 갱신한다.
            min = Math.min(min, calculate());
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                divide(depth + 1, i + 1);
                visit[i] = false;
            }
        }
    }

    static int calculate() {

        int startSum = 0;
        int linkSum = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (visit[i] && visit[j]) { //true를 스타트 팀으로 본다.
                    startSum += map[i][j] + map[j][i];
                }
                else if (!visit[i] && !visit[j]) { //false를 링크 팀으로 본다.
                    linkSum += map[i][j] + map[j][i];
                }
            }
        }

        return Math.abs(startSum - linkSum);
    }
}
