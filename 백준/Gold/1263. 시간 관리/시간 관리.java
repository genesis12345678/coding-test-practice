import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            arr[i][0] = t;
            arr[i][1] = s;
        }

        Arrays.sort(arr, (o1, o2) -> o1[1] - o2[1]);

        int time = arr[n - 1][1] - arr[n - 1][0];
        for (int i = n - 2; i >= 0; i--) {
            time = Math.min(time, arr[i][1]);
            time -= arr[i][0];
        }

        System.out.println(time < 0 ? -1 : time);
    }
}
