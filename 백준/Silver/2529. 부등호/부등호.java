import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int k;
    static char[] sign;
    static boolean[] visit = new boolean[10];

    static long min = Long.MAX_VALUE;
    static long max = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        sign = new char[k];

        for (int i = 0; i < k; i++) {
            sign[i] = st.nextToken().charAt(0);
        }

        backTrack(0, "");

        System.out.println(max);
        if (String.valueOf(min).length() == k) {
            System.out.println(0 + "" + min);
        } else {
            System.out.println(min);

        }
    }

    static void backTrack(int depth, String num) {
        if (depth == k + 1) {
            long n = Long.parseLong(num);
            min = Math.min(min, n);
            max = Math.max(max, n);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!visit[i]) {
                if (depth == 0 || compare(num.charAt(depth - 1) - '0', i, sign[depth - 1])) {
                    visit[i] = true;
                    backTrack(depth + 1, num + i);
                    visit[i] = false;
                }
            }
        }
    }

    static boolean compare(int a, int b, char c) {
        switch (c) {
            case '<':
                return a < b;
            default:
                return a > b;
        }
    }
}
