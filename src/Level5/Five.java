package Level5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        String[] s = br.readLine().split("");

        for (int i = 0; i < N; i++) {
            sum += Integer.parseInt(s[i]);
        }

        br.close();
        System.out.println(sum);
    }
}
