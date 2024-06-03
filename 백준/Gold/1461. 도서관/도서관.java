import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int total = 0;
        int max = 0;

        for (int i = 0; i < n; i += m) {
            if (arr[i] < 0) {
                total += Math.abs(arr[i] * 2);
                max = Math.max(max, Math.abs(arr[i]));
            }
        }

        for (int i = n - 1; i >= 0; i -= m) {
            if (arr[i] > 0) {
                total += arr[i] * 2;
                max = Math.max(max, arr[i]);
            }
        }

        System.out.println(total - max);
    }
}
