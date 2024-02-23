package dfs.fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 양이나 늑대가 있는 칸을 시작으로 dfs 탐색으로 울타리 안 영역에 양과 늑대가 각각 몇 마리 있는지 센다.
 * 양이 많다면 늑대를 없애고 그 외에는 양을 없앤다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/3187">백준 3187번 : 깊이우선탐색 - 양치기꿍</a>
 * @since 2024-02-22
 */
public class Two {

    static int R, C;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int sheeps, wolfs;//전체 양과 늑대
    static int targetSheeps, targetWolfs;//각 dfs 탐색에서 쓰일 울타리 안에 양과 늑대

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visit = new boolean[R][C];

        //입력을 받으면서 전체 양과 늑대 마리 수를 구한다.
        for (int i = 0; i < R; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i] = arr;
                if (map[i][j] == 'k') {
                    sheeps++;
                } else if (map[i][j] == 'v') {
                    wolfs++;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visit[i][j]) { //방문하지 않고 늑대나 양인 칸부터 탐색
                    if (map[i][j] == 'k' || map[i][j] == 'v') {
                        targetWolfs = 0;
                        targetSheeps = 0;
                        dfs(i, j);
                    }

                    //탐색이 끝나고 울타리 안에 양과 늑대의 수를 비교한다.
                    if (targetSheeps > targetWolfs) {//양이 더 많다면 늑대를 없앤다.
                        wolfs -= targetWolfs;
                    } else {//그 외에는 양을 없앤다.
                        sheeps -= targetSheeps;
                    }
                }
            }
        }
        System.out.println(sheeps + " " + wolfs);
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;

        if (map[x][y] == 'v') {
            targetWolfs++;
        } else if (map[x][y] == 'k') {
            targetSheeps++;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                if (!visit[nx][ny] && map[nx][ny] != '#') {
                    dfs(nx, ny);
                }
            }
        }
    }
}
