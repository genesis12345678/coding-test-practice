import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] sorted;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        sorted = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        mergeSort(arr, 0, n - 1);

        int s = 0;
        int e = n - 1;

        int count = 0;

        while (s < e) {
            int sum = arr[s] + arr[e];
            if (sum == x) {
                count++;
                s++;
                e--;
            } else if (sum > x) {
                e--;
            } else {
                s++;
            }
        }

        System.out.print(count);
    }

    static void mergeSort(int[] arr, int start, int end) {
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;

        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        merge(arr, start, mid, end);
    }

    static void merge(int[] arr, int start, int mid, int end) {
        int left = start;
        int right = mid + 1;
        int index = start;

        while (left <= mid && right <= end) {
            if (arr[left] <= arr[right]) {
                sorted[index++] = arr[left++];
            } else {
                sorted[index++] = arr[right++];
            }
        }

        while (left <= mid) {
            sorted[index++] = arr[left++];
        }

        while (right <= end) {
            sorted[index++] = arr[right++];
        }

        for (int i = start; i <= end; i++) {
            arr[i] = sorted[i];
        }
    }
}
