package bruteForce.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/18428">백준 18428번 - 브루트포스 : 감시 피하기</a>
 * @since 2024-05-19
 */
public class Six {

    static int n;
    static char[][] map;
    static ArrayList<int[]> T = new ArrayList<>();
    static ArrayList<int[]> X = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);

                if (map[i][j] == 'T') {
                    T.add(new int[]{i, j});

                } else if (map[i][j] == 'X') {
                    X.add(new int[]{i, j});
                }
            }
        }

        if (install(0, 0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    static boolean install(int depth, int start) {
        if (depth == 3) {
            return check();
        }

        for (int i = start; i < X.size(); i++) {
            int[] now = X.get(i);
            int x = now[0];
            int y = now[1];

            map[x][y] = 'O';

            if (install(depth + 1, i + 1)) {
                return true;
            }

            map[x][y] = 'X';
        }

        return false;
    }

    static boolean check() {
        for (int[] t : T) {
            for (int i = 0; i < 4; i++) {
                if (watch(t[0], t[1], i)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean watch(int x, int y, int dir) {
        x += dx[dir];
        y += dy[dir];

        while (x >= 0 && y >= 0 && x < n && y < n) {
            if (map[x][y] == 'O') {
                break;
            }
            if (map[x][y] == 'S') {
                return true;
            }

            x += dx[dir];
            y += dy[dir];
        }
        return false;
    }
}
