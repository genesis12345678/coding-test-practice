package Level17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        br.close();

        int a , b, c;

        a = 1;
        for (int i = 1; i <= N; i++) {
            a *= i;
        }

        b = 1;
        for (int i = 1; i <= N - K; i++) {
            b *= i;
        }

        c = 1;
        for (int i = 1; i <= K; i++) {
            c *= i;
        }

        System.out.println(a / (b * c));

    }
}
