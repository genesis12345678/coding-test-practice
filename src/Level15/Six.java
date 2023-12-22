package Level15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Six {
    public static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            BigInteger N = new BigInteger(br.readLine());
            if (N.isProbablePrime(10)) {
                sb.append(N).append('\n');
            } else {
                sb.append(N.nextProbablePrime()).append('\n');
            }
        }
        System.out.println(sb);

    }
}
