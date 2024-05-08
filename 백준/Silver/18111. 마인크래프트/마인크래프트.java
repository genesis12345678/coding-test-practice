import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        int lowest = Integer.MAX_VALUE;
        int highest = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                lowest = Math.min(lowest, map[i][j]);
                highest = Math.max(highest, map[i][j]);
            }
        }

        int minTime = Integer.MAX_VALUE;
        int height = 0;

        for (int h = lowest; h <= highest; h++) {
            int toFill = 0;
            int toRemove = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int diff = map[i][j] - h;

                    if (diff > 0) {
                        toRemove += diff;
                    } else {
                        toFill -= diff;
                    }
                }
            }

            if (b + toRemove >= toFill) {

                int time = toRemove * 2 + toFill;

                if (minTime >= time) {
                    minTime = time;
                    height = h;
                }
            }
        }

        System.out.print(minTime + " " + height);
    }
}
