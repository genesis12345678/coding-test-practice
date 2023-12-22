package Level15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] prime = new boolean[1000001];
        prime[0] = prime[1] = true;
        for (int i = 2; i <= Math.abs(Math.sqrt(prime.length)); i++) {
            if (prime[i]) continue;
            for (int j = i * i; j < 1000000; j += i) {
                prime[j] = true;
            }
        }

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int temp = Integer.parseInt(br.readLine());
            int count = 0;
            for (int i = 2; i <= temp / 2; i++) {
                if (!prime[i] && !prime[temp - i]) {
                    count++;
                }
            }
            sb.append(count).append('\n');
        }
        System.out.println(sb);
    }
}
