package Greedy.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 아이디어
 * 가장 높은 통나무를 중심으로 양쪽으로 내려갈 수 있는 형태로 만들어야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11497">백준 11497번 : 그리디 알고리즘 - 통나무 건너뛰기</a>
 * @since 2024-01-09
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            int[] arr = Arrays.stream(br.readLine().split(" "))
                              .mapToInt(Integer::parseInt)
                              .toArray();
            Arrays.sort(arr);

            int[] num = new int[N];
            int lastIdx = N - 1;
            int firstIdx = 0;
            /**
             * 정렬된 배열에서 꺼내면서 새로운 배열에 저장하는데
             * 작은 값은 끝에서부터 좁혀오는 형태로 저장된다.
             * [2,4,5,7,9] -> [2,5,9,7,4]
             */
            for (int i = 0; i < N; i++) {
                if (i % 2 == 0) {
                    num[firstIdx++] = arr[i];
                } else {
                    num[lastIdx--] = arr[i];
                }
            }

            // 인접한 통나무 간의 높이의 최댓값을 구한다.
            int max = Integer.MIN_VALUE;
            for (int i = 1; i < N; i++) {
                max = Math.max(max, Math.abs(num[i] - num[i - 1]));
            }

            // 처음과 마지막도 인접해 있기 때문에 처음과 마지막까지 구해줘야 한다.
            max = Math.max(max, Math.abs(num[0] - num[N - 1]));
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }
}
