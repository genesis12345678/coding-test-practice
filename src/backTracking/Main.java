package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int[] arr;
    public static int N;
    public static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        dfs(0);

        System.out.println(count);
    }

    private static void dfs(int depth) {
        if (depth == N) {
            count ++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[depth] = i;

            if(promising(depth)){
                dfs(depth + 1);
            }
        }
    }

    private static boolean promising(int depth) {
        for (int i = 0; i < depth; i++) {
            if (arr[i] == arr[depth]) {
                return false;
            } else if (Math.abs(depth - i) == Math.abs(arr[depth] - arr[i])) {
                return false;
            }
        }
        return true;
    }
}
