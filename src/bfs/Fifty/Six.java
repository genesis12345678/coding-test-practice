package bfs.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 불! 문제와 똑같다. 불을 먼저 퍼뜨리고 상근이를 이동한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/5427">백준 5427번 : 너비우선탐색 - 불</a>
 * @since 2024-02-05
 */
public class Six {

    static int w, h;
    static char[][] map;
    static boolean[][][] visit;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();
    static Queue<int[]> S; //상근이
    static Queue<int[]> F; //불
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            S = new LinkedList<>();
            F = new LinkedList<>();

            map = new char[h][w];
            visit = new boolean[h][w][2];//불과 상근이 각각 방문처리를 해야 하기에 2배열 추가

            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = s.charAt(j);

                    if (map[i][j] == '*') {
                        F.offer(new int[]{i, j});//불 시작 위치 저장
                    }
                    else if (map[i][j] == '@') {
                        S.offer(new int[]{i, j, 0});//상근이 시작 위치 저장
                    }
                }
            }
            bfs();
        }
        System.out.println(sb);
    }

    static void bfs() {
        while (!S.isEmpty()) {
            //불을 먼저 퍼뜨린다.
            int fSize = F.size();
            for (int i = 0; i < fSize; i++) {
                int[] now = F.poll();
                int x = now[0];
                int y = now[1];

                for (int j = 0; j < 4; j++) {
                    int nx = x + mx[j];
                    int ny = y + my[j];

                    if (nx >= 0 && ny >= 0 && nx < h && ny < w) {
                        if (!visit[nx][ny][0] && map[nx][ny] == '.') {
                            visit[nx][ny][0] = true;
                            map[nx][ny] = '*';
                            F.offer(new int[]{nx, ny});
                        }
                    }
                }
            }

            //불이 퍼진 뒤 상근이 이동
            int sSize = S.size();
            for (int i = 0; i < sSize; i++) {
                int[] now = S.poll();
                int x = now[0];
                int y = now[1];
                int cnt = now[2];

                for (int j = 0; j < 4; j++) {
                    int nx = x + mx[j];
                    int ny = y + my[j];

                    if (nx >= 0 && ny >= 0 && nx < h && ny < w) {
                        if (!visit[nx][ny][1] && map[nx][ny] == '.') {
                            visit[nx][ny][1] = true;
                            map[nx][ny] = '@';
                            S.offer(new int[]{nx, ny, cnt + 1});
                        }
                    }

                    if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
                        sb.append(cnt + 1).append("\n");
                        return;
                    }
                }
            }
        }
        sb.append("IMPOSSIBLE").append("\n");
    }
}
