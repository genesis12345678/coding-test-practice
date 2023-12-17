package Level8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());
        int sum = 0;
        int tmp = 1;

        for (int i = N.length() - 1; i >= 0; i--) {
            char c = N.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sum += (c - 'A' + 10) * tmp;
            }
            else if (c >= 'a' && c <= 'z') {
                sum += (c - 'a' + 10) * tmp;
            }
            else sum += (c-'0')*tmp;

            tmp *= B;
        }
        System.out.println(sum);

    }
}
