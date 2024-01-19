package bfs.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * bfs를 수행하면서 사용할 큐를 우선순위 큐를 사용한다.(왼쪽 위부터 탐색할 수 있도록)
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16236">백준 16236번 : 너비우선탐색 - 아기 상어</a>
 * @since 2024-01-19
 */
public class Ten {
    static int[][] map;
    static int[] mx = new int[]{-1, 1, 0, 0};
    static int[] my = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        int[] arr = null;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) { // 첫 아기 상어 위치부터 시작
                    map[i][j] = 0;
                    arr = new int[]{i, j};
                }
            }
        }

        int size = 2; // 처음 아기 상어 크기
        int eat = 0; // 먹은 물고기 수 -> 먹은 물고기 수 = 크기가 되면 크기가 증가한다.
        int move = 0; // 이동거리

        while (true) {
            // 우선순위 큐({x, y, 이동거리})
            Queue<int[]> qu = new PriorityQueue<>((o1, o2) -> {
                if (o1[2] != o2[2]) { // 이동 거리 오름차순
                    return Integer.compare(o1[2], o2[2]);
                } else if (o1[0] != o2[0]) { // 이동 거리가 같다면 x 오름차순(상하)
                    return Integer.compare(o1[0], o2[0]);
                } else { // 이동 거리와 x까지 같다면 y 오름차순(좌우)
                    return Integer.compare(o1[1], o2[1]);
                }
            });

            // 물고기를 먹고 그 위치에서 다시 탐색할 때 방문 배열 초기화
            boolean[][] visit = new boolean[n][n];
            // 시작 위치
            qu.offer(new int[]{arr[0], arr[1], 0});
            visit[arr[0]][arr[1]] = true;

            boolean check = false; // 물고기를 먹고 while문을 끝내기 위함

            while (!qu.isEmpty()) {
                int[] now = qu.poll();

                // 아기 상어 size보다 작은 물고기를 만났을 때
                if (map[now[0]][now[1]] != 0 && map[now[0]][now[1]] < size) {
                    map[now[0]][now[1]] = 0; // 물고기 제거
                    eat++; // 잡아먹은 횟수
                    move += now[2]; // 그간의 이동거리 증가
                    arr[0] = now[0]; // 다시 탐색할 때 물고기 위치부터 탐색해야 하기 때문에
                    arr[1] = now[1]; // 아기 상어의 위치를 업데이트 해준다.
                    check = true;
                    break; // 탐색 종료
                }

                // 상하좌우를 탐색하면서 지나갈 수 있는 칸을 큐에 저장한다.
                // 우선순위 큐이기 때문에 위치에 따라 정렬이 된다.
                for (int i = 0; i < 4; i++) {
                    int nx = now[0] + mx[i];
                    int ny = now[1] + my[i];

                    if (ny >= 0 && nx >= 0 && nx < n && ny < n) {
                        if (!visit[nx][ny] && map[nx][ny] <= size) {
                            qu.offer(new int[]{nx, ny, now[2] + 1});
                            visit[nx][ny] = true;
                        }
                    }
                }
            }
            if(!check) break; // 탐색을 마칠 때까지 물고기를 먹지 못했다.== 먹을 물고기가 없다 == 탐색 종료

            // 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가한다.
            if (size == eat) {
                size++;
                eat = 0;
            }
        }
        System.out.println(move);
    }
}
