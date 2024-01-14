package bfs.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 아이디어
 * 최단 경로가 아닌 1로 이루어진 범위를 구해야 하기 때문에 bfs보단 dfs가 더 적합하다.
 * 미로탐색 문제랑 비슷하게 상하좌우를 탐색하면서 재귀호출 방식으로 범위를 찾아 나간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2667">백준 2667번 : 너비우선탐색 - 단지번호붙이기</a>
 * @since 2024-01-11
 */
public class Four {

    static int[][] map;
    static boolean[][] visit;
    static int[] moveX = {-1, 1, 0, 0}; // X좌표의 움직임 == 상,하,좌,우
    static int[] moveY = {0, 0, -1, 1}; // Y좌표의 움직임 == 상,하,좌,우
    static int count; // 집 개수
    static int apart; // 단지 개수
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>(); // 단지 당 집 개수를 저장할 배열

        map = new int[N][N];
        visit = new boolean[N][N];

        // 입력
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - 48;
            }
        }

        // dfs 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 & !visit[i][j]) {
                    count = 0;
                    apart++;
                    dfs(i, j);
                    list.add(count);
                }
            }
        }

        sb.append(apart).append("\n"); // 단지수

        Collections.sort(list);
        for (Integer num : list) {
            sb.append(num).append("\n");
        }

        System.out.println(sb);
    }

    // dfs 알고리즘 시작
    static void dfs(int x, int y) {
        visit[x][y] = true;
        count++;

        for (int i = 0; i < 4; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];

            if (rangeCheck(nextX, nextY) && !visit[nextX][nextY] && map[nextX][nextY] == 1) {
                visit[nextX][nextY] = true;
                dfs(nextX, nextY);
            }
        }
    }

    // 범위 체크
    private static boolean rangeCheck(int nextX, int nextY) {
        return nextX >= 0 && nextY >= 0 && nextX < N && nextY < N;
    }
}
