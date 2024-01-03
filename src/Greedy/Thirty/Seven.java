package Greedy.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 받은 문자열에 길이와 각 문자의 개수를 통해 팰린드롬 여부를 판단할 수 있다.
 * 길이가 짝수고 문자 중에 홀수개인 문자가 있다면 만들 수 없고,
 * 길이가 홀수고 문자 중에 홀수개 문자가 2개 이상이면 만들 수 없다.
 */

/**
 * <a href ="https://www.acmicpc.net/problem/1213">백준 1213번 :그리디 알고리즘 - 팰린드롬 만들기</a>
 * @since 2024-01-03
 */
public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        int[] alphabet = new int[26]; // 알파벳 개수만큼

        for (char c : input.toCharArray()) {
            // 입력받은 문자열에서 어떤 알파벳이 몇번 나오는지 아스키 문자를 통해 구한다.
            alphabet[c - 'A']++;
        }

        // 홀수의 개수, 중간 문자
        int odd = 0, mid = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] % 2 == 1) {
                odd++;
                mid = i;
            }
        }

        /**
         * 만약 문자의 길이가 홀수이고 홀수인 문자가 1개보다 많고
         * 문자의 길이가 짝수이고 홀수인 문자가 있다면 팰린드롬을 만들 수 없다.
         */
        if ((input.length() % 2 == 1 && odd > 1) || (input.length() % 2 == 0 && odd != 0)) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        /**
         * 최종 문자가 대칭이 되어야 하기 때문에 (알파벳이 나온 개수 / 2)만큼 진행하고
         * 밑에서 뒤집은 문자를 더해준다.
         */
        for (int i = 0; i < alphabet.length; i++) {
            for (int j = 0; j < alphabet[i] / 2; j++) {
                sb.append((char) (i + 'A'));
            }
        }

        String temp = sb.toString();
        // 문자의 길이가 홀수라는 것은 정확한 중간이 있다는 뜻이니 더해준다.
        if (input.length() % 2 == 1) {
            temp += (char) (mid + 'A');
        }

        // 대칭이 되기 위해 뒤집은 문자열을 더해준다.
        temp += sb.reverse().toString();
        System.out.println(temp);
    }
}
