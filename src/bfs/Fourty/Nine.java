package bfs.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 일단 외부공기와 내부공기를 구분해야 한다. 처음(0,0)부터 시작해서 bfs탐색으로 바깥쪽만 2로 표시한다. (2 = 외부 공기)
 * 이후 2변 이상이 외부 공기(2)와 접촉한 곳을 제거한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2638">백준 2638번 : 너비우선탐색 - 치즈</a>
 * @since 2024-02-02
 */
public class Nine {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int cheese = 0;
    static List<int[]> cheesePos = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    cheese++;
                    cheesePos.add(new int[]{i, j});//치즈 위치 저장
                }
            }
        }

        int count = 0;
        while (cheese > 0) {
            count++;
            visit = new boolean[N][M];
            bfs();
            melting();
        }
        System.out.println(count);
    }

    static void bfs() {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{0, 0});
        visit[0][0] = true;
        map[0][0] = 2;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + mx[i];
                int ny = y + my[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (!visit[nx][ny] && map[nx][ny] != 1) {
                        map[nx][ny] = 2;
                        visit[nx][ny] = true;
                        qu.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
    static void melting() {
        for (int i = 0; i < cheesePos.size(); i++) {
            int[] now = cheesePos.get(i);
            int x = now[0];
            int y = now[1];

            int cnt = 0;
            for (int j = 0; j < 4; j++) {
                int nx = x + mx[j];
                int ny = y + my[j];
                if (map[nx][ny] == 2) {
                    cnt++;
                }
            }
            if (cnt >= 2) {
                map[x][y] = 0;
                cheese--;
                cheesePos.remove(i--);
            }
        }
    }


}
