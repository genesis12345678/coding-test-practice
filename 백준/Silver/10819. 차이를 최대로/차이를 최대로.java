import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static boolean[] visit;
    static int[] temp;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[n];
        visit = new boolean[n];
        temp = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backTrack(0);

        System.out.print(max);
    }

    private static void backTrack(int depth) {
        if (depth == arr.length) {
            int sum = 0;
            for (int i = 0; i < temp.length - 1; i++) {
                sum += Math.abs(temp[i] - temp[i + 1]);
            }
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                temp[depth] = arr[i];
                backTrack(depth + 1);
                visit[i] = false;
            }
        }
    }
}
