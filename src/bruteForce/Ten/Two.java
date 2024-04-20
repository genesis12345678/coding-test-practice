package bruteForce.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n < 100) {
            System.out.println(n);
            return;
        }

        int count = 99;

        for (int i = 100; i <= n; i++) {

            int hundreds = i / 100;
            int tens = (i / 10) % 10;
            int ones = i % 10;

            if (hundreds - tens == tens - ones) {
                count++;
            }
        }

        System.out.println(count);
    }
}
