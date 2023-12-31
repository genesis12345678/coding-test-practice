package Greedy.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 아이디어
 * 각 알파벳의 자릿수의 합을 구한 뒤 큰 자리수부터 9부터 곱해 나간다.
 * 예를 들어 GCF + ACDEB면 (A10_000 + C1010 + D100 + G100 + E10 + 1B + 1F)라는 식이 나오고
 * A: 9, C: 8, D: 7, G: 6, E: 5, B: 4, F: 3으로 대입하여 계산하면 가장 큰 값이 나온다.
 * 이 때 D100 + G100이나 1B + 1F 처럼 자릿수가 같다면 순서는 상관없다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1339">백준 1339번 : 그리디 알고리즘 - 단어 수학</a>
 * @since 2023-12-31
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] str = new String[N]; // N개의 문자열을 받을 배열
        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
        }

        int[] alphabet = new int[26]; // 알파벳 개수만큼

        for (int i = 0; i < N; i++) {
            /**
             * 현재 문자열이 몇의 자리수인지 계산한다.
             * "GCF"라면 3자리니까 백의 자리까지이다.
             * str.length만큼 하면 10^3 = 1000 이므로 -1로 계산
             */
            int pow = (int) Math.pow(10, str[i].length() - 1);
            for (int j = 0; j < str[i].length(); j++) {
                /**
                 * 각 알파벳이 두 식을 합쳐서 총 몇의 자리수로 이루어져 있는지 계산한다.
                 * 같은 알파벳이 같은 자리수이든 다른 자리수이든 계속 누적이 된다.
                 */
                alphabet[str[i].charAt(j) - 65] += pow;
                pow /= 10;
            }
        }
        Arrays.sort(alphabet); // 큰 자리수부터 큰 값을 곱하기 위해 정렬
        int index = 9;
        int sum = 0;

        for (int i = alphabet.length-1; i >= 0; i--) {
            if (alphabet[i] != 0) {
                sum += alphabet[i] * index--;
            }else break;
        }
        System.out.println(sum);
    }
}
