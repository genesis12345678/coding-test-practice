package Greedy.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * Map을 사용하려다가 key가 중복이 될 수 있어서 2차원 배열로 함.
 * 회의가 끝나는 시간 기준으로 오름차순 정렬을 한다, 시간이 같다면 시작시간을 비교하여 시작시간 오름차순으로 한다.
 * 첫 회의가 끝나는 시간부터 계산하면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1931">백준 1931번 : 그리디 알고리즘 - 회의실 배정</a>
 * @since 2023-12-28
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int[][] meeting = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meeting[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
            meeting[i][1] = Integer.parseInt(st.nextToken()); // 끝나는 시간
        }

       Arrays.sort(meeting, (o1, o2) -> { // 정렬
           if (o1[1] == o2[1]) { // 끝나는 시간이 같다면
               return o1[0] - o2[0]; // 시작 시간 오름차순 정렬
           }
           return o1[1] - o2[1]; // 기본적으로는 끝나는 시간 오름차순 정렬
       });

        int count = 1; // 첫 회의는 가능하므로 일단 1로 시작
        int end = meeting[0][1]; // 첫 회의의 끝나는 시간으로 초기화

        for (int i = 1; i < N; i++) {  // 0번째는 이미 한 회의이므로 볼 필요 없음
            /**
             * 시작시간이 끝나는 시간보다 크거나 같다면
             * count를 증가하고 end를 갱신한다.
             */
            if (meeting[i][0] >= end) {
                count++;
                end = meeting[i][1];
            }
        }
        System.out.println(count);
    }
}
