import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int[][] rotate;
    static int n, m, k;
    static boolean[] visit;
    static int[] select;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n + 1][m + 1];
        rotate = new int[k][3];
        visit = new boolean[k];
        select = new int[k];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            rotate[i][0] = Integer.parseInt(st.nextToken());
            rotate[i][1] = Integer.parseInt(st.nextToken());
            rotate[i][2] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        System.out.println(min);
    }

    static void dfs(int depth) {
        if (depth == k) {
            check();
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!visit[i]) {
                visit[i] = true;
                select[depth] = i;
                dfs(depth + 1);
                visit[i] = false;
            }

        }
    }

    static void check() {
        int[][] map = copy();

        for (int i = 0; i < k; i++) {
            int r = rotate[select[i]][0]; //3
            int c = rotate[select[i]][1]; //4
            int s = rotate[select[i]][2]; //2

            for (int j = s; j >= 1; j--) { //S

                int u_temp = map[r - j][c + j];
                for (int x = c + j; x > c - j; x--) {
                    map[r - j][x] = map[r - j][x - 1];
                }

                int l_temp = map[r + j][c + j];
                for (int y = r + j; y > r - j; y--) {
                    map[y][c + j] = map[y - 1][c + j];
                }

                map[r - j + 1][c + j] = u_temp;

                int d_temp = map[r + j][c - j];
                for (int x = c - j; x < c + j; x++) {
                    map[r + j][x] = map[r + j][x + 1];
                }

                map[r + j][c + j - 1] = l_temp;
                for (int y = r - j; y < r + j; y++) {
                    map[y][c - j] = map[y + 1][c - j];
                }

                map[r + j - 1][c - j] = d_temp;
            }
        }

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= m; j++) {
                sum += map[i][j];
            }
            min = Math.min(min, sum);
        }
    }

    private static int[][] copy() {
        int[][] temp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
           System.arraycopy(arr[i], 1, temp[i], 1, m);
        }

        return temp;
    }

}
