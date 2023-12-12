package Level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        if (A != B && A != C && B != C) {
            int max = Math.max(A, Math.max(B, C));
            System.out.println(max * 100);
        }
        else if (A == B && A == C) {
            System.out.println(10_000 + A * 1_000);
        } else if (A == B || A == C) {
            System.out.println(1_000 + A * 100);
        }
        else System.out.println(1_000 + B * 100);
    }
}
