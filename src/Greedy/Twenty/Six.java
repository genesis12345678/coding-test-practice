package Greedy.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 서류심사 성적이나 면접 성적 중 하나를 오름차순으로 정렬한다.
 * 예를 들어 서류심사 성적 순으로 정렬한다고 하면
 * 서류심사 성적 1위를 제외하고는 모두 서류심사만 봤을 때 선발되지 못한다.
 * 즉 2위부터는 면접 성적을 비교하여 선발해 나간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1946">백준 1946번 : 그리디 알고리즘 - 신입 사원</a>
 * @since 2023-12-31
 */
public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine()); // 지원자 수
            int[][] arr = new int[N][2]; // [서류성적][면접성적] 2차원 배열
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr, Comparator.comparingInt(o -> o[0])); // 서류성적 오름차순 정렬
            int count = 1; // 서류성적 1위는 바로 선발되므로 1로 시작한다.
            int rate = arr[0][1]; // 현재 서류성적 1위의 면접 성적이 면접순위를 매길 시작점이 된다.

            for (int i = 1; i < N; i++) {
                if (arr[i][1] < rate) {
                    count++;
                    rate = arr[i][1];
                }
            }
            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}
