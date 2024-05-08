package bruteForce.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 중복되지 않게 하려면 문자를 찾으면 시작점을 건너뛰면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1543">백준 1543번 - 브루트포스 : 문서 검색</a>
 * @since 2024-05-02
 */
public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String doc = br.readLine();
        String search = br.readLine();

        int count = 0;
        for (int i = 0; i <= doc.length() - search.length(); i++) { //시작점
            boolean flag = true;

            for (int j = 0; j < search.length(); j++) {
                if (search.charAt(j) != doc.charAt(i + j)) {
                    flag = false;
                    break;
                }
            }

            if (flag) { //같은 단어면 시작점을 건너뛴다. 증감식 i++ 을 생각해 1 빼준다.
                i += search.length() - 1;
                count++;
            }
        }

        System.out.println(count);
    }
}
