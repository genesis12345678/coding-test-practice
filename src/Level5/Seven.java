package Level5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] str = br.readLine().split(" ");

            int R = Integer.parseInt(str[0]);
            String s = str[1];

            for (int j = 0; j < s.length(); j++) {
                sb.append(String.valueOf(s.charAt(j)).repeat(R));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
