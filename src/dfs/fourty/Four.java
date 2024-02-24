package dfs.fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 처음(0, 0)부터 시작해 각 칸의 숫자로 오른쪽과 아래 쪽으로 갈 수 있는 칸 수를 구한다.
 * 해당 칸 수로 dfs로 탐색을 하면서 끝점에 도달했을 경우 결과를 출력하고 프로그램을 종료한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16173">백준 16173번 : 깊이우선탐색 - 점프왕 쩰리(Small)</a>
 * @since 2024-02-24
 */
public class Four {
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println("Hing");
    }

    static void dfs(int x, int y) {
        if (map[x][y] == -1) {
            System.out.println("HaruHaru");
            System.exit(0);
        }
        if (visit[x][y]) {
            return;
        }
        visit[x][y] = true;
        int next = map[x][y];

        for (int i = 0; i < 2; i++) {
            int nx = x + next * dx[i];
            int ny = y + next * dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                dfs(nx, ny);
            }
        }
    }
}
