package Level19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/24060">백준 24060번 : 재귀 - 알고리즘 수업(병합 정렬1)</a>
 * @since 2023-12-26
 */
public class Four {
    static int count = 0; // 배열에 저장될 때마다 +1
    static int K; // 입력받을 K
    static int result = -1; // count가 K를 넘어가면 기본값 -1
    static int[] temp; // 정렬 후 저장할 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        temp = new int[N]; // 오름차순 정렬해서 저장할 배열

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * 시작 인덱스 : 0, 마지막 인덱스 N-1
         * 즉 배열 전체로 시작한다.
          */
        merge_sort(arr, 0, N - 1);
        System.out.println(result);
    }

    /**
     * divide 해가면서 정렬은 merge에서 한다.
     * @param arr 배열 : 점점 나눠진다.
     * @param start 시작 인덱스
     * @param end 마지막 안덱스
     */
    static void merge_sort(int[] arr, int start, int end) {
        if (start < end) { // start < end 조건문으로 최소 2개끼리 비교하게 함.
            int mid = (start + end) / 2; // 중간점 찾기
            merge_sort(arr, start, mid); // 나눈 후 왼쪽 정렬 [4, 5] -> [1, 4, 5]
            merge_sort(arr, mid + 1, end); // 나눈 후 오른쪽 정렬 [3, 2] -> [2, 3]
            merge(arr, start, mid, end); // 병합 [1, 2, 3, 4, 5]
        }
    }

    static void merge(int[] arr, int start, int mid, int end) {
        int leftIndex = start;
        int rightIndex = mid + 1;
        int tempIndex = 0;

        while (leftIndex <= mid && rightIndex <= end) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                temp[tempIndex++] = arr[leftIndex++];
            } else {
                temp[tempIndex++] = arr[rightIndex++];
            }
        }
        while (leftIndex <= mid) {
            temp[tempIndex++] = arr[leftIndex++];
        }
        while (rightIndex <= end) {
            temp[tempIndex++] = arr[rightIndex++];
        }
        leftIndex = start;
        tempIndex = 0;

        while (leftIndex <= end) {
            count++;
            if (count == K) {
                result = temp[tempIndex];
                break;
            }
            arr[leftIndex++] = temp[tempIndex++];
        }
    }
}
