import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i][0] = Math.min(x, y);
            arr[i][1] = Math.max(x, y);
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }

            return o1[0] - o2[0];
        });

        int result = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i][0] >= arr[j][0] && arr[i][1] >= arr[j][1] && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }
}
