import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int max = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    visit[i][j] = true;
                    dfs(i, j, 1, map[i][j]);
                    visit[i][j] = false;

                    dfs(0, i, j, 0, map[i][j]);
                }
            }
        }

        System.out.print(max);

    }

    static void dfs(int start, int x, int y, int depth, int sum) {
        if (depth == 3) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = start; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                dfs(i + 1, x, y, depth + 1, sum + map[nx][ny]);
            }
        }
    }

    static void dfs(int x, int y, int depth, int sum) {

        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visit[nx][ny]) {
                visit[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + map[nx][ny]);
                visit[nx][ny] = false;
            }
        }
    }
}
