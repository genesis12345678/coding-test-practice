package Level7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int[][] a = new int[9][9];

        int max = 0;
        int row = -1;
        int col = -1;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                if (a[i][j] > max) {
                    max = a[i][j];
                    row = i + 1;
                    col = j + 1;
                } else if (max == 0) {
                    row = 1;
                    col = 1;
                }
            }
        }
        sb.append(max).append("\n").append(row).append(" ").append(col);
        System.out.println(sb);
    }
}
