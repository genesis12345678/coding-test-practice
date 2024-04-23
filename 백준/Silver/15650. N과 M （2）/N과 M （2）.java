import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    static int[] arr;
    static int n, m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        backTrack(0, "", 1);
        System.out.print(sb);
    }

    private static void backTrack(int depth, String result, int start) {
        if (depth == m) {
            sb.append(result).append("\n");
            return;
        }

        for (int i = start; i <= n; i++) {
             backTrack(depth + 1, result + i + " ", i + 1);
                
        }
    }
}
