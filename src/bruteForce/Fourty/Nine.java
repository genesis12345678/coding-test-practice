package bruteForce.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/12919">백준 12919번 - 브루트포스 : A와 B 2</a>
 * @since 2024-05-23
 */
public class Nine {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        if (backTrack(S, T)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    /**
     * 역으로 T를 S로 돌아갈 수 있는지 확인한다.
     */
    private static boolean backTrack(String target, String now) {
        if (now.equals(target)) {
            return true;
        }

        if (target.length() > now.length()) {
            return false;
        }

        //기존 S에서 'A'를 추가한 것이라면 'A'를 빼고 다시 확인
        if (now.charAt(now.length() - 1) == 'A') {
            if (backTrack(target, now.substring(0, now.length() - 1))) {
                return true;
            }
        }

        //기존 S에서 'B'를 추가하고 뒤집은 것이라면
        //뒤집은 다음 'B'를 빼고 다시 확인
        if (now.charAt(0) == 'B') {
            if (backTrack(target,
                    new StringBuilder(now.substring(1)).reverse().toString())) {
                return true;
            }
        }

        return false;
    }
}