package Level15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int gcd = gcd(a, b);
        sb.append(gcd).append("\n").append(a * b / gcd);
        System.out.println(sb);
    }

    /**
     * 유클리드 호제법
     * a를 b로 나눈 나머지를 r이라고 했을 때,
     * GCD(a, b) = GCD(b, r)과 같다.
     * r이 0이면 그때 b가 최대공약수이다.
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
