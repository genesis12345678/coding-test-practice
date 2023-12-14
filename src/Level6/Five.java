package Level6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().toUpperCase();

        int[] count = new int[26];

        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'A']++;
        }

        int max = 0;
        char answer= '?';

        for (int i = 0; i < count.length; i++) {
            if (count[i] > max) {
                max = count[i];
                answer = (char)(i + 'A');
            } else if (max == count[i]) {
                answer = '?';
            }
        }
        System.out.println(answer);
    }
}
