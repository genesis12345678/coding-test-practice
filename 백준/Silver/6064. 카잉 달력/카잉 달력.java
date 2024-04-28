import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int limit = lcm(m, n);

            int count = x;

            boolean flag = false;

            while (count <= limit) {
                if (count % n == y % n) {
                    flag = true;
                    break;
                }

                count += m;
            }

            System.out.println(flag ? count : -1);
        }
    }

    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}

