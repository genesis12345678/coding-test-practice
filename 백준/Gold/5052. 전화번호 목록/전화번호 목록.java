import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            String[] numbers = new String[n];
            for (int j = 0; j < n; j++) {
                numbers[j] = br.readLine();
            }

            Arrays.sort(numbers);

            boolean flag = false;

            for (int j = 0; j < n - 1; j++) {
                if (numbers[j + 1].startsWith(numbers[j])) {
                    flag = true;
                    break;
                }
            }

            System.out.println(flag ? "NO" : "YES");
        }
    }
}
