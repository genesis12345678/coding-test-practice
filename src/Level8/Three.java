package Level8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        int Q = 25;
        int D = 10;
        int N = 5;
        int P = 1;
        for (int i = 0; i < T; i++) {
            int C = Integer.parseInt(br.readLine());
            sb.append(C / Q).append(" ");
            C %= Q;
            sb.append(C / D).append(" ");
            C %= D;
            sb.append(C / N).append(" ");
            C %= N;
            sb.append(C / P).append("\n");
        }
        System.out.println(sb);
    }
}
