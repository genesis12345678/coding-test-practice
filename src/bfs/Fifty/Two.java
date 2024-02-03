package bfs.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 불과 지훈이 위치를 저장하는 큐를 따로 만들어서 불을 먼저 퍼뜨리고 그 다음에 지훈이를 이동한다.
 * 이때 방문여부가 겹치면 안 되기 때문에 3차원 배열로 [][][0]은 불, [][][1]은 지훈이로 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/4179">백준 4179번 : 너비우선탐색 - 불!</a>
 * @since 2024-02-03
 */
public class Two {
    static char[][] map;
    static boolean[][][] visit;
    static int R, C;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static Queue<int[]> J = new LinkedList<>(); //지훈
    static Queue<int[]> F = new LinkedList<>(); //불
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visit = new boolean[R][C][2];
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'J') { // 지훈 첫 위치 저장
                    J.offer(new int[]{i, j, 0});
                }
                else if (map[i][j] == 'F') { // 불 위치 저장
                    F.offer(new int[]{i, j});
                }
            }
        }
        bfs();
        System.out.println("map = " + Arrays.deepToString(map));
    }

    static void bfs() {

        while (!J.isEmpty()) {
            // 불을 먼저 퍼뜨린다.
            int fSize = F.size();
            for (int i = 0; i < fSize; i++) {
                int[] now = F.poll();
                int x = now[0];
                int y = now[1];

                for (int j = 0; j < 4; j++) {
                    int nx = x + mx[j];
                    int ny = y + my[j];

                    if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                        if (!visit[nx][ny][0] && map[nx][ny] == '.') {
                            visit[nx][ny][0] = true;
                            map[nx][ny] = 'F';
                            F.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
            // 지훈이 이동
            int jSize = J.size();
            for (int i = 0; i < jSize; i++) {
                int[] now = J.poll();
                int x = now[0];
                int y = now[1];
                int cnt = now[2];

                for (int j = 0; j < 4; j++) {
                    int nx = x + mx[j];
                    int ny = y + my[j];

                    if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                        if (!visit[nx][ny][1] && map[nx][ny] == '.') {
                            visit[nx][ny][1] = true;
                            map[nx][ny] = 'J';
                            J.offer(new int[]{nx, ny, cnt + 1});
                        }
                    }
                    // 범위를 벗어나면 불이 주변에 퍼지기 전에 탈출한 것이므로 이동횟수 출력하고 return
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                        System.out.println(cnt + 1);
                        return;
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
