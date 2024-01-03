package Greedy.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 무게추를 오름차순 정렬한 뒤 누적합과 비교하여 해당 무게추가 더 무거운지 판단한다.
 * 이 때 추의 최소 무게는 1이므로 누적합을 1로 초기화한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2437">백준 2437번 : 그리디 알고리즘 - 저울</a>
 * @since 2024-01-03
 */
public class Nine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int min = 1;
        for (int num : arr) {
            if (num > min) {
                break;
            }
            min += num;
        }
        System.out.println(min);
    }
}
