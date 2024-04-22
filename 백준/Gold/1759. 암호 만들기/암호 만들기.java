import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

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

        Arrays.sort(arr);

        backTrack(0, "");

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }

    static void backTrack(int index, String password) {
        if (password.length() == L) {
            if (isValidPassword(password)) {
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

        int vowel = 0;
        int consonant = 0;

        for (char ch : password.toCharArray()) {
            if (isVowel(ch)) {
                vowel++;
            } else {
                consonant++;
            }
        }

        return vowel >= 1 && consonant >= 2;
    }

    private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
