package dfs.thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 시계 방향으로 90도 회전하는 부분과 얼음의 양을 줄어드는 메서드를 적절하게 나누어 진행한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/20058">백준 20058번 : 깊이우선탐색 - 마법사 상어와 파이어스톰</a>
 * @since 2024-02-18
 */
public class Three {

    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        int Q = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visit = new boolean[N][N];

        //초기 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        //Q번 파이어스톰 시전
        for (int i = 0; i < Q; i++) {
            int L = Integer.parseInt(st.nextToken());
            rotateMap(L);//시계 방향으로 90도 회전하는 메서드, 단계 L을 파라미터로 넘긴다.
            mapUpdate();//얼음이 있는 칸 3개 이상 인접 해있지 않으면 얼음의 양을 1 줄게하는 메서드
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count += map[i][j];//남아있는 얼음의 합 계산
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && map[i][j] > 0) {
                    max = Math.max(max, dfs(i, j));//남아있는 얼음 중 가장 큰 덩어리 계산
                }
            }
        }

        //덩어리가 없으면 0을 출력해야 한다.
        System.out.print(count + "\n" + (max == Integer.MIN_VALUE ? 0 : max));
    }

    /**
     * 남아있는 얼음 중 가장 큰 덩어리를 차지하는 칸의 개수를 계산하는 메서드
     */
    static int dfs(int x, int y) {
        visit[x][y] = true;

        int size = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (!visit[nx][ny] && map[nx][ny] > 0) {
                    size += dfs(nx, ny);
                }
            }
        }
        return size;
    }

    /**
     * 얼음이 있는 칸 3개 이상 인접해있지 않은 칸 얼음의 양 1 줄어드는 메서드
     */
    private static void mapUpdate() {
        int[][] temp = new int[N][N];//변경될 map을 갖고 있을 임시 배열

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {//얼음이 있는 칸만 실행
                    int count = 0;
                    for (int k = 0; k < 4; k++) {//상하좌우에 얼음이 있으면 count 증가
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                            if (map[nx][ny] > 0) {
                                count++;
                            }
                        }
                    }
                    if (count < 3) {//3칸 이상 인접해 있지 않으면 -1
                        temp[i][j] = map[i][j] - 1;
                    } else {//3칸 이상 인접해 있으면 그대로
                        temp[i][j] = map[i][j];
                    }
                }
            }
        }
        map = temp;//map 업데이트
    }

    /**
     * 2^L x 2^L 크기로 나눈 격자를 회전하는 메서드
     */
    private static void rotateMap(int l) {
        int size = (int) Math.pow(2, l);
        for (int i = 0; i < N; i += size) {
            for (int j = 0; j < N; j += size) {
                rotate(i, j, size);
            }
        }
    }

    private static void rotate(int x, int y, int size) {
        int[][] temp = new int[size][size];//회전하기 전의 부분 격자를 임시로 저장하는 배열

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[i][j] = map[x + i][y + j];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[x + i][y + j] = temp[size - 1 - j][i];
            }
        }
    }
}
