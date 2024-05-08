import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
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

        int A_start = 0;
        int B_start = 0;

        int index = 0;

        while (A_start < n && B_start < m) {
            if (A[A_start] < B[B_start]) {
                arr[index++] = A[A_start++];
            } else {
                arr[index++] = B[B_start++];
            }
        }

        while (A_start < n) {
            arr[index++] = A[A_start++];
        }
        while (B_start < m) {
            arr[index++] = B[B_start++];
        }

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append(" ");
        }
        System.out.print(sb);
    }
}
