package bruteForce.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1120">백준 1120번 - 브루트포스 : 문자열</a>
 * @since 2024-05-01
 */
public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String A = st.nextToken();
        String B = st.nextToken();

        int min = Integer.MAX_VALUE;

        for (int i = 0; i <= B.length() - A.length(); i++) { //A에 추가해야 하는 알파벳 개수만큼
            int temp = 0;
            for (int j = 0; j < A.length(); j++) {
                if (A.charAt(j) != B.charAt(i + j)) {
                    temp++;
                }
            }

            min = Math.min(min, temp);
        }

        System.out.print(min);
    }
}
