package bruteForce.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/4375">백준 4375번 - 브루트포스 : 1</a>
 * @since 2024-05-13
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String s = br.readLine();
            if (s == null || s.isEmpty()) {
                break;
            }

            int n = Integer.parseInt(s);

            sb.append(check(n)).append("\n");
        }

        System.out.print(sb);
    }

    private static int check(int n) {

        long num = 1;
        int len = 1;

        while (num % n != 0) {
            num = (num * 10 + 1) % n;
            len++;
        }

        return len;
    }
}
