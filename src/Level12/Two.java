package Level12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int length = str.length();
        int N = Integer.parseInt(str);
        int result = 0;
        for (int i = N-(length*9); i < N; i++) {
            int n = i;
            int sum = 0;

            while (n != 0) {
                sum += n % 10;
                n /= 10;
            }

            if (sum + i == N) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }
}
