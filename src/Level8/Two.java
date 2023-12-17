package Level8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        while (N > 0) {
            System.out.println(N / B);
            System.out.println(N % B);
            if (N % B >= 10) {
                sb.append((char) ((N % B) + 55));
            }else {
                sb.append(N % B);
            }
            N /= B;
        }
        System.out.println(sb.reverse());
    }
}
