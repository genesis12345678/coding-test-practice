package dfs.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 단순 boolean 방문 배열로 하면 시간 초과가 난다.
 * 일단 기본적으로 dfs로 목적지까지 탐색을 한다.
 * 메모이제이션을 사용하여 기존 이동 가능한 경로에 끼어들 경우 더 이상 탐색하지 않는다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1520">백준 1520번 : 깊이우선탐색 - 내리막 길</a>
 * @since 2024-02-07
 */
public class One {

    static int[][] dp;
    static int[][] map;
    static int M, N;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        dp = new int[M][N];
        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0, 0));
    }

    static int dfs(int x, int y) {
        //목적지에 도달할 경우 경우의 수 1 추가
        if (x == M - 1 && y == N - 1) {
            return 1;
        }

        //-1이 아니라는 것은 이미 방문한 경로다 == 목적지로 갈 수 있는 경로다.
        //더 이상 탐색할 필요가 없다.
        if (dp[x][y] != -1) {
            return dp[x][y];

        }
        else {
            dp[x][y] = 0;//이동 가능 경로 초기화
            for (int i = 0; i < 4; i++) {
                int nx = x + mx[i];
                int ny = y + my[i];

                if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                    if (map[x][y] > map[nx][ny]) {
                        //이동 가능하다면 경로의 수를 누적한다.
                        //최종 탐색이 끝나면 0,0에 총 경우의 수가 저장된다.
                        dp[x][y] += dfs(nx, ny);
                    }
                }
            }
        }
        return dp[x][y];
    }
}
