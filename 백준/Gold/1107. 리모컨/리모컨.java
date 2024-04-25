import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[] broken = new boolean[10];
    static int n, m;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        if (n == 100) {
            System.out.println(0);
            return;
        }

        min = Math.min(min, Math.abs(n - 100));

        dfs(0, 0);

        System.out.print(min);
    }

    static void dfs(int depth, int num) {

        for (int i = 0; i < 10; i++) {
            if (!broken[i]) {

                int channel = num * 10 + i;
                int count = Math.abs(n - channel) + String.valueOf(channel).length();
                min = Math.min(min, count);

                if (depth < 6) {
                    dfs(depth + 1, channel);
                }
            }
        }
    }
}
