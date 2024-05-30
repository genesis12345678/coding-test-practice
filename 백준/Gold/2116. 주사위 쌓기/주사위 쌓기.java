import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dice;
    static int n;
    static int max = 0;
    static int[] arr = {5, 3, 4, 1, 2, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dice = new int[n][6];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 6; i++) {
            int max = 0;
            for (int j = 0; j < 6; j++) {
                if (i != j && j != arr[i]) {
                    max = Math.max(max, dice[0][j]);
                }
            }

            dfs(dice[0][i], max, 1);
        }

        System.out.println(max);
    }

    private static void dfs(int prev, int sum, int depth) {
        if (depth == n) {
            max = Math.max(max, sum);
            return;
        }

        int top_index = 0;
        for (int i = 0; i < 6; i++) {
            if (dice[depth][i] == prev) {
                top_index = i;
                break;
            }
        }

        int next = arr[top_index];
        int max = 0;
        for (int i = 0; i < 6; i++) {
            if (i != top_index && i != next) {
                max = Math.max(max, dice[depth][i]);
            }
        }

        dfs(dice[depth][next], sum + max, depth + 1);
    }
}
