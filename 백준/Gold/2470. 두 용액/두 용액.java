import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

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

        Arrays.sort(arr);

        solution(n);

        System.out.println(result[0] + " " + result[1]);
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
        if (start < end) {
            int pivot = partition(start, end);

            quickSort(start, pivot - 1);
            quickSort(pivot + 1, end);
        }
    }

    static int partition(int start, int end) {

        if (start + 1 == end) {
            if (arr[start] > arr[end]) {
                swap(start, end);
            }
            return end;
        }

        int mid = (start + end) / 2;
        swap(start, mid);
        int pivot = arr[start];

        int s = start + 1;
        int e = end;

        while (s <= e) {
            while (pivot < arr[e] && e > 0) {
                e--;
            }
            while (pivot > arr[s] && s < arr.length - 1) {
                s++;
            }

            if (s <= e) {
                swap(s, e);
                s++;
                e--;
            }
        }

        arr[start] = arr[e];
        arr[e] = pivot;

        return e;
    }

    static void swap(int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }
}
