import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String s = br.readLine();
            if (s==null) {
                break;
            }

            int x = Integer.parseInt(s) * 10_000_000;

            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr);

            int start = 0;
            int end = n - 1;

            boolean flag = false;
            while (start < end) {
                int sum = arr[start] + arr[end];
                if (sum == x) {
                    flag = true;
                    break;
                }

                if (sum > x) {
                    end--;
                } else {
                    start++;
                }
            }

            if (flag) {
                sb.append("yes ").append(arr[start]).append(" ").append(arr[end]).append("\n");
            } else {
                sb.append("danger").append("\n");
            }

        }

        System.out.println(sb);
    }
}
