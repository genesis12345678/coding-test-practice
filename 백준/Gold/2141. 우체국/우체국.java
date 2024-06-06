import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            sum += a;
            arr[i][0] = x;
            arr[i][1] = a;
        }
        
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        long now = 0;
        for (int i = 0; i < n; i++) {
            now += arr[i][1];
            if (now >= (sum + 1) / 2) {
                System.out.println(arr[i][0]);
                return;
            }
        }

    }
}
