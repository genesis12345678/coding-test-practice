package Level18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1037">백준 1037번 : 심화2 - 약수</a>
 * @since 2023-12-25
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * min과 max를 각각 반대로 최대값과 최소값을 초기화한다.
         * 반대로 하는 이유는 처음 수를 비교할 때 어떤 값이든
         * 최대/최소로 간주되어 업데이트가 보장된다.
         */
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        /**
         * while(N-- > 0) : 반복이 끝날때마다 N--를 한다.
         * 즉, N번 만큼 반복한다.
         */
        while (N-- > 0) {
            int x = Integer.parseInt(st.nextToken());
            min = Math.min(x, min);
            max = Math.max(x, max);
        }

        // 약수가 주어졌을 때 최대값과 최소값을 곱하면 어떤 수의 약수인지 알 수 있다.
        System.out.println(min * max);

    }
}
