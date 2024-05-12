import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String s = br.readLine();
            if (s == null || s.isEmpty()) {
                break;
            }

            int n = Integer.parseInt(s);

            sb.append(check(n)).append("\n");
        }

        System.out.print(sb);
    }

    private static int check(int n) {

        long num = 1;
        int len = 1;

        while (num % n != 0) {
            num = (num * 10 + 1) % n;
            len++;
        }

        return len;
    }
}
