import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] ladder;
    static int n, m, h;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladder = new int[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladder[a][b] = 1;
        }

        for (int i = 0; i <= 3; i++) {
            addLine(0, i);
        }

        System.out.print(-1);
    }

    static void addLine(int count, int depth) {
        if (count == depth) {
            if (check()) {
                System.out.print(count);
                System.exit(0);
            }
            return;
        }

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (ladder[i][j] == 0 && ladder[i][j - 1] == 0 && ladder[i][j + 1] == 0) {
                    ladder[i][j] = 1;
                    addLine(count + 1, depth);
                    ladder[i][j] = 0;
                }
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= n; i++) {
            int now = i;
            for (int j = 1; j <= h; j++) {
                if (ladder[j][now] == 1) {
                    now++;
                } else if (ladder[j][now - 1] == 1) {
                    now--;
                }
            }

            if (now != i) {
                return false;
            }
        }

        return true;
    }
}
