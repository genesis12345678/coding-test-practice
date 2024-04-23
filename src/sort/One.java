package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 우선 배열을 정렬을 한 후 투포인터로 양쪽 끝에서부터 점점 좁혀오면서 합의 최소를 구한다.
 * 합이 0보다 작으면 시작을 오른쪽으로 옮기고,
 * 합이 0보다 크면 끝을 왼쪽으로 옮긴다.
 * 그러다가 합이 0이면 더 이상 탐색할 필요 없다.
 * 참고로 내장 함수 정렬을 사용해도 문제 없지만, 정렬 연습을 위해 퀵 정렬을 사용했다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2470">백준 2470번 - 정렬 : 두 용액</a>
 * @since 2024-04-23
 */
public class One {

    static int[] arr;
    static int[] result = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(0, n - 1);

//        Arrays.sort(arr);
        solution(n);

        System.out.print(result[0] + " " + result[1]);
    }

    static void solution(int n) {
        int s = 0;
        int e = n - 1;

        int min = Integer.MAX_VALUE;

        while (s < e) {
            int sum = arr[s] + arr[e];

            if (min > Math.abs(sum)) {
                min = Math.abs(sum);

                result[0] = arr[s];
                result[1] = arr[e];

                if (sum == 0) {
                    break;
                }
            }

            if (sum < 0) {
                s++;
            } else {
                e--;
            }
        }
    }

    static void quickSort(int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = partition(start, end);

        quickSort(start, pivot);
        quickSort(pivot + 1, end);
    }

    static int partition(int start, int end) {

        int s = start - 1;
        int e = end + 1;
        int pivot = arr[(start + end) / 2];

        while (true) {

            do {
                s++;
            } while (arr[s] < pivot);

            do {
                e--;
            } while (arr[e] > pivot && s <= e);

            if (s >= e) {
                return e;
            }

            swap(s, e);
        }
    }

    static void swap(int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }
}
