package Level15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Eight {
    public static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            prime = new boolean[2 * N + 1];
            getPrime();
            int count = 0;
            for (int i = N + 1; i <= 2 * N; i++) {
                if (!prime[i]) {
                    count++;
                }
            }
            sb.append(count).append('\n');
        }
        System.out.println(sb);
    }

    public static void getPrime() {

        for (int i = 2; i <= Math.abs(Math.sqrt(prime.length)) ; i++) {
            if(prime[i]) continue;
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }
}
