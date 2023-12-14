package Level6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 0;

        for (int i = 0; i < N; i++) {
            boolean[] check = new boolean[26];
            boolean success = true;
            String s = br.readLine();

            for (int j = 0; j < s.length(); j++) {
                int index = s.charAt(j) - 'a';

                if (check[index]) {
                    if (s.charAt(j) != s.charAt(j - 1)) {
                        success = false;
                        break;
                    }
                }else check[index] = true;
            }
            if(success) count ++;
        }

        System.out.println(count);
    }
}
