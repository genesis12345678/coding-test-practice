import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean[] visit;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        visit = new boolean[n + 1];
        arr = new int[n + 1];

        solution(0);
        System.out.print(sb);
    }

    private static void solution(int depth) {
        if (depth == n) {
            for (int i = 0; i < n; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                arr[depth] = i;
                solution(depth + 1);
                visit[i] = false;
            }
        }
    }
}
