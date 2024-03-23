package DP.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 없애야 하는 줄의 최소 개수는 전체 줄 개수 - 없앴을 때 남아 있는 줄의 개수와 같다.
 * 선이 교차하지 않으려면 B 쪽에서 증가하는 부분 수열만 남기면 된다.
 * 즉, 1번부터 순서대로 A에 연결되어 있는 B의 줄 번호로 수열을 만든 후
 * 전체 줄 개수에서 가장 긴 증가하는 부분 수열의 길이를 빼면 된다.
 * 그럴려면 A의 순서를 정렬해줘야 하는데, 입력은 뒤죽박죽 오기 때문에 먼저 정렬을 해주어야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2565">백준 2565번 - DP : 전깃줄</a>
 * @since 2024-03-23
 */
public class Nine {

    static class Line { //전깃줄 정보 객체
        int A, B;

        public Line(int a, int b) {
            A = a;
            B = b;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Line[] lines = new Line[n + 1];
        lines[0] = new Line(0, 0);

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lines[i] = new Line(a, b); //전깃줄 정보 저장
        }

        Arrays.sort(lines, Comparator.comparingInt(o -> o.A)); //A 오름차순 정렬

        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = lines[i].B; //배열에 B 정보 저장
            dp[i] = 1; //초기에 가장 긴 증가하는 부분 수열은 자기 자신 하나로 초기화할 수 있다.
        }

        int max = dp[1];

        //가장 긴 증가하는 부분 수열 구하기
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(n - max); //전체 줄 개수에서 증가하는 부분 수열을 뺀 값이 정답
    }
}
