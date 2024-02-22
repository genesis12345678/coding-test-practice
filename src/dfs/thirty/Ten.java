package dfs.thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * boolean 방문 배열이 아닌 숫자로 구역을 구분한다.
 */

/**
 * <a href = "https://lahezy.tistory.com/33">백준 16724번 : 깊이우선탐색 - 피리 부는 사나이</a>
 * @since 2024-02-21
 */
public class Ten {
    static int N, M;
    static char[][] map;
    static int[][] visit;
    static int count = 0;
    static int area = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visit = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visit[i][j] == 0) {//구역이 정해지지 않은 곳
                    dfs(i, j);
                }
            }
        }
        System.out.println(count);
    }

    static void dfs(int x, int y) {
        visit[x][y] = area;//구역 표시

        int[] move = getMove(map[x][y]);
        int nx = x + move[0];
        int ny = y + move[1];

        if (visit[nx][ny] == 0) {
            dfs(nx, ny);
        } else {//싸이클이 발생했다면 현재 턴에서 나온 싸이클인지 확인한다.
            if (visit[nx][ny] == area) {
                count++;
            }
            area++;
        }
    }

    static int[] getMove(char ch) {
        switch (ch) {
            case 'U':
                return new int[]{-1, 0};
            case 'D':
                return new int[]{1, 0};
            case 'L':
                return new int[]{0, -1};
            case 'R':
                return new int[]{0, 1};
            default:
                return new int[]{0};
        }
    }
}
