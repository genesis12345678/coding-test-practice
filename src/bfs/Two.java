package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 최단 경로 문제의 경우 BFS가 더 적합하다.
 * 헷갈렸던 건 x를 좌우, y를 상하로 생각해서 만들었었는데 반대였다.
 * 처음 시작 좌표부터 시작해서 1씩 더하면서 갈 수 있는 경로를 찾아간다.
 * 너비우선탐색은 갈 수 있는 경로만 찾아간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2178">백준 2178번 : 너비우선탐색 - 미로 탐색</a>
 * @since 2024-01-10
 */
public class Two {

    static int[] moveX = {-1, 1, 0, 0}; // X좌표의 움직임 == 상,하,좌,우
    static int[] moveY = {0, 0, -1, 1}; // Y좌표의 움직임 == 상,하,좌,우
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited; // 방문기록
//    static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

       N = Integer.parseInt(st.nextToken());
       M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - 48; // 문자이기 때문에 아스키 계산 48=='0'
            }
        }

        visited = new boolean[N][M]; // 시작지점
        visited[0][0] = true; // 시작은 방문

        bfs(0,0);
        System.out.println(map[N - 1][M - 1]);

    }

    /**
     * 너비우선탐색
     */
    static void bfs(int x, int y) {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{x,y});

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int nowX = now[0];
            int nowY = now[1];

            // 상,하,좌,우 탐색으로 4번 반복한다.
            for (int i = 0; i < 4; i++) {
                int nextX = nowX + moveX[i]; // 다음 x의 위치
                int nextY = nowY + moveY[i]; // 다음 y의 위치

                // 이동한 좌표의 위치가 범위 안에 있어야 한다.
                // 이동한 좌표의 값이 0이거나 방문했었다면 건너뛴다.
                if (isOk(nextY, nextX) || map[nextX][nextY] == 0 || visited[nextX][nextY]) {
                    continue;
                }

                // 너비우선이기 때문에 큐에 넣어준다.
                qu.add(new int[]{nextX, nextY});
                // 마지막에 위치한 값이 답이 되기 때문에 더하면서 이동해준다.
                map[nextX][nextY] = map[nowX][nowY] + 1;
                // 방문기록 저장
                visited[nextX][nextY] = true;
            }
        }
    }
    private static boolean isOk(int nextY, int nextX) {
        return nextY < 0 || nextX < 0 || nextX >= N || nextY >= M;
    }

//    static void dfs(int x, int y, int distance) {
//        if (x == N - 1 && y == M - 1) {
//            // 도착 지점에 도달한 경우 최소 경로 갱신
//            minDistance = Math.min(minDistance, distance);
//            return;
//        }
//
//        for (int i = 0; i < 4; i++) {
//            int nextX = x + moveX[i];
//            int nextY = y + moveY[i];
//
//            if (isValid(nextX, nextY) && map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
//                visited[nextX][nextY] = true;
//                dfs(nextX, nextY, distance + 1);
//                visited[nextX][nextY] = false; // 백트래킹
//            }
//        }
//    }
//
//    static boolean isValid(int x, int y) {
//        return x >= 0 && x < N && y >= 0 && y < M;
//    }


}
