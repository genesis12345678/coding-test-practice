import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int f = Integer.parseInt(br.readLine());

        int temp = n - (n % 100);
        for (int i = temp; i <= temp + 99; i++) {
            if (i % f == 0) {
                if (i % 100 < 10) {
                    System.out.print(0 + "" + i % 100);
                } else {
                    System.out.print(i % 100);
                }
                return;
            }
        }
    }
}
