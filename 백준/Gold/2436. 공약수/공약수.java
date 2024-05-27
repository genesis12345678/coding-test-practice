import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        long multiple = (long) a * b;

        int r1 = 0, r2 = 0;

        for (int i = a; i <= Math.sqrt(multiple); i += a) {
            if (multiple % i == 0 && gcd(i, multiple / i) == a) {

                r1 = i;
                r2 = (int) (multiple / i);
            }
        }

        System.out.println(r1 + " " + r2);
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }else return gcd(b, a % b);
    }
}
