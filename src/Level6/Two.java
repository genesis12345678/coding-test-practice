package Level6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] chess = new int[]{1, 1, 2, 2, 2, 8};
        int[] count = new int[6];

        for (int i = 0; i < count.length; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < count.length; i++) {
            sb.append(chess[i] - count[i]).append(" ");
        }

        System.out.println(sb);

    }
}
