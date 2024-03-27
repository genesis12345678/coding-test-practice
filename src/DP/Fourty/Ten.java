package DP.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 아이디어
 * dp[i]를 i번째까지 가능한 경우의 수라고 생각해본다.
 * dp[1] = 1이다.
 * 2번째, 즉 2글자 부터는 경우의 수를 고려해야 한다.
 * 2글자가 26 이하면 경우의 수가 추가되고, 26을 넘어서면 추가되지 않는다.
 * dp 점화식
 * 한 글자씩 읽었다고 했을 때 -> dp[i] = (dp[i] + dp[i-1])
 * 이후 앞에 두 글자를 1~26인지 판단해 범위 내라면 경우의 수를 추가한다.
 * dp[i] = (dp[i] + dp[i-2])
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2011">백준 2011번 - DP : 암호코드</a>
 * @since 2024-03-27
 */
public class Ten {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        if (arr[0] == '0') { //첫 숫자가 0이면 잘못된 암호다.
            System.out.println(0);
        } else {
            int[] dp = new int[5001];
            dp[0] = dp[1] = 1;

            for (int i = 2; i <= arr.length; i++) {
                //한 글자씩 읽었을 경우
                if (arr[i - 1] != '0') { //암호에 0이 하나라도 있으면 잘못된 암호이기 때문에 중간에 0이 있다면 최종 출력에도 0이 나온다.
                    dp[i] = (dp[i] + dp[i-1]) % 1_000_000;
                }

                //두 글자씩 읽었을 경우
                //앞 두 글자가 알파벳을 표현할 수 있는 범위면 경우의 수에 추가한다.
                int temp = (arr[i - 2] - '0') * 10 + (arr[i - 1] - '0');

                if (temp >= 10 && temp <= 26) {
                    dp[i] = (dp[i] + dp[i - 2]) % 1_000_000;
                }
            }

            System.out.println(dp[arr.length]);

        }
    }
}
