import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            dfs(1, 1, "1", n);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int now, int index, String str, int n) {
        if (index == n) {
            if (check(str) == 0) {
                sb.append(str).append("\n");
            }
            return;
        }

        dfs(now + 1, index + 1, str + " " + (now + 1), n);
        dfs(now + 1, index + 1, str + "+" + (now + 1), n);
        dfs(now + 1, index + 1, str + "-" + (now + 1), n);
    }

    private static int check(String str) {
        str = str.replace(" ", "");

        Iterator<Integer> iter = Arrays.stream(str.split("[+,-]"))
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                .iterator();

        int sum = iter.next();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+') {
                sum += iter.next();
            } else if (str.charAt(i) == '-') {
                sum -= iter.next();
            }
        }

        return sum;
    }
}
