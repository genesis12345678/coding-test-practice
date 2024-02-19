package dfs.thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 최대 4^(50*50)의 시간복잡도 걸린다. 일반적인 dfs로는 시간 초과가 걸린다.
 * 동적 프로그래밍을 사용해야 한다.
 * dp배열에 각 위치에 게임 진행 횟수를 저장한다.
 * 현재 위치에서 다음 위치로 가기 전에 dp 배열을 확인한다.
 * 다음 위치의 dp 배열 값이 현재 게임진행 횟수보다 크다면 그 방향으로는 탐색하지 않는다.
 * 그 방향으로는 탐색해도 최댓값 갱신에는 의미가 없다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1103">백준 1103번 : 깊이우선탐색 - 게임</a>
 * @since 2024-02-19
 */
public class Six {
    static int N, M;
    static char[][] map;
    static int[][] dp;
    static boolean[][] visit;
    static int max = -1;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        dp = new int[N][M];
        visit = new boolean[N][M];

        dfs(0, 0, 1);
        System.out.println(max);

        for (int[] ints : dp) {
            System.out.println("dp = " + Arrays.toString(ints));
        }
    }

    static void dfs(int x, int y, int depth) {
        int moveCount = Character.getNumericValue(map[x][y]);//다음 이동할 칸수

        //이미 방문한 칸이 나오면 사이클이 발생한 것이므로 -1을 출력하고 프로그램을 종료한다.
        if (visit[x][y]) {
            System.out.println(-1);
            System.exit(0);
        }

        dp[x][y] = depth;//dp배열에 현재 게임 횟수를 저장한다.
        visit[x][y] = true;

        max = Math.max(max, depth);//게임 최대 횟수를 갱신한다.

        for (int i = 0; i < 4; i++) {
            int nx = x + moveCount * dx[i];
            int ny = y + moveCount * dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {//범위를 벗어나면 안 된다.
                if (map[nx][ny] != 'H' && depth >= dp[nx][ny]) {//다음 위치가 구멍이 아니고 게임 최대 횟수를 갱신할 가능성이 있는 경로만 탐색한다.
                    dfs(nx, ny, depth + 1);
                }
            }
        }
        visit[x][y] = false;
    }
}
