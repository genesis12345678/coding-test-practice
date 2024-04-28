package bruteForce.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 배열의 순서를 모든 경우의 수에 대해 구해야 한다.
 * 재귀 호출에서 현재 depth를 인덱스로 두어 모든 경우의 수를 찾아본다.
 * depth가 배열 길이만큼 찼다면 인접한 원소의 차이를 절댓값으로 구해 합을 구한 다음 최댓값을 갱신한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10819">백준 10819번 - 브루트포스 : 차이를 최대로</a>
 * @since 2024-04-26
 */
public class Six {

    static int[] arr;
    static boolean[] visit;
    static int[] temp;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[n];
        visit = new boolean[n];
        temp = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backTrack(0);

        System.out.print(max);
    }

    static void backTrack(int depth) {
        if (depth == arr.length) {
            int sum = 0;
            for (int i = 0; i < temp.length - 1; i++) {
                sum += Math.abs(temp[i] - temp[i + 1]);
            }
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                temp[depth] = arr[i];
                backTrack(depth + 1);
                visit[i] = false;
            }
        }
    }
}
