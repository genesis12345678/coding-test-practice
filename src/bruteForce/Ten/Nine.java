package bruteForce.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 재귀호출로 모든 부분 수열의 값을 확인한다.
 * 주의할 점은 S가 0일 때는 결과에 1을 뺀 값이 정답이다.
 * 왜냐하면 0은 어떤 수도 고르지 않았을 때도 경우의 수에 포함되는데, 조건에서 크기가 양수인 부분수열 중에서 구하는 것이라고 했다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1182">백준 1182번 - 브루트포스 : 부분수열의 합</a>
 * @since 2024-04-22
 */
public class Nine {

    static int n, s, count;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        count = 0;

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution(0, 0);

        if (s == 0) {
            count--;
        }

        System.out.println(count);
    }

    static void solution(int index, int sum) {
        if (index == n) {
            if (sum == s) {
                count++;
            }
            return;
        }

        solution(index + 1, sum + arr[index]);
        solution(index + 1, sum);
    }
}
