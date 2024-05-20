package bruteForce.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/10448">백준 10448번 - 브루트포스 : 유레카 이론</a>
 * @since 2024-05-12
 */
public class Three {

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
