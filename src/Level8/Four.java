package Level8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int d = 2;

        for (int i = 0; i < N; i++) {
            d += (d - 1);
        }
        System.out.println(d * d);
    }
}
