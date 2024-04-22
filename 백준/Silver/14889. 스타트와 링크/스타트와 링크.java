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

        divide(0, 0);
        System.out.println(min);
    }

    static void divide(int depth, int start) {
        if (depth == n / 2) {
            min = Math.min(min, calculate());
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                divide(depth + 1, i + 1);
                visit[i] = false;
            }
        }
    }

    private static int calculate() {

        int[] startTeam = new int[n / 2];
        int[] linkTeam = new int[n / 2];

        int startIdx = 0;
        int linkIdx = 0;

        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                startTeam[startIdx++] = i;
            } else {
                linkTeam[linkIdx++] = i;
            }
        }

        int startSum = 0;
        int linkSum = 0;

        for (int i = 0; i < n / 2; i++) {
            for (int j = i + 1; j < n / 2; j++) {
                startSum += map[startTeam[i]][startTeam[j]] + map[startTeam[j]][startTeam[i]];
                linkSum += map[linkTeam[i]][linkTeam[j]] + map[linkTeam[j]][linkTeam[i]];
            }
        }

        return Math.abs(startSum - linkSum);
    }
}
