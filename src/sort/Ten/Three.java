package sort.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 아이디어
 * 문자열의 길이만큼 정답이 나오므로 우선 길이만큼 배열을 만든다.
 * substring의 자르는 시작 위치를 하나씩 높여가면서 각 문자를 구한다.
 * 정렬한 다음 출력하면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11656">백준 11656번 - 정렬 : 접미사 배열</a>
 * @since 2024-04-29
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        String[] str = new String[s.length()];

        for (int i = 0; i < s.length(); i++) {
            str[i] = s.substring(i);
        }

        Arrays.stream(str)
                .sorted()
                .forEach(System.out::println);
    }
}
