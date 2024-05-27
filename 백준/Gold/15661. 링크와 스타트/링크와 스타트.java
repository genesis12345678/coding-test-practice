import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[] visit;
    static int n;
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

        for (int i = 1; i < n; i++) {
            divide(0, 0, i);
        }

        System.out.print(min);
    }

    static void divide(int depth, int start, int t) {
        if (depth == t) {
            min = Math.min(min, calculate());
            if (min == 0) {
                System.out.println(0);
                System.exit(0);
            }
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                divide(depth + 1, i + 1, t);
                visit[i] = false;
            }
        }
    }

    static int calculate() {

        int startSum = 0;
        int linkSum = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (visit[i] && visit[j]) { 
                    startSum += map[i][j] + map[j][i];
                }
                else if (!visit[i] && !visit[j]) { 
                    linkSum += map[i][j] + map[j][i];
                }
            }
        }

        return Math.abs(startSum - linkSum);
    }
}

