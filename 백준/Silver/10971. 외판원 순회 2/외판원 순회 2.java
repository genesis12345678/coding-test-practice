import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n;
    static boolean[] visit;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0,1);

        System.out.println(min);

    }

    static void dfs(int depth, int sum, int count) {
        if (count == n) {
            if (map[depth][0] != 0) {
                min = Math.min(min, sum + map[depth][0]);
            }
            return;
        }

        for (int i = 1; i < n; i++) {
            if (!visit[i] && map[depth][i] != 0) {
                visit[i] = true;
                dfs(i, sum + map[depth][i], count + 1);
                visit[i] = false;
            }
        }
    }
}
