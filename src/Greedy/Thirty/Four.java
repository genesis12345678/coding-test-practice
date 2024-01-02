package Greedy.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 물이 새는 곳을 정렬한 뒤 시작 지점부터 테이핑하면서 테이핑의 범위를 갱신해 나간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1449">백준 1449번 : 그리디 알고리즘 - 수리공 항송</a>
 * @since 2024-01-02
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N : 물이 새는 곳의 개수, L : 테이프의 길이
        int N = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int count = 0; // 필요한 테이프 개수
        int tape = 0; // 현재 마지막으로 테이핑 되어있는 위치

        for (int i = 0; i < N; i++) {
            /**
             * 만약 현재 위치가 테이핑 되어있지 않다면,
             * 테이프가 필요하니 테이프 개수를 늘리고
             * 현재 지점부터 테이프 길이만큼 더해주고 1을 빼준다.
             * 1을 빼주는 이유는 좌우 0.5만큼 간격을 주기 때문이다.
             * 현재 위치의 값보다 테이프가 더 크다는 것은 그 위치는 이미 테이핑이 되어 있다는 뜻이다.
             */
            if (arr[i] > tape) {
                count++;
                tape = arr[i] + L -1;
            }
        }
        System.out.println(count);
    }
}
