package bruteForce.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 문제 조건
 * 1. L개의 알파벳 중 최소 한 개의 모음, 두 개의 자음으로 구성되어야 한다.
 * 2. 증가하는 순서로 배열되어야 한다.
 * 1번을 만족시키기 위해서 모든 조합을 따지면서 크기가 L인 문자열이 되었을 때 저장하기 전에 체크한다.
 * 2번은 입력받은 문자들을 정렬하면 자연스럽게 만족이 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1759">백준 1759번 - 브루트포스 : 암호 만들기</a>
 * @since 2024-04-22
 */

public class Ten {

    static int L, C;
    static ArrayList<String> list = new ArrayList<>();
    static boolean[] visit = new boolean[16];
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr); //문자 정렬

        backTrack(0, "");

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }

    static void backTrack(int index, String password) {
        if (password.length() == L) {
            if (isValidPassword(password)) { //리스트에 저장하기 전에 조건에 맞는지 확인
                list.add(password);
            }
            return;
        }

        for (int i = index; i < C; i++) {
            if (!visit[i]) {
                visit[i] = true;
                backTrack(i + 1, password + arr[i]);
                visit[i] = false;
            }
        }
    }

    static boolean isValidPassword(String password) {

        int vowel = 0; //모음 수
        int consonant = 0; //자음 수

        for (char ch : password.toCharArray()) {
            if (isVowel(ch)) {
                vowel++;
            } else {
                consonant++;
            }
        }

        return vowel >= 1 && consonant >= 2;
    }

    static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
