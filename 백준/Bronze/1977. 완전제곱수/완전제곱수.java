import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int min = -1;
        int sum = 0;
        while (n >= m) {
            if (isSquareNum(m)) {
                sum += m;
                if (min == -1) {
                    min = m;
                }
            }
            m++;
        }

        if (min == -1) {
            System.out.print(min);
        } else {
            System.out.print(sum + "\n" + min);
        }
    }

    private static boolean isSquareNum(int n) {
        int sqrt = (int) Math.sqrt(n);

        return sqrt * sqrt == n;
    }
}
