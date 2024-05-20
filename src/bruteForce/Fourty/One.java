package bruteForce.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2503">백준 2503번 - 브루트포스 : 숫자 야구</a>
 * @since 2024-05-09
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                for (int k = 1; k <= 9; k++) {
                    if (i != j && j != k && i != k) {
                        int num = i * 100 + j * 10 + k;
                        candidates.add(num);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int question = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            List<Integer> temp = new ArrayList<>();
            for (int candidate : candidates) {
                if (check(candidate, question, strike, ball)) {
                    temp.add(candidate);
                }
            }
            candidates = temp;
        }

        System.out.print(candidates.size());
    }

    private static boolean check(int candidate, int question, int strike, int ball) {
        int c1 = candidate / 100;
        int c2 = (candidate % 100) / 10;
        int c3 = candidate % 10;

        int q1 = question / 100;
        int q2 = (question % 100) / 10;
        int q3 = question % 10;

        int s = 0;
        int b = 0;

        if (c1 == q1) s++;
        if (c2 == q2) s++;
        if (c3 == q3) s++;

        if (c1 == q2 || c1 == q3) b++;
        if (c2 == q1 || c2 == q3) b++;
        if (c3 == q1 || c3 == q2) b++;

        return strike == s && ball == b;
    }
}
