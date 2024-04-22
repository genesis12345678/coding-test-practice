import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, s, count;
    static int[] arr;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        count = 0;

        arr = new int[n];
        visit = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution(0, 0);
        
         if (s == 0) {
            count--;
         }

        System.out.println(count);
    }

    private static void solution(int index, int sum) {
        if (index == n) {
            if (sum == s) {
                count++;
            }
            return;
        }

        solution(index + 1, sum + arr[index]);
        solution(index + 1, sum);
    }
}
