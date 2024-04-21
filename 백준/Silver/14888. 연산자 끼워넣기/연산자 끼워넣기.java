import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int plus;
    static int minus;
    static int multiple;
    static int divide;
    static int[] numbers;
    static int n;

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        plus = Integer.parseInt(st.nextToken());
        minus = Integer.parseInt(st.nextToken());
        multiple = Integer.parseInt(st.nextToken());
        divide = Integer.parseInt(st.nextToken());

        dfs(numbers[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int number, int depth) {

        if (depth == n) {
            max = Math.max(max, number);
            min = Math.min(min, number);
            return;
        }


        if (plus > 0) {
            plus--;
            dfs(number + numbers[depth], depth + 1);
            plus++;
        }

        if (minus > 0) {
            minus--;
            dfs(number - numbers[depth], depth + 1);
            minus++;
        }

        if (multiple > 0) {
            multiple--;
            dfs(number * numbers[depth], depth + 1);
            multiple++;
        }

        if (divide > 0) {
            divide--;
            dfs(number / numbers[depth], depth + 1);
            divide++;
        }
    }
}
