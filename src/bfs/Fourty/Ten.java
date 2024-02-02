package bfs.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * k번 말로 이동하고 이후 원숭이로 이동한다는 이론은 간단하다. 문제는 말 이동경로와 원숭이 이동경로가 겹쳐서는 안 된다.
 * 그래서 visit 2차원 배열에 k+1만큼 배열을 하나 추가해준다. k+1인 이유는 말 만큼 개수(k)에다가 원숭이(1)의 경로를 각각 구하기 위해서다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1600">백준 1600번 : 너비우선탐색 - 말이 되고픈 원숭이</a>
 * @since 2024-02-02
 */
public class Ten {
    static int k, w, h;
    static int[][] map;
    static boolean[][][] visit;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int[] horseX = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] horseY = {1, 2, 2, 1, -1, -2, -2, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{0, 0, 0, k});//처음부터 시작
        visit = new boolean[h][w][k + 1];
        visit[0][0][k] = true;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];
            int cnt = now[2];
            int horse = now[3];

            if (x == h - 1 && y == w - 1) {
                return cnt;
            }

            //말 이동횟수가 남아 있다면 말로 이동하고 말 경로를 체크한다.
            if (horse > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + horseX[i];
                    int ny = y + horseY[i];
                    if (nx >= 0 && ny >= 0 && nx < h && ny < w) {
                        if (!visit[nx][ny][horse - 1] && map[nx][ny] == 0) {
                            visit[nx][ny][horse - 1] = true;
                            qu.offer(new int[]{nx, ny, cnt + 1, horse - 1});
                        }
                    }
                }
            }

            //일반 원숭이 경로
            for (int i = 0; i < 4; i++) {
                int nx = x + mx[i];
                int ny = y + my[i];

                if (nx >= 0 && ny >= 0 && nx < h && ny < w) {
                    if (!visit[nx][ny][horse] && map[nx][ny] == 0) {
                        visit[nx][ny][horse] = true;
                        qu.offer(new int[]{nx, ny, cnt + 1, horse});
                    }
                }
            }
        }
        return -1;
    }
}
