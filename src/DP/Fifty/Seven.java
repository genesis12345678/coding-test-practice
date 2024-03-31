package DP.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 아이디어
 * 일반적인 피보나치 수 문제랑 같지만 주의해야 할 게 있다.
 * n의 최댓값이 10,000인데 10,000번째의 피보나치 값은 long 으로도 감당이 안 되는 매우 큰 값이다.
 * 그래서 dp 배열도 long 말고 무한대의 숫자를 보관할 수 있는 BigInteger를 사용한다.
 * dp[n] = dp[n-1] + dp[n-2]
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10826">백준 10826번 - DP : 피보나치 수 4</a>
 * @since 2024-03-31
 */
public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 0) {
            System.out.println(0);
        } else if (n == 1) {
            System.out.println(1);
        } else {
            BigInteger[] dp = new BigInteger[n + 1];

            dp[0] = BigInteger.ZERO;
            dp[1] = BigInteger.ONE;

            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1].add(dp[i - 2]);
            }

            System.out.println(dp[n]);

        }
    }
}
