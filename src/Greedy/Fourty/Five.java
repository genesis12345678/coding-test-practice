package Greedy.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 오른쪽 위부터 오른쪽, 오른쪽 아래를 탐색하면서 갈 수 있는 경로를 찾는다.
 * 경로는 겹칠 수 없기 때문에 갔던 경로는 탐색하면 안 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/3109">백준 3109번 : 그리디 알고리즘 - 빵집</a>
 * @since 2024-01-05
 */
public class Five {

    static char[][] map;
    static int R;
    static int C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int count = 0;
        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) {
                count++;
            }
        }
        System.out.println(count);

    }

    private static boolean dfs(int x, int y) {
        map[x][y] = '-'; // 탐색한 경로 표시

        /**
         * 반대편까지 도달했다면 반복을 마친다.
         */
        if (y == C - 1) {
            return true;
        }

        /**
         * 오른쪽 위 탐색
         */
        if (x > 0 && map[x - 1][y + 1] == '.') {
            if (dfs(x - 1, y + 1)) {
                return true;
            }
        }

        /**
         * 오른쪽 탐색
         */
        if (map[x][y + 1] == '.') {
            if (dfs(x, y + 1)) {
                return true;
            }
        }

        /**
         * 오른쪽 아래 탐색
         */
        if (x + 1 < R && map[x + 1][y + 1] == '.') {
            if (dfs(x + 1, y + 1)) {
                return true;
            }
        }

        return false;

    }
}
