import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, k;
    static String[] words;
    static boolean[] visit = new boolean[26];
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new String[n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            words[i] = str.substring(4, str.length() - 4);
        }

        visit['a' - 'a'] = true;
        visit['n' - 'a'] = true;
        visit['t' - 'a'] = true;
        visit['i' - 'a'] = true;
        visit['c' - 'a'] = true;

        learnWord(0, 0);

        System.out.print(max);

    }

    static void learnWord(int start, int length) {

        if (length == k - 5) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                boolean canRead = true;
                String target = words[i];

                for (int j = 0; j < target.length(); j++) {
                    if (!visit[target.charAt(j) - 'a']) {
                        canRead = false;
                        break;
                    }
                }
                if (canRead) {
                    count++;
                }
            }
            max = Math.max(max, count);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!visit[i]) {
                visit[i] = true;
                learnWord(i, length + 1);
                visit[i] = false;
            }
        }
    }
}
