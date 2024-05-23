import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

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

    private static boolean backTrack(String target, String now) {
        if (now.equals(target)) {
            return true;
        }

        if (target.length() > now.length()) {
            return false;
        }

        if (now.charAt(now.length() - 1) == 'A') {
            if (backTrack(target, now.substring(0, now.length() - 1))) {
                return true;
            }
        }

        if (now.charAt(0) == 'B') {
            if (backTrack(target,
                    new StringBuilder(now.substring(1)).reverse().toString())) {
                return true;
            }
        }

        return false;
    }
}