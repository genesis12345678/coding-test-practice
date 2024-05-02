package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 이미 정렬되어 있는 두 배열이 입력으로 주어진다고 했다.
 * 따라서 병합정렬 하듯이 두 배열을 병합하면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11728">백준 11728번 - 정렬 : 배열 합치기</a>
 * @since 2024-05-01
 */
public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] A = new int[n];
        int[] B = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int[] arr = new int[n + m];

        int A_idx = 0;
        int B_idx = 0;

        int index = 0;

        while (A_idx < n && B_idx < m) {
            if (A[A_idx] < B[B_idx]) {
                arr[index++] = A[A_idx++];
            } else {
                arr[index++] = B[B_idx++];
            }
        }

        while (A_idx < n) {
            arr[index++] = A[A_idx++];
        }
        while (B_idx < m) {
            arr[index++] = B[B_idx++];
        }

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append(" ");
        }
        System.out.print(sb);
    }
}
