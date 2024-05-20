import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] trie = new int[46];

    public static void main(String[] args) throws IOException {

        for (int i = 1; i <= 45; i++) {
            trie[i] = (i * (i + 1)) / 2;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            System.out.println(check(k));
        }
    }

    private static int check(int k) {
        for (int i = 1; i <= 45; i++) {
            for (int j = 1; j <= 45; j++) {
                for (int l = 1; l <= 45; l++) {
                    if (k == trie[i] + trie[j] + trie[l]) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
}
