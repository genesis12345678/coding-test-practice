package bfs.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 고슴도치만 옮기는 것이 아니라 물도 이동해야 해서 어떻게 해야 하나 정말 고민이 됐다.
 * 더 효울적인 방법이 있는진 모르겠지만 일단 고슴도치와 물의 위치를 각각 사용하는 방법으로 했다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/3055">백준 3055번 : 너비우선탐색 - 탈출</a>
 * @since 2024-01-26
 */
public class Eight {
    static int R, C;
    static char[][] map;
    static boolean[][] sVisit; // 고슴도치 전용 방문 배열
    static boolean[][] wVisit; // 물 전용 방문 배열
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static Queue<int[]> s = new LinkedList<>(); // 고슴도치 전용 큐
    static Queue<int[]> water = new LinkedList<>(); // 물 전용 큐
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        sVisit = new boolean[R][C];
        wVisit = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {
                    s.offer(new int[]{i, j, 0});
                } else if (map[i][j] == '*') {
                    water.offer(new int[]{i, j});
                }
            }
        }

        bfs();
    }

    static void bfs() {

        while (!s.isEmpty()) {

            // 물을 먼저 퍼뜨려야 한다.
            int size = water.size();
            for (int i = 0; i < size; i++) {
                int[] now = water.poll();
                int x = now[0];
                int y = now[1];

                for (int j = 0; j < 4; j++) {
                    int nx = x + mx[j];
                    int ny = y + my[j];

                    if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                        if (!wVisit[nx][ny] && map[nx][ny] == '.') {
                            wVisit[nx][ny] = true;
                            map[nx][ny] = '*';
                            water.offer(new int[]{nx, ny});
                        }
                    }
                }
            }

            size = s.size();
            for (int i = 0; i < size; i++) {
                int[] now = s.poll();
                int x = now[0];
                int y = now[1];
                int cnt = now[2];

                for (int j = 0; j < 4; j++) {
                    int nx = x + mx[j];
                    int ny = y + my[j];

                    if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                        if (map[nx][ny] == 'D') {
                            System.out.println(cnt + 1);
                            return;
                        }
                         if (!sVisit[nx][ny] && map[nx][ny] == '.') {
                            sVisit[nx][ny] = true;
                            map[nx][ny] = 'S';
                            s.offer(new int[]{nx, ny, cnt + 1});
                        }
                    }
                }
            }
        }

        System.out.println("KAKTUS");
    }
}
