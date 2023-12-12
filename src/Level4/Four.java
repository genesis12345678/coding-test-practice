package Level4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Four {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int max = 0;
        int count = 0;

        for (int i = 0; i < 9; i++) {
            int value = Integer.parseInt(br.readLine());

            if (value > max) {
                max = value;
                count = i + 1;
            }
        }
        System.out.println(max + "\n" +count);
    }
}
