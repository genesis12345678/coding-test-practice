package bfs.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * bfs로 상하좌우 움직이면서 구슬의 위치와 이동 횟수를 큐에 저장한다.
 * 구슬이 두 개 이기에 4차원 배열을 사용한다.
 * 방문처리를 해주지 않아도 통과는 된다. 하지만 시간이 오래 걸린다. 왜냐하면 기울여서 한 번 갔던 곳을 또 갈 필요는 없는데 한번 더 가게 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/13460">백준 13460번 : 너비우선탐색 - 구슬 탈출2</a>
 * @since 2024-01-23
 */
public class Five {

    static int N, M;
    static char[][] map;
    static boolean[][][][] visit;
    static int[] mx = new int[]{-1, 1, 0, 0};
    static int[] my = new int[]{0, 0, -1, 1};
    static int[] red, blue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visit = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'B') { // 파란 구슬 위치 저장
                    blue = new int[]{i, j};
                } else if (map[i][j] == 'R') { // 빨간 구슬 위치 저장
                    red = new int[]{i, j};
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> qu = new LinkedList<>();
        int rx = red[0]; // 빨간 구슬 x좌표
        int ry = red[1]; // 빨간 구슬 y좌표
        int bx = blue[0]; // 파란 구슬 x좌표
        int by = blue[1]; // 파란 구슬 y좌표

        // 시작
        qu.add(new int[]{rx, ry, bx, by, 0});

        visit[rx][ry][bx][by] = true;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int cnt = now[4];// 이동횟수

            if (cnt >= 10) { // 10번이 넘어가면 -1 출력
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int nowRx = now[0];
                int nowRy = now[1];
                int nowBx = now[2];
                int nowBy = now[3];

                // 빨간 구슬을 벽에 닿을 때까지 이동한다.
                // 구멍을 만나면 break
                while (map[nowRx + mx[i]][nowRy + my[i]] != '#') {
                    nowRx += mx[i];
                    nowRy += my[i];
                    if (map[nowRx][nowRy] == 'O') {
                        break;
                    }
                }

                // 파란 구슬을 벽에 닿을 때까지 이동한다.
                // 구멍을 만나면 break
                while (map[nowBx + mx[i]][nowBy + my[i]] != '#') {
                    nowBx += mx[i];
                    nowBy += my[i];
                    if (map[nowBx][nowBy] == 'O') {
                        break;
                    }
                }

                // 파란 구슬이 구멍을 만나면 이 방향은 탐색 종료한다.
                // 다른 방향으로 했을 때 빨간 구슬이 구멍을 만날 수도 있다.
                if (map[nowBx][nowBy] == 'O') {
                    continue;
                }
                // 빨간 구슬이 구멍을 만나면 현재 이동 횟수에 방금 이동한 거를 포함해서 return한다.
                if (map[nowRx][nowRy] == 'O') {
                    return ++cnt;
                }

                /**
                 * 벽에 닿을 때까지 이동을 했는데 빨간 구슬과 파란 구슬의 위치가 같다면 먼저 온 구슬이 우선이다.(이동 횟수가 적은)
                 * 근데 예제 5를 보면 둘 다 한칸 이동 후 구멍을 만나도 1이 출력되므로 빨간 구슬이 더 우선권을 가지는 듯 하다.
                 * 즉, 이동 횟수가 같고 같은 위치에서 만난다면 빨간 구슬이 우선이다.
                 */
                if (nowRx == nowBx && nowRy == nowBy) { // 두 구슬의 위치가 같고
                    if (map[nowRx][nowRy] != 'O') { // 빨간 구슬의 위치가 구멍이 아니다.

                        // 빨간 구슬 이동거리 = (이동된 x좌표 - 처음 x좌표) + (이동된 y좌표 - 처음 y좌표)
                        int redMove = Math.abs(nowRx - now[0]) + Math.abs(nowRy - now[1]);

                        // 파란 구슬 이동거리 = (x좌표 움직임 - 처음 x좌표) + (y좌표 움직임 - 처음 y좌표)
                        int blueMove = Math.abs(nowBx - now[2]) + Math.abs(nowBy - now[3]);

                        // 파란 구슬이 먼저 도착한 경우
                        // 빨간 구슬을 현재 이동 방향의 반대 방향으로 한 칸 이동
                        if (redMove > blueMove) {
                            nowRx -= mx[i];
                            nowRy -= my[i];
                        // 빨간 구슬이 먼저 도착하거나 같은 경우, 빨간 구슬이 더 우선권을 가진다.
                        // 파란 구슬을 현재 이동 방향의 반대 방향으로 한 칸 이동
                        } else {
                            nowBx -= mx[i];
                            nowBy -= my[i];
                        }
                    }
                }
                // 방문 처리를 해주지 않아도 통과는 되지만 시간이 오래 걸린다.
                if (!visit[nowRx][nowRy][nowBx][nowBy]) {
                    visit[nowRx][nowRy][nowBx][nowBy] = true;
                    qu.offer(new int[]{nowRx, nowRy, nowBx, nowBy, cnt + 1});
                }
            }
        }
        return -1;
    }
}
