package bruteForce.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1051">백준 1051번 - 브루트포스 : 숫자 정사각형</a>
 * @since 2024-05-06
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }

        int max = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int size = 1;

                while (i + size < n && j + size < m) {
                    if (map[i][j] == map[i + size][j] &&
                        map[i][j] == map[i][j + size] &&
                        map[i][j] == map[i + size][j + size]) {

                        max = Math.max(max, size + 1);
                    }
                    size++;
                }
            }
        }

        System.out.println(max * max);
    }
}
