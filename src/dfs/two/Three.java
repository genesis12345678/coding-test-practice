package dfs.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 입력을 받을 때 양과 늑대의 총 수를 센다.
 * 0, 0(처음)부터 탐색을 시작하여 양이나 늑대가 있는 칸이 나오면 그 자리를 시작으로 dfs 탐색을 한다.
 * 울타리 안에 있는 범위에 양과 늑대의 수를 비교하여 양이 많다면 늑대를 없애고, 늑대가 많거나 같다면 양을 없앤다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/3184">백쥰 3184번 : 깊이우선탐색 - 양</a>
 * @since 2024-02-13
 */

public class Three {
    static char[][] map;
    static boolean[][] visit;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int R, C;
    static int lambs, wolfs, l, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visit = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'o') {
                    lambs++;
                } else if (map[i][j] == 'v') {
                    wolfs++;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'v' || map[i][j] == 'o' && !visit[i][j]) {
                    l = 0;
                    w = 0;
                    dfs(i, j);
                    if (l > w) {
                        wolfs -= w;
                    } else {
                        lambs -= l;
                    }
                }
            }
        }
        System.out.println(lambs + " " + wolfs);
    }

    static void dfs(int x, int y) {
        if (map[x][y] == 'o') {
            l++;
        } else if (map[x][y] == 'v') {
            w++;
        }

        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + mx[i];
            int ny = y + my[i];

            if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                if (!visit[nx][ny] && map[nx][ny] != '#') {
                    dfs(nx, ny);
                }
            }
        }
    }
}
