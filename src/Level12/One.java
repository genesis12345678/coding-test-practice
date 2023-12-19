package Level12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
           arr[j] = Integer.parseInt(st.nextToken());
        }
        int i = 0;
        for (int j = 0; j < N - 2; j++) {
            for (int k = j + 1; k < N - 1; k++) {
                for (int l = k + 1; l < N; l++) {
                    int sum = arr[j] + arr[k] + arr[l];
                    if(sum <= M && sum > i)
                        i = sum;
                }
            }
        }
        System.out.println(i);

    }
}
