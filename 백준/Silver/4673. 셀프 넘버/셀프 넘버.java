import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {

    static boolean[] visit = new boolean[10001];

    public static void main(String[] args) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 1; i <= 10000; i++) {
            if (d(i) <= 10000) {
                visit[d(i)] = true;
            }
        }

        for (int i = 1; i <= 10000; i++) {
            if (!visit[i]) {
                bw.write(i + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static int d(int n) {

        int sum = n;

        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }
}
