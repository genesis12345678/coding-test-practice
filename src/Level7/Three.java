package Level7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();

        char[][] ch = new char[5][15];

        for (int i = 0; i < ch.length; i++) {
            String value = br.readLine();
            for (int j = 0; j < value.length(); j++) {
                ch[i][j] = value.charAt(j);
            }
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (ch[j][i] != '\0') {
                    sb.append(ch[j][i]);
                }
            }
        }
        System.out.println(sb);

    }
}
