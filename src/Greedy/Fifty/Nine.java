package Greedy.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 그냥 손으로 그려보면서 사과 떨어지는 걸 상상해가면서 해야 할 것 같다..
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2828">백준 2828번 : 그리디 알고리즘 - 사과 담기 게임</a>
 * @since 2024-01-09
 */
public class Nine{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int J = Integer.parseInt(br.readLine());

        int sum = 0;
        int start = 1; // 처음에 바구니는 왼쪽을 차지하고 있다고 했다.
        int end = M; // 바구니 범위(오른쪽)
        for (int i = 0; i < J; i++) {
            int apple = Integer.parseInt(br.readLine()); // 사과가 떨어질 위치

            // 바구니 범위 내라면 그대로 진행한다.
            if (apple >= start && apple <= end) {
                continue;
            }

            /**
             * 바구니 위치보다 왼쪽에 떨어진 경우
             *
             * 거리만큼 누적합 해주고, 바구니의 시작 부분을 사과가 떨어진 곳에 위치해준다.
             * 바구니의 끝 부분은 시작 부분에서 사과위치 범위만큼 위치해준다.
             */
            if (start > apple) {
                int diff = start - apple;
                sum += diff;
                start = apple;
                end -= diff;
            /**
             * 바구니 위치보다 오른쪽에 떨어진 경우
             *
             * 거리만큼 누적합 해주고, 바구니의 끝 부분을 사과가 떨어진 곳에 위치해준다.
             * 바구니의 시작 부분은 끝 부분에서 사과위치 범위만큼 위치해준다.
             */
            } else {
                int diff = apple - end;
                sum += diff;
                end = apple;
                start += diff;
            }
        }
        System.out.println(sum);
    }
}
