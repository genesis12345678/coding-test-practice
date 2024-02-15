package dfs.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * I를 시작으로 상하좌우 dfs 탐색하면서 사람(P)을 만나면 그 위치를 리스트에 저장한다.
 * 리스트가 비어 있으면 TT, 그렇지 않으면 저장된 수를 출력한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/21736">백준 21736번 : 깊이우선탐색 - 헌내기는 친구가 필요해</a>
 * @since 2024-02-15
 */

public class Eight {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][] visit;
    static int N, M;
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visit = new boolean[N][M];

        int x = 601, y = 601;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'I') {
                    x = i;
                    y = j;
                }
            }
        }

        dfs(x, y);

        if (list.isEmpty()) {
            System.out.println("TT");
        } else {
            System.out.println(list.size());
        }
    }

    static void dfs(int x, int y) {
        if (map[x][y] == 'P') {
            list.add(new int[]{x, y});
        }
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (map[nx][ny] != 'X' && !visit[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }
    }
}