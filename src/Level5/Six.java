package Level5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = -1;
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            /**
             * ch 'b' = 98
             * ch 'a' = 97
             * ch 'e' = 101
             * ch 'k' = 107
             * ch 'j' = 106
             * ch 'o' = 111
             * ch 'o' = 111
             * ch 'n' = 110
             */
            if (arr[ch - 'a'] == -1) {
                arr[ch - 'a'] = i;
            }
        }
        for (int i : arr) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
